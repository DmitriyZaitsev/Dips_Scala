package com.dzaitsev.scala.dips.activities

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
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
class TimerActivity extends Activity with View.OnClickListener {

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
		mDigitLeft = findViewById(R.id.iv_digit_left).asInstanceOf[ImageView]
		mDigitRight = findViewById(R.id.iv_digit_right).asInstanceOf[ImageView]
		mHandler = new UiUpdateHandler
		mExecutorService = Executors.newSingleThreadExecutor
	}

	protected override def onPause() {
		super.onPause()
		mExecutorService.shutdown()
	}

	protected override def onResume() {
		super.onResume()
		mExecutorService.execute(new TimerThread(mHandler, 90))
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
				val mediaPlayer = MediaPlayer.create(TimerActivity.this, R.raw.alarme)
				mediaPlayer.start()
			}
		}.start()
	}

	private var mExecutorService: ExecutorService = _
	private var mHandler: Handler = _
	private var mDigitLeft: ImageView = _
	private var mDigitRight: ImageView = _

	private class UiUpdateHandler extends Handler {

		override def handleMessage(message: Message) {
			if (message.what == Activity.RESULT_OK) {
				setResultOkAndFinish()
			} else if (message.what == Activity.RESULT_CANCELED) {
				setResultCancelAndFinish()
			} else {
				val seconds = message.what - 1
				val leftDigit = seconds / 10

				if (seconds % 10 == 9) {
					switchDigit(mDigitLeft, leftDigit)
				}

				val rightDigit = seconds - leftDigit * 10
				switchDigit(mDigitRight, rightDigit)
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
				case _ => iv.setBackgroundResource(R.drawable.digit_9)
			}
		}
	}

	def onClick(v: View) {
		v.getId match {
			case R.id.btn_proceed => setResultOkAndFinish()
		}
	}
}