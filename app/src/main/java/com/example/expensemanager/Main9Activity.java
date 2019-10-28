package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main9Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    DatabaseHelper myDB;
    String dates[];
    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    String[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    int amount[];
    BarChart mChart;
    Button add;
    EditText year;
    String year1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        getSupportActionBar().setTitle("Monthly Statistics");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        add = (Button) findViewById(R.id.ok);
        year = (EditText) findViewById(R.id.year);
        mChart =(BarChart) findViewById(R.id.bargraph2);

        getSupportActionBar().setTitle("Monthly Statistics");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDB = new DatabaseHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year1= year.getText().toString();
                System.out.println(year1);
                year.setText("");
                Cursor data = myDB.getListContents();
                int numRows = data.getCount();
                dates = new String[numRows];
                amount = new int[12];
                if (numRows == 0) {
                    Toast.makeText(Main9Activity.this, "The Database is empty .", Toast.LENGTH_LONG).show();
                } else {
                    int i = 0;
                    while (data.moveToNext()) {
                        String date1=data.getString(4);
                        dates[i]=date1;
                        //System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getInt(3)+" "+data.getString(4));
                        i++;
                    }
                }

                for(int i=0;i<dates.length;i++){
                    String[] parts= dates[i].split("/");
                    for(int j=0;j<month.length;j++){
                        if(  parts[1].equals(month[j]) && parts[2].equals(year1)){
                            amount[j]=myDB.getmonthamount(parts[1].trim());
                        }

                    }

                }
                System.out.println(year1);

                System.out.println(Arrays.toString(dates));
                System.out.println(Arrays.toString(months));
                System.out.println(Arrays.toString(amount));


                mChart.animateY(2000);


                mChart.setDrawBarShadow(false);
                mChart.setDrawValueAboveBar(false);
                mChart.getDescription().setEnabled(false);



                XAxis xaxis = mChart.getXAxis();
                xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xaxis.setGranularity(1f);
                xaxis.setValueFormatter(new IndexAxisValueFormatter(months));

                YAxis yAxisLeft = mChart.getAxisLeft();
                yAxisLeft.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
                yAxisLeft.setEnabled(true);

                mChart.getAxisRight().setEnabled(false);

                Legend legend = mChart.getLegend();
                legend.setEnabled(false);

                ArrayList<BarEntry> valueSet1 = new ArrayList<BarEntry>();

                for (int i = 0; i < months.length; i++) {
                    BarEntry entry = new BarEntry(i,amount[i]);
                    valueSet1.add(entry);
                }

                List<IBarDataSet> dataSets = new ArrayList<>();
                BarDataSet barDataSet = new BarDataSet(valueSet1, "Daily Expenses");
                barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                dataSets.add(barDataSet);
                mChart.setVisibleXRange(100,100);


                BarData data2 = new BarData(dataSets);
                mChart.setData(data2);
                mChart.invalidate();
            }
        });






    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
