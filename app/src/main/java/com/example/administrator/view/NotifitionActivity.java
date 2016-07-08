package com.example.administrator.view;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.administrator.text5x.R;

/**
 * Created by Administrator on 2016/7/6.
 */
public class NotifitionActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private String mTitle;

    private Toolbar toolbar;



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

        setContentView(R.layout.activity_notifition);
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
    }

    public void click(View view){
        switch (view.getId()){
            case R.id.activity_notifition_basic:
                createBasicNotifition();
                break;
            case R.id.activity_notifition_collapsed:
                createCollapsedNotifition();
                break;
            case R.id.activity_notifition_headsup:
                createHeadsupNotifition();
                break;
        }
    }


    private void createBasicNotifition(){
        Notification.Builder builder = new Notification.Builder(this);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        PendingIntent pendingIntent =  PendingIntent.getActivity(this,0,intent,0);

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("My Notifition");
        builder.setContentText("this is content");
        builder.setSubText("this is sub content");


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }


    private void createCollapsedNotifition(){
        Notification.Builder builder = new Notification.Builder(this);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sina.com"));
        PendingIntent pendingIntent =  PendingIntent.getActivity(this,0,intent,0);

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("My Notifition");
//        builder.setContentText("this is content");
        builder.setSubText("this is sub content");

        RemoteViews expendView = new RemoteViews(getPackageName(),R.layout.notification_bigview);
        expendView.setTextViewText(R.id.notification_bigview_tv, "this is bigview content");
        RemoteViews remoteView = new RemoteViews(getPackageName(),R.layout.notification_remoteview);
        remoteView.setTextViewText(R.id.notification_remoteview_tv, "this is remoteview content");

        Notification notification =   builder.build();
        notification.bigContentView = expendView;
        notification.contentView = remoteView;

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,notification);
    }


    private void createHeadsupNotifition(){
        Notification.Builder builder = new Notification.Builder(this);

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(this,MainActivity.class);
        PendingIntent pendingIntent =  PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setPriority(Notification.PRIORITY_DEFAULT);
        builder.setCategory(Notification.CATEGORY_TRANSPORT);
//        builder.setContentIntent(pendingIntent);
//        builder.setAutoCancel(true);
//        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("My Notifition");
        builder.setContentText("this is content");
//        builder.setSubText("this is sub content");

        builder.setFullScreenIntent(pendingIntent,true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(2,builder.build());
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
