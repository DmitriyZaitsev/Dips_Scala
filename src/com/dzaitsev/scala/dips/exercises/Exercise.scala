package com.dzaitsev.scala.dips.exercises

/**
 * ------------------------ DESCRIPTION ------------------------<br>
 * <br>
 * Created by Dmitriy Zaitsev at 2013-04-29, 17:47.<br>
 */
object Exercise {

	final val MIN_LEVEL: Int = 1
	final val MIN_SET: Int = 1
}

trait Exercise {

	/**
	 * @param number number of set
	 *
	 * @return amount of exercises of set
	 */
	def set(number: Int): Int

	/** @return number of completed exercises */
	def completed: Int = _completed

	/** @return number of current dips necessary to do */
	def current: Int = _current

	/** @return number of current set */
	def currentSet: Int = _currentSet

	def remaining: Int = total - _completed

	/** @return number of total exercises amount for all sets */
	def total: Int = {
		var total: Int = 0

		for (i <- Exercise.MIN_SET to _maxSet) {
			total += set(i)
		}
		total
	}

	/** Confirm that current set was successfully finished */
	def confirmSet() {
		_completed += set(_currentSet)

		if (_completed > total) {
			_completed = total
		}

		_currentSet += 1
		_current = set(_currentSet)

		if (_currentSet < Exercise.MIN_SET) {
			_currentSet = Exercise.MIN_SET
		} else if (_currentSet > _maxSet) {
			_currentSet = _maxSet
			_current = 0
		}
	}

	/** Sets values of current exercise to default */
	def reset() {
		_currentSet = Exercise.MIN_SET
		_current = set(1)
		_completed = 0
	}

	protected var _level: Int = 0
	protected var _maxLevel: Int = 0
	protected var _maxSet: Int = 0
	private var _completed: Int = 0
	private var _current: Int = 0
	private var _currentSet: Int = 0
}