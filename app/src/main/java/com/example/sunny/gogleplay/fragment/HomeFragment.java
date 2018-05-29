package com.example.sunny.gogleplay.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.sunny.gogleplay.R;
import com.example.sunny.gogleplay.util.UiUtils;


public class HomeFragment extends Fragment {

    public static final int STATU_UNKNOWN = 0;//未知状态
    public static final int STATU_LOADING = 1;//加载中状态
    public static final int STATU_ERROR = 2;//失败状态
    public static final int STATU_EMPTY = 3;//为空状态
    public static final int STATU_SUCCESS = 4;//成功状态

    private int statu = STATU_LOADING;//默认加载中
    private FrameLayout frameLayout;

    private View loadingView;//加载中的view
    private View errorView;//加载失败view
    private View emptyView;//加载为空view
    private View successView;//加载成功view

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        使用上下文时，一般使用生命周期最长的。防止内存泄露？
        frameLayout = new FrameLayout(UiUtils.getContext());
        init();//将不同的界面添加到帧布局
        showPage();//根据状态切换，显示不同界面的显示和隐藏
        show();//根据服务器返回的状态，切换为不同界面
        return frameLayout;
    }

    /**
     * 将几种不同的界面添加到帧布局中
     */
    private void init() {
        if(loadingView == null){
            loadingView = UiUtils.createView(R.layout.page_loading);
//            -1 == match_parent
            frameLayout.addView(loadingView,new FrameLayout.LayoutParams(-1,-1));
        }
        if(errorView == null){
            errorView = UiUtils.createView(R.layout.page_error);
            frameLayout.addView(errorView,new FrameLayout.LayoutParams(-1,-1));
        }
        if(emptyView == null){
            emptyView = UiUtils.createView(R.layout.page_empty);
            frameLayout.addView(emptyView,new FrameLayout.LayoutParams(-1,-1));
        }
//        成功的，等获取到数据之后再添加；
    }

    /**
     * 根据状态切换，显示不同界面的显示和隐藏
     */
    private void showPage() {
//        如果当Gone和Invisible用谁都可以时，最好用Invisible。优化
        if (loadingView != null) {
            loadingView.setVisibility(statu == STATU_LOADING || statu == STATU_UNKNOWN ? View.VISIBLE : View.INVISIBLE);
        }

        if (errorView != null){
            errorView.setVisibility(statu== STATU_ERROR?View.VISIBLE:View.INVISIBLE);
        }

        if (emptyView != null){
            emptyView.setVisibility(statu == STATU_EMPTY?View.VISIBLE:View.INVISIBLE);
        }
    }

    /**
     * 根据服务器返回的状态，切换为不同界面
     */
    private void show() {
    }

}
