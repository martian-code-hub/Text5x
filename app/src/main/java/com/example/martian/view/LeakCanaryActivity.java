package com.example.martian.view;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import com.example.martian.R;
import com.example.martian.util.LeakCanaryUtil;

/**
 * Created by Administrator on 2016/7/6.
 */
public class LeakCanaryActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private String mTitle;

    private Toolbar toolbar;

    private Handler myHandler = new Handler();

//    private ImageView iv;

    private static Drawable drawable = null;

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
        setContentView(R.layout.activity_leakcanary);
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
//
//        iv = (ImageView) findViewById(R.id.activity_leakcanary_iv);

//        if(drawable == null){
//            drawable = getResources().getDrawable(R.mipmap.resize);
//        }
//        iv.setImageDrawable(drawable);
        exampleTwo();
    }

    /**
     * 非静态内部类 （线程造成的内存泄漏）
     */
    private void exampleOne() {
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 30 * 1000);
        finish();
    }

    /**
     * 持有context引用的单例模式
     */
    private void exampleTwo() {
        LeakCanaryUtil.getInstance(this);
    }

    /**
     * 非静态内部类 Handler
     */
    private void exampleThree() {
        //Handler 非静态内部类
    }

//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//        }
//    };

    /**
     * 非静态内部类（线程造成的内存泄漏）
     */
    private void exampleFour() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 对于使用了BraodcastReceiver，ContentObserver，File，Cursor，Stream，Bitmap等资源的使用，
     * 应该在Activity销毁时及时关闭或者注销，否则这些资源将不会被回收，造成内存泄漏。
     */
    private void exampleFive() {

    }
/**
 *  文章链接 https://www.liaohuqiu.net/cn/posts/leak-canary-read-me/
 */
    /**
     * 工作机制

     RefWatcher.watch() 创建一个 KeyedWeakReference 到要被监控的对象。

     然后在后台线程检查引用是否被清除，如果没有，调用GC。

     如果引用还是未被清除，把 heap 内存 dump 到 APP 对应的文件系统中的一个 .hprof 文件中。

     在另外一个进程中的 HeapAnalyzerService 有一个 HeapAnalyzer 使用HAHA 解析这个文件。

     得益于唯一的 reference key, HeapAnalyzer 找到 KeyedWeakReference，定位内存泄露。

     HeapAnalyzer 计算 到 GC roots 的最短强引用路径，并确定是否是泄露。如果是的话，建立导致泄露的引用链。

     引用链传递到 APP 进程中的 DisplayLeakService， 并以通知的形式展示出来。
     */


    /**
     * leak trace 之外

     有时，leak trace 不够，你需要通过 MAT 或者 YourKit 深挖 dump 文件。

     通过以下方法，你能找到问题所在：

     查找所有的 com.squareup.leakcanary.KeyedWeakReference 实例。
     检查 key 字段
     Find the KeyedWeakReference that has a key field equal to the reference key reported by LeakCanary.
     找到 key 和 和 logcat 输出的 key 值一样的 KeyedWeakReference。
     referent 字段对应的就是泄露的对象。
     剩下的，就是动手修复了。最好是检查到 GC root 的最短强引用路径开始。
     */

    /**
     * UI 样式

     DisplayLeakActivity 有一个默认的图标和标签，你只要在你自己的 APP 资源中，替换以下资源就可。

     res/
     drawable-hdpi/
     __leak_canary_icon.png
     drawable-mdpi/
     __leak_canary_icon.png
     drawable-xhdpi/
     __leak_canary_icon.png
     drawable-xxhdpi/
     __leak_canary_icon.png
     drawable-xxxhdpi/
     __leak_canary_icon.png
     //     <?xml version="1.0" encoding="utf-8"?>
     <resources>
     <string name="__leak_canary_display_activity_label">MyLeaks</string>
     </resources>
     */

    /**
     * 保存 leak trace

     DisplayLeakActivity saves up to 7 heap dumps & leak traces in the app directory. You can change that number by providing R.integer.__leak_canary_max_stored_leaks in your app:

     在 APP 的目录中，DisplayLeakActivity 保存了 7 个 dump 文件和 leak trace。你可以在你的 APP 中，定义 R.integer.__leak_canary_max_stored_leaks 来覆盖类库的默认值。

     //     <?xml version="1.0" encoding="utf-8"?>
     <resources>
     <integer name="__leak_canary_max_stored_leaks">20</integer>
     </resources>
     */

    /**
     * 上传 leak trace 到服务器
     * <p>
     * 你可以改变处理完成的默认行为，将 leak trace 和 heap dump 上传到你的服务器以便统计分析。
     * <p>
     * 创建一个 LeakUploadService， 最简单的就是继承 DisplayLeakService ：
     * <p>
     * public class LeakUploadService extends DisplayLeakService {
     *
     * @Override protected void afterDefaultHandling(HeapDump heapDump, AnalysisResult result, String leakInfo) {
     * if (!result.leakFound || result.excludedLeak) {
     * return;
     * }
     * myServer.uploadLeakBlocking(heapDump.heapDumpFile, leakInfo);
     * }
     * }
     * 请确认 release 版本 使用 RefWatcher.DISABLED：
     * <p>
     * public class ExampleApplication extends Application {
     * <p>
     * public static RefWatcher getRefWatcher(Context context) {
     * ExampleApplication application = (ExampleApplication) context.getApplicationContext();
     * return application.refWatcher;
     * }
     * <p>
     * private RefWatcher refWatcher;
     * @Override public void onCreate() {
     * super.onCreate();
     * refWatcher = installLeakCanary();
     * }
     * <p>
     * protected RefWatcher installLeakCanary() {
     * return RefWatcher.DISABLED;
     * }
     * }
     * 自定义 RefWatcher：
     * <p>
     * public class DebugExampleApplication extends ExampleApplication {
     * protected RefWatcher installLeakCanary() {
     * return LeakCanary.install(app, LeakUploadService.class);
     * }
     * }
     * 别忘了注册 service：
     * <p>
     * <?xml version="1.0" encoding="utf-8"?>
     * <manifest xmlns:android="http://schemas.android.com/apk/res/android"
     * xmlns:tools="http://schemas.android.com/tools"
     * >
     * <application android:name="com.example.DebugExampleApplication">
     * <service android:name="com.example.LeakUploadService" />
     * </application>
     * </manifest>
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        MyApplication.getRefWatcher(this).watch(this);
    }

}
