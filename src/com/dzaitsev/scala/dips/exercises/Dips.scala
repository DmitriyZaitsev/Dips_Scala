package com.dzaitsev.scala.dips.exercises

/**
 * ------------------------ DESCRIPTION ------------------------<br>
 * <br>
 * Created by Dmitriy Zaitsev at 2013-04-29, 15:32.<br>
 */
object Dips {

	/** @return level which depends on dips */
	def calcLevel(dips: Int): Int = {
		for (i <- DipsSet.MIN_LEVEL until DipsSet.MAX_LEVEL) {
			if (DipsSet.set1(i - 1) <= dips && dips < DipsSet.set1(i)) {
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

class Dips(l: Int) extends Exercise {

	maxLevel = 16
	maxSet = 5

	if (l <= Exercise.MIN_LEVEL) {
		level = Exercise.MIN_LEVEL
	} else if (maxLevel < l) {
		level = maxLevel
	} else {
		level = l
	}
	reset()


	/**
	 * @param number number of set
	 *
	 * @return amount of exercises of set
	 */
	def set(number: Int): Int = {
		if (number <= 1) {
			return DipsSet.set1(level - 1)
		}
		number match {
			case 2 => DipsSet.set2(level - 1)
			case 3 => DipsSet.set3(level - 1)
			case 4 => DipsSet.set4(level - 1)
			case _ => DipsSet.set5(level - 1)
		}
	}
}