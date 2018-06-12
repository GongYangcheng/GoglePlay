package com.example.sunny.gogleplay.activity;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.example.sunny.gogleplay.util.ImageUtils;
import com.example.sunny.gogleplay.util.UiUtils;

/**
 * Created by sunny on 2018/5/28.
 */

public class BaseApplication extends Application{

    private static BaseApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ImageUtils.initImage(UiUtils.getContext());
    }

    /**
     * 获得上下文
     * @return 上下文
     */
    public static Context getContext(){
        return instance;
    }
}
