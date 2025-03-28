#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_data_util_Secrets_getApiKey(JNIEnv* env, jobject /* this */) {
    std::string api_key = "your_actual_api_key_here";  // Replace with your real API key
    return env->NewStringUTF(api_key.c_str());
}
