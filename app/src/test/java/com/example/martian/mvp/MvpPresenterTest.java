package com.example.martian.mvp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Created by yangpei on 2016/11/28.
 */
public class MvpPresenterTest {
    private IMvpActivity iMvpActivity;
    private MvpPresenter mp;
    private User user;

    @Before
    public void setUp(){
        iMvpActivity = Mockito.mock(IMvpActivity.class);
        user = Mockito.mock(User.class);
         mp = new MvpPresenter(iMvpActivity,user);

    }
    @Test
    public void login() throws Exception {

        mp.login("user","name");
        // Mock verify方法(验证方法调用)
//        Mockito.verify(iMvpActivity).showProgress();

        // Mock times方法(调用次数)
//        Mockito.verify(iMvpActivity,Mockito.times(1)).showProgress();
//        Mockito.verify(iMvpActivity,Mockito.atLeast(1)).showProgress();
//        Mockito.verify(iMvpActivity,Mockito.atMost(1)).showProgress();
//        Mockito.verify(iMvpActivity,Mockito.atLeastOnce()).showProgress();

        //any方法，来表示任何的参数都行
//        Mockito.verify(user).checkUserValidity(anyString(),anyString());

        // 指定mock对象的某个方法(返回特定的值)
//        Mockito.when(user.checkUserValidity(anyString(),anyString())).thenReturn(0);

        //指定mock对象的某个方法(执行特定的动作)
//        Mockito.doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                Object[] arguments =  invocation.getArguments();
//                String password = (String) arguments[2];
//                password = "";
//                return password;
//            }
//        }).when(user).checkUserValidity(anyString(),anyString());

        //ArgumentCaptor 参数捕获器
//        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
//        captor.capture()
//        captor.getValue()
    }

}