package com.sd.style.common.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sd.style.R;

/**
 * description: activity 基类
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
