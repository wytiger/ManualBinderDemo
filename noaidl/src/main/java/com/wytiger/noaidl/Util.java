package com.wytiger.noaidl;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import java.util.List;

/**
 * desc:
 *
 * @author wuyong_cd
 */
public class Util {
    /**
     * 获取进程名
     *
     * @param context 上下文
     * @return 进程名
     */
    public static String getProcessName(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = activityManager.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }

        int pid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo processInfo : runningApps) {
            if (processInfo.pid == pid) {
                return processInfo.processName;
            }
        }
        return null;
    }

    /**
     * 获取线程名（获取不到则返回线程id）
     *
     * @return
     */
    public static String getThreadName() {
        Thread t = Thread.currentThread();
        String name = t.getName();
        if (TextUtils.isEmpty(name)) {
            name = t.getId() + "";
        }

        return name;
    }
}
