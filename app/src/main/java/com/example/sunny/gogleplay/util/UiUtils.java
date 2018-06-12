package com.example.sunny.gogleplay.util;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;

import com.example.sunny.gogleplay.R;
import com.example.sunny.gogleplay.activity.BaseApplication;
import com.example.sunny.gogleplay.view.LoadingPage;

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
//        Button errorBtn = (Button) view.findViewById(R.id.page_bt);
//        if(errorBtn != null){
//            errorBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    LoadingPage.show();
//                }
//            });
//        }

        return view;
    }

}
