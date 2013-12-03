package com.dzaitsev.scala.dips.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.dzaitsev.scala.dips.R
import java.util.Timer
import java.util.TimerTask

/**
 * ------------------------ DESCRIPTION ------------------------<br>
 * <br>
 * Created by Dmitriy Zaitsev at 2013-04-25, 16:08.<br>
 */
class SplashActivity extends Activity {

	protected override def onCreate(savedInstanceState: Bundle) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.scr_splash)
		switchScreen()
	}

	private def switchScreen() {
		val task = new TimerTask {

			def run() {
				startActivity(new Intent(SplashActivity.this, classOf[HelloActivity]))
				finish()
			}
		}

		(new Timer).schedule(task, 2000)
	}
}