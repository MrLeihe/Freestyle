package com.sd.style.common.db;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orhanobut.logger.Logger;
import com.sd.style.common.app.MyApplication;

/**
 * Author: HeLei on 2017/9/15 16:48
 */

public class DatabaseManager {

    public static DatabaseManager manager;

    private SQLiteOpenHelper mOpenHelper;

    public DatabaseManager() {
        mOpenHelper = new MySQLiteOpenHelper(MyApplication.getInstance());
    }

    public static DatabaseManager getDefault(){
        if(manager == null) {
            synchronized (DatabaseManager.class){
                if(manager == null) {
                    manager = new DatabaseManager();
                }
            }
        }
        return manager;
    }

    public SQLiteDatabase getSQLiteDatabase(){
        return mOpenHelper.getWritableDatabase();
    }

    public void closeDatabase(){
        if(mOpenHelper != null) {
            mOpenHelper.close();
        }
    }

    public void operate() {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getDefault().getSQLiteDatabase();
        String sql = "insert into userInfo (id, name, age) values (3, 'guis', 33)";
        try {
            sqLiteDatabase.execSQL(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            sqLiteDatabase.close();
        }

    }

    public void deleteById(int id){
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getDefault().getSQLiteDatabase();
        String sql = "delete from userInfo where id = " + id;
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.close();
    }

    public void updateByName(String name){
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getDefault().getSQLiteDatabase();
        String sql = "update userInfo set age = 22, name = 'magua' where name = " + name;
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.close();
    }

    private void rawQuery(){
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getDefault().getSQLiteDatabase();
        String sql = "select * from userInfo where id = ? and name like ? and age = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{"2", "magua", "22"});
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            Logger.e("id---" + id + "---name---" + name + "---age---" + age);
        }

        cursor.close();
        sqLiteDatabase.close();
    }

    public void query(){
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getDefault().getSQLiteDatabase();
        Cursor cursor = sqLiteDatabase.query("userInfo", new String[]{"id", "name", "age"}, "name like ? and id = ? and age = ?", new String[]{"magua", "2", "22"}, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            Logger.e("id---" + id + "---name---" + name + "---age---" + age);
        }

        cursor.close();
        sqLiteDatabase.close();
    }
}
