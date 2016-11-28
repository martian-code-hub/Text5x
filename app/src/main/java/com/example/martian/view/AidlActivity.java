package com.example.martian.view;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martian.PersonManager;
import com.example.martian.R;
import com.example.martian.bean.Person;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
@SuppressWarnings("unused")
public class AidlActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private String mTitle;

    private Toolbar toolbar;

    private TextView temp, content;

    private PersonManager personManager;

    private boolean isSuccessConnection = false;

    private List<Person> list;


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
        setContentView(R.layout.activity_aidl);
        iniView();
        upDataTempView();
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
        temp = (TextView) findViewById(R.id.activity_aidl_temp);
        content = (TextView) findViewById(R.id.activity_aidl_content);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.activity_aidl_connection:
                connection();
                break;
            case R.id.activity_aidl_get:
                get();
                break;
            case R.id.activity_aidl_add:
                add();
                break;
            case R.id.activity_aidl_update:
                if (list != null && list.size() > 0) {
                    int index = (int) (Math.random() * list.size());
                    update(updatePerson(index), index);
                } else {
                    Toast.makeText(this, "还没有数据，请添加数据", Toast.LENGTH_SHORT).show();
                }
                break;


        }
    }

    /**
     * 连接服务器
     */
    private void connection() {
        if (isSuccessConnection) {
            Toast.makeText(this, "与服务器已连接，请勿重复操作", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.setAction("com.example.martian.aidl.service.connection");
            intent.setPackage("service.martian.example.com.aidlservice");
            bindService(intent, sc, Context.BIND_AUTO_CREATE);

        }
    }


    /**
     * 与服务器通信(添加数据)
     */
    private void add() {
        if (!isSuccessConnection) {
            upDataTempView();
            upDataContentView(null);
            connection();
        }
        if (personManager != null) {
            try {
                personManager.addPerson(createPerson());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 与服务器通信(获取数据)
     */
    private void get() {
        if (!isSuccessConnection) {
            upDataTempView();
            upDataContentView(null);
            connection();
        }
        if (personManager != null) {
            try {
                if (list != null) {
                    list.clear();
                }
                list = personManager.getPersons();
                upDataContentView(list);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 与服务器通信(修改数据)
     */
    private void update(Person person, int index) {
        if (!isSuccessConnection) {
            upDataTempView();
            upDataContentView(null);
            connection();
        }
        if (personManager != null) {
            try {
                personManager.updatePerson(person, index);
                upDataContentView(list);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 提示与服务器连接的状态
     */
    private void upDataTempView() {
        temp.setText(isSuccessConnection ? "与服务器连接成功" : "与服务器未连接");
        Toast.makeText(this, isSuccessConnection ? "连接成功" : "连接失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 创建person实例
     *
     * @return
     */
    private Person createPerson() {
        Person p = new Person();
        p.setName("martian");
        p.setAge("30");
        p.setSex("boy");
        return p;
    }

    /**
     * 更新person实例
     *
     * @param index
     * @return
     */
    private Person updatePerson(int index) {
        Person person = list.get(index);
        person.setName("martian" + index);
        person.setAge("3" + index);
        person.setSex(((index % 2) == 0) ? "boy" : "girl");
        return person;
    }

    /**
     * 显示从服务器获取的数据
     */
    private void upDataContentView(List<Person> list) {
        if (list == null || list.size() == 0) {
            content.setText("");
        } else {
            StringBuffer sb = new StringBuffer();
            for (Person person : list) {
                sb.append(person.toString()).append("\n");
            }
            content.setText(sb.toString());
        }
    }

    /**
     * ServiceConnection
     */
    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            personManager = PersonManager.Stub.asInterface(iBinder);
            if (personManager != null) {
                try {
                    isSuccessConnection = true;
                    list = personManager.getPersons();
                    upDataTempView();
                    upDataContentView(list);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isSuccessConnection = false;
            upDataTempView();
            upDataContentView(null);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if (isSuccessConnection&&sc != null) {
            unbindService(sc);
        }
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
