package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main7Activity extends AppCompatActivity implements View.OnClickListener{

    private CardView piechart,bargraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        getSupportActionBar().setTitle("Statistics");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        piechart = (CardView) findViewById(R.id.pi1);
        bargraph = (CardView) findViewById(R.id.bar);

        piechart.setOnClickListener(this);
        bargraph.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent i;

        switch (view.getId()) {
            case R.id.pi1:
                i= new Intent(this, Main6Activity.class );
                startActivity(i);
                break;
            case R.id.bar:
                i= new Intent(this, Main10Activity.class );
                startActivity(i);
                break;


            default: break;
        }

    }
}
