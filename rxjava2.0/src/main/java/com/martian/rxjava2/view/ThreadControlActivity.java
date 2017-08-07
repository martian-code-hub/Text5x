package com.martian.rxjava2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.martian.rxjava2.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@Route(path = "/view/thread_control")
public class ThreadControlActivity extends AppCompatActivity {
  @BindView(R.id.ac_em_content_tv) TextView acEmContentTv;
  @BindView(R.id.ac_em_button_one) Button acEmButtonOne;
  @BindView(R.id.ac_em_button_two) Button acEmButtonTwo;

  private static final String TAG = "---";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_thread_control);
    ButterKnife.bind(this);
    iniView();
    iniData();
  }

  private void iniView() {
    acEmContentTv.setText("简单的来说, subscribeOn() 指定的是上游发送事件的线程, observeOn() 指定的是下游接收事件的线程.\n"
        + "\n"
        + "多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.\n"
        + "\n"
        + "多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.\n\n"
        + "在RxJava中, 已经内置了很多线程选项供我们选择, 例如有\n"
        + "\n"
        + "Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作\n"
        + "Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作\n"
        + "Schedulers.newThread() 代表一个常规的新线程\n"
        + "AndroidSchedulers.mainThread() 代表Android的主线程\n\n\n"
        + "看似很完美, 但我们忽略了一点, 如果在请求的过程中Activity已经退出了, 这个时候如果回到主线程去更新UI, 那么APP肯定就崩溃了, 怎么办呢, 上一节我们说到了Disposable , 说它是个开关, 调用它的dispose()方法时就会切断水管, 使得下游收不到事件, 既然收不到事件, 那么也就不会再去更新UI了. 因此我们可以在Activity中将这个Disposable 保存起来, 当Activity退出时, 切断它即可.\n"
        + "那如果有多个Disposable 该怎么办呢, RxJava中已经内置了一个容器CompositeDisposable, 每当我们得到一个Disposable时就调用CompositeDisposable.add()将它添加到容器中, 在退出的时候, 调用CompositeDisposable.clear() 即可切断所有的水管.\n"
        + "");
  }

  private void iniData() {

  }

  @OnClick({R.id.ac_em_button_one, R.id.ac_em_button_two})
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.ac_em_button_one:
        methodOne();
        break;
      case R.id.ac_em_button_two:
        methodTwo();
        break;
    }
  }

  private void methodOne() {
    Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override public void subscribe(@NonNull ObservableEmitter<Integer> emitter)
          throws Exception {
        Log.d(TAG, "subscribe thread is: " + Thread.currentThread().getName());
        emitter.onNext(1);
        emitter.onComplete();
      }
    }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Integer>() {
          @Override public void accept(@NonNull Integer integer) throws Exception {
            Log.d(TAG, "accept thread is :" + Thread.currentThread().getName());
            Log.d(TAG, "onNext: " + integer);
          }
        });
  }

  private void methodTwo() {
  Observable.create(new ObservableOnSubscribe<Integer>() {
    @Override public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
      Log.d(TAG, "subscribe thread is: " + Thread.currentThread().getName());
      emitter.onNext(1);
      emitter.onComplete();
    }
  }).subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread())
      .doOnNext(new Consumer<Integer>() {
        @Override public void accept(@NonNull Integer integer) throws Exception {
          Log.d(TAG, "accept thread is :" + Thread.currentThread().getName());
          Log.d(TAG, "onNext: " + integer);
        }
      }).observeOn(Schedulers.newThread())
  .doOnNext(new Consumer<Integer>() {
    @Override public void accept(@NonNull Integer integer) throws Exception {
      Log.d(TAG, "accept thread is :" + Thread.currentThread().getName());
      Log.d(TAG, "onNext: " + integer);
    }
  });
  }
}
