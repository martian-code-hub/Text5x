package com.example.martian.bean;

/**
 * Created by yangpei on 2016/11/18.
 */

public class Node {
    public int data;
    public Node left;
    public Node right;

    public  Node(int data){
        this.data = data;
        left = null;
        right = null;
    }
}
