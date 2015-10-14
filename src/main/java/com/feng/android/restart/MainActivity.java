package com.feng.android.restart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.feng.android.common.util.T;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        findViewById(R.id.root_rl).setOnTouchListener(
                new QuickTapListener(this,
                        new QuickTapListener.OnQuickTapListener() {
                            @Override
                            public void onTap() {
                                T.getInstance(MainActivity.this).s("Show egg");
                            }
                        }));

        findViewById(R.id.restart_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartApp();
            }
        });

        TextView tv = (TextView)findViewById(R.id.start_time_tv);
        tv.setText(((MyApplication)getApplication()).getmLauchTime());
    }

    private void restartApp() {
        Intent intent = new Intent();
        intent.setClass(this, RestartAppService.class);
        startService(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
