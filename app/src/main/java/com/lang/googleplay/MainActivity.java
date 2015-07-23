package com.lang.googleplay;

import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lang.googleplay.Utils.UiUtils;
import com.lang.googleplay.fragment.AppFragment;
import com.lang.googleplay.fragment.CategoryFragment;
import com.lang.googleplay.fragment.FragmentFactory;
import com.lang.googleplay.fragment.GameFragment;
import com.lang.googleplay.fragment.HomeFragment;
import com.lang.googleplay.fragment.SubjectFragment;
import com.lang.googleplay.fragment.TopFragment;


public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ViewPager mViewPager;
    private PagerTabStrip pager_tab_strip;
    private String[] tab_names;

    @Override
    protected void init() {
        tab_names = UiUtils.getStringArray(R.array.tab_names);

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        pager_tab_strip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);

        pager_tab_strip.setTabIndicatorColor(getResources().getColor(R.color.indicatorcolor));

        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
    }

    @Override
    protected void initActionBar() {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_drawer_am);



        // 参数1 当前ActionBar所在的Activity 参数2 控制的哪个抽屉
        // 参数3 按钮的图片 参数4,5 抽屉的描述
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.mipmap.ic_drawer_am, R.string.open_drawer, R.string.close_drawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(getApplicationContext(), "抽屉打开了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(getApplicationContext(), "抽屉关闭了", Toast.LENGTH_SHORT).show();
            }
        };


        mDrawerLayout.setDrawerListener(mDrawerToggle);
        // 让开关和ActionBar建立关系
        mDrawerToggle.syncState();
    }

    private class MainAdapter extends FragmentStatePagerAdapter {
        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // 通过Fragment工厂生产Fragment
            return FragmentFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            return tab_names.length;
        }

        // 返回每个条目的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return tab_names[position];
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    /**
     * 处理ActionBar菜单条目的点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_search){
            Toast.makeText(getApplicationContext(), "搜索", Toast.LENGTH_SHORT).show();
        }

        return mDrawerToggle.onOptionsItemSelected(item) | super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        return true;
    }
}