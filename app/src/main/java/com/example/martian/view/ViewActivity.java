package com.example.martian.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.martian.R;
import com.example.martian.base.IntentKey;

/**
 * @author martian on 2017/4/18.
 */
@Route(path = IntentKey.AROUTER_VIEW)
public class ViewActivity  extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view);
    iniView();
  }

  //private void iniData() {
  //  str = getIntent().getStringExtra(IntentKey.AROUTER_BUNDLE_STRING);
  //}

  private void iniView() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });
    //TextView textView = (TextView) findViewById(R.id.textView);
    //textView.setText(str);
  }

}
