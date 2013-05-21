package com.dzaitsev.scala.dips

import android.content.Context
import android.content.SharedPreferences

/**
 * ------------------------ DESCRIPTION ------------------------<br>
 * <br>
 * Created by Dmitriy Zaitsev at 2013-04-25, 16:13.<br>
 */
final class DipsPreferences extends IDipsPreferences {

	def this(context: Context) {
		this()
		_sharedPreferences = context.getSharedPreferences(DIPS_PREFERENCES, Context.MODE_PRIVATE)
		_preferencesEditor = _sharedPreferences.edit
	}

	def alreadyRegistered: Boolean = _sharedPreferences.getBoolean(KEY_ALREADY_REGISTERED, false)

	def userLevel: Int = _sharedPreferences.getInt(KEY_USER_LEVEL, 0)

	def alreadyRegistered(alreadyRegistered: Boolean) {
		_preferencesEditor.putBoolean(KEY_ALREADY_REGISTERED, alreadyRegistered)
		_preferencesEditor.commit
	}

	def dipsInitial(dipsInitial: Int): Boolean = {
		_preferencesEditor.putInt(KEY_DIPS_INITIAL, dipsInitial)
		_preferencesEditor.commit
	}

	def userLevel(userLevel: Int) {
		if (userLevel >= 0) {
			_preferencesEditor.putInt(KEY_USER_LEVEL, userLevel)
			_preferencesEditor.commit
		}
	}

	private var _preferencesEditor: SharedPreferences.Editor = null
	private var _sharedPreferences: SharedPreferences = null
}