package com.wytiger.noaidl.server;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wytiger.noaidl.IMyServer;
import com.wytiger.noaidl.client.MyServerProxy;

/**
 * desc: 服务端本地对象，继承Binder代表拥有跨进程通信能力，实现IMyServer代表提供什么样的能力。
 * 抽象类，对应aidl中Stub类。
 *
 * @author wuyong_cd
 */
public abstract class MyServerNative extends Binder implements IMyServer {

    public MyServerNative() {
        //关联该接口与对应的名字，以便后续查找
        this.attachInterface(this, DESCRIPTOR);
    }

    /**
     * 将binder转换为服务接口
     *
     * @param obj
     * @return
     */
    public static IMyServer asInterface(IBinder obj) {
        if (obj == null) {
            return null;
        }

        //在本地查询
        IInterface iin = obj.queryLocalInterface(DESCRIPTOR);

        //查询到直接返回
        if (iin != null && iin instanceof IMyServer) {
            return (IMyServer) iin;
        }

        //查询不到，创建代理
        return new MyServerProxy(obj);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION:
                //写入服务端接口标识到响应参数
                reply.writeString(DESCRIPTOR);
                return true;
            case TRANSACTION_add:
                //校验
                data.enforceInterface(DESCRIPTOR);
                //获取客户端传过来的参数
                int _arg0 = data.readInt();
                int _arg1 = data.readInt();
                //调用方法
                this.add(_arg0, _arg1);

                //将结果写入响应参数
                reply.writeNoException();// Parcel队头写入, “无异常“
                return true;
            default:
                return super.onTransact(code, data, reply, flags);
        }
    }
}
