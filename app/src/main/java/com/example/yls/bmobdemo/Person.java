package com.example.yls.bmobdemo;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by yls on 2017/3/6.
 */

public class Person extends BmobObject {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    private String name;
    private int age;
    private String address;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int score;

    public BmobFile getHeadImg() {
        return headImg;
    }

    public void setHeadImg(BmobFile headImg) {
        this.headImg = headImg;
    }

    private BmobFile headImg;
}
