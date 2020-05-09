package com.wytiger.noaidl.server;

import android.os.RemoteException;

/**
 * desc: 服务端真正实现
 *
 * @author wuyong_cd
 */
public class MyServer extends MyServerNative {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
