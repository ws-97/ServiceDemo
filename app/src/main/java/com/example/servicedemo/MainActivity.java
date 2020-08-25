package com.example.servicedemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: " +Thread.currentThread().getName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startBtn = findViewById(R.id.btn_start);
        Button stopBtn = findViewById(R.id.btn_stop);

        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
    }

    private static final String TAG = "MainActivity";
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start:
                startServiceByExtendsIntentService();

//                startServiceInChildThread();
                //startServiceInMain();
                break;
            case R.id.btn_stop:
                Log.e(TAG, "onClick: stop" );
                Intent stopIntent = new Intent(MainActivity.this, StartService.class);
                // 绑定服务,关闭
                stopService(stopIntent);
                break;
        }
    }

    /**
     * 新启动一个子线程来启动服务
     */
    public void startServiceInChildThread(){
        //下面这种方式看起来是将服务运行在子线程中，实际上该服务仍然运行在主线程，她所到的效果只是
        //启动一个线程启动一个服务，然后服务独立运行不受该线程影响
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: "+Thread.currentThread().getName() );
                Intent startIntent = new Intent(MainActivity.this, StartService.class);
                // 绑定服务,启动
                startService(startIntent);
            }
        }).start();
        //-----------分割线------------
        //正确的将服务运行在子线程中应该是在继承的Service类中重写OnStartCommand()方法，并将要做的内容
        //放在一个新的线程里或者使用IntentService
    }

    /**
     * 在主线程（即UI线程   ）启动服务
     * 当服务执行的时耗时操作时，会阻塞住主线程导致stop按钮无法点击
     */
    public void startServiceInMain(){

        Intent startIntent = new Intent(MainActivity.this, StartService.class);
        // 绑定服务,启动
        startService(startIntent);
    }
    /**
     * 使用继承IntentService的类来启动服务
     */
    public void startServiceByExtendsIntentService(){
        Intent intentService=new Intent(this,StartServiceInChildThread.class);
        startService(intentService);
    }
}