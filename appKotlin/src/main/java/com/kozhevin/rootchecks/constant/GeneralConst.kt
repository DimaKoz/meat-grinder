package com.kozhevin.rootchecks.constant



object GeneralConst {

    const val CH_TYPE_UNKNOWN = 0
    const val CH_TYPE_TEST_KEYS = 10
    const val CH_TYPE_DEV_KEYS = 20
    const val CH_TYPE_NON_RELEASE_KEYS = 30
    const val CH_TYPE_DANGEROUS_PROPS = 40
    const val CH_TYPE_PERMISSIVE_SELINUX = 50
    const val CH_TYPE_SU_EXISTS = 60
    const val CH_TYPE_SUPER_USER_APK = 70
    const val CH_TYPE_SU_BINARY = 80
    const val CH_TYPE_BUSYBOX_BINARY = 90
    const val CH_TYPE_XPOSED = 100
    const val CH_TYPE_RESETPROP = 110
    const val CH_TYPE_WRONG_PATH_PERMITION = 120
    const val CH_TYPE_HOOKS = 130

    const val CH_STATE_UNCHECKED = 0
    const val CH_STATE_STILL_GOING = 10
    const val CH_STATE_CHECKED_ROOT_DETECTED = 20
    const val CH_STATE_CHECKED_ROOT_NOT_DETECTED = 30
    const val CH_STATE_CHECKED_ERROR = 40

    const val GITHUB = "https://github.com/DimaKoz/meat-grinder"
}
