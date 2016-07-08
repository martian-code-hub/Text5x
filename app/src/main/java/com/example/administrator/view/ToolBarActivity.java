package com.example.administrator.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.example.administrator.fragment.DrawerContentFragment;
import com.example.administrator.fragment.DrawerLeftFragment;
import com.example.administrator.text5x.R;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class ToolBarActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private String[] menus ;
    private String mTitle;

    private DrawerLeftFragment leftFragment;
    private DrawerContentFragment contentFragment;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    private static final String KEY_TITLLE = "key_title";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        int flag = getIntent().getExtras().getInt("flag");
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
        setContentView(R.layout.activity_toolbar);
        iniView();
        restoreTitle(savedInstanceState);
        iniFragment();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void iniFragment() {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        contentFragment = (DrawerContentFragment) fragmentManager.findFragmentByTag(mTitle);
        if (contentFragment == null) {
            contentFragment = DrawerContentFragment.newInstance(mTitle);
            fragmentManager.beginTransaction().add(R.id.id_content_container, contentFragment, mTitle).commit();
        }

        leftFragment = (DrawerLeftFragment) fragmentManager.findFragmentById(R.id.id_left_menu_container);
        if (leftFragment == null) {
            leftFragment = new DrawerLeftFragment();
            fragmentManager.beginTransaction().add(R.id.id_left_menu_container, leftFragment).commit();
        }

        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {

            for (Fragment fragment : fragments) {
                if (fragment == contentFragment || fragment == leftFragment) continue;
                fragmentManager.beginTransaction().hide(fragment).commit();
            }
        }

        leftFragment.setOnMenuItemSelectedListener(new DrawerLeftFragment.OnMenuItemSelectedListener() {
            @Override
            public void menuItemSelected(String title) {

                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                DrawerContentFragment fragment = (DrawerContentFragment) fm.findFragmentByTag(title);

                if (fragment == contentFragment) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    return;
                }
                android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
                transaction.hide(contentFragment);

                if (fragment == null) {
                    fragment = DrawerContentFragment.newInstance(title);
                    transaction.add(R.id.id_content_container, fragment, title);
                } else {
                    transaction.show(fragment);
                }
                transaction.commit();

                contentFragment = fragment;
                mTitle = title;
                toolbar.setTitle(mTitle);
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
    }

    private void restoreTitle(Bundle savedInstanceState) {
        if (savedInstanceState != null)
            mTitle = savedInstanceState.getString(KEY_TITLLE);


        toolbar.setTitle(mTitle);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_TITLLE, mTitle);
    }



    private void iniView() {
        menus = getResources().getStringArray(R.array.drawer_left);
        toolbar = (Toolbar) findViewById(R.id.toolbar_toolbar);
        toolbar.setBackgroundColor(this.getResources().getColor(R.color.colorPrimaryDark));
//        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        mTitle = menus[0];
        toolbar.setTitle(mTitle);
//        toolbar.setSubtitle("toolbar");
//        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);

//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return false;
            }
        });
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.v7.app.ActionBarDrawerToggle abdt = new android.support.v7.app.ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_description, R.string.drawer_description_format);
        abdt.syncState();
        drawerLayout.setDrawerListener(abdt);
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
