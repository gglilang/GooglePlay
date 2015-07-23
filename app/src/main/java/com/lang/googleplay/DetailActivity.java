package com.lang.googleplay;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Lang on 2015/7/22.
 */
public class DetailActivity extends BaseActivity {


    @Override
    protected void initView() {
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
