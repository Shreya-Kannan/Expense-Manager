package com.example.expensemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddBudgetActivity extends AppCompatActivity {

    Button btnAdd;
    EditText budget;
    BudgetDB myDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);

        getSupportActionBar().setTitle("Add Budget");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAdd = (Button) findViewById(R.id.button2);
        budget = (EditText) findViewById(R.id.editText3);
        myDB = new BudgetDB(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String len= budget.getText().toString();
                float val = Float.parseFloat( budget.getText().toString() );
                if(len.length() != 0){
                    AddData( val);
                    budget.setText("");
                }else{
                    Toast.makeText(AddBudgetActivity.this,"You must put something in the text field!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public void AddData(float bud ){
        boolean insertData = myDB.addData(bud);

        if(insertData==true){
            Toast.makeText(AddBudgetActivity.this,"Successfully Entered Data!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(AddBudgetActivity.this,"Something went wrong :(.",Toast.LENGTH_LONG).show();
        }
    }
}
