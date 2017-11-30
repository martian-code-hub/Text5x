package com.martian.kotlin.java

/**
 * @author martian on 2017/11/29.
 */
fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sum(a: Int, b: Int, c: Int) = a + b + c

fun sum(a: Int, b: Int, c: Int, d: Int) = if ((a + b) > 0) a + b else c + d

fun max(a: Int, b: Int) = if (a > b) {
    a
} else {
    b
}