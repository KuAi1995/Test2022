Android11下实现两个App的AIDL跨进程通信


1.创建服务端工程

2.服务端创建aidl文件，在aidl文件内定义接口（注意如果需要使用非基本数据类型，则需要导入引用类型的包）

3.如果需要传递对象，需要创建对象的AIDL文件，比如Book类，创建Book.java(实现Parcelable接口)和Book.aidl并放在默认生成的aidl包下方便后面复制到客户端

4.gradle文件的android中配置，不然会找不到Book类

sourceSets {
        main {
            java.srcDirs = ['src/main/java', 'src/main/aidl']
        }
    }
5.自定义Service，并在服务端的AndroidManifest配置android:process=":remote"和export为true

6.创建客户端工程，整体拷贝服务端aidl包下的文件到客户端

7.客户端绑定服务，客户端记得在gradle文件的android中同步骤四配置

8.客户端高版本绑定服务时需要setPackage和解决包可见性问题

    包可见性问题解决方案：AndroidManifest.xml的applicationt同级中配置

 <uses-permission android:name="android.permission.QUERY_ALL_PACKAGES"
        tools:ignore="QueryAllPackagesPermission" />
或者

<!--    服务端包名-->
<queries>
    <package android:name="com.example.aidl_server" />
</queries>
9.客户端使用远程service的方法

10.运行两个工程，注意先运行服务端工程

11.同工程不同模块AIDL可提取出来，防止复制错误；不同工程AIDL要保证在服务端和客户端完全一致。

server代码：https://github.com/shuiming11/aidl_server

client代码：https://github.com/shuiming11/aidl_client
