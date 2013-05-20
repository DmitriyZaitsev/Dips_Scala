package com.dzaitsev.scala.dips.exercises

/**
 * ------------------------ DESCRIPTION ------------------------<br>
 * <br>
 * Created by Dmitriy Zaitsev at 2013-04-29, 15:32.<br>
 */
object Dips {

	/** @return level which depends on dips */
	def calcLevel(dips: Int): Int = {
		for (i <- DipsSet.MIN_LEVEL to DipsSet.MAX_LEVEL - 1) {
			if (DipsSet.getSet1(i - 1) <= dips && dips < DipsSet.getSet1(i)) {
				return i
			}
		}
		0
	}

	/**
	 * @param initDips your initial dips
	 *
	 * @return -1 if you're too frail<br>
	 *         1 if you're too cool<br>
	 *         0 if you're OK :)
	 */
	def recommend(initDips: Int): Int = {
		if (initDips < DipsSet.MIN_DIPS) {
			-1
		} else if (DipsSet.MIN_DIPS <= initDips && initDips < DipsSet.MAX_DIPS) {
			0
		} else {
			1
		}
	}
}

class Dips extends Exercise {

	def this(level: Int) {
		this()
		mMaxLevel = 16
		mMaxSet = 5
		if (level <= Exercise.MIN_LEVEL) {
			mLevel = Exercise.MIN_LEVEL
		} else if (mMaxLevel < level) {
			mLevel = mMaxLevel
		} else {
			mLevel = level
		}
		reset()
	}

	/**
	 * @param number number of set
	 *
	 * @return amount of exercises of set
	 */
	def getSet(number: Int): Int = {
		if (number <= 1) {
			return DipsSet.getSet1(mLevel - 1)
		}
		number match {
			case 2 => DipsSet.getSet2(mLevel - 1)
			case 3 => DipsSet.getSet3(mLevel - 1)
			case 4 => DipsSet.getSet4(mLevel - 1)
			case _ => DipsSet.getSet5(mLevel - 1)
		}
	}
}