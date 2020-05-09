package com.wytiger.noaidl.client;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import com.wytiger.noaidl.IMyServer;

/**
 * desc: 服务端代理对象，实现服务端接口。
 * 对应aidl中Stub的内部类Proxy。
 *
 * @author wuyong_cd
 */
public class MyServerProxy implements IMyServer {
    private IBinder mRemote;

    public MyServerProxy(IBinder remote) {
        mRemote = remote;
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();

        //请求参数写入数据
        _data.writeInterfaceToken(DESCRIPTOR);//服务接口标识
        _data.writeInt(a);//参数
        _data.writeInt(b);//参数

        try {
            //将数据传输到服务端
            mRemote.transact(IMyServer.TRANSACTION_add, _data, _reply, 0);

            //响应参数读取
            _reply.readException();//在Parcel队头读取，若读取值为异常，则抛出该异常；否则，程序正常运行。
            return _reply.readInt();
        } finally {
            _data.recycle();
            _reply.recycle();
        }
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }
}
