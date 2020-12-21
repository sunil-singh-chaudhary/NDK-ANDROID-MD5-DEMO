#include <android/log.h>
#include <jni.h>
#include <stdio.h>
#include <string.h>
#include "md5.h"
#define TAG "MY_TAG"

JNIEXPORT jstring JNICALL Java_com_example_testmd5_MainActivity_encryptByMD5(JNIEnv *env, jclass obj, jstring strText)
{


	//__android_log_print(ANDROID_LOG_VERBOSE, TAG, "%s",strText);
	char* szText = (char*)(*env)->GetStringUTFChars(env, strText, 0);



	MD5_CTX context = { 0 };
	MD5Init(&context);
	MD5Update(&context, (unsigned char*)szText, strlen(szText));
	unsigned char dest[16] = { 0 };
	MD5Final(dest, &context);
	(*env)->ReleaseStringUTFChars(env, strText, szText);

	int i = 0;
	char szMd5[33] = { 0 };
	for (i = 0; i < 16; i++)
	{
		sprintf(szMd5, "%s%02x", szMd5, dest[i]);
	}

	return (*env)->NewStringUTF(env, szMd5);
}
