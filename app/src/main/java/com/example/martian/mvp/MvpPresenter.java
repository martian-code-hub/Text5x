package com.example.martian.mvp;

import android.os.Handler;

/**
 * Created by yangpei on 2016/11/28.
 */

public class MvpPresenter implements IMvpPresenter {

    private IMvpActivity iMvpActivity;
    private User user;

    public MvpPresenter(IMvpActivity iMvpActivity) {
        this.iMvpActivity = iMvpActivity;
        user = new User(iMvpActivity.getUserName(), iMvpActivity.getPassWord());
    }

    @Override
    public void login(final String username, final String password) {
        iMvpActivity.showProgress();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iMvpActivity.hideProgress();
                int code = user.checkUserValidity(username, password);
                if (code == -1) {
                    iMvpActivity.setPassWordError();
                } else if (code == 0) {
                    iMvpActivity.loginSuccess();
                }
            }
        }, 3000);
    }
}
