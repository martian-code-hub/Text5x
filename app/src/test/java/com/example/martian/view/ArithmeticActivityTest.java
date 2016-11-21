package com.example.martian.view;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangpei on 2016/11/17.
 */
public class ArithmeticActivityTest {

    private ArithmeticActivity aa ;
    private int[] dataOld = {20,13,07,55,45,88};
    private int[] dataNew = {07,13,20,45,55,88};

    @Before
    public void setUp(){
        aa =  new ArithmeticActivity();
    }
    @Ignore
    public void arrayToString() throws Exception {

        String string= aa.arrayToString(dataOld);
        assertEquals("[20,13,07,55,45,88]",string);

    }
    @Ignore
    public void arithmeticOne() throws Exception {
        assertArrayEquals(dataNew,aa.arithmeticOne(dataOld));
    }

    @Ignore
    public void arithmeticTwo() throws Exception {
        assertArrayEquals(dataNew,aa.arithmeticTwo(dataOld));
    }

    @Ignore
    public void arithmeticThree() throws Exception {
        assertArrayEquals(dataNew,aa.arithmeticThree(dataOld));
    }

    @Ignore
    public void arithmeticFour() throws Exception {
        assertArrayEquals(dataNew,aa.arithmeticFour(dataOld));
    }

    @Ignore
    public void arithmeticFive() throws Exception {
        assertArrayEquals(dataNew,aa.arithmeticFive(dataOld));
    }

    @Ignore
    public void arithmeticNine() throws Exception {
        int data = (int)(Math.random()*dataNew.length);
     assertEquals(data,aa.recursion(dataNew,0,dataNew.length-1,dataNew[data]));
    }

    @Ignore
    public void arithmeticTen() throws Exception {
        int data = (int)(Math.random()*dataNew.length);
        assertEquals(data,aa.cycle(dataNew,dataNew[data]));
    }

    @Test
    public void createTree() throws Exception {

    }
}