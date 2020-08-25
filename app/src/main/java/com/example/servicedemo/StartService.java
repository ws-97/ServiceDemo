package com.example.servicedemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

/**
 * @Author ws
 * @Date 2020/8/25 11:31
 * @Version 1.0
 */
public class StartService extends Service {
    private static final String TAG = "StartService";
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //下面这段代码将会运行在主线程中
//        for(int i=0;i<30;i++){
//            Log.e("MyService:"+Thread.currentThread().getName(),i+"***********");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        Log.e(TAG, "onStartCommand: ");
        ///-----------分割线----------------
        //启用新的线程运行服务要做的事（耗时）
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    Log.e("MyService"+Thread.currentThread().getName(),i+"***********");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf();//执行完毕停止服务
            }
        }).start();


        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: " );
    }
}