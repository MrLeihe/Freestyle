//
// Created by 贺磊 on 2018/1/4.
//
#include <jni.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_sd_style_common_jni_JniUtils_studyJni
        (JNIEnv* env, jobject instance, jstring _name, jint _age){
    return env-> NewStringUTF("我就是我，不一样的花火");
}
