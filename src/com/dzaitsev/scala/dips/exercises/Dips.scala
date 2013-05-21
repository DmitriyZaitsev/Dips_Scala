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

class Dips extends Exercise {

	def this(level: Int) {
		this()
		_maxLevel = 16
		_maxSet = 5
		if (level <= Exercise.MIN_LEVEL) {
			_level = Exercise.MIN_LEVEL
		} else if (_maxLevel < level) {
			_level = _maxLevel
		} else {
			_level = level
		}
		reset()
	}

	/**
	 * @param number number of set
	 *
	 * @return amount of exercises of set
	 */
	def set(number: Int): Int = {
		if (number <= 1) {
			return DipsSet.set1(_level - 1)
		}
		number match {
			case 2 => DipsSet.set2(_level - 1)
			case 3 => DipsSet.set3(_level - 1)
			case 4 => DipsSet.set4(_level - 1)
			case _ => DipsSet.set5(_level - 1)
		}
	}
}