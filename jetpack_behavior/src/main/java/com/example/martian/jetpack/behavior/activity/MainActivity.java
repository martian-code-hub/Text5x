package com.example.martian.jetpack.behavior.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.martian.jetpack.behavior.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void downloadManager(View view) {
        startActivity(new Intent(this, DownloadManagerActivity.class));
    }
}