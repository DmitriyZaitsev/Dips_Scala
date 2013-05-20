package com.dzaitsev.scala.dips

/**
 * ------------------------ DESCRIPTION ------------------------<br>
 * <br>
 * Created by Dmitriy Zaitsev at 2013-04-29, 12:05.<br>
 */
trait IDipsPreferences {

	final val DIPS_PREFERENCES: String = "dips_preferences"
	final val KEY_ALREADY_REGISTERED: String = "already_registered"
	final val KEY_DIPS_INITIAL: String = "dips_initial"
	final val KEY_USER_LEVEL: String = "user_level"

	/** @return <i>true</i> if user already registered */
	def isAlreadyRegistered: Boolean

	def getUserLevel: Int

	def setAlreadyRegistered(alreadyRegistered: Boolean)

	def setDipsInitial(dipsInitial: Int)

	def setUserLevel(userLevel: Int)
}