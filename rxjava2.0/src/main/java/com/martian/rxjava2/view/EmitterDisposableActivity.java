package com.martian.rxjava2.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

@Route(path = "/view/emitter_disposable")
public class EmitterDisposableActivity extends AppCompatActivity {


  @BindView(R.id.ac_em_content_tv) TextView acEmContentTv;
  @BindView(R.id.ac_em_button_one) Button acEmButtonOne;
  @BindView(R.id.ac_em_button_two) Button acEmButtonTwo;

  private static final String TAG = "---";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_emitter_disposable);
    ButterKnife.bind(this);
    iniView();
    iniData();
  }

  private void iniView() {
    acEmContentTv.setText(
        "ObservableEmitter： Emitter是发射器的意思，那就很好猜了，这个就是用来发出事件的，它可以发出三种类型的事件，通过调用emitter的onNext(T value)、onComplete()和onError(Throwable error)就可以分别发出next事件、complete事件和error事件。\n上游可以发送无限个onNext, 下游也可以接收无限个onNext.\n"
            + "当上游发送了一个onComplete后, 上游onComplete之后的事件将会继续发送, 而下游收到onComplete事件之后将不再继续接收事件.\n"
            + "当上游发送了一个onError后, 上游onError之后的事件将继续发送, 而下游收到onError事件之后将不再继续接收事件.\n"
            + "上游可以不发送onComplete或onError.\n"
            + "最为关键的是onComplete和onError必须唯一并且互斥, 即不能发多个onComplete, 也不能发多个onError, 也不能先发一个onComplete, 然后再发一个onError, 反之亦然\n"
            + "注: 关于onComplete和onError唯一并且互斥这一点, 是需要自行在代码中进行控制, 如果你的代码逻辑中违背了这个规则, 并不一定会导致程序崩溃. 比如发送多个onComplete是可以正常运行的, 依然是收到第一个onComplete就不再接收了, 但若是发送多个onError, 则收到第二个onError事件会导致程序会崩溃.\n"
            + "\n\n\n"
            + "Disposable, 这个单词的字面意思是一次性用品,用完即可丢弃的. 那么在RxJava中怎么去理解它呢, 对应于上面的水管的例子, 我们可以把它理解成两根管道之间的一个机关, 当调用它的dispose()方法时, 它就会将两根管道切断, 从而导致下游收不到事件.\n"
            + "注意: 调用dispose()并不会导致上游不再继续发送事件, 上游会继续发送剩余的事件.\n"
            + "subscribe()有多个重载的方法:\n"
            + "不带任何参数的subscribe() 表示下游不关心任何事件,你上游尽管发你的数据去吧, 老子可不管你发什么.\n"
            + "带有一个Consumer参数的方法表示下游只关心onNext事件, 其他的事件我假装没看见, 因此我们如果只需要onNext事件可以这么写:\n");
  }

  private void iniData() {
  }

  @OnClick({R.id.ac_em_button_one,R.id.ac_em_button_two})
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
        emitter.onNext(1);
        emitter.onNext(2);
        emitter.onNext(3);
        emitter.onComplete();
      }
    }).subscribe(new Observer<Integer>() {
      @Override public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe");
      }

      @Override public void onNext(Integer integer) {
        Log.d(TAG, "onNext:" + integer);
      }

      @Override public void onError(Throwable e) {
        Log.d(TAG, "onError");
      }

      @Override public void onComplete() {
        Log.d(TAG, "onComplete");
      }
    });
  }
  private void methodTwo() {
  Observable.create(new ObservableOnSubscribe<Integer>() {
    @Override public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
      Log.d(TAG, "emit 1");
      emitter.onNext(1);
      Log.d(TAG, "emit 2");
      emitter.onNext(2);
      Log.d(TAG, "emit 3");
      emitter.onNext(3);
      Log.d(TAG, "emit 1");
      emitter.onComplete();
      Log.d(TAG, "emit 4");
      emitter.onNext(4);
    }
  }).subscribe(new Observer<Integer>() {
    Disposable disposable;
    @Override public void onSubscribe(Disposable d) {
      Log.d(TAG, "onSubscribe");
      disposable = d;
    }

    @Override public void onNext(Integer integer) {
      if(integer == 4){
        disposable.dispose();
      }
      Log.d(TAG, "onNext:"+integer);
    }

    @Override public void onError(Throwable e) {
      Log.d(TAG, "onError");
    }

    @Override public void onComplete() {
      Log.d(TAG, "onComplete");
    }
  });

  }
}
