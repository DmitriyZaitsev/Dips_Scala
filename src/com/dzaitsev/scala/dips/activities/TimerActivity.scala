package com.dzaitsev.scala.dips.activities

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.dzaitsev.scala.dips.R
import com.dzaitsev.scala.dips.TimerThread
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * ------------------------ DESCRIPTION ------------------------<br>
 * <br>
 * Created by Dmitriy Zaitsev at 2013-04-25, 14:40.<br>
 */
class TimerActivity extends Activity {

	override def onBackPressed() {
		super.onBackPressed()
		setResultCancelAndFinish()
	}

	protected override def onDestroy() {
		super.onDestroy()
		playAlarm()
	}

	protected override def onCreate(savedInstanceState: Bundle) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.scr_timer)
		_digitLeft = findViewById(R.id.iv_digit_left).asInstanceOf[ImageView]
		_digitRight = findViewById(R.id.iv_digit_right).asInstanceOf[ImageView]
		val mProceed: Button = findViewById(R.id.btn_proceed).asInstanceOf[Button]
		mProceed.setOnClickListener(new View.OnClickListener {
			def onClick(view: View) {
				setResultOkAndFinish()
			}
		})
		_handler = new UiUpdateHandler
		_executorService = Executors.newSingleThreadExecutor
	}

	protected override def onPause() {
		super.onPause()
		_executorService.shutdown()
	}

	protected override def onResume() {
		super.onResume()
		_executorService.execute(new TimerThread(_handler, 90))
	}

	private def setResultCancelAndFinish() {
		setResult(Activity.RESULT_CANCELED)
		finish()
	}

	private def setResultOkAndFinish() {
		setResult(Activity.RESULT_OK)
		finish()
	}

	private def playAlarm() {
		new Thread {
			override def run() {
				val mp: MediaPlayer = MediaPlayer.create(TimerActivity.this, R.raw.alarme)
				mp.start()
			}
		}.start()
	}

	private var _executorService: ExecutorService = null
	private var _handler: Handler = null
	private var _digitLeft: ImageView = null
	private var _digitRight: ImageView = null

	private class UiUpdateHandler extends Handler {

		override def handleMessage(message: Message) {
			if (message.what == Activity.RESULT_OK) {
				setResultOkAndFinish()
			} else if (message.what == Activity.RESULT_CANCELED) {
				setResultCancelAndFinish()
			} else {
				val seconds: Int = message.what - 1
				val leftDigit: Int = seconds / 10
				if (seconds % 10 == 9) {
					switchDigit(_digitLeft, leftDigit)
				}
				val rightDigit: Int = seconds - leftDigit * 10
				switchDigit(_digitRight, rightDigit)
			}
		}

		private def switchDigit(iv: ImageView, digit: Int) {
			digit match {
				case 0 => iv.setBackgroundResource(R.drawable.digit_0)
				case 1 => iv.setBackgroundResource(R.drawable.digit_1)
				case 2 => iv.setBackgroundResource(R.drawable.digit_2)
				case 3 => iv.setBackgroundResource(R.drawable.digit_3)
				case 4 => iv.setBackgroundResource(R.drawable.digit_4)
				case 5 => iv.setBackgroundResource(R.drawable.digit_5)
				case 6 => iv.setBackgroundResource(R.drawable.digit_6)
				case 7 => iv.setBackgroundResource(R.drawable.digit_7)
				case 8 => iv.setBackgroundResource(R.drawable.digit_8)
				case 9 => iv.setBackgroundResource(R.drawable.digit_9)
				case _ => iv.setBackgroundResource(R.drawable.digit_9)
			}
		}
	}

}