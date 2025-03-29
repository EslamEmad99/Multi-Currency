#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_data_util_Secrets_getApiKey(JNIEnv* env, jobject /* this */) {
    std::string api_key = "6bdc2b3f90c2e4989a42d1ff6439cc8e";
    return env->NewStringUTF(api_key.c_str());
}
