package com.example.homeworkweek4day1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

class LooperThread extends Thread {
    //Our handler for the worker thread
    Handler workerThreadHandler;
    Handler mainThreadHandler;

    public LooperThread(Handler handler){
        super();
        mainThreadHandler = handler;
        workerThreadHandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                String duplicate = "";
                String in  = msg.toString();
                for (int i = 0 ; i < in.length(); i++){
                    for (int j = 0; j< in.length(); j++){
                        if (i!= j && in.charAt(i) == in.charAt(j)){
                            duplicate += in.charAt(i);
                        }
                    }
                }
                Message message = new Message();
                message.what = msg.what;
                Bundle bundle = new Bundle();
                bundle.putString("key", duplicate);
                message.setData(bundle);
                //Send the message back to main thread message queue use main thread message Handler
                mainThreadHandler.sendMessage(message);
            }
        };
    }
    @Override
    public void run() {
        super.run();
        Looper.prepare();
        //Loop the child thread message queue
        Looper.loop();
    }
}

