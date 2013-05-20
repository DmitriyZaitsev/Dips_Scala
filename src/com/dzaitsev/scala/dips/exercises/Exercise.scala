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
	def getSet(number: Int): Int

	/** @return number of completed exercises */
	def getCompleted: Int = {
		mCompletedExercises
	}

	/** @return number of current dips necessary to do */
	def getCurrent: Int = {
		mCurrentExercises
	}

	/** @return number of current set */
	def getCurrentSet: Int = {
		mCurrentSet
	}

	def getRemaining: Int = {
		getTotal - mCompletedExercises
	}

	/** @return number of total exercises amount for all sets */
	def getTotal: Int = {
		var total: Int = 0

		for (i <- Exercise.MIN_SET to mMaxSet) {
			total += getSet(i)
		}
		total
	}

	/** Confirm that current set was successfully finished */
	def confirmSet() {
		mCompletedExercises += getSet(mCurrentSet)

		if (mCompletedExercises > getTotal) {
			mCompletedExercises = getTotal
		}

		mCurrentSet += 1
		mCurrentExercises = getSet(mCurrentSet)

		if (mCurrentSet < Exercise.MIN_SET) {
			mCurrentSet = Exercise.MIN_SET
		} else if (mCurrentSet > mMaxSet) {
			mCurrentSet = mMaxSet
			mCurrentExercises = 0
		}
	}

	/** Sets values of current exercise to default */
	def reset() {
		mCurrentSet = Exercise.MIN_SET
		mCurrentExercises = getSet(1)
		mCompletedExercises = 0
	}

	private[exercises] var mLevel: Int = 0
	private[exercises] var mMaxLevel: Int = 0
	private[exercises] var mMaxSet: Int = 0
	private var mCompletedExercises: Int = 0
	private var mCurrentExercises: Int = 0
	private var mCurrentSet: Int = 0
}