package com.example.kpurnomo.user1;

import com.firebase.client.Firebase;

/**
 * Created by kpurnomo on 5/24/2016.
 */
public class FirebaseContext extends android.app.Application {
    public void onCreate()
    {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
