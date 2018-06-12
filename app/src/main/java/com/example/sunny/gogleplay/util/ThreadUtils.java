package com.example.sunny.gogleplay.util;

import android.os.Handler;

import com.example.sunny.gogleplay.manager.ThreadPoolManager;

import java.util.logging.LogRecord;

/**
 * Created by sunny on 2018/5/30.
 */

public class ThreadUtils {

    private static Handler handler = new Handler();
    public static void runOnBackThread(Runnable runnable){
        ThreadPoolManager.getInstace().createThreadPoolProxy().execture(runnable);
    }

    public static void runOnUIThread(Runnable runnable){
        handler.post(runnable);
    }
}
