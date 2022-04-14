package com.fulin.serverapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Description Book
 * <p>
 * author FuLin
 * created at 2022/4/14 22:25
 */
public class Book {
    private String name;
    private int price;

    public Book() {
    }

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    //方便打印数据
    @Override
    public String toString() {
        return "name : " + name + " , price : " + price;
    }
}
