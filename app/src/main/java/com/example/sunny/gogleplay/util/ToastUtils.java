package com.example.sunny.gogleplay.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sunny on 2018/5/15.
 */

public class ToastUtils {

    public static final String TAG = "ToastUtils";
    public static Toast toast;
    public static boolean isToastShow = true, isLogShow = true;
    public static void toastShow(Context context,String text){
        if (isToastShow) {
            if (toast == null) {
                toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            }
            toast.setText(text);
            toast.show();
        }
    }

    public static void logShow(String text){
        if(isLogShow){
            Log.d(TAG,text);
        }
    }
}
