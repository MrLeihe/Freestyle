package com.sd.style.common.db.run;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.stetho.common.LogUtil;
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
        if(entity == null) {
            return;
        }
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PointEntity.Companion.getLNG(), entity.getLng());
        contentValues.put(PointEntity.Companion.getLAT(), entity.getLat());
        contentValues.put(PointEntity.Companion.getTIME(), entity.getTime());
        contentValues.put(PointEntity.Companion.getPROVINCE(), entity.getProvince());
        contentValues.put(PointEntity.Companion.getCITY(), entity.getCity());
        contentValues.put(PointEntity.Companion.getAREA(), entity.getArea());
        contentValues.put(PointEntity.Companion.getDISTANCE(), entity.getDistance());
        try{
            database.insert(RunOpenHelper.getTableName(), null, contentValues);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            database.close();
        }
    }

    /**
     * 通过ID删除
     */
    public void deletePoint(String[] whereArgs){
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(RunOpenHelper.getTableName(), "distance=?", whereArgs);
        writableDatabase.close();
    }

    public void queryPoint(String province){
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String queryStr = "select * from " + RunOpenHelper.getTableName() + " where " + PointEntity.Companion.getPROVINCE() + " =?";
        Cursor cursor = readableDatabase.rawQuery(queryStr, new String[]{province});
        if(cursor != null) {
            while (cursor.moveToNext()) {
                String area = cursor.getString(cursor.getColumnIndex(PointEntity.Companion.getAREA()));
                LogUtil.e("area---------------------->" + area);
            }
            cursor.close();
        }
        readableDatabase.close();
    }
}
