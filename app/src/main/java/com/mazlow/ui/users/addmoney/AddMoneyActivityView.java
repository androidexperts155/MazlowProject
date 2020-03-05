package com.mazlow.ui.users.addmoney;

import com.mazlow.ui.users.addmoney.models.MyCardModel;

public interface AddMoneyActivityView {

    void getCardOnSuccess(MyCardModel myCardModel);
    void getCardOnError();
    void getCardNoInternet();
}
