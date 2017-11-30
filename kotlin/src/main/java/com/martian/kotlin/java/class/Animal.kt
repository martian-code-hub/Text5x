package com.martian.kotlin.java.`class`

/**
 * @author martian on 2017/11/29.
 */
open class Animal {

    open  fun say(){
        print("Animal")
    }
}


var cat = object :Animal(){
    override fun say() {
        super.say()
    }
}