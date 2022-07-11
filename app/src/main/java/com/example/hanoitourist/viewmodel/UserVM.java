package com.example.hanoitourist.viewmodel;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hanoitourist.database.Database;
import com.example.hanoitourist.model.User;

import java.util.ArrayList;
public class UserVM {
    public ArrayList<User> getALl(){
        SQLiteDatabase database = Database.db;
        ArrayList<User> list = new ArrayList<>();
        String sqlSelect = "SELECT * FROM "+Database.USERS_TABLE;
        Cursor cs = database.rawQuery(sqlSelect, null);
        while (cs.moveToNext()){
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String phoneNumOrGmail = cs.getString(2);
            String gender = cs.getString(3);
            int age = cs.getInt(4);
            byte[] avt = cs.getBlob( 5 );
            list.add(new User(id,name,phoneNumOrGmail,gender,age,avt));
        }
        return list;
    }
    public long insert(User u){
        SQLiteDatabase database = Database.db;
        ContentValues cv = new ContentValues();
        cv.put("name", u.getName());
        cv.put("phoneNumOrGmail", u.getPhoneNumOrGmail());
        cv.put("gender", u.getGender());
        cv.put("age", u.getAge());
        cv.put("avt", u.getAvt());
        return database.insert(Database.USERS_TABLE, null, cv);
    }
    public long update(User u){
        SQLiteDatabase database = Database.db;
        ContentValues cv = new ContentValues();
        cv.put("name", u.getName());
        cv.put("phoneNumOrGmail", u.getPhoneNumOrGmail());
        cv.put("gender", u.getGender());
        cv.put("age", u.getAge());
        cv.put("avt", u.getAvt());
        String whereClause = "id = ?";
        String[] whereArgs = {u.getId()+""};
        return database.update(Database.USERS_TABLE, cv, whereClause, whereArgs);
    }
}
