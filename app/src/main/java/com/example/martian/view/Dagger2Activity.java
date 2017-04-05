package com.example.martian.view;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import com.example.martian.R;
import com.example.martian.dagger.DaggerPersonComponent;
import com.example.martian.dagger.IDaggerActivity;
import com.example.martian.dagger.PasswordValidator;
import com.example.martian.dagger.Person;
import com.example.martian.dagger.UserManager;
import com.example.martian.data.RetrofitService;
import com.google.gson.Gson;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/7/6.
 */
@SuppressWarnings("unused")
public class Dagger2Activity extends AppCompatActivity implements IDaggerActivity {

  /**
   * ATTENTION: This was auto-generated to implement the App Indexing API.
   * See https://g.co/AppIndexing/AndroidStudio for more information.
   */

  private String mTitle;

  private Toolbar toolbar;

  private TextView tx;

  private static final String BASEURL = "http://news-at.zhihu.com/api/4/";

  //    @Inject @Named("defult")
  //    DaggerPresenter daggerPresenter;

  //    @Named("time")
  //    @Inject
  //    OkHttpClient okHttpClient;

  @Inject
  Person person;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
    int flag = getIntent().getExtras().getInt("flag");
    mTitle = getIntent().getExtras().getString("title");
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      switch (flag) {
        case 0:
          getWindow().setEnterTransition(new Explode());
          break;
        case 1:
          getWindow().setEnterTransition(new Slide());
          break;
        case 2:
          getWindow().setEnterTransition(new Fade());
          break;
      }
    }
    setContentView(R.layout.activity_dagger2);
    iniView();
    //        iniData();
    //        iniDagger();
    //        initOkHttpClient();
    iniPerson();
  }

  private void iniPerson() {
    //DaggerPersonComponent.create().inject(this);
    DaggerPersonComponent.builder().build().inject(this);
    tx.setText(person.say());
  }

  private void initOkHttpClient() {
    //        OkHttpClientComponent okHttpClientComponent = DaggerOkHttpClientComponent.builder().okHttpManage(new OkHttpManage()).build();
    ////        okHttpClientComponent.createOkHttpClient();
    //        okHttpClientComponent.inject(this);
  }

  /**
   * 不用Dagger2时代码
   */
  private void iniData() {

    OkHttpClient okHttpClient =
        new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).build();

    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create(new Gson()))
        .client(okHttpClient)
        .build();

    RetrofitService retrofitSerivce = retrofit.create(RetrofitService.class);

    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

    UserManager userManager = new UserManager(sp, retrofitSerivce);

    PasswordValidator passwordValidator = new PasswordValidator();

    //        DaggerPresenter    daggerPresenter = new DaggerPresenter(userManager, passwordValidator, this);
    //        daggerPresenter.getData();

  }

  private void iniDagger() {
    //没有使用Inject
    //AppComponent appComponent = DaggerAppComponent.builder().daggerManage(new DaggerManage(this,30)).build();
    //appComponent.createDaggerPresenter().getData();

    //使用Inject
    //        AppComponent daggerComponent = DaggerAppComponent.builder().daggerManage(new DaggerManage(this,30)).build();
    //        daggerComponent.inject(this);
    //        daggerPresenter.getData();
  }

  private void iniView() {
    toolbar = (Toolbar) findViewById(R.id.toolbar_toolbar);
    toolbar.setBackgroundColor(this.getResources().getColor(R.color.colorPrimaryDark));
    //        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
    toolbar.setTitle(mTitle);
    //        toolbar.setSubtitle("toolbar");
    //        toolbar.inflateMenu(R.menu.menu_main);
    setSupportActionBar(toolbar);

    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {

        return false;
      }
    });
    //        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    //        fab.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View view) {
    //                createNotifition();
    //            }
    //        });
    tx = (TextView) findViewById(R.id.activity_dagger2_tx);
  }

  public void click(View view) {
    switch (view.getId()) {
      //            case R.id.activity_mvp_bt:
      ////                hideInPutSoft();
      //                mvppresenter.login(getUserName(), getPassWord());
      //                break;
    }
  }

  @Override
  public void getSuccess(String data) {
    tx.setText(data);
    Toast.makeText(this, "获取数据成功", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void getFailure() {
    tx.setText("获取数据失败！");
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
