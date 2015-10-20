LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := dspatch
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_SRC_FILES := \
	/Users/wangqi/AndroidStudioProjects/AndroidTest/dspatch/src/main/jni/com_wq_dspatch_MainActivity.c \

LOCAL_C_INCLUDES += /Users/wangqi/AndroidStudioProjects/AndroidTest/dspatch/src/main/jni
LOCAL_C_INCLUDES += /Users/wangqi/AndroidStudioProjects/AndroidTest/dspatch/src/debug/jni

include $(BUILD_SHARED_LIBRARY)
