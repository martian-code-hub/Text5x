package com.example.martian.view;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.martian.text5x.R;


/**
 * Created by yangpei on 2016/11/16.
 */
public class RxJavaActivityTest extends ActivityUnitTestCase<RxJavaActivity> {

    private Intent intent;

    public RxJavaActivityTest() {
        super(RxJavaActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        intent = new Intent(getInstrumentation().getTargetContext(), RxJavaActivity.class);
    }

    @MediumTest
    public void testNull() {
        startActivity(intent, null, null);
        Toolbar tb = (Toolbar) getActivity().findViewById(R.id.toolbar_toolbar);
        assertNotNull(tb);
    }

    @SmallTest
    public void testObserver() {
        startActivity(intent, null, null);
        RxJavaActivity activity = getActivity();
        Button bt = (Button) activity.findViewById(R.id.activity_rxjava_bt_1);
        bt.performClick();
        assertNotNull(activity.observable);

    }

}