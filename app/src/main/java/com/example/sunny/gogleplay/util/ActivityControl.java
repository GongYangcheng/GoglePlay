package com.example.sunny.gogleplay.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny on 2018/5/28.
 */

public class ActivityControl {
    static List<Activity> activities = new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void removeActivityAll(){
        for (Activity activity:activities
             ) {
            if (activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }
}