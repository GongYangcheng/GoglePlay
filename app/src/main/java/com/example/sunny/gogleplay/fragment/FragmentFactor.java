package com.example.sunny.gogleplay.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * Created by sunny on 2018/5/28.
 */

public class FragmentFactor {

    private static HashMap<Integer,Fragment> map = new HashMap<>();

    public static Fragment getFragment(int position){
        Fragment fragment = map.get(position);
        if(fragment == null) {
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new AppFragment();
                    break;
                case 2:
                    fragment = new GameFragment();
                    break;
                case 3:
                    fragment = new SubjectFragment();
                    break;
                case 4:
                    fragment = new CategoryFragment();
                    break;
                case 5:
                    fragment = new TopFragment();
                    break;
            }
//            缓存
            if(fragment != null){
                map.put(position,fragment);
            }
        }
        return fragment;
    }
}
