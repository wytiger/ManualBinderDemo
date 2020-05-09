package com.wytiger.noaidl;

import android.os.IInterface;
import android.os.RemoteException;

/**
 * desc: 定义服务端接口，继承IInterface接口代表提供什么样的能力
 *
 * @author wuyong_cd
 */
public interface IMyServer extends IInterface {
    String TAG = "IMyServer";
    String DESCRIPTOR = "com.wytiger.noaidl.IMyServer";
    int TRANSACTION_add = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);

    int add(int a, int b) throws RemoteException;
}
