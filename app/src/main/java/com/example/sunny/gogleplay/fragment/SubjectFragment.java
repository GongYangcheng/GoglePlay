package com.example.sunny.gogleplay.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sunny.gogleplay.view.LoadingPage;

/**
 * A simple {@link Fragment} subclass.
 * 专题
 */
public class SubjectFragment extends BaseFragment {


    @Override
    public int load() {

        return LoadingPage.STATU_ERROR;
    }

    @Override
    protected View createSuccessView() {
        return null;
    }
}
