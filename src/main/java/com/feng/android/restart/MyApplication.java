package com.feng.android.restart;

import android.app.Application;

import com.feng.android.common.util.L;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author fengjun
 * @update 15/10/10
 */
public class MyApplication extends Application{

    private static final String TAG = "MyApplication";

    private String mLauchTime;

    @Override
    public void onCreate() {
        super.onCreate();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        mLauchTime = simpleDateFormat.format(new Date());
        L.i(TAG, "Applciation start at " + mLauchTime);

    }

    public String getmLauchTime() {
        return mLauchTime;
    }
}
