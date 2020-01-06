package com.example.shoppinglist;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;



public abstract class ShoppingListMain extends Activity implements ActionBar.TabListener {
    private Fragment addItem;
    private Fragment itemList;
    private Fragment addItemToFav;
    private Fragment favItemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list_main);
        final ActionBar actionBar=getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText("Add item").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Add favourite").setTabListener(this));


    }
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        switch (tab.getPosition()){
            case 0:
                addItem=Fragment.instantiate(this,AddItem.class.getName());
                fragmentTransaction.add(android.R.id.content, addItem, "AddItem");
                break;
            case 1:
                itemList=Fragment.instantiate(this,ItemList.class.getName());
                fragmentTransaction.add(android.R.id.content, itemList, "ItemList");
                break;
            case 2:
                addItemToFav=Fragment.instantiate(this,AddItemToFavourites.class.getName());
                fragmentTransaction.add(android.R.id.content, addItemToFav, "AddItemToFavourites");
                break;
            case 3:
                favItemList=Fragment.instantiate(this,FavouritesItemList.class.getName());
                fragmentTransaction.add(android.R.id.content, favItemList, "FavouritesItemList");
                break;
        }

    }

    public void onTabUnselected(ActionBar.Tab tab,
                                FragmentTransaction fragmentTransaction) {
        switch (tab.getPosition()){
            case 0:
                fragmentTransaction.detach(addItem);
                break;
            case 1:
                fragmentTransaction.detach(itemList);
                break;
            case 2:
                fragmentTransaction.detach(addItemToFav);
                break;
            case 3:
                fragmentTransaction.detach(favItemList);
                break;

        }
    }


    @SuppressLint("ResourceType")
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id){
        PopupMenu menu=new PopupMenu(ShoppingListMain.this,view);
        menu.getMenuInflater().inflate(R.layout.pop_up_menu,menu.getMenu());

        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.AddQuantity:
                        AlertDialog.Builder builder=new AlertDialog.Builder(ShoppingListMain.this);
                        View view= LayoutInflater.from(ShoppingListMain.this).inflate(R.layout.quantity_price_dialog,null,false);
                        builder.setTitle("Add Quantity:");
                        final EditText text=view.findViewById(R.id.AddQuantity);
                        text.setText(itemList.getText(position));

                        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ShoppingListMain.this,"Quantity Added",Toast.LENGTH_SHORT).show();

                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        break;

                    case R.id.AddPrice:
                        AlertDialog.Builder builder2=new AlertDialog.Builder(ShoppingListMain.this);
                        View view2= LayoutInflater.from(ShoppingListMain.this).inflate(R.layout.quantity_price_dialog,null,false);
                        builder2.setTitle("Add Price:");
                        final EditText text2=view2.findViewById(R.id.AddPrice);
                        text2.setText(itemList.getText(position));

                        builder2.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ShoppingListMain.this,"Price Added",Toast.LENGTH_SHORT).show();

                            }
                        });
                        builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        break;
                }
                return false;

            }
        });

    }
}
