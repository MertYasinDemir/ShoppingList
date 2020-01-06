package com.example.shoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class Database{

    private SQLiteDatabase database;
    private DatabaseOpenHelper databaseOpenHelper;

    public Database(Context context){
        databaseOpenHelper=new DatabaseOpenHelper(context,"ShoppingList",null,1);
    }


    public void open() throws SQLException{
        database=databaseOpenHelper.getWritableDatabase();
    }

    public void close(){
        if(database!=null)
            database.close();

    }


    public void addItem(String name){
        ContentValues newItem=new ContentValues();
        newItem.put("name",name);
        open();
        database.insert("item",null,newItem);
        close();

    }

    public void addItemToFav(String name){
        ContentValues newFavItem = new ContentValues();
        newFavItem.put("name", name);
        open();
        database.insert("favItem", null, newFavItem);
        close();
    }

    public Cursor getItem(){
        return database.query("item", new String[] {"_id", "name"}, null, null, null, null, "name");
    }

    public Cursor getFavItem(){
        return database.query("favItem", new String[] {"_id", "name"}, null, null, null, null, "name");
    }


    private class DatabaseOpenHelper extends SQLiteOpenHelper{

        public DatabaseOpenHelper(Context context, String name, CursorFactory factory, int version){
            super(context, name, factory, version);
        }

        public void onCreate(SQLiteDatabase db){
            String sqlItem = "CREATE TABLE item" +
                    "(_id integer primary key autoincrement," +
                    "name TEXT);";
            db.execSQL(sqlItem);

            String sqlItemFav = "CREATE TABLE favItem" +
                    "(_id integer primary key autoincrement," +
                    "name TEXT);";
            db.execSQL(sqlItemFav);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        }
    }
}
