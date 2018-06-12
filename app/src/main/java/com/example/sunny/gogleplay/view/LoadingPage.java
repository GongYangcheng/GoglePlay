package com.example.sunny.gogleplay.view;

import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.sunny.gogleplay.R;
import com.example.sunny.gogleplay.fragment.BaseFragment;
import com.example.sunny.gogleplay.util.ThreadUtils;
import com.example.sunny.gogleplay.util.UiUtils;

/**
 * Created by sunny on 2018/5/30.
 */

public abstract class LoadingPage extends FrameLayout {

    public static final int STATU_UNKNOWN = 0;//未知状态
    public static final int STATU_LOADING = 1;//加载中状态
    public static final int STATU_ERROR = 2;//失败状态
    public static final int STATU_EMPTY = 3;//为空状态
    public static final int STATU_SUCCESS = 4;//成功状态

    private int statu = STATU_LOADING;//默认加载中

    private View loadingView;//加载中的view
    private View errorView;//加载失败view
    private View emptyView;//加载为空view
    private View successView;//加载成功view
    public LoadingPage(@NonNull Context context) {
        super(context);
        init();
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 将几种不同的界面添加到帧布局中
     */
    private void init() {
        if(loadingView == null){
            loadingView = createLoadingView();
//            -1 == match_parent
            this.addView(loadingView,new FrameLayout.LayoutParams(-1,-1));
        }
        if(errorView == null){
            errorView = createErrorView();
            this.addView(errorView,new FrameLayout.LayoutParams(-1,-1));
        }
        if(emptyView == null){
            emptyView = createEmptyView();
            this.addView(emptyView,new FrameLayout.LayoutParams(-1,-1));
        }
        showPage();
    }
    /**
     * 创建加载中的view对象
     *
     * @return
     */
    private View createLoadingView() {

        // xml --- View  自定义组合控件
//        View.inflate(UIUtils.getContext(), R.layout.page_loading,null);
        View view = UiUtils.createView(R.layout.page_loading);
        return view;
    }

    /**
     * 创建加载失败的view对象
     *
     * @return
     */
    private View createErrorView() {
        // xml-- view
        View view = UiUtils.createView(R.layout.page_error);
        view.findViewById(R.id.page_bt).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                show(); //根据服务器返回的状态切换为不同的界面
            }
        });
        return view;
    }

    /**
     * 创建一个加载为空的View对象
     *
     * @return
     */
    private View createEmptyView() {
        // xml -- -View
        return UiUtils.createView(R.layout.page_empty);
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
//        成功的，等获取到数据之后再添加；
        if (statu == STATU_SUCCESS){
            if (successView == null){
                successView = createSuccessView();
                this.addView(successView,new FrameLayout.LayoutParams(-1,-1));
            }
        }else{
            if (successView != null){
                successView.setVisibility(View.INVISIBLE);
            }
        }
    }

    protected abstract View createSuccessView();

    /**
     * 根据服务器返回的状态，切换为不同界面
     */
    public void show() {
        if(statu == LoadingPage.STATU_ERROR){
            statu = STATU_LOADING;// 如果是错误状态 请求服务器时候 重新置为 加载中的状态
        }
        showPage();
        ThreadUtils.runOnBackThread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                statu = load();
                ThreadUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        showPage();
                    }
                });
            }
        });
    }

    protected abstract int load();
}
