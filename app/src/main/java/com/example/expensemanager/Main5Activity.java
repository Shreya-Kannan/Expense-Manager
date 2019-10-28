package com.example.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Main5Activity extends AppCompatActivity implements View.OnClickListener {


    private CardView addexpense,viewexpense;
    DatabaseHelper db;
    int sumexpenses;
    private TextView sumexpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        sumexpense = (TextView)findViewById(R.id.textView9);

        getSupportActionBar().setTitle("Expenses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db= new DatabaseHelper(this);
        sumexpenses=db.sumExpenses();
        String sum= Integer.toString(sumexpenses);
        sumexpense.setText(sum);



        addexpense = (CardView) findViewById(R.id.id1);
        viewexpense = (CardView) findViewById(R.id.id2);


        addexpense.setOnClickListener(this);
        viewexpense.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent i;

        switch (view.getId()) {
            case R.id.id1:
                i= new Intent(this, Main2Activity.class );
                startActivity(i);
                break;
            case R.id.id2:
                i= new Intent(this, ViewListContents.class );
                startActivity(i);
                break;


            default: break;
        }

    }
}
