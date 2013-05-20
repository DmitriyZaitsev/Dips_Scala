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
		mSharedPreferences = context.getSharedPreferences(DIPS_PREFERENCES, Context.MODE_PRIVATE)
		mPreferencesEditor = mSharedPreferences.edit
	}

	def isAlreadyRegistered: Boolean = {
		mSharedPreferences.getBoolean(KEY_ALREADY_REGISTERED, false)
	}

	def getUserLevel: Int = {
		mSharedPreferences.getInt(KEY_USER_LEVEL, 0)
	}

	def setAlreadyRegistered(alreadyRegistered: Boolean) {
		mPreferencesEditor.putBoolean(KEY_ALREADY_REGISTERED, alreadyRegistered)
		mPreferencesEditor.commit
	}

	def setDipsInitial(dipsInitial: Int) {
		mPreferencesEditor.putInt(KEY_DIPS_INITIAL, dipsInitial)
		mPreferencesEditor.commit
	}

	def setUserLevel(userLevel: Int) {
		if (userLevel >= 0) {
			mPreferencesEditor.putInt(KEY_USER_LEVEL, userLevel)
			mPreferencesEditor.commit
		}
	}

	private var mPreferencesEditor: SharedPreferences.Editor = null
	private var mSharedPreferences: SharedPreferences = null
}