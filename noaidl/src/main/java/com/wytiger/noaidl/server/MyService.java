package com.wytiger.noaidl.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;


/**
 * desc:
 *
 * @author wuyong_cd
 */
public class MyService extends Service {
    MyServer myServer;

    @Override
    public void onCreate() {
        super.onCreate();
        myServer = new MyServer();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myServer;
    }
}
