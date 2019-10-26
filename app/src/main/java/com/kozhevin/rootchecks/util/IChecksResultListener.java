package com.kozhevin.rootchecks.util;

import com.kozhevin.rootchecks.data.TotalResult;



public interface IChecksResultListener {
    void onProcessStarted();
    void onUpdateResult(TotalResult result);
    void onProcessFinished(TotalResult result);
}
