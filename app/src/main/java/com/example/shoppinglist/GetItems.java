package com.example.shoppinglist;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.CursorAdapter;

public class GetItems extends AsyncTask<Object,Object, Cursor> {
    private Database database;
    private CursorAdapter itemAdapter;

    public GetItems(Context context, CursorAdapter itemAdapter){
        database=new Database(context);
        this.itemAdapter=itemAdapter;

    }

    @Override
    protected Cursor doInBackground(Object... params){
        database.open();
        return database.getItem();
    }

    @Override
    protected void onPostExecute(Cursor result){
        itemAdapter.changeCursor(result);
        database.close();
    }
}
