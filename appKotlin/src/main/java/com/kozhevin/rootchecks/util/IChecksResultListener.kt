package com.kozhevin.rootchecks.util

import com.kozhevin.rootchecks.data.TotalResult


interface IChecksResultListener {
    fun onProcessStarted()
    fun onUpdateResult(result: TotalResult)
    fun onProcessFinished(result: TotalResult)
}
