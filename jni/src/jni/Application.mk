# --~--~--~--~----~--~--~--~----~--~--~--~----~--~--~--~----~--~--~--~--
# Copyright (c) 2014-2015 Qualcomm Technologies, Inc.
# All Rights Reserved.
# Confidential & Proprietary – Qualcomm Technologies, Inc.
# --~--~--~--~----~--~--~--~----~--~--~--~----~--~--~--~----~--~--~--~--
NDK_TOOLCHAIN_VERSION := 4.9
APP_STL := gnustl_shared
APP_CPPFLAGS += -std=c++11
APP_PLATFORM := android-21
APP_PIE := true
APP_ABI := armeabi-v7a-hard
