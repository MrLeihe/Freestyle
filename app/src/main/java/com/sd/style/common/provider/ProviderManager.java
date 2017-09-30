package com.sd.style.common.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.orhanobut.logger.Logger;

/**
 * Author: HeLei on 2017/9/18 00:19
 */

public class ProviderManager {

    /**
     *
     * query sms in phone use contentProvider
     */
    void contentProvider(Context context) {
        Uri smsUri = Uri.parse("content://sms/");
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(smsUri, new String[]{"address", "date", "type", "body"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String address = cursor.getString(0);
                String date = cursor.getString(1);
                String type = cursor.getString(2);
                String body = cursor.getString(3);
                Logger.e("address-----" + address + "/n" + "date-----" + date
                        + "/n" + "type-----" + type + "/n" + "body-----" + body);
            }

            cursor.close();
        }
    }

}
