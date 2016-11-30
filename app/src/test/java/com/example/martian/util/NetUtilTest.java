package com.example.martian.util;

import com.orhanobut.logger.Logger;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangpei on 2016/12/1.
 */
public class NetUtilTest {
    NetUtil netUtil;
    @Before
    public void setUp(){
         netUtil = new NetUtil();
    }
    @Test
    public void get() throws Exception {
        String data = NetUtil.get("http://www.baidu.com");
        System.out.print(data);
    }

}