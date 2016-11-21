package com.example.martian.util;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by yangpei on 2016/11/16.
 */

public class TestJunitTest {

    private TestJunit tj;

    @Before
    public void setup() {
        tj = new TestJunit();
    }

    @Test
    @Ignore
    public void testAdd() throws Exception {
        int sum = tj.add(1, 2);
        Assert.assertEquals(4, sum);
    }

    @Test
    public void testMultiply() throws Exception {
        int count = tj.multiply(4, 5);
        Assert.assertEquals(20, count);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivide() throws Exception {
        int data = tj.divide(4, 0);

    }


}
