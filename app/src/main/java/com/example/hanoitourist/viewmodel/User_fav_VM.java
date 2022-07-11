package com.example.hanoitourist.viewmodel;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hanoitourist.model.User_Fav;
import com.example.hanoitourist.database.Database;

import java.util.ArrayList;

public class User_fav_VM {
    public ArrayList<User_Fav> getALl(){
        SQLiteDatabase database = Database.db;
        ArrayList<User_Fav> list = new ArrayList<>();
        String sqlSelect = "SELECT * FROM "+Database.USERS_FAV_TABLE;
        Cursor cs = database.rawQuery(sqlSelect, null);
        while (cs.moveToNext()){
            int id = cs.getInt(0);
            int id_user = cs.getInt(1);
            int id_place = cs.getInt(2);
            int is_like = cs.getInt(3);
            list.add(new User_Fav(id,id_user,id_place,is_like));
        }
        return list;
    }
    public long insert(User_Fav user_fav){
        SQLiteDatabase database = Database.db;
        ContentValues cvp = new ContentValues();
        cvp.put("id_user", user_fav.getId_user());
        cvp.put("id_place", user_fav.getId_place());
        cvp.put("is_like", user_fav.getIs_like());
        return database.insert(Database.USERS_FAV_TABLE, null, cvp);
    }

    public int update(User_Fav user_fav){
        SQLiteDatabase database = Database.db;
        ContentValues cv = new ContentValues();
        cv.put("id_user", user_fav.getId_user());
        cv.put("id_place", user_fav.getId_place());
        cv.put("is_like", user_fav.getIs_like());
        String whereClause = "id = ?";
        String[] whereArgs = {user_fav.getId()+""};
        return database.update(Database.USERS_FAV_TABLE, cv, whereClause, whereArgs);
    }
}
