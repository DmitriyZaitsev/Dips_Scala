package com.dzaitsev.scala.dips.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.dzaitsev.scala.dips.DipsPreferences
import com.dzaitsev.scala.dips.R

/**
 * ------------------------ DESCRIPTION ------------------------<br>
 * <br>
 * Created by Dmitriy Zaitsev at 2013-04-25, 16:35.<br>
 */
class HelloActivity extends Activity with View.OnClickListener {

	protected override def onCreate(savedInstanceState: Bundle) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.scr_hello)
	}

	def onClick(v: View) {
		v.getId match {
			case R.id.btn_yes =>
				val prefs = new DipsPreferences(HelloActivity.this)
				startActivity(if (prefs.alreadyRegistered) {
					new Intent(HelloActivity.this, classOf[MainActivity])
				} else {
					new Intent(HelloActivity.this, classOf[InitialDipsActivity])
				})
				finish()
			case R.id.btn_no =>
				finish()
		}
	}
}