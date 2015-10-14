package com.feng.android.restart;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.feng.android.common.util.AppUtils;

/**
 * Server can lauch an app in {$RESTART_DURATION} seconds <br/>
 * and then kill itself (including process)
 *
 * <p/>
 * NOTICE:
 *
 * should use <code>android:process</code> in its
 * manifest tag to specify anothor process
 *
 * @author fengjun
 * @update 15/10/10
 */
public class RestartAppService extends Service{

    private static final int RESTART_DURATION = 500;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(RESTART_DURATION);
                } catch (InterruptedException e) {
                    // No need to handle
                }
                AppUtils.restartApplication(RestartAppService.this);
                stopSelf();
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
