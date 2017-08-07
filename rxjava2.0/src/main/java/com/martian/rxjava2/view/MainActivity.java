package com.martian.rxjava2.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.alibaba.android.arouter.launcher.ARouter;
import com.martian.rxjava2.R;
import com.martian.rxjava2.adapter.HomeAdapter;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private String[] list_data;
  private String[] list_arouter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    iniData();
    iniView();
  }

  private void iniData() {
    list_data = getResources().getStringArray(R.array.list_data);
    list_arouter = getResources().getStringArray(R.array.list_arouter);
  }

  private void iniView() {
    RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recycler);
    recyclerview.setLayoutManager(new LinearLayoutManager(this));
    recyclerview.setItemAnimator(new DefaultItemAnimator());
    HomeAdapter adapter = new HomeAdapter(list_data);
    adapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
      @Override public void onItemClick(View view, int position) {
        ARouter.getInstance().build(list_arouter[position]).navigation();
      }
    });
    recyclerview.setAdapter(adapter);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
