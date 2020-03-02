package com.mazlow.payments_subscription.activities.select_cards;

import com.mazlow.payments_subscription.models.apply_coupon_code.ApplyCouponCodeResponse;

public interface SelectCardView {
    void onSuccess(ApplyCouponCodeResponse response);
    void onError(String error);
    void noInternet(String tag);
}
