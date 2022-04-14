package com.fulin.clientapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.fulin.serverapplication","com.fulin.serverapplication.BookService"));
        intent.putExtra("name", "book1");
        intent.putExtra("price", 100);
        startService(intent);
    }
}