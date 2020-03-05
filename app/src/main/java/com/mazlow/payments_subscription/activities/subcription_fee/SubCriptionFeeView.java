package com.mazlow.payments_subscription.activities.subcription_fee;

import okhttp3.ResponseBody;

public interface SubCriptionFeeView {
    void onSuccessSubrionfee(ResponseBody responseBody);
    void onError(String error);
    void onNetworkSubcription(String tag);
}
