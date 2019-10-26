package com.kozhevin.rootchecks.annotation


import androidx.annotation.IntDef
import com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_CHECKED_ERROR
import com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_CHECKED_ROOT_DETECTED
import com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_CHECKED_ROOT_NOT_DETECTED
import com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_STILL_GOING
import com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_UNCHECKED


//Why? Just look here https://noobcoderblog.wordpress.com/2015/04/12/java-enum-and-android-intdefstringdef-annotation/
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@IntDef(CH_STATE_UNCHECKED, CH_STATE_STILL_GOING, CH_STATE_CHECKED_ROOT_DETECTED, CH_STATE_CHECKED_ROOT_NOT_DETECTED, CH_STATE_CHECKED_ERROR)
annotation class CheckingState

