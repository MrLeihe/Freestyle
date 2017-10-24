package com.sd.style.common.uitls;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Author: HeLei on 2017/10/24 10:51
 */

public class ContextCompactUtils {

    public static int getColor(Context context, int resColorId){
        return ContextCompat.getColor(context, resColorId);
    }

    public static Drawable getDrawable(Context context, int resDrawableId){
        return ContextCompat.getDrawable(context, resDrawableId);
    }
}
