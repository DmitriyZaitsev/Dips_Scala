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
	def alreadyRegistered: Boolean

	def userLevel: Int

	def alreadyRegistered(alreadyRegistered: Boolean)

	def dipsInitial(dipsInitial: Int)

	def userLevel(userLevel: Int)
}