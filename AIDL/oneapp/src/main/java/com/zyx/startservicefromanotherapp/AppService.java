package com.zyx.startservicefromanotherapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AppService extends Service {
    private static final String TAG = AppService.class.getSimpleName();
    public AppService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new IAppServiceRemoteBinder.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }
        };
    }

    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("Service started");
        Log.d(TAG, "Service started");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        System.out.println("Service destroy");
        Log.d(TAG, "Service destroy");
    }
}