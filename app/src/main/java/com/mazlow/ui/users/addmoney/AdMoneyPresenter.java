package com.mazlow.ui.users.addmoney;

public interface AdMoneyPresenter {

    void getCard(String access_token );
    void topUpWallet(String access_token,String amount,String currencyCode ,String pfsToken);
}
