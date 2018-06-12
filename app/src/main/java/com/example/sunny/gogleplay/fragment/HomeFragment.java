package com.example.sunny.gogleplay.fragment;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.sunny.gogleplay.R;
import com.example.sunny.gogleplay.bean.AppInfo;
import com.example.sunny.gogleplay.protocol.HomeProtocol;
import com.example.sunny.gogleplay.util.HttpHelper;
import com.example.sunny.gogleplay.util.ThreadUtils;
import com.example.sunny.gogleplay.util.ToastUtils;
import com.example.sunny.gogleplay.util.UiUtils;
import com.example.sunny.gogleplay.view.LoadingPage;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;


public class HomeFragment extends BaseFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();// 手动调用 解决  首页 自动请求网络问题
    }
    List<AppInfo> datas ;
    @Override
    public int load() {
//        1.请求服务器。2，缓存。3，复用缓存。4，解析
        HomeProtocol homeProtocol = new HomeProtocol();
        datas = homeProtocol.load(0);
        return checkDatas(datas);
    }

    public int checkDatas(List<AppInfo> datas){
        if(datas == null){
            return LoadingPage.STATU_ERROR;
        }else{
            if(datas.size() == 0){
                return LoadingPage.STATU_EMPTY;
            }else{
                return LoadingPage.STATU_SUCCESS;
            }
        }
    }

    @Override
    protected View createSuccessView() {
//        OkHt
        RecyclerView recyclerView = new RecyclerView(UiUtils.getContext());
        LinearLayoutManager manager = new LinearLayoutManager(UiUtils.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new HomeAdapter(datas));
//        ToastUtils.logShow(datas.get(0).size+"");
//        TextView tv = new TextView(UiUtils.getContext());
//        tv.setText("homePage");
        return recyclerView;
    }

    private class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
        private List<AppInfo> mAppInfo;


        public HomeAdapter(List<AppInfo> appInfo){
            mAppInfo = appInfo;
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            public View rootView;
            public ImageView item_icon;
            public TextView action_txt;
            public TextView item_title;
            public RatingBar item_rating;
            public TextView item_size;
            public TextView item_bottom;

            public ViewHolder(View itemView) {
                super(itemView);
                this.rootView = itemView;
                this.item_icon = (ImageView) rootView.findViewById(R.id.item_icon);
                this.action_txt = (TextView) rootView.findViewById(R.id.action_txt);
                this.item_title = (TextView) rootView.findViewById(R.id.item_title);
                this.item_rating = (RatingBar) rootView.findViewById(R.id.item_rating);
                this.item_size = (TextView) rootView.findViewById(R.id.item_size);
                this.item_bottom = (TextView) rootView.findViewById(R.id.item_bottom);
            }
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = UiUtils.createView(R.layout.item_appinfo);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            AppInfo appInfo = mAppInfo.get(position);
            holder.item_title.setText(appInfo.name);
            holder.item_rating.setRating((float) appInfo.stars);
            holder.item_size.setText(Formatter.formatFileSize(UiUtils.getContext(), appInfo.size));// long -   xxMB
            holder.item_bottom.setText(appInfo.des);
            Picasso.with(UiUtils.getContext())
                    .load(HttpHelper.BASEURL+"/image?name="+appInfo.iconUrl)
                    .into(holder.item_icon);

        }

        @Override
        public int getItemCount() {
            return mAppInfo.size();
        }

    }
}
