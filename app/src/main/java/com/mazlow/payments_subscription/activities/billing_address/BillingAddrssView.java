package com.mazlow.payments_subscription.activities.billing_address;

import com.mazlow.payments_subscription.activities.billing_address.model.BillingAddressResponse;

public interface BillingAddrssView {
    void billingAddressSuccess(BillingAddressResponse billingAddressResponse);
    void billingAddrssError(String error);
    void noInternet(String error);
}
