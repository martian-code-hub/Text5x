package com.example.martian.view;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.VideoView;
import com.example.martian.R;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.widget.MediaController;

/**
 * @author martian on 2017/8/7.
 */

public class VideoActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener,
    MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener,
    io.vov.vitamio.MediaPlayer.OnPreparedListener, io.vov.vitamio.MediaPlayer.OnErrorListener,
    io.vov.vitamio.MediaPlayer.OnCompletionListener {

  private String mTitle;

  private Toolbar toolbar;

  private VideoView vvVideo;

  private io.vov.vitamio.widget.VideoView vvVideoVitamio;

  private String  PATH_URL = "http://10.1.160.34:10800/hls/stream_1/stream_1_live.m3u8";

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
    setContentView(R.layout.activity_video);
    iniView();
    iniVideoView();
    iniVitamioVideoView();
  }

  private void iniView() {
    toolbar = (Toolbar) findViewById(R.id.toolbar_toolbar);
    toolbar.setBackgroundColor(this.getResources().getColor(R.color.colorPrimaryDark));
    toolbar.setTitle(mTitle);
    setSupportActionBar(toolbar);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
    if (!LibsChecker.checkVitamioLibs(this))
      return;
  }

  private void iniVideoView() {
    vvVideo = (VideoView) findViewById(R.id.vv_video);
    //网络地址
    vvVideo.setVideoURI(Uri.parse(PATH_URL));
    //开始播放
    vvVideo.start();
    //设置相关的监听
    vvVideo.setOnPreparedListener(this);
    vvVideo.setOnErrorListener(this);
    vvVideo.setOnCompletionListener(this);
  }

  private void iniVitamioVideoView() {
    vvVideoVitamio = (io.vov.vitamio.widget.VideoView) findViewById(R.id.vv_video_vitamio);
    vvVideoVitamio.setVideoURI(Uri.parse(PATH_URL)); //实例化控制器
    MediaController mMediaController = new MediaController(this); //绑定控制器
    vvVideoVitamio.setMediaController(mMediaController); //控制器显示9s后自动隐藏 mMediaController.show(9000); //设置播放画质 高画质
    vvVideoVitamio.setVideoQuality(io.vov.vitamio.MediaPlayer.VIDEOQUALITY_HIGH);
    //vvVideoVitamio.setVideoLayout(io.vov.vitamio.widget.VideoView.VIDEO_LAYOUT_STRETCH,0.25f); //取得焦点 mVideoView.requestFocus(); //设置相关的监听
    //vvVideoVitamio.setOnPreparedListener(this);
    //vvVideoVitamio.setOnErrorListener(this);
    //vvVideoVitamio.setOnCompletionListener(this);
    //vvVideoVitamio.start();
    //vvVideoVitamio.setVideoPath(PATH_URL);
    //vvVideoVitamio.setMediaController(new MediaController(this));
    vvVideoVitamio.requestFocus();

    vvVideoVitamio.setOnPreparedListener(new io.vov.vitamio.MediaPlayer.OnPreparedListener() {
      @Override public void onPrepared(io.vov.vitamio.MediaPlayer mp) {
        mp.setPlaybackSpeed(1.0f);
      }
    });
  }

  @Override public void onPrepared(MediaPlayer mp) {

  }

  @Override public boolean onError(MediaPlayer mp, int what, int extra) {
    return false;
  }

  @Override public void onCompletion(MediaPlayer mp) {

  }

  @Override public void onPrepared(io.vov.vitamio.MediaPlayer mp) {

  }

  @Override public boolean onError(io.vov.vitamio.MediaPlayer mp, int what, int extra) {
    return false;
  }

  @Override public void onCompletion(io.vov.vitamio.MediaPlayer mp) {

  }
}
