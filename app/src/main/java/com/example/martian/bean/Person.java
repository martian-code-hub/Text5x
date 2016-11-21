package com.example.martian.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by yangpei on 2016/11/11.
 */

public class Person implements Parcelable {

    private String name;
    private String age;
    private String sex;
    private ArrayList<String> roles;

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.roles);
        dest.writeString(this.name);
        dest.writeString(this.age);
        dest.writeString(this.sex);
    }

    public Person() {
    }

    protected Person(Parcel parcel) {
        this.roles = parcel.createStringArrayList();
        this.name = parcel.readString();
        this.age = parcel.readString();
        this.sex = parcel.readString();
    }

    public static final Creator<Person> create = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[0];
        }
    };
}
