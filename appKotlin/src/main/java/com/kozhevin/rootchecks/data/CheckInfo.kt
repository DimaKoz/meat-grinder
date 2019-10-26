package com.kozhevin.rootchecks.data

import com.kozhevin.rootchecks.annotation.CheckingMethodType



class CheckInfo(var state: Boolean?, @param:CheckingMethodType @field:CheckingMethodType
@get:CheckingMethodType
var typeCheck: Int) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CheckInfo

        if (state != other.state) return false
        if (typeCheck != other.typeCheck) return false

        return true
    }

    override fun hashCode(): Int {
        var result = state?.hashCode() ?: 0
        result = 31 * result + typeCheck
        return result
    }
}
