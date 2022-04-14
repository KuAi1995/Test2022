package com.fulin.clientapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.fulin.serverapplication","com.fulin.serverapplication.BookService"));
        intent.putExtra("name", "book1");
        intent.putExtra("price", 100);
        startService(intent);
    }
}