package com.feng.android.restart;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * @author fengjun
 * @update 15/10/10
 */
public class QuickTapListener implements View.OnTouchListener{

    private static final String TAG = "QuickTapListener";

    private static final int MIN_TAP_DURATION = 200;
    private static final int MAX_TAG_COUNT = 3;
    private static final int TAP_AREA_FAVOR = 10;

    private int mTapCount;
    private long mLastTapTime;
    private float mXCriticalPoint;
    private float mYCriticalPoint;

    private OnQuickTapListener mOnQuickTapListener;

    public interface OnQuickTapListener {
        void onTap();
    }

    public QuickTapListener(Context context, OnQuickTapListener listener){
        this.mOnQuickTapListener = listener;
        initTapArea(context);
    }

    private void initTapArea(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final float width = wm.getDefaultDisplay().getWidth();
        final float height = wm.getDefaultDisplay().getHeight();

        mXCriticalPoint = width - width / TAP_AREA_FAVOR;
        mYCriticalPoint = height / TAP_AREA_FAVOR;
    }

    private boolean isInsideTapArea(MotionEvent event){
        return (event.getRawX() > mXCriticalPoint) && (event.getRawY() < mYCriticalPoint);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(MotionEvent.ACTION_DOWN == event.getAction() && isInsideTapArea(event)){

            long duration = System.currentTimeMillis() - mLastTapTime;

            if(mTapCount == 0 || duration <= MIN_TAP_DURATION){
                mTapCount++;
            }else{
                mTapCount = 1;
            }
            mLastTapTime = System.currentTimeMillis();

            if(MAX_TAG_COUNT == mTapCount){
                mTapCount = 0;
                mLastTapTime = 0;
                mOnQuickTapListener.onTap();
            }
            return true;
        }
        return false;
    }
}
