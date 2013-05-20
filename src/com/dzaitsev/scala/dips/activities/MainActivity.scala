package com.dzaitsev.scala.dips.activities

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.dzaitsev.scala.dips.DipsPreferences
import com.dzaitsev.scala.dips.IDipsPreferences
import com.dzaitsev.scala.dips.R
import com.dzaitsev.scala.dips.exercises.Dips
import com.dzaitsev.scala.dips.exercises.Exercise

class MainActivity extends Activity {

	override def onBackPressed() {
		super.onBackPressed()
		startActivity(new Intent(MainActivity.this, classOf[HelloActivity]))
		finish()
	}

	protected override def onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
		if (resultCode == Activity.RESULT_OK) {
			updateUserProgress()

			if (mDips.getRemaining <= 0) {
				mDoneButton.setText(R.string.finish)
			}
		} else if (resultCode == Activity.RESULT_CANCELED) {
			// do nothing
		}
	}

	/** Called when the activity is first created. */
	protected override def onCreate(savedInstanceState: Bundle) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.scr_stats)
		mPrefs = new DipsPreferences(MainActivity.this)
		initUserProgress()
		mDoneButton = findViewById(R.id.btn_done).asInstanceOf[Button]
		mCurrentTextView = findViewById(R.id.tv_cur_exercises).asInstanceOf[TextView]
		mCompletedTextView = findViewById(R.id.tv_done).asInstanceOf[TextView]
		mRemainingTextView = findViewById(R.id.tv_remaining).asInstanceOf[TextView]

		mCurrentTextView.setText(String.valueOf(mDips.getCurrent))
		mCompletedTextView.setText(String.valueOf(mDips.getCompleted))
		mRemainingTextView.setText(String.valueOf(mDips.getRemaining))
		mDoneButton.setOnClickListener(new View.OnClickListener {
			def onClick(view: View) {
				buttonDoneClick()
			}
		})
	}

	private def buttonDoneClick() {
		if (mDips.getRemaining <= 0) {
			showDialog()
		} else {
			val intent: Intent = new Intent(this, classOf[TimerActivity])
			val ACTIVITY_TIMER: Int = 1

			startActivityForResult(intent, ACTIVITY_TIMER)
		}
	}

	private def initUserProgress() {
		val mUserLevel: Int = mPrefs.getUserLevel

		mDips = new Dips(mUserLevel)
	}

	private def showDialog() {
		val dialog: Dialog = new Dialog(MainActivity.this)
		dialog.setCancelable(false)
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
		dialog.setContentView(R.layout.dlg_after_training)
		val bEasy: Button = dialog.findViewById(R.id.btn_easy).asInstanceOf[Button]
		bEasy.setOnClickListener(new View.OnClickListener {
			def onClick(view: View) {
				mPrefs.setUserLevel(mPrefs.getUserLevel + 1)
				dialog.dismiss()
				finish()
			}
		})

		val bFine: Button = dialog.findViewById(R.id.btn_fine).asInstanceOf[Button]
		bFine.setOnClickListener(new View.OnClickListener {
			def onClick(view: View) {
				dialog.dismiss()
				finish()
			}
		})

		val bHard: Button = dialog.findViewById(R.id.btn_hard).asInstanceOf[Button]
		bHard.setOnClickListener(new View.OnClickListener {
			def onClick(view: View) {
				mPrefs.setUserLevel(mPrefs.getUserLevel - 1)
				dialog.dismiss()
				finish()
			}
		})
		dialog.show()
	}

	private def updateUserProgress() {
		mDips.confirmSet()
		mCurrentTextView.setText(String.valueOf(mDips.getCurrent))
		mCompletedTextView.setText(String.valueOf(mDips.getCompleted))
		mRemainingTextView.setText(String.valueOf(mDips.getRemaining))
	}

	private var mDoneButton: Button = null
	private var mDips: Exercise = null
	private var mPrefs: IDipsPreferences = null
	private var mCompletedTextView: TextView = null
	private var mCurrentTextView: TextView = null
	private var mRemainingTextView: TextView = null
}