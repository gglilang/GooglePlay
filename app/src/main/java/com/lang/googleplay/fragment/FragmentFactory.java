package com.lang.googleplay.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lang on 2015/7/23.
 */
public class FragmentFactory {

    private static Map<Integer, Fragment> mFragments = new HashMap<>();

    public static Fragment createFragment(int position){
        Fragment fragment = mFragments.get(position);
        if(fragment == null) {
            if (position == 0) {
                fragment = new HomeFragment();
            } else if (position == 1) {
                fragment = new AppFragment();
            } else if (position == 2) {
                fragment = new GameFragment();
            } else if (position == 3) {
                fragment = new SubjectFragment();
            } else if (position == 4) {
                fragment = new CategoryFragment();
            } else if (position == 5) {
                fragment = new TopFragment();
            }
            // 把创建好的Fragment缓存起来
            if(fragment != null) {
                mFragments.put(position, fragment);
            }
        }

        return fragment;
    }
}
