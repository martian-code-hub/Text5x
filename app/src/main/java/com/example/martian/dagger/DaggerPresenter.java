package com.example.martian.dagger;

import com.example.martian.bean.NewsList;
import com.example.martian.data.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yangpei on 2016/11/28.
 */

public class DaggerPresenter {
    private UserManager userManager;
    private PasswordValidator passwordValidator;
    private IDaggerActivity iDaggerActivity;

    public DaggerPresenter(UserManager userManager, PasswordValidator passwordValidator,IDaggerActivity iDaggerActivity) {
        this.userManager = userManager;
        this.passwordValidator = passwordValidator;
        this.iDaggerActivity = iDaggerActivity;
    }

    public void getData(){
       RetrofitService retrofitService =  userManager.getRetrofitService();
      Call<NewsList> list =  retrofitService.getLatestNews();

        list.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                iDaggerActivity.getSuccess(Util.data(response.body()));
                if(passwordValidator.check(response.body().getDate())){
                    userManager.getmPref().edit().putString("date",response.body().getDate()).commit();
                }
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                iDaggerActivity.getFailure();
            }
        });
    }
}
