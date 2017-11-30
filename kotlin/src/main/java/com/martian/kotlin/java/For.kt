package com.martian.kotlin.java

/**
 * @author martian on 2017/11/29.
 * for
 */
fun forLoop(array: Array<String>) {

    for (str in array) {
        print(str)
    }

    array.forEach {
        print(it)
    }

    for (i in array.indices) {
        print(array[i])
    }

    var i = 0
    while (i < array.size) {
        print(array[i++])
    }
    //循环嵌套 跳出循环
    Loop@ for (i in 0..2) {
        for (j in 0..3) {
            print("i:$i,j:$j")
            if (j == 2) {
                break@Loop
            }
        }
    }
    //倒序
    for (i in 5 downTo 0) {
        print(i.toString())
    }
    //正序 步长
    for (i in 0..10 step 2) {
        print(i)
    }
    //倒序 步长
    for (i in 10 downTo 0 step 3) {
        print(i)
    }
}