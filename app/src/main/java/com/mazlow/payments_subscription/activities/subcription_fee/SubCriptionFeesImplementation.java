package com.mazlow.payments_subscription.activities.subcription_fee;

import android.content.Context;

public class SubCriptionFeesImplementation implements SubcriptionFeePresenter {

    Context context;
    SubCriptionFeeView subCriptionFeeView;


    public SubCriptionFeesImplementation(Context context, SubCriptionFeeView subCriptionFeeView) {
        this.context = context;
        this.subCriptionFeeView = subCriptionFeeView;
    }

    @Override
    public void hitCharge(String token, String amount, String duration, String subcriptionid) {

    }
}
