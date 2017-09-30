package com.sd.style.common.algorithm.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.orhanobut.logger.Logger;
import com.sd.style.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Author: HeLei on 2017/9/6 23:46
 */

public class AssetsTest {

    public static void testAssets(Context context){
        //read image
        InputStream imageIs = null;
        try {
            imageIs = context.getAssets().open("image/ic_launcher.png");
            Bitmap bitmap = BitmapFactory.decodeStream(imageIs);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(imageIs != null) {
                try {
                    imageIs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //list assets
        try {
            String[] list = context.getAssets().list("image");
            for (String s : list) {
                Logger.d("list----" + s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream assets = context.getAssets().open("haha.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(assets));
            String length;
            StringBuilder builder = new StringBuilder();
            while ((length = reader.readLine()) != null) {
                builder.append(length);
            }
            Logger.d(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream inputStream = context.getResources().openRawResource(R.raw.haha);
        InputStreamReader rawReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(rawReader);
        try {
            String line = bufferedReader.readLine();
            Logger.d("line---" + line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
