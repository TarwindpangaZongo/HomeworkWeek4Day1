package com.example.homeworkweek4day1;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    EditText etInputDisplay;
    TextView tvJavaResultDisplay;
    TextView tvAsyncResultDisplay;
    TextView tvLooperResultDisplay;
    Button btnJava;
    Button btnAsyncTask;
    Button btnLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        //looper
        LooperThread looperThread;

    }

    private void bindView() {
        etInputDisplay = findViewById(R.id.etUserInput);
        tvJavaResultDisplay = findViewById(R.id.tvJavaThreadResult);
        tvAsyncResultDisplay = findViewById(R.id.tvAsyncTaskResult);
        tvLooperResultDisplay = findViewById(R.id.tvLooperResult);
        btnJava = findViewById(R.id.btnStartJavaThread);
        btnAsyncTask = findViewById(R.id.btnStartAsyncTask);
        btnLooper = findViewById(R.id.btnStartLooper);
    }



    // Standard Java Thread
    private void startThread(){
        Thread javaThread = new Thread(runnableForThread());
        javaThread.start();
    }

    //Standard Java Thread
    private Runnable runnableForThread(){

        return new Runnable() {
            @Override
            public void run() {
                int textLength = etInputDisplay.getText().length();
                tvJavaResultDisplay.setText(textLength);
            }
        };
    }




    public void onClicl(View view) {
        switch (view.getId()){
            case R.id.btnStartJavaThread:
                startThread();
                break;
            case R.id.btnStartAsyncTask:
                //Declare out async task
                AsyncTreading asyncTreading;
                asyncTreading = new AsyncTreading();
                asyncTreading.execute();

                break;
            case R.id.btnStartLooper:
                //looper
                LooperThread looperThread;
                //instantiate the looper and handle results from the looper to the main looper
                looperThread = new LooperThread(new Handler(Looper.getMainLooper()){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        msg.equals(etInputDisplay.getText());
                        Bundle bundle = msg.getData();
                        tvLooperResultDisplay.setText(bundle.getString("key"));
                    }
                });
                looperThread.start();
                looperThread.workerThreadHandler.sendMessage(new Message());
                break;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    //Thread that will run on the UI
    private void uiThreadDemo(){
        runOnUiThread(runnableForThread());
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAsyncMassageReceived(AsyncTaskEvent asyncTaskEvent){
        tvAsyncResultDisplay.setText(asyncTaskEvent.getMessage());
    }


}
