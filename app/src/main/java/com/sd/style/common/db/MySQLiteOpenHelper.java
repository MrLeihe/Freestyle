package com.sd.style.common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orhanobut.logger.Logger;

/**
 * Author: HeLei on 2017/9/15 16:47
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper{

    //数据库名
    private static final String DB_NAME = "style.db";
    private static final String TABLE_NAME = "userInfo";
    //当前数据库版本
    private static final int version = 1;

    public MySQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists " + TABLE_NAME + " (id integer primary key, name text, age integer)";
        db.execSQL(sql);
        Logger.e("create db success");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
