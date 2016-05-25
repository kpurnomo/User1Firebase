package com.example.kpurnomo.user1;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class DemoService extends Service {

    private String[] words;

    public DemoService() {
    }

    final class MyThread implements Runnable
    {
        int startId;

        public MyThread(int startId)
        {
            this.startId = startId;
        }

        @Override
        public void run() {

            synchronized(this)
            {
                try {
                    Firebase mref;
                    mref = new Firebase("https://promita2.firebaseio.com/");

                    for(int i = 0; i < words.length; i++) {
                        wait(3000);

                        mref.setValue(words[i]);
                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                stopSelf();
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(DemoService.this, "Service started", Toast.LENGTH_SHORT).show();

        Bundle bundle = intent.getExtras();

        words = bundle.getStringArray("words");

        Thread thread = new Thread(new MyThread(startId));
        thread.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {


        super.onDestroy();
    }
}
