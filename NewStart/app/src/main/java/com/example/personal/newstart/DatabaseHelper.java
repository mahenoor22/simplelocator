package com.example.personal.newstart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MayDay_Database.db";
    public static final String TABLE_NAME = "info_table";
    public static final String col1 = "ID";
    public static final String col2 = "NAME";
    public static final String col3 = "NUMBER";
    public static final String TABLE_1 = "id_remember";
    public static final String Col = "ID" ;
    public static int id = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT , NUMBER LONG)");
        db.execSQL("create table " + TABLE_1+ "(ID INTEGER)");
        db.execSQL("insert into " + TABLE_1 + " values " + "( " + id + " )" );
        if (id<4)
            id++;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " +TABLE_NAME );
        db.execSQL("drop table if exists " +TABLE_1);
        onCreate(db);
    }

    public void createTables()
    {
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public boolean insertData(int id ,String name , String number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1,id);
        contentValues.put(col2,name);
        contentValues.put(col3,number);

        Long inserted = db.insert(TABLE_NAME , null ,contentValues);
        if (inserted==-1)
            return false;
        else
            return true;

    }

    public Cursor getId()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT ID from " + TABLE_1 , null);

        return cursor ;
    }

    public Cursor getAllData(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where  ID = ? " , new String[] {String.valueOf(id)});

        return cursor;
    }

    public boolean updateData(int id ,String name , String number)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1 , id);
        contentValues.put(col2,name);
        contentValues.put(col3 ,number);
        db.update(TABLE_NAME , contentValues , "ID = ? " ,new String[]{String.valueOf(id)});
        return true;
    }

    public boolean updateID(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col , id);
        db.update(TABLE_1  , contentValues , null , null);
        return true ;
    }

    public int tellCount()
    {
        int ans ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(ID) from " + TABLE_NAME , null);
        if(cursor != null && cursor.moveToFirst()) {
            ans = cursor.getInt(0);
        }
        else
            ans = -1 ;
        return ans ;
    }

}
