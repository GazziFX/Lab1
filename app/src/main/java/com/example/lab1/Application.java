package com.example.lab1;

import android.content.Context;

import java.lang.ref.WeakReference;

public class Application extends android.app.Application {
    private static WeakReference<Context> context = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = new WeakReference<>(getApplicationContext());
    }

    public static Context getContext()
    {
        return context.get();
    }
}
