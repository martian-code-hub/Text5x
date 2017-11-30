package com.martian.kotlin.java.`class`

/**
 * @author martian on 2017/11/30.
 */
class Extend(var name: String) {

    fun pint() {
        print("This is class method")
    }
}

//扩展函数
fun Extend.pintln(msg: String) {
    pintln(msg)
}

//可以扩展一个空对象
fun Any?.toString1(): String {
    if (this == null) {
        return "toString1 is null"
    } else {
        return "toString1" + toString();
    }
}
/**
 * 扩展属性
 * 由于扩展属性实际上不会向类添加新的成员,
 * 因此无法让一个扩展属性拥有一个后端域变量. 所以,对于扩展属性不允许存在初始化器.
 * 扩展属性的行为只能通过明确给定的取值方法与设值方法来定义，也就意味着扩展属性只
 * 能被声明为val而不能被声明为var.如果强制声明为var，即使进行了初始化，
 * 在运行也会报异常错误，提示该属性没有后端域变量。
 */
val Extend.nick: String
    get() {
        return "martian"
    }

