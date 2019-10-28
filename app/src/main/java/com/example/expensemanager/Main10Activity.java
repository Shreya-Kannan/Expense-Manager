package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main10Activity extends AppCompatActivity implements View.OnClickListener{

    private CardView daycard, yearcard, monthcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        getSupportActionBar().setTitle("Statistics");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        daycard = (CardView) findViewById(R.id.dailystatId);
        yearcard = (CardView) findViewById(R.id.yearstatId);
        monthcard = (CardView) findViewById(R.id.monthstatId);

        daycard.setOnClickListener(this);
        yearcard.setOnClickListener(this);
        monthcard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent i;

        switch (view.getId()) {
            case R.id.dailystatId:
                i= new Intent(this, Main8Activity.class );
                startActivity(i);
                break;

            case R.id.yearstatId:
                i= new Intent(this, Main3Activity.class );
                startActivity(i);
                break;

            case R.id.monthstatId:
                i= new Intent(this, Main9Activity.class );
                startActivity(i);
                break;
            default: break;
        }

    }
}
