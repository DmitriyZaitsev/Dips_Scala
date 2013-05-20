package com.dzaitsev.scala.dips.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.dzaitsev.scala.dips.DipsPreferences
import com.dzaitsev.scala.dips.IDipsPreferences
import com.dzaitsev.scala.dips.R

/**
 * ------------------------ DESCRIPTION ------------------------<br>
 * <br>
 * Created by Dmitriy Zaitsev at 2013-04-25, 16:35.<br>
 */
class HelloActivity extends Activity {

	protected override def onCreate(savedInstanceState: Bundle) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.scr_hello)

		val bYes: Button = findViewById(R.id.btn_yes).asInstanceOf[Button]
		val bNo: Button = findViewById(R.id.btn_no).asInstanceOf[Button]

		bYes.setOnClickListener(new View.OnClickListener {
			def onClick(v: View) {
				val prefs: IDipsPreferences = new DipsPreferences(HelloActivity.this)
				var intent: Intent = null

				if (prefs.isAlreadyRegistered) {
					intent = new Intent(HelloActivity.this, classOf[MainActivity])
				} else {
					intent = new Intent(HelloActivity.this, classOf[InitialDipsActivity])
				}

				startActivity(intent)
				finish()
			}
		})

		bNo.setOnClickListener(new View.OnClickListener {
			def onClick(v: View) {
				finish()
			}
		})
	}
}