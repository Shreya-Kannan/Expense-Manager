package com.example.expensemanager;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewListContents extends AppCompatActivity {

    DatabaseHelper myDB;
    ArrayList<Expenses> expenseList;
    Expenses expense;
    private static final String TAG = "ViewListContents";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_contents);

        getSupportActionBar().setTitle("View Expenses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDB = new DatabaseHelper(this);

        expenseList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(ViewListContents.this,"The Database is empty .",Toast.LENGTH_LONG).show();
        }else {
            int i = 0;
            while (data.moveToNext()) {
                expense = new Expenses(data.getString(1), data.getString(2), data.getInt(3));
                expenseList.add(i, expense);
                //System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getInt(3)+" "+data.getString(4));
                i++;
            }

            ExpenseListAdapter adapter = new ExpenseListAdapter(this, R.layout.adapter_view_layout, expenseList);
            ListView mListView = (ListView) findViewById(R.id.listView);
            mListView.setAdapter(adapter);

        }


    }


}