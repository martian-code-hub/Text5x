package com.example.martian.dagger;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangpei on 2016/11/28.
 */

public class PasswordValidator {

    public PasswordValidator() {
    }

    public boolean check(String password){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String data = df.format(new Date());
        if(!TextUtils.isEmpty(password)&&TextUtils.equals(data,password)){
            return true;
        }
        return false;
    }
}
