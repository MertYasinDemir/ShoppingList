package com.example.shoppinglist;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.CursorAdapter;

public class GetFavouritesItems extends AsyncTask<Object,Object, Cursor> {
    private Database database;
    private CursorAdapter favItemAdapter;

    public GetFavouritesItems(Context context, CursorAdapter favItemAdapter){
        database=new Database(context);
        this.favItemAdapter=favItemAdapter;

    }

    @Override
    protected Cursor doInBackground(Object... params){
        database.open();
        return database.getFavItem();
    }

    @Override
    protected void onPostExecute(Cursor result){
        favItemAdapter.changeCursor(result);
        database.close();
    }
}
