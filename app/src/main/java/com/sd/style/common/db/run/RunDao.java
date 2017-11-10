package com.sd.style.common.db.run;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sd.style.common.db.bean.PointEntity;

/**
 * Author: HeLei on 2017/11/10 14:52
 */

public class RunDao {

    private RunOpenHelper mRunOpenHelper;

    public RunDao(Context context) {
        mRunOpenHelper = new RunOpenHelper(context);
    }

    private SQLiteDatabase getWritableDatabase(){
        return mRunOpenHelper.getWritableDatabase();
    }
    private SQLiteDatabase getReadableDatabase(){
        return mRunOpenHelper.getReadableDatabase();
    }

    public void addPoint(PointEntity entity){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PointEntity.Companion.getLNG(), entity.getLng());
        database.insert()
    }
}
