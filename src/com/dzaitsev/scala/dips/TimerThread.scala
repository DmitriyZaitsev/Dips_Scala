package com.dzaitsev.scala.dips

import android.app.Activity
import android.os.Handler
import java.util.concurrent.TimeUnit

/**
 * ------------------------ DESCRIPTION ------------------------<br>
 * <br>
 * Created by Dmitriy Zaitsev at 2013-04-25, 14:40.<br>
 */
class TimerThread(handler: Handler, seconds: Int) extends Runnable {

	override def run() {
		for (i <- seconds to(1, -1)) {
			handler.sendEmptyMessage(i)

			try {
				TimeUnit.SECONDS.sleep(1)
			} catch {
				case e: InterruptedException =>
					e.printStackTrace()
					handler.sendEmptyMessage(Activity.RESULT_CANCELED)
			}
		}
		handler.sendEmptyMessage(Activity.RESULT_OK)
	}
}