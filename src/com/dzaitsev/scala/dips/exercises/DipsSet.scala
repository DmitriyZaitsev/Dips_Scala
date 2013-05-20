package com.dzaitsev.scala.dips.exercises

/**
 * ------------------------ DESCRIPTION ------------------------<br>
 * <br>
 * Created by Dmitriy Zaitsev at 2013-04-25, 14:01.<br>
 */
object DipsSet {

	def getSet1: Array[Int] = {
		sSet1
	}

	def getSet2: Array[Int] = {
		sSet2
	}

	def getSet3: Array[Int] = {
		sSet3
	}

	def getSet4: Array[Int] = {
		sSet4
	}

	def getSet5: Array[Int] = {
		sSet5
	}

	private[exercises] val MAX_DIPS: Int = 68
	private[exercises] val MAX_LEVEL: Int = 17
	private[exercises] val MIN_DIPS: Int = 10
	private[exercises] val MIN_LEVEL: Int = 1
	private val sSet1: Array[Int] = Array[Int](MIN_DIPS, 15, 20, 25, 30, 35, 40, 43, 45, 47, 50, 53,
		55, 60, 63, 65, MAX_DIPS)
	private val sSet2: Array[Int] = Array[Int](5, 15, 20, 25, 30, 30, 35, 40, 40, 45, 45, 50, 50,
		55, 60, 60, 65)
	private val sSet3: Array[Int] = Array[Int](5, 10, 15, 20, 25, 25, 25, 30, 35, 35, 35, 40, 40,
		40, 45, 45, 45)
	private val sSet4: Array[Int] = Array[Int](3, 5, 15, 15, 20, 20, 25, 30, 35, 35, 35, 40, 40, 40,
		45, 45, 45)
	private val sSet5: Array[Int] = Array[Int](2, 5, 10, 10, 15, 15, 15, 20, 25, 25, 30, 35, 35, 35,
		40, 40, 40)
}