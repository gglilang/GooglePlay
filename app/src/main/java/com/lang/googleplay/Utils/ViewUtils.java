package com.lang.googleplay.Utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by Lang on 2015/7/23.
 */
public class ViewUtils {

    public static void removeParent(View view){
        ViewParent parent = view.getParent();

        if(parent instanceof ViewGroup){
            // view的父亲一般是ViewGroup
            ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.removeView(view);
        }
    }
}
