package com.martian.kotlin.java.`class`

/**
 * @author martian on 2017/11/29.
 */
class Student(id: Int, name: String, age: Int) : People(id, name, age) {

    var num: Number = 5

    private var nick: String?
        get() {
            return nick
        }
        set(value) {
            nick = value
        }

    override fun study() {
        super.study()
    }

}