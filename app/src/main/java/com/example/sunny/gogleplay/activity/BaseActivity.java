package com.example.sunny.gogleplay.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sunny.gogleplay.util.ActivityControl;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initView();
        initToolBar();
        ActivityControl.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityControl.removeActivity(this);
    }

    /**
     * 初始化
     */
    protected void init() {

    }

    /**
     * 初始化控件
     */
    protected void initView() {
    }

    /**
     * 初始化ToolBar
     */
    protected void initToolBar() {
    }
}
