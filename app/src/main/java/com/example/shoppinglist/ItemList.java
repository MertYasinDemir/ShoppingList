package com.example.shoppinglist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import androidx.fragment.app.ListFragment;

public class ItemList extends ListFragment {
    private CursorAdapter itemAdapter;
    private Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View itemListView=inflater.inflate(R.layout.item_list,container,false);
        context=this.getActivity();
        String[] fieldList=new String[]{"name"};
        int[] showList=new int[]{R.id.ItemName};
        itemAdapter=new SimpleCursorAdapter(this.getActivity(),R.layout.item_cell,null,fieldList,showList,0);
        setListAdapter(itemAdapter);
        new GetItems(context,itemAdapter).execute((Object[])null);
        return itemListView;
    }
}
