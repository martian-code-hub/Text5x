package com.martian.kotlin.java.impl

/**
 * @author martian on 2017/11/30.
 * 代理
 */
class Drived(base:Base):Base by base {
    fun show(){
        var b = BaseImpl()
         var drived = Drived(b)
        drived.show()
    }
}