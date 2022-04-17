package com.fulin.clientapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fulin.bookaidl.Book;
import com.fulin.bookaidl.BookAidl;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button bindService;

    //定义aidl接口变量
    private BookAidl mAIDLService;

    //创建ServiceConnection的匿名类
    private ServiceConnection connection = new ServiceConnection() {

        //重写onServiceConnected()方法和onServiceDisconnected()方法
        //在Activity与Service建立关联和解除关联的时候调用
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        //在Activity与Service建立关联时调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            //BookAidl.Stub.asInterface()方法将传入的IBinder对象传换成了mAIDLService对象
            mAIDLService = BookAidl.Stub.asInterface(service);

            try {

                //通过该对象调用在IBookDisposeService.aidl文件中定义的接口方法,从而实现跨进程通信
                mAIDLService.testService();

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
    private Button bind_service2;
    private Button bind_service3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService = (Button) findViewById(R.id.bind_service);
        bind_service2 = (Button) findViewById(R.id.bind_service2);
        bind_service3 = (Button) findViewById(R.id.bind_service3);

        //设置绑定服务的按钮
        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "点击了[绑定服务]按钮");

                //通过Intent指定服务端的服务名称和所在包，与远程Service进行绑定
                //参数与服务器端的action要一致,即"服务器包名.aidl接口文件名"
                Intent intent = new Intent("com.fulin.serverapplication.BookService");

                //Android5.0后无法只通过隐式Intent绑定远程Service
                //需要通过setPackage()方法指定包名
                intent.setPackage("com.fulin.serverapplication");

                //绑定服务,传入intent和ServiceConnection对象
                bindService(intent, connection, Context.BIND_AUTO_CREATE);

            }
        });

        bind_service2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("bookTest");
                Log.e(TAG, "set book name: " + book.getName());
                try {
                    mAIDLService.addBook(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        bind_service3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = null;
                try {
                    book = mAIDLService.getBooks().get(0);
                    Log.e(TAG, "get book name: " + book.getName());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}