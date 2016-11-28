package com.example.martian.view;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.martian.bean.Person;
import com.example.martian.R;
import com.jakewharton.rxbinding.view.RxView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2016/7/6.
 */
@SuppressWarnings("unused")
public class RxJavaActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private String mTitle;

    private Toolbar toolbar;
    private ImageView iv;
    public Observable observable;
    public Observer observer;


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
        setContentView(R.layout.activity_rxjava);
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

        iv = (ImageView) findViewById(R.id.activity_rxjava_iv);
        Button button = (Button) findViewById(R.id.activity_rxjava_bt_6);
        RxView.clicks(button).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {

            }
        });
    }

    public void clickMothed(View view) {
        switch (view.getId()) {
            case R.id.activity_rxjava_bt_1:
                createObserver();
                break;
            case R.id.activity_rxjava_bt_2:
                createObservable();
                break;
            case R.id.activity_rxjava_bt_3:
                subscribe();
                break;
            case R.id.activity_rxjava_bt_4:
                test();
                break;
            case R.id.activity_rxjava_bt_5:
                scheduler();
                break;
            case R.id.activity_rxjava_bt_6:
                map();
                break;


        }
    }

    /**
     * 创建Observer
     */
    private void createObserver() {
        Logger.d("createObserver");
        observer = new Observer() {
            @Override
            public void onCompleted() {
                Logger.d("Observer--onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Logger.d("Observer--onError");
            }

            @Override
            public void onNext(Object o) {
                Logger.d("Observer--onNext:" + o.toString());
            }
        };


//        Subscriber subscriber = new Subscriber() {
//            @Override
//            public void onSubscribe(Subscription s) {
//                Logger.d("Subscriber--onSubscribe");
//            }
//
//            @Override
//            public void onNext(Object o) {
//                Logger.d("Subscriber--onNext");
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                Logger.d("Subscriber--onError");
//            }
//
//            @Override
//            public void onComplete() {
//                Logger.d("Subscriber--onComplete");
//
//            }
//        };
    }

    /**
     * 创建Obserable
     */
    private void createObservable() {
        Logger.d("createObservable");
        observable = Observable.just("hello", "world", "rxjava");
    }

    /**
     * 订阅
     */
    private void subscribe() {
        observable.subscribe(observer);

        //不完整的回调
//        Consumer<String>  comsumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Logger.d("comsumer:"+s);
//            }
//        };
//        observable.subscribe(comsumer);
    }

    /**
     * 测试
     */
    private void test() {
//        String[] datas = {"hello", "world", "rxjava"};
//
//        Observable.from(datas).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Logger.d("test:" + s);
//            }
//        });
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(R.mipmap.positiveenergy_top);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
                ;
            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {
                Logger.d("test:onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(RxJavaActivity.this, "image error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Drawable drawable) {
                iv.setImageDrawable(drawable);
                Logger.d("test:onNext");
            }
        });
    }

    /**
     * Scheduler
     */
    private void scheduler() {
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(R.mipmap.positiveenergy_top);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {
                        Logger.d("scheduler:onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxJavaActivity.this, "image error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        iv.setImageDrawable(drawable);
                        Logger.d("scheduler:onNext");
                    }
                });
    }

    /**
     * map
     */
    private void map() {
      //一转换一
//    Observable.just(createPerson(0)).map(new Func1<Person, String>() {
//        @Override
//        public String call(Person persons) {
//            return persons.getAge();
//        }
//    }).subscribe(new Action1<String>() {
//        @Override
//        public void call(String s) {
//            Logger.d("person-age:"+s);
//        }
//    });
       //一转换一（循环）
//        Observable.from(createPersons()).map(new Func1<Person, String>() {
//            @Override
//            public String call(Person person) {
//                return person.getName();
//            }
//        }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Logger.d("person-name:" + s);
//            }
//        });
        //一转换多（循环）
        Observable.from(createPersons()).flatMap(new Func1<Person, Observable<String>>() {
            @Override
            public Observable<String> call(Person person) {
                return Observable.from(person.getRoles());
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Logger.d("person-roles:" + s);
            }
        });
    }

    private Person createPerson(int index) {
        Person person = new Person();
        person.setName("rxjava" + index);
        person.setAge(String.valueOf(index));
        person.setSex((index % 2) == 0 ? "boy" : "girl");
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < index; i++) {
            list.add("roles:" + index);
        }
        person.setRoles(list);
        return person;
    }

    private ArrayList<Person> createPersons() {
        ArrayList<Person> list = new ArrayList<Person>();
        for (int i = 0; i < 5; i++) {
            list.add(createPerson(i));
        }
        return list;
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
