

#include <jni.h>
#include "../../../../distribution/meat_grinder.h"



JNIEXPORT jboolean JNICALL
Java_com_kozhevin_rootchecks_util_MeatGrinder_isDetectedTestKeys(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isDetectedTestKeys();
}

JNIEXPORT jboolean JNICALL
Java_com_kozhevin_rootchecks_util_MeatGrinder_isDetectedDevKeys(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isDetectedDevKeys();
}

JNIEXPORT jboolean JNICALL
Java_com_kozhevin_rootchecks_util_MeatGrinder_isNotFoundReleaseKeys(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isNotFoundReleaseKeys();
}

JNIEXPORT jboolean JNICALL
Java_com_kozhevin_rootchecks_util_MeatGrinder_isFoundDangerousProps(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isFoundDangerousProps();
}

JNIEXPORT jboolean JNICALL
Java_com_kozhevin_rootchecks_util_MeatGrinder_isPermissiveSelinux(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isPermissiveSelinux();
}

JNIEXPORT jboolean JNICALL
Java_com_kozhevin_rootchecks_util_MeatGrinder_isSuExists(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isSuExists();
}

JNIEXPORT jboolean JNICALL
Java_com_kozhevin_rootchecks_util_MeatGrinder_isAccessedSuperuserApk(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isAccessedSuperuserApk();
}

JNIEXPORT jboolean JNICALL
Java_com_kozhevin_rootchecks_util_MeatGrinder_isFoundSuBinary(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isFoundSuBinary();
}

JNIEXPORT jboolean JNICALL
Java_com_kozhevin_rootchecks_util_MeatGrinder_isFoundBusyboxBinary(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isFoundBusyboxBinary();
}


JNIEXPORT jboolean JNICALL
Java_com_kozhevin_rootchecks_util_MeatGrinder_isFoundXposed(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isFoundXposed();
}

JNIEXPORT jboolean JNICALL
Java_com_kozhevin_rootchecks_util_MeatGrinder_isFoundResetprop(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isFoundResetprop();
}

JNIEXPORT jboolean JNICALL
Java_com_kozhevin_rootchecks_util_MeatGrinder_isFoundWrongPathPermission(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isFoundWrongPathPermission();
}

JNIEXPORT jboolean JNICALL
Java_com_kozhevin_rootchecks_util_MeatGrinder_isFoundHooks(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isFoundHooks();
}
