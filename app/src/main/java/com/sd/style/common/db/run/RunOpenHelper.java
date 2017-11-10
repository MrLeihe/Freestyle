package com.sd.style.common.db.run;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Author: HeLei on 2017/11/10 10:31
 */

public class RunOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "run";
    private static final String TABLE_NAME = "run_table";
    private static final int VERSION = 1;

    public RunOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table " + TABLE_NAME + "(");
        stringBuffer.append("_id integer primary key autoincrement,");
        stringBuffer.append("lng double, lat double, time long,");
        stringBuffer.append("province varchar(20), city varchar(20), area varchar(20)");
        stringBuffer.append(")");
        db.execSQL(stringBuffer.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String getTableName(){
        return TABLE_NAME;
    }
}
