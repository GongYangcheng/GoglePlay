package com.example.sunny.gogleplay;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.sunny.gogleplay.fragment.AppFragment;
import com.example.sunny.gogleplay.fragment.HomeFragment;
import com.example.sunny.gogleplay.util.ToastUtils;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ViewPager mViewPager;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      初始化View
        initView();
        mViewPager.setAdapter(new MyPagerViewAdapter(getSupportFragmentManager()));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);设置actionBar图标
        final ActionBar actionBar = getSupportActionBar();
//        actionBar.set
//        显示tab
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab tab = actionBar.newTab().setText("标题1").setTabListener(new MyTabListener());
        actionBar.addTab(tab);
        tab = actionBar.newTab().setText("标题2").setTabListener(new MyTabListener());
        actionBar.addTab(tab);
        tab = actionBar.newTab().setText("标题3").setTabListener(new MyTabListener());
        actionBar.addTab(tab);
        tab = actionBar.newTab().setText("标题4").setTabListener(new MyTabListener());
        actionBar.addTab(tab);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
//pager切换时
                actionBar.setSelectedNavigationItem(position);
            }
        });
//ActionBarDrawerToggle 让actionbar和侧滑菜单做联动
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        和Actionbar同步
        actionBarDrawerToggle.syncState();
    }

    private void initView() {
        mViewPager = (ViewPager)findViewById(R.id.viewPage);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    public class MyPagerViewAdapter extends FragmentStatePagerAdapter{

        public MyPagerViewAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position%2 == 0){
                return new AppFragment();
            }
            return new HomeFragment();
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
    public class MyTabListener implements ActionBar.TabListener {

        @Override
        public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
            ToastUtils.logShow("onTabSelected");
            mViewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
            ToastUtils.logShow("onTabUnselected");
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
            ToastUtils.logShow("onTabReselected");
        }
    }

//连接菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu,menu);
        //获取searchView findviewbyid
        SearchView sv = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        sv.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        // 响应ActionBar的按钮点击
        if(item.getItemId() == R.id.action_search){
            ToastUtils.toastShow(this, "搜索");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
//        文字提交监听
        ToastUtils.toastShow(this,"提交"+query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
//        文字改变监听
        ToastUtils.toastShow(this,"变化"+newText);
        return false;
    }
}
