package com.martian.kotlin.java

import com.martian.kotlin.java.`class`.Extend
import com.martian.kotlin.java.`class`.Model
import com.martian.kotlin.java.`class`.pintln
import com.martian.kotlin.java.`class`.toString1
import com.martian.kotlin.java.impl.User

/**
 * @author martian on 2017/11/29.
 */
var str: String = ""

fun main(args: Array<String>) {
    str = "Hello World"
    print(str)
    val sum = { x: Int, y: Int -> x + y }
    print(sum)
    model()
    enum()
    extend()
    by()
}

//操作对象
fun model() {
    //使用对象
    var model = Model(1, "Matian", 20)
    print(model)

    model.age = 25
    print(model)

    val id = model.id
    print("id:$id")
}

//枚举
fun enum() {
    var color = Color.BLACK
}

//扩展
fun extend() {
    var extend = Extend("kotlin")
    extend.pint()
    extend.pintln("extend method")
    print(extend.toString1())
    print(extend.name)
}


fun by() {
    var user = User(mapOf("name" to "martian", "age" to 25))
    print(user.name)
    print(user.age)
}






