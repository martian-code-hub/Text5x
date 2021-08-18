package com.example.martian.jetpack.behavior.activity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.martian.jetpack.behavior.R;
import com.example.martian.jetpack.behavior.util.DownloadManagerUtil;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadManagerActivity extends AppCompatActivity {

    private String url = "http://apk-cdn.zhangxinhulian.com/com.zxhl.weather-guanwang-1.0.6_106_jiagu.apk";
    private String fileName = "OAPlus.apk";
    private DownloadManagerUtil downloadManagerUtil;
    private long downloadId = 0;
    private boolean isRegisterReceiver;
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_manager);
        initView();
        boolean result = isDownloadManagerAvailable(this);
        Log.e("DownloadManager", "是否可以使用DownloadManager--result:"+result);
        initData();
    }

    private void initView() {
        editText = findViewById(R.id.edit_text);
        editText.setText(url);
    }

    private void initData() {
        downloadManagerUtil = new DownloadManagerUtil(this);
        setReceiver();
    }

    /**
     * @param context used to check the device version and DownloadManager information
     * @return true if the download manager is available
     */
    public static boolean isDownloadManagerAvailable(Context context) {
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setClassName("com.android.providers.downloads.ui", "com.android.providers.downloads.ui.DownloadList");
            List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            return list.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void download(View view) {
        fixedThreadPool.execute(() -> {
            if (downloadId != 0) {
                downloadManagerUtil.clearCurrentTask(downloadId);
            }
            downloadId = downloadManagerUtil.download(getUrl(), getString(R.string.app_name), "下载中",fileName);
        });
    }

    public void cancel(View view) {
        if (downloadId != 0) {
            downloadManagerUtil.clearCurrentTask(downloadId);
        }
    }

    private String getUrl(){
        if(editText!=null){
            return editText.getText().toString().trim();
        }
        return "";
    }

    /**
     * 注册下载成功的广播监听
     */
    private void setReceiver() {
        if (!isRegisterReceiver) {
            DownloadReceiver receiver = new DownloadReceiver();
            IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
            intentFilter.addAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
            this.registerReceiver(receiver, intentFilter);
            isRegisterReceiver = true;
        }
    }

    /**
     * 下载成功广播类
     */
    public class DownloadReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("DownloadManager", "DownloadReceiver--Action:"+intent.getAction());
            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                Log.e("DownloadManager", "DownloadReceiver--id:"+id);
//                installApk(context, id, Environment.getExternalStorageDirectory() +"/"+  fileName);
            } else if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
                //处理 如果还未完成下载，用户点击Notification ，跳转到下载中心
                Intent viewDownloadIntent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
                viewDownloadIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(viewDownloadIntent);
            }
        }

        /**
         * 启动安装
         *
         * @param context
         * @param downloadApkId
         * @param apkPath
         */
        private void installApk(Context context, long downloadApkId, String apkPath) {
            DownloadManager dManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Intent install = new Intent(Intent.ACTION_VIEW);
            Uri downloadFileUri = dManager.getUriForDownloadedFile(downloadApkId);
            if (downloadFileUri != null) {
                Log.e("DownloadManager", downloadFileUri.toString());
                install.setDataAndType(Uri.parse("file://" + apkPath), "application/vnd.android.package-archive");
                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(install);
            } else {
                Log.e("DownloadManager", "download error");
            }
        }
    }
}