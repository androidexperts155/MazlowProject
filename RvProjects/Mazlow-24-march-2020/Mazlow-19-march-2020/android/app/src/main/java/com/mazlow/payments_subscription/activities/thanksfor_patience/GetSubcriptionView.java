package com.mazlow.payments_subscription.activities.thanksfor_patience;

import com.mazlow.payments_subscription.activities.thanksfor_patience.model.SubcriptionResponsemodel;
import com.mazlow.payments_subscription.models.apply_coupon_code.ApplyCouponCodeResponse;

public interface GetSubcriptionView {
    void onSuccessSubcription(SubcriptionResponsemodel response);
    void onErrorSubcription(String error);
    void noInternetSubcription(String tag);
}
