//
// Created by 贺磊 on 2017/11/2.
//

#include <jni.h>
#include <stdio.h>

extern "C"
{
    JNIEXPORT jstring JNICALL Java_com_sd_style_module_mine_v_fragment_MineFragment_getFromUri(JNIEnv *env, jobject obj){
        return env-> NewStringUTF("hello, i am from jni");
    }
}