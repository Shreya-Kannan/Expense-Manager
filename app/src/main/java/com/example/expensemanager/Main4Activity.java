package com.example.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {

    private CardView budgetcard, expensecard, statcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        getSupportActionBar().setTitle("Expense Manager");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        expensecard = (CardView) findViewById(R.id.expenseId);
        budgetcard = (CardView) findViewById(R.id.budgetId);
        statcard = (CardView) findViewById(R.id.statId);

        expensecard.setOnClickListener(this);
        budgetcard.setOnClickListener(this);
        statcard.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        Intent i;

        switch (view.getId()) {
            case R.id.expenseId:
                i= new Intent(this, Main5Activity.class );
                startActivity(i);
                break;

            case R.id.budgetId:
                i= new Intent(this, Budget.class );
                startActivity(i);
                break;

            case R.id.statId:
                i= new Intent(this, Main7Activity.class );
                startActivity(i);
                break;
            default: break;
        }

    }
}
