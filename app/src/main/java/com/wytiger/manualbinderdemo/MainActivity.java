package com.wytiger.manualbinderdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 手写binder实现进程间通信
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
