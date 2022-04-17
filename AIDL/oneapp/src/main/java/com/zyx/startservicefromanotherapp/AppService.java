package com.zyx.startservicefromanotherapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AppService extends Service {
    private static final String TAG = AppService.class.getSimpleName();

    private String data = "默认数据";

    private boolean running = false;

    public AppService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new IAppServiceRemoteBinder.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public void setData(String data) throws RemoteException {
                AppService.this.data = data;
            }


        };
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"Service started");

        new Thread(){
            @Override
            public void run() {
                super.run();

                Log.d(TAG,"data is : " + data );
                running = true;
                while (running){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        running = false;
        Log.d(TAG,"Service destory");
    }
}