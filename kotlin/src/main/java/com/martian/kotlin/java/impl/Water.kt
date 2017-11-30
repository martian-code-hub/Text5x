package com.martian.kotlin.java.impl

import kotlin.properties.Delegates

/**
 * @author martian on 2017/11/30.
 */
class Water {
    public  var weigth:Int by Delegates.notNull()

    val volume :Int by lazy {
        200
    }

    var type :String by Delegates.observable("quanshui",{
        proprety,old,new->
        print("proprety:$proprety---old:$old---new:$new")
    })

    var produce :String by Delegates.vetoable("nongfushanquan",{
        proprety,old,new->
        print("proprety:$proprety---old:$old---new:$new")
        new.contains("nongfu")
    })


}