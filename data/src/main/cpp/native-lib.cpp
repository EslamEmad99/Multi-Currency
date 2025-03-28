#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_data_util_Secrets_getApiKey(JNIEnv* env, jobject /* this */) {
    std::string api_key = "0848ee203030487f8c58494aa064df4c";  // Replace with your real API key
    return env->NewStringUTF(api_key.c_str());
}
