package com.fulin.serverapplication;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description BookService
 *
 * @Author FuLin
 * @Time 2022/4/15 22:04
 */
public class BookService extends Service {
    private static final String TAG = BookService.class.getSimpleName();
    private List<Book> books = new ArrayList<>();

    // 实例化AIDL的Stub类(Binder的子类)
    IBookDispose.Stub mBinder = new IBookDispose.Stub() {

        //重写接口里定义的方法
        @Override
        public void testService() throws RemoteException {
            Log.d(TAG, "客户端通过AIDL与远程后台成功通信" +
                    " 当前进程名：" + getProcessName(getApplicationContext()));
        }

        @Override
        public List<Book> getBooks() throws RemoteException {
            return books;
        }

        @Override
        public Book addBook(Book book) throws RemoteException {
            // book.setName("bookName");
            books.add(book);
            Log.d(TAG, "add book: " + book.getName());
            return book;
        }
    };

    public static String getProcessName(Context cxt) {
        int pid = android.os.Process.myPid();
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    //在onBind()返回Stub类实例
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }
}