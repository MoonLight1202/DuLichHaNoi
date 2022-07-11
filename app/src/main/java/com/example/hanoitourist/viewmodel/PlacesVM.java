package com.example.hanoitourist.viewmodel;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hanoitourist.database.Database;
import com.example.hanoitourist.model.Places;

import java.util.ArrayList;

public class PlacesVM {
    public ArrayList<Places> getALl(){
        SQLiteDatabase database = Database.db;
        ArrayList<Places> list = new ArrayList<>();
        String sqlSelect = "SELECT * FROM "+Database.PLACES_TABLE;
        Cursor cs = database.rawQuery(sqlSelect, null);
        while (cs.moveToNext()){
            int id = cs.getInt(0);
            String ten = cs.getString(1);
            String avt = cs.getString(2);
            String info = cs.getString(3);
            double lat = cs.getDouble(4);
            double lon = cs.getDouble(5);
            String urlwiki = cs.getString( 6 );
            list.add(new Places(id,ten,avt,info,lat,lon,urlwiki));
        }
        return list;
    }
    public long insert(Places p){
        SQLiteDatabase database = Database.db;
        ContentValues cvp = new ContentValues();
        cvp.put("name", p.getName());
        cvp.put("avt", p.getAvt());
        cvp.put("info", p.getInfo());
        cvp.put("lat", p.getLat());
        cvp.put("lon", p.getLon());
        cvp.put( "urlwiki",p.getUrlwiki() );
        return database.insert(Database.PLACES_TABLE, null, cvp);
    }
}
