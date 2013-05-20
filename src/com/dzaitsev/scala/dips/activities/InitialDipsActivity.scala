package com.dzaitsev.scala.dips.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.dzaitsev.scala.dips.DipsPreferences
import com.dzaitsev.scala.dips.IDipsPreferences
import com.dzaitsev.scala.dips.exercises.Dips
import com.dzaitsev.scala.dips.R

/**
 * ------------------------ DESCRIPTION ------------------------<br>
 * <br>
 * Created by Dmitriy Zaitsev at 2013-04-25, 17:17.<br>
 */
class InitialDipsActivity extends Activity {

	override def onBackPressed() {
		super.onBackPressed()
		startActivity(new Intent(InitialDipsActivity.this, classOf[HelloActivity]))
		finish()
	}

	protected override def onCreate(savedInstanceState: Bundle) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.scr_initial_dips)

		mPrefs = new DipsPreferences(InitialDipsActivity.this)
		val bOk: Button = findViewById(R.id.btn_ok).asInstanceOf[Button]

		mInitialDips = findViewById(R.id.et_initial_dips).asInstanceOf[EditText]
		mInitialDips.setOnKeyListener(new View.OnKeyListener {
			def onKey(v: View, keyCode: Int, event: KeyEvent): Boolean = {
				if (event.getAction == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
					buttonOkClick()
					return true
				}
				false
			}
		})

		bOk.setOnClickListener(new View.OnClickListener {
			def onClick(v: View) {
				buttonOkClick()
			}
		})
	}

	private def buttonOkClick() {
		val enteredDips: String = mInitialDips.getText.toString
		val initDips: Int = Integer.parseInt(enteredDips)

		if (Dips.recommend(initDips) < 0) {
			showAlertDialog(DIALOG_TOO_FRAIL)
		} else if (0 < Dips.recommend(initDips)) {
			showAlertDialog(DIALOG_TOO_COOL)
		} else if (Dips.recommend(initDips) == 0) {
			setupUser(initDips)
			startActivity(new Intent(this, classOf[MainActivity]))
			finish()
		}
	}

	private def setupUser(dips: Int) {
		mPrefs.setUserLevel(Dips.calcLevel(dips))
		mPrefs.setDipsInitial(dips)
		mPrefs.setAlreadyRegistered(true)
	}

	private def showAlertDialog(id: Int) {
		val alertDialog: AlertDialog.Builder = new AlertDialog.Builder(InitialDipsActivity.this)

		if (id == DIALOG_TOO_COOL) {
			alertDialog.setMessage(R.string.sorry_pal_too_cool)
		} else {
			alertDialog.setMessage(R.string.sorry_pal_too_frail)
		}

		alertDialog.setCancelable(true)
		alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener {
			def onClick(dialogInterface: DialogInterface, i: Int) {
				startActivity(new Intent(InitialDipsActivity.this, classOf[HelloActivity]))
				finish()
			}
		})
		alertDialog.show
	}

	@SuppressWarnings(Array("FieldCanBeLocal")) private final val DIALOG_TOO_FRAIL: Int = 2
	private final val DIALOG_TOO_COOL: Int = 1
	private var mInitialDips: EditText = null
	private var mPrefs: IDipsPreferences = null
}