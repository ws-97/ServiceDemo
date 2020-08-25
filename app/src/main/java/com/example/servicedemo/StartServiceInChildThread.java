package com.example.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @Author ws
 * @Date 2020/8/25 16:16
 * @Version 1.0
 */
public class StartServiceInChildThread extends IntentService {
    private static final String TAG = "StartServiceInChildThre";
    public StartServiceInChildThread() {
        super("StartServiceInChildThread");
        Log.e(TAG, "StartServiceInChildThread: "+Thread.currentThread().getName() );
    }

    /**
     * @param name
     * @deprecated
     */
    public StartServiceInChildThread(String name) {
        super(name);
        Log.e(TAG, "StartServiceInChildThread: "+Thread.currentThread().getName() );
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        for(int i=0;i<10;i++){
            Log.e(TAG, "onHandleIntent: "+Thread.currentThread().getName()   );
            Log.e(TAG, "onHandleIntent: "+i);
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
