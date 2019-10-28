package com.example.expensemanager;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Budget extends AppCompatActivity implements View.OnClickListener {



    BudgetDB DB;
    DatabaseHelper db;
    private CardView addbudget;
    private TextView budget, amountleft;
    float b;
    float amtleft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        getSupportActionBar().setTitle("Budget");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addbudget = (CardView) findViewById(R.id.id3);
        budget = (TextView) findViewById(R.id.textBudget);
        amountleft= (TextView) findViewById(R.id.textView6);

        addbudget.setOnClickListener(this);

        db= new DatabaseHelper(this);

        DB = new BudgetDB(this);
        Cursor data = DB.getListContents();
        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(Budget.this,"The Database is empty .",Toast.LENGTH_LONG).show();
        }
        else {
            int i = 0;
            while (data.moveToNext()) {
                b=  data.getFloat(1);
            }

            String bu= Float.toString(b);
            budget.setText(bu);
        }

        if(numRows!=0) {
            amtleft = b - db.sumExpenses();
            String al = Float.toString(amtleft);
            amountleft.setText(al);
            if (amtleft > 0) {
                amountleft.setTextColor(Color.GREEN);
            } else {
                amountleft.setTextColor(Color.RED);
            }
        }

    }

    @Override
    public void onClick(View view) {

        Intent i;

        switch (view.getId()) {

            case R.id.id3:
                i = new Intent(this, AddBudgetActivity.class);
                startActivity(i);
                break;

        }
    }

}