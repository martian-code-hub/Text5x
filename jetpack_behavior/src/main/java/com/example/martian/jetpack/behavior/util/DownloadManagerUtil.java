package com.example.martian.jetpack.behavior.util;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

import java.io.File;

/**
 * Create by：Martian
 * on 2021/8/18
 */
public class DownloadManagerUtil {
    private Context mContext;

    public DownloadManagerUtil(Context context) {
        mContext = context;
    }
//    //简单的下载功能
//    public long download(String url, String title, String desc) {
//        Uri uri = Uri.parse(url);
//        DownloadManager.Request req = new DownloadManager.Request(uri);
//        //设置WIFI下进行更新
//        req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
//        //下载中和下载完后都显示通知栏
//        req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        //使用系统默认的下载路径 此处为应用内 /android/data/packages ,所以兼容7.0
//        req.setDestinationInExternalFilesDir(mContext, Environment.DIRECTORY_DOWNLOADS, title);
//        //通知栏标题
//        req.setTitle(title);
//        //通知栏描述信息
//        req.setDescription(desc);
//        //设置类型为.apk
//        req.setMimeType("application/vnd.android.package-archive");
//        //获取下载任务ID
//        DownloadManager dm = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
//        return dm.enqueue(req);
//    }


    /**
     * 比较实用的升级版下载功能
     *
     * @param url   下载地址
     * @param title 文件名字
     * @param desc  文件路径
     */
    public long download(String url, String title, String desc, String fileName) {
        DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        long download_id;

        //以下两行代码可以让下载的apk文件被直接安装而不用使用Fileprovider,系统7.0或者以上才启动。
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                StrictMode.VmPolicy.Builder localBuilder = new StrictMode.VmPolicy.Builder();
//                StrictMode.setVmPolicy(localBuilder.build());
//            }

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        // 仅允许在WIFI连接情况下下载
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setAllowedOverRoaming(true);//是否允许网络漫游
        // 通知栏中将出现的内容
        request.setTitle(title);
        request.setDescription(desc);

        //7.0以上的系统适配
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                request.setRequiresDeviceIdle(false);
//                request.setRequiresCharging(false);
//            }
        request.setVisibleInDownloadsUi(true);
        //制定下载的文件类型为APK
        request.setMimeType("application/octet-stream;charset=UTF-8");
//        request.setMimeType("application/vnd.android.package-archive");

        // 下载过程和下载完成后通知栏有通知消息。
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE );

        // 指定下载文件地址，使用这个指定地址可不需要WRITE_EXTERNAL_STORAGE权限。
        request.setDestinationUri(Uri.fromFile(new File(mContext.getExternalFilesDir("") + "/" + fileName)));

        //大于11版本手机允许扫描
        //表示允许MediaScanner扫描到这个文件，默认不允许。
        request.allowScanningByMediaScanner();
//            Activity activity = PublicUtile.getInstance().getmActivity();
//            if (activity != null) {
//        mContext.startActivity(new android.content.Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));//启动系统下载界面
//            }
            // 将下载请求放入队列
            download_id = downloadManager.enqueue(request);
//                commitAdEvent(adInfoUtils.list_down_starts);
        return download_id;
    }

    /**
     * 下载前先移除前一个任务，防止重复下载
     *
     * @param downloadId
     */
    public void clearCurrentTask(long downloadId) {
        DownloadManager dm = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        try {
            dm.remove(downloadId);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }
}

