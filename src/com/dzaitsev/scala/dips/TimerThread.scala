package com.dzaitsev.scala.dips

import android.app.Activity
import android.os.Handler
import java.util.concurrent.TimeUnit

/**
 * ------------------------ DESCRIPTION ------------------------<br>
 * <br>
 * Created by Dmitriy Zaitsev at 2013-04-25, 14:40.<br>
 */
class TimerThread extends Runnable {

	def this(handler: Handler, seconds: Int) {
		this()
		mHandler = handler
		mSeconds = seconds
	}

	override def run() {
		for (i <- mSeconds to(1, -1)) {
			mHandler.sendEmptyMessage(i)
			try {
				TimeUnit.SECONDS.sleep(1)
			} catch {
				case e: InterruptedException => {
					e.printStackTrace()
					mHandler.sendEmptyMessage(Activity.RESULT_CANCELED)
				}
			}
		}
		mHandler.sendEmptyMessage(Activity.RESULT_OK)
	}

	private var mHandler: Handler = null
	private var mSeconds: Int = 0
}