package com.kozhevin.rootchecks.annotation


import androidx.annotation.IntDef
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_BUSYBOX_BINARY
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_DANGEROUS_PROPS
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_DEV_KEYS
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_HOOKS
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_NON_RELEASE_KEYS
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_PERMISSIVE_SELINUX
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_RESETPROP
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_SUPER_USER_APK
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_SU_BINARY
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_SU_EXISTS
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_TEST_KEYS
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_UNKNOWN
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_WRONG_PATH_PERMITION
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_XPOSED


//Why? Just look here https://noobcoderblog.wordpress.com/2015/04/12/java-enum-and-android-intdefstringdef-annotation/
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@IntDef(CH_TYPE_UNKNOWN, CH_TYPE_TEST_KEYS, CH_TYPE_DEV_KEYS, CH_TYPE_NON_RELEASE_KEYS, CH_TYPE_DANGEROUS_PROPS, CH_TYPE_PERMISSIVE_SELINUX, CH_TYPE_SU_EXISTS, CH_TYPE_SUPER_USER_APK, CH_TYPE_SU_BINARY, CH_TYPE_BUSYBOX_BINARY, CH_TYPE_XPOSED, CH_TYPE_RESETPROP, CH_TYPE_WRONG_PATH_PERMITION, CH_TYPE_HOOKS)
annotation class CheckingMethodType

