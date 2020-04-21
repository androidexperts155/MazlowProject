package com.mazlow.login;

public interface LoginPresenter {
    void doLogin(String phonenumber,String ccode,String pcode);
    void doLoginWithPhone(String phonenumber,String ccode);
}
