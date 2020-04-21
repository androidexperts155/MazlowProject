package com.mazlow.payments_subscription.activities.select_cards;

public interface SelectCardPresenter {
    void applyCoponCode(String token, String subscriptionId, String couponCode);
}
