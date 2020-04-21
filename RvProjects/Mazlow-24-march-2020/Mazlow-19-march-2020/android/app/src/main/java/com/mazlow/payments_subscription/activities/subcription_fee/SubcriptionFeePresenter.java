package com.mazlow.payments_subscription.activities.subcription_fee;

public interface SubcriptionFeePresenter {

    void hitCharge(String token,String amount,String duration, String subcriptionid);
}
