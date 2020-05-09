package com.wytiger.manualbinderdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import com.wytiger.noaidl.server.MyServer;

/**
 * 手写binder实现进程间通信
 */
public class MainActivity extends AppCompatActivity {
    MyServer myServer;

    TextView tv_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService();

        tv_call = findViewById(R.id.tv_call);
        tv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int result = myServer.add(1, 1);
                    tv_call.setText(result + "");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void bindService() {
        Intent intent = new Intent("com.wytiger.noaidl.ACTION_MY_SERVER");
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myServer = (MyServer) MyServer.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                myServer = null;
            }
        }, Context.BIND_AUTO_CREATE);
    }
}
