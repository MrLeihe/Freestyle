package com.sd.style;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest{
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.sd.style", appContext.getPackageName());
    }

    @Test
    public void formatDataTest(){
        String dateTime = FormatDateTime("2017-8-10");
        assertEquals("2017年8月10日", dateTime);
    }

    /**
     * 将yyyy-MM-dd转化为年月日格式
     */
    public String FormatDateTime(String time){
        if(TextUtils.isEmpty(time)) {
            return time;
        }
        String[] timeArray = time.split("-");
        StringBuilder sb = new StringBuilder();
        if(timeArray.length >0) {
            for (int i = 0; i < timeArray.length; i++) {
                sb.append(timeArray[i]);
                if(i == 0) {
                    sb.append("年");
                }else if(i == 1) {
                    sb.append("月");
                }else if(i == 2) {
                    sb.append("日");
                }
            }
        }
        return sb.toString();
    }
}
