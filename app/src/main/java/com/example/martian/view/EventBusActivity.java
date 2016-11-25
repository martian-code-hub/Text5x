package com.example.martian.view;

import android.os.Bundle;
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
import com.example.martian.bean.Person;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by Administrator on 2016/7/6.
 */
@SuppressWarnings("unused")
public class EventBusActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private String mTitle;

    private Toolbar toolbar;

    private TextView  content_1,content_2,content_3,content_4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        int flag = getIntent().getExtras().getInt("flag");
        mTitle = getIntent().getExtras().getString("title");
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

        setContentView(R.layout.activity_eventbus);
        iniView();
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
        content_1 = (TextView) findViewById(R.id.activity_eventbus_tx_1);
        content_2 = (TextView) findViewById(R.id.activity_eventbus_tx_2);
        content_3 = (TextView) findViewById(R.id.activity_eventbus_tx_3);
        content_4 = (TextView) findViewById(R.id.activity_eventbus_tx_4);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.activity_eventbus_bt_1:
                EventBus.builder().sendNoSubscriberEvent(false).build().post("getString");
                break;
            case R.id.activity_eventbus_bt_2:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Person person = new Person();
                        person.setName("mainthread");
                        EventBus.getDefault().post(person);
                    }
                }).start();

                break;
            case R.id.activity_eventbus_bt_3:
                break;
            case R.id.activity_eventbus_bt_4:
                break;



        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,priority = 20)
    public void getString(Person person){
        person.setName("getString");
        content_1.setText( person.getName());
    }
    @Subscribe(threadMode = ThreadMode.MAIN,priority = 0)
    public void getPerson(Person person){
        person.setName("getPerson");
        content_2.setText(person.getName());
    }
    @Subscribe(threadMode = ThreadMode.MAIN,priority = 50)
    public  void getPersonOnMainThread(Person person){
        person.setName("getPersonOnMainThread");
        content_3.setText(person.getName());
    }
    @Subscribe(threadMode = ThreadMode.MAIN,priority = 100)
    public void onEventAsync(Person person){
        person.setName("onEventAsync");
        content_4.setText(person.getName());
    }


    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
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
