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

			if (_dips.remaining <= 0) {
				_doneButton.setText(R.string.finish)
			}
		} else if (resultCode == Activity.RESULT_CANCELED) {
			// do nothing
		}
	}

	/** Called when the activity is first created. */
	protected override def onCreate(savedInstanceState: Bundle) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.scr_stats)
		_dipsPrefs = new DipsPreferences(MainActivity.this)

		initUserProgress()

		_doneButton = findViewById(R.id.btn_done).asInstanceOf[Button]
		_currentTextView = findViewById(R.id.tv_cur_exercises).asInstanceOf[TextView]
		_completedTextView = findViewById(R.id.tv_done).asInstanceOf[TextView]
		_remainingTextView = findViewById(R.id.tv_remaining).asInstanceOf[TextView]

		_currentTextView.setText(String.valueOf(_dips.current))
		_completedTextView.setText(String.valueOf(_dips.completed))
		_remainingTextView.setText(String.valueOf(_dips.remaining))
		_doneButton.setOnClickListener(new View.OnClickListener {
			def onClick(view: View) {
				buttonDoneClick()
			}
		})
	}

	private def buttonDoneClick() {
		if (_dips.remaining <= 0) {
			showDialog()
		} else {
			val intent: Intent = new Intent(this, classOf[TimerActivity])
			val ACTIVITY_TIMER: Int = 1

			startActivityForResult(intent, ACTIVITY_TIMER)
		}
	}

	private def initUserProgress() {
		val mUserLevel: Int = _dipsPrefs.userLevel

		_dips = new Dips(mUserLevel)
	}

	private def showDialog() {
		val dialog: Dialog = new Dialog(MainActivity.this)
		dialog.setCancelable(false)
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
		dialog.setContentView(R.layout.dlg_after_training)

		val bEasy: Button = dialog.findViewById(R.id.btn_easy).asInstanceOf[Button]
		bEasy.setOnClickListener(new View.OnClickListener {
			def onClick(view: View) {
				_dipsPrefs.userLevel(_dipsPrefs.userLevel + 1)
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
				_dipsPrefs.userLevel(_dipsPrefs.userLevel - 1)
				dialog.dismiss()
				finish()
			}
		})
		dialog.show()
	}

	private def updateUserProgress() {
		_dips.confirmSet()
		_currentTextView.setText(String.valueOf(_dips.current))
		_completedTextView.setText(String.valueOf(_dips.completed))
		_remainingTextView.setText(String.valueOf(_dips.remaining))
	}

	private var _doneButton: Button = null
	private var _dips: Exercise = null
	private var _dipsPrefs: IDipsPreferences = null
	private var _completedTextView: TextView = null
	private var _currentTextView: TextView = null
	private var _remainingTextView: TextView = null
}