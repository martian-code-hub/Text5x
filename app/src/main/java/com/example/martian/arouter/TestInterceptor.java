package com.example.martian.arouter;

import android.content.Context;
import android.os.Bundle;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.example.martian.base.IntentKey;

/**
 * @author martian on 2017/4/5.
 */
@Interceptor(priority = 666,name = "测试用拦截器")
public class TestInterceptor implements IInterceptor {
  @Override public void process(Postcard postcard, InterceptorCallback callback) {
    Bundle bundle = postcard.getExtras();
    String str = bundle.getString(IntentKey.AROUTER_BUNDLE_STRING);
    str += "，成功添加后缀";
    bundle.putString(IntentKey.AROUTER_BUNDLE_STRING,str);
    callback.onContinue(postcard.with(bundle));
  }

  @Override public void init(Context context) {

  }
}
