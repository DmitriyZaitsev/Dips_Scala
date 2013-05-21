package com.dzaitsev.scala.dips.exercises

/**
 * ------------------------ DESCRIPTION ------------------------<br>
 * <br>
 * Created by Dmitriy Zaitsev at 2013-04-25, 14:01.<br>
 */
private[exercises] object DipsSet {

	def set1: Array[Int] = _set1

	def set2: Array[Int] = _set2

	def set3: Array[Int] = _set3

	def set4: Array[Int] = _set4

	def set5: Array[Int] = _set5


	private[exercises] val MAX_DIPS: Int = 68
	private[exercises] val MAX_LEVEL: Int = 17
	private[exercises] val MIN_DIPS: Int = 10
	private[exercises] val MIN_LEVEL: Int = 1

	private val _set1: Array[Int] = Array[Int](MIN_DIPS, 15, 20, 25, 30, 35, 40, 43, 45, 47, 50, 53,
		55, 60, 63, 65, MAX_DIPS)
	private val _set2: Array[Int] = Array[Int](5, 15, 20, 25, 30, 30, 35, 40, 40, 45, 45, 50, 50,
		55, 60, 60, 65)
	private val _set3: Array[Int] = Array[Int](5, 10, 15, 20, 25, 25, 25, 30, 35, 35, 35, 40, 40,
		40, 45, 45, 45)
	private val _set4: Array[Int] = Array[Int](3, 5, 15, 15, 20, 20, 25, 30, 35, 35, 35, 40, 40, 40,
		45, 45, 45)
	private val _set5: Array[Int] = Array[Int](2, 5, 10, 10, 15, 15, 15, 20, 25, 25, 30, 35, 35, 35,
		40, 40, 40)
}