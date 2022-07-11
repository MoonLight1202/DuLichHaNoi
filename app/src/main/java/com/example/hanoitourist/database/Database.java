package com.example.hanoitourist.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DB_NAME = "HanoiTourist.sqlite";
    public static final int VERSION = 3;
    public static final String PLACES_TABLE = "PLACES";
    public static final String USERS_TABLE = "USERS";
    public static final String USERS_FAV_TABLE = "USER_FAV";

    public static SQLiteDatabase db = null;
    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
        db = getWritableDatabase();
    }
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreateDB = "CREATE TABLE "+PLACES_TABLE + "(id integer primary key autoincrement, name text, avt text, info text, lat double,lon double, urlwiki text)";
        sqLiteDatabase.execSQL(sqlCreateDB);
        String sqlCreateDB2 = "CREATE TABLE "+USERS_TABLE + "(id integer primary key autoincrement, name text, phoneNumOrGmail text, gender text, age integer, avt blob)";
        sqLiteDatabase.execSQL(sqlCreateDB2);
        String sqlCreateDB3 = "CREATE TABLE "+USERS_FAV_TABLE + "(id integer primary key autoincrement, id_user integer, id_place integer, is_like integer, FOREIGN KEY (id_user) REFERENCES "+USERS_TABLE +"(id),FOREIGN KEY (id_place) REFERENCES "+PLACES_TABLE +"(id))";
        sqLiteDatabase.execSQL(sqlCreateDB3);
    }
    public void excuteSQL(String sql){
        SQLiteDatabase database = getReadableDatabase();
        database.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+PLACES_TABLE);
        onCreate(sqLiteDatabase);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+USERS_TABLE);
        onCreate(sqLiteDatabase);
    }
    public Cursor selectSQL(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    public int countUserSQL(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor mCount= database.rawQuery("SELECT COUNT(*) FROM "+ USERS_TABLE, null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        return count;
    }
    public int countPlaceSQL(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor mCount= database.rawQuery("SELECT COUNT(*) FROM "+ PLACES_TABLE, null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        return count;
    }
    public int SelectPhoneUserSQL(String sql){
        SQLiteDatabase database = getReadableDatabase();
        Cursor mPhone= database.rawQuery(sql, null);
        mPhone.moveToFirst();
        int phoneNum= mPhone.getInt(0);
        mPhone.close();
        return phoneNum;
    }
    public Cursor updateFav(String sql){
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery(sql,null);
    }
}

