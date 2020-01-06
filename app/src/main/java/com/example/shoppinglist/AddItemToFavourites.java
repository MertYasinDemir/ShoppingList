package com.example.shoppinglist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class AddItemToFavourites extends Fragment {
    private EditText favItemName;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View addItemView=inflater.inflate(R.layout.add_item_favourites,container,false);
        context=this.getActivity();
        favItemName=(EditText)addItemView.findViewById(R.id.AddItem);
        Button add=(Button)addItemView.findViewById(R.id.AddButton);
        add.setOnClickListener(clickAdd);
        return addItemView;

    }

    AsyncTask<Object, Object, Object> addItemTask = new AsyncTask<Object, Object, Object>(){
        @SuppressLint("WrongThread")
        @Override
        protected Object doInBackground(Object... params){
            Database database=new Database(context);
            database.addItemToFav(favItemName.getText().toString());
            return null;

        }

        @Override
        protected void onPostExecute(Object result){
        }
    };

    public View.OnClickListener clickAdd=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addItemTask.execute((Object[])null);
        }
    };

}
