package com.example.test;

public class MyClass {
  public static int[] dataNew = {07, 13, 20, 45, 55, 88};

  public static void main(String[] args) {

    //        int head = 7;
    //        int tail = 7;
    //        head = (head-1)&tail;
    //        int result = 0;
    //
    ////        System.out.println("--head--:"+head);
    //        for (int i = 0; i < 10; i++) {
    //            System.out.println(result);
    //            result =  getData(i);
    //            System.out.println(result);
    ////            (int)Math.random()*datasOld.length]
    //        }
    System.out.println(comparedWithCurrentPackage("1",4,false));
    //laber:
    //for (int i = 0; i < 10; i++) {
    //  for (int j = 0; j < 10; j++) {
    //    System.out.println("--i-:" + i + "---j-:" + j);
    //    if (i == 5 && j == 3) {
    //      break laber;
    //    }
    //  }
    //}
  }

  public static int getData(int data) {
    return data + 5;
  }

  public static boolean comparedWithCurrentPackage(String version, int context, boolean show) {
    if (version == null) {
      return false;
    }
    if (context == 0) {
      return true;
    } else {
      if (!show) {
        if (context == 1) {
        } else {
          if (context == 3) {
            return true;
          }
        }
      } else {
        if (context == 4) {
        } else {
          return true;
        }
      }
    }
    return false;
  }
}
