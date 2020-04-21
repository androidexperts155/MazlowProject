package com.mazlow.ui.users.addmoney;

import com.mazlow.ui.users.addmoney.models.MyCardModel;
import com.mazlow.ui.users.addmoney.models.topupmodel.TopupWallet;

public interface AddMoneyActivityView {

    void getCardOnSuccess(MyCardModel myCardModel);
    void getCardOnError();
    void getCardNoInternet();


    void topUpOnSuccess(TopupWallet model);
    void topUpOnError();
    void topUpNoInternet();
}
