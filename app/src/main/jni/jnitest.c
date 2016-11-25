//
// Created by yangpei on 2016/11/22.
//
#include "com_example_martian_util_JniTestUtil.h"
/*
 * Class:     com_example_martian_util_JniTestUtil
 * Method:    getNativeData
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_martian_util_JniTestUtil_getNativeData
        (JNIEnv * env, jclass obj){
    return (*env)->NewStringUTF(env,"This is my first native method!!!");
}