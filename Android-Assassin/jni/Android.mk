# File: Android.mk
 LOCAL_PATH := $(call my-dir)

 include $(CLEAR_VARS)

 LOCAL_MODULE    := tophat_header
 LOCAL_SRC_FILES := tophat_header_wrap.c tophat_header.c

 include $(BUILD_SHARED_LIBRARY)
