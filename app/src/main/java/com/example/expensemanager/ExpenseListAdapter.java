package com.example.expensemanager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class ExpenseListAdapter extends ArrayAdapter<Expenses> {


    DatabaseHelper myDB;
    private static final String TAG = "ExpenseListAdapter";

    private Context mContext;
    private int mResource;


    public ExpenseListAdapter(Context context, int resource, ArrayList<Expenses> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final DatabaseHelper db = new DatabaseHelper(mContext);

        //get the persons information
        final String expense_title = getItem(position).getExpense_title();
        String category = getItem(position).getCategory();
        int amount = getItem(position).getAmount();

        //Create the person object with the information
        Expenses expense = new Expenses(expense_title,category,amount);

        LayoutInflater inflater= LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        final TextView tvexpense_title = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvcategory = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvamount = (TextView) convertView.findViewById(R.id.textView3);

        tvexpense_title.setText(expense_title);
        tvcategory.setText(category);
        tvamount.setText(""+amount);

        tvexpense_title.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String str=  tvexpense_title.getText().toString();

                Cursor data = db.getItemID(str); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }

                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent( mContext , EditDataActivity.class);
                    editScreenIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",expense_title);
                    mContext.startActivity(editScreenIntent);
                }


            }
        });


        return convertView;


    }



}