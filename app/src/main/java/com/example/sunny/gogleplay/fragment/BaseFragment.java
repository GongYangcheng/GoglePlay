package com.example.sunny.gogleplay.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.sunny.gogleplay.util.UiUtils;
import com.example.sunny.gogleplay.view.LoadingPage;

/**
 * Created by sunny on 2018/5/30.
 */

public abstract class BaseFragment extends Fragment {

    private LoadingPage frameLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        复用fragment，使用FragmentStatePagerAdapter时，会缓存当前页面的左右两个页面，其他页面会销毁
//        做一个缓存防止被销毁
        if (frameLayout == null){
//        使用上下文时，一般使用生命周期最长的。防止内存泄露？
            frameLayout = new LoadingPage(UiUtils.getContext()) {
                @Override
                protected View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }

                @Override
                protected int load() {
                    return BaseFragment.this.load();
                }
            };
//            init();//将不同的界面添加到帧布局
        }
//        show();//根据服务器返回的状态，切换为不同界面
        return frameLayout;
    }

    public void show(){
        if(frameLayout != null){
            frameLayout.show();
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        当View销毁时会执行此方法，
//        此方法为了适配低版本安卓，低版本如果不让View和父View接触关系会造成内存泄漏
        ViewParent parentView = frameLayout.getParent();
//        ViewParent没有removeView方法所以要强转成ViewGroup；
        if (parentView != null&& parentView instanceof ViewGroup){
            ViewGroup view = (ViewGroup) parentView;
            view.removeView(frameLayout);
        }

    }
    public abstract int load();

    protected abstract View createSuccessView();

}
