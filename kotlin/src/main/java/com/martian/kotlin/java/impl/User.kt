package com.martian.kotlin.java.impl

/**
 * @author martian on 2017/11/30.
 */
class User(var map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}