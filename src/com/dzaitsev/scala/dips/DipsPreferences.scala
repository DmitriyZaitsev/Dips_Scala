package com.dzaitsev.scala.dips

import android.content.Context

/**
 * ------------------------ DESCRIPTION ------------------------<br>
 * <br>
 * Created by Dmitriy Zaitsev at 2013-04-25, 16:13.<br>
 */
final class DipsPreferences(context: Context) extends IDipsPreferences {

	val _sharedPreferences = context.getSharedPreferences(DIPS_PREFERENCES, Context.MODE_PRIVATE)
	val _preferencesEditor = _sharedPreferences.edit

	def alreadyRegistered = _sharedPreferences.getBoolean(KEY_ALREADY_REGISTERED, false)

	def userLevel = _sharedPreferences.getInt(KEY_USER_LEVEL, 0)

	def alreadyRegistered(alreadyRegistered: Boolean) {
		_preferencesEditor.putBoolean(KEY_ALREADY_REGISTERED, alreadyRegistered)
		_preferencesEditor.commit
	}

	def dipsInitial(dipsInitial: Int) {
		_preferencesEditor.putInt(KEY_DIPS_INITIAL, dipsInitial)
		_preferencesEditor.commit
	}

	def userLevel(userLevel: Int) {
		if (userLevel >= 0) {
			_preferencesEditor.putInt(KEY_USER_LEVEL, userLevel)
			_preferencesEditor.commit
		}
	}
}