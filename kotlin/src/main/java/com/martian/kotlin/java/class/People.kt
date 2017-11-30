package com.martian.kotlin.java.`class`

/**
 * @author martian on 2017/11/29.
 */
open class People private constructor(var id:Int,var name:String){

    //可以类中初始化属性
    var customName = name.toUpperCase()

    //次构造函数，使用constructor前缀声明，且必须调用primary constructor，使用this关键字
    constructor(id:Int,name:String,age:Int):this(id,name){
     print("constructor")
    }

    //初始化方法
    init {
        print("初始化操作，可使用constructor的参数")
    }

    //需要open修饰符 子类才可以使用
    open fun study(){
        print("study")
    }

}