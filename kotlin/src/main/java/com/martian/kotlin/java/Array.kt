package com.martian.kotlin.java

/**
 * @author martian on 2017/11/29.
 */
fun test() {
    val iArray: IntArray = intArrayOf(1, 2, 3)
    val sArray: Array<String> = Array<String>(5, { i -> i.toString() })
    val anyArray: Array<Any> = arrayOf(1, "2", true)
    val lArray: LongArray = longArrayOf(1L, 2L, 3L)
}