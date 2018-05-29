package com.example.sunny.gogleplay.util;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import com.example.sunny.gogleplay.activity.BaseApplication;

/**
 * Created by sunny on 2018/5/28.
 */

public class UiUtils {

    public static String[] getStringArray(int id){
        return getResources().getStringArray(id);
    }

    public static Resources getResources(){
        return getContext().getResources();
    }

    public static Context getContext(){
        return BaseApplication.getContext();
    }

    public static View createView(int id) {
        View view = View.inflate(UiUtils.getContext(),id,null);
        return view;
    }

}
