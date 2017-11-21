package com.example.martian.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.bumptech.glide.Glide;
import com.example.martian.R;
import com.example.martian.alicloudapi.Demo_数据服务_5_1_身份证识别;
import com.example.martian.bean.RequestParams;
import com.example.martian.util.DateUtil;
import com.example.martian.util.PathUtil;
import com.google.gson.Gson;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.util.Base64;

import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;

/**
 * @author martian on 2017/8/7.
 */

public class IDRecognitionActivity extends AppCompatActivity {

  private String mTitle;
  private Toolbar toolbar;
  @Bind(R.id.toolbar_toolbar) Toolbar toolbarToolbar;
  @Bind(R.id.tv_album) Button tvAlbum;
  @Bind(R.id.tv_camera) Button tvCamera;
  @Bind(R.id.tv_submit) Button tvSubmit;
  @Bind(R.id.cb_face) CheckBox cbFace;
  @Bind(R.id.iv_picture) ImageView ivPicture;
  @Bind(R.id.iv_picture_two) ImageView ivPictureTwo;
  @Bind(R.id.iv_content) TextView ivContent;

  private int maxSelectNumber = 1;
  private static final int REQUEST_CODE_CHOOSE_PHOTO = 0x1;
  private static final int REQUEST_CODE_OPEN_CAMERA = 0x2;

  private Uri imageUri;
  private String picturePath;
  private List<Uri> imageUris;
  private List<String> imagePahts = new ArrayList<>();

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
    setContentView(R.layout.activity_id_recognition);
    ButterKnife.bind(this);
    iniView();
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
  }

  @OnClick({R.id.tv_album, R.id.tv_camera, R.id.tv_submit})
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.tv_album:
        openAlbum();
        break;
      case R.id.tv_camera:
        openCamera();
        break;
      case R.id.tv_submit:
        if (checkData()) {
          requestIDRecognition(imagePahts);
        }
        break;
    }
  }

  private boolean checkData() {
    if (imagePahts.size() <= 0) {
      Toast.makeText(this, "请添加图片", Toast.LENGTH_SHORT).show();
      return false;
    }
    return true;
  }

  private void openAlbum() {
    Matisse.from(this)
        .choose(MimeType.allOf())
        .countable(true)
        .maxSelectable(maxSelectNumber)
        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
        .thumbnailScale(0.85f)
        .imageEngine(new GlideEngine())
        .forResult(REQUEST_CODE_CHOOSE_PHOTO);
  }

  private void openCamera() {
    String state = Environment.getExternalStorageState();
    if (state.equals(Environment.MEDIA_MOUNTED)) {
      File outputImage = new File(getExternalCacheDir(), DateUtil.getCurrentTime() + ".jpg");
      try {
        if (outputImage.exists()) {
          outputImage.delete();
        }
        outputImage.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (Build.VERSION.SDK_INT >= 24) {
        imageUri =
            FileProvider.getUriForFile(this, this.getPackageName() + ".fileprovider", outputImage);
        picturePath = outputImage.getAbsolutePath();
      } else {
        imageUri = Uri.fromFile(outputImage);
      }
      Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
      //授予URI的临时权限
      intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION);
      intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
      startActivityForResult(intent, REQUEST_CODE_OPEN_CAMERA);
    } else {
      Toast.makeText(this, "请插入SD卡", Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      switch (requestCode) {
        case REQUEST_CODE_CHOOSE_PHOTO:
          imageUris = Matisse.obtainResult(data);
          for (Uri uri : imageUris) {
            imagePahts.add(PathUtil.getPath(IDRecognitionActivity.this, uri));
          }
          showImage(imagePahts);
          break;
        case REQUEST_CODE_OPEN_CAMERA:
          clearImagePaths();
          if (Build.VERSION.SDK_INT >= 24) {
            imagePahts.add(picturePath);
          } else {
            imagePahts.add(PathUtil.getPath(IDRecognitionActivity.this, imageUri));
          }
          showImage(imagePahts);
          break;
        default:
          break;
      }
    }
  }

  private void clearImagePaths(){
    if(imagePahts.size()>=maxSelectNumber){
      imagePahts.clear();
    }
  }

  private void showImage(List<String> imagePahts) {
    if (imagePahts != null && imagePahts.size() > 0) {
      Glide.with(this).load(imagePahts.get(0)).into(ivPicture);

      if(imagePahts.size()>1){
        Glide.with(this).load(imagePahts.get(1)).into(ivPictureTwo);
      }
    }
  }

  private void requestIDRecognition(List<String> imagePahts) {
    if (imagePahts != null && imagePahts.size() > 0) {
      List<RequestParams.InputsBean> list = new ArrayList<>();
      RequestParams params = new RequestParams();
      list.add(getInputsBean(imagePahts.get(0),"face"));
      if(imagePahts.size()>1){
        list.add(getInputsBean(imagePahts.get(1),"back"));
      }
      params.setInputs(list);
      String data = new Gson().toJson(params);
      Demo_数据服务_5_1_身份证识别.印刷文字识别_身份证识别HttpTest(data, new ApiCallback() {
        @Override
        public void onFailure(ApiRequest request, Exception e) {
          e.printStackTrace();
          Message msg = handler.obtainMessage();
          msg.what = 2;
          msg.obj = e.toString();
          handler.sendMessage(msg);
        }

        @Override
        public void onResponse(ApiRequest request, ApiResponse response) {
          try {
            final String content = Demo_数据服务_5_1_身份证识别.getResultString(response);
            Message msg = handler.obtainMessage();
            msg.what = 1;
            msg.obj = content;
            handler.sendMessage(msg);
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      });
    }
  }

  private RequestParams.InputsBean getInputsBean(String path,String side){
    RequestParams.InputsBean inputsBean = new RequestParams.InputsBean();
    inputsBean.setImage(getImageBean(path));
    inputsBean.setConfigure(getConfigureBean(side));
    return inputsBean;
  }

  private RequestParams.InputsBean.ImageBean getImageBean(String path) {
    RequestParams.InputsBean.ImageBean imageBean = new RequestParams.InputsBean.ImageBean();
    imageBean.setDataType(50);
    imageBean.setDataValue(getBase64(path));
    return imageBean;
  }

  private RequestParams.InputsBean.ConfigureBean getConfigureBean(String side) {
    RequestParams.InputsBean.ConfigureBean configure = new RequestParams.InputsBean.ConfigureBean();
    configure.setDataType(50);
    configure.setDataValue("{\"side\":\"" + side + "\"}");
    return configure;
  }

  private String getBase64(String path) {
    String imgBase64 = "";
    try {
      File file = new File(path);
      byte[] content = new byte[(int) file.length()];
      FileInputStream finputstream = null;
      finputstream = new FileInputStream(file);
      finputstream.read(content);
      finputstream.close();
      imgBase64 = new String(Base64.encodeToString(content, Base64.NO_WRAP));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return imgBase64;
  }

  private void showUserInfo(String content){
    Log.d("---","content:"+content);
    Toast.makeText(IDRecognitionActivity.this, "获取成功", Toast.LENGTH_SHORT).show();
    ivContent.setText(content);
  }

  private void showFail(String content){
    Log.d("---","e:"+content);
    Toast.makeText(IDRecognitionActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
  }

  private Handler handler = new Handler(){

    @Override public void handleMessage(Message msg) {
      super.handleMessage(msg);
       switch (msg.what){
         case 1:
           showUserInfo((String)msg.obj);
           break;
         case 2:
           showFail((String)msg.obj);
           break;
       }
    }
  };
}
