package com.example.hanoitourist.model;

public class User {
    private int id;
    private String name;
    private String phoneNumOrGmail;
    private String gender;
    private int age;
    private byte[] avt;

    public User(int id, String name, String phoneNumOrGmail, String gender, int age, byte[] avt) {
        this.id = id;
        this.name = name;
        this.phoneNumOrGmail = phoneNumOrGmail;
        this.gender = gender;
        this.age = age;
        this.avt = avt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumOrGmail() {
        return phoneNumOrGmail;
    }

    public void setPhoneNumOrGmail(String phoneNumOrGmail) {
        this.phoneNumOrGmail = phoneNumOrGmail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte[] getAvt() {
        return avt;
    }

    public void setAvt(byte[] avt) {
        this.avt = avt;
    }
}
