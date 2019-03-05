package com.example.homeworkweek4day1;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

public class AsyncTreading extends AsyncTask<String,String,String> {
//     TextView TextView;
//
//    public AsyncTreading(TextView textView) {
//        this.textView = textView;
//    }


    @Override
    protected void onPreExecute() {
        //Runs on main Thread
        Log.d("TAG", "onPreExecute: ABOUT TO RUN");
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... strings) {

        

        String in  = "";
        String reverse = "";
        for (int i = in.length(); i > 0; i--) {
            reverse += in.charAt(i);
            //Send the current thread update ingo to onPregressUpdate

        }
        //Runs on Wroker thread
        return null;

    }
//    //handles updates from the thread(doInBackground method above
//    @Override
//    protected void onProgressUpdate(String... values) {
//        //Runs on main Thread
//        super.onProgressUpdate(values);
//        //textView.setText(values[0]);
//    }
//
//    @Override
//    protected void onPostExecute(String s) {
//        //Runs on main Thread
//        //reports the results
//        super.onPostExecute(s);
//        EventBus.getDefault().post(new AsyncTaskEvent(s));
//
//
//    }
}
