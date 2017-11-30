package com.martian.kotlin.java

/**
 * @author martian on 2017/11/29.
 */
//when
fun whenFun(obj: Any) {
    when (obj) {
        25 -> print("25")
        "Kotlin" -> print("Kotlin")
        !is String -> print("is not String")
        else -> print("is nothing")
    }
}