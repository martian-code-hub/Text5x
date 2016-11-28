package com.example.martian.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martian.R;
import com.example.martian.bean.Person;
import com.example.martian.mvp.IMvpActivity;
import com.example.martian.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by Administrator on 2016/7/6.
 */
@SuppressWarnings("unused")
public class MvpActivity extends AppCompatActivity implements IMvpActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private String mTitle;

    private Toolbar toolbar;

    private TextInputEditText et_1, et_2;

    private ProgressDialog pd;

    private MvpPresenter mvppresenter;

    private Button bt;
    private TextInputLayout inputLayout;


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
        setContentView(R.layout.activity_mvp);
        iniView();
        initData();
    }

    private void initData() {
        mvppresenter = new MvpPresenter(this);
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
        et_1 = (TextInputEditText) findViewById(R.id.activity_mvp_ex_1);
        et_2 = (TextInputEditText) findViewById(R.id.activity_mvp_ex_2);
        bt = (Button) findViewById(R.id.activity_mvp_bt);
        inputLayout= (TextInputLayout) findViewById(R.id.activity_mvp_exl_2);

        pd = new ProgressDialog(this);
        et_2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                                           @Override
                                           public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                                               switch (i) {
                                                   case EditorInfo.IME_ACTION_DONE:
                                                       bt.performClick();
                                                       break;
                                               }
                                               return false;
                                           }
                                       }
        );
        et_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                inputLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.activity_mvp_bt:
//                hideInPutSoft();
                mvppresenter.login(getUserName(), getPassWord());
                break;
        }
    }

    /**
     * 隐藏输入法
     */
    private void hideInPutSoft() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();//isOpen若返回true，则表示输入法打开
        if (isOpen) {
            imm.hideSoftInputFromWindow(MvpActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * implements IMvpActivity
     */
    @Override
    public void showProgress() {
        pd.show();
    }

    @Override
    public void hideProgress() {
        pd.cancel();
    }

    @Override
    public void setPassWordError() {
        inputLayout.setError("passwrod error....");
    }

    @Override
    public String getUserName() {
        return et_1.getText().toString();
    }

    @Override
    public String getPassWord() {
        return et_2.getText().toString();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
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
