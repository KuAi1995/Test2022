package com.fulin.serverapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description BookService
 *
 * @Author FuLin
 * @Time 2022/4/14 22:27
 */
public class BookService extends Service {
    private static final String TAG = BookService.class.getSimpleName();

    private String name;
    private int price;
    private Book book;
    private List<Book> books= new ArrayList();

    public BookService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        name = intent.getStringExtra("name");
        price = intent.getIntExtra("price", 10);
        book = new Book(name, price);
        books.add(book);
        Log.d(TAG, "book's name: " + books.get(0).getName() + " book's price: " + books.get(0).getPrice());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }
}