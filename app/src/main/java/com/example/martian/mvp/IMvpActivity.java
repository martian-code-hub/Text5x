package com.example.martian.mvp;

/**
 * Created by yangpei on 2016/11/28.
 */

public interface IMvpActivity {

    void showProgress();

    void hideProgress();

    void setPassWordError();

    String getUserName();

    String getPassWord();

    void loginSuccess();


}
