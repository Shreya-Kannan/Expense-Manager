package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
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

public class Main8Activity extends AppCompatActivity {

    DatabaseHelper myDB;
    String dates[];
    int amount[];
    BarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        getSupportActionBar().setTitle("Date-wise Statistics");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDB = new DatabaseHelper(this);
        mChart = (BarChart) findViewById(R.id.bargraph1);

        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        dates = new String[numRows];
        amount = new int[numRows];
        if (numRows == 0) {
            Toast.makeText(Main8Activity.this, "The Database is empty .", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                String date1=data.getString(4);
                dates[i]=date1;
                //System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getInt(3)+" "+data.getString(4));
                i++;
            }
        }

        for(int i=0;i<dates.length;i++)
        {
            amount[i]=myDB.getamount(dates[i]);
        }

        System.out.println(Arrays.toString(dates));
        System.out.println(Arrays.toString(amount));


        //String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun"};
        mChart.animateY(2000);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(false);
        mChart.getDescription().setEnabled(false);


        XAxis xaxis = mChart.getXAxis();
        xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xaxis.setGranularity(1f);
        xaxis.setValueFormatter(new IndexAxisValueFormatter(dates));

        YAxis yAxisLeft = mChart.getAxisLeft();
        yAxisLeft.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        yAxisLeft.setEnabled(true);

        mChart.getAxisRight().setEnabled(false);

        Legend legend = mChart.getLegend();
        legend.setEnabled(false);

        ArrayList<BarEntry> valueSet1 = new ArrayList<BarEntry>();

        for (int i = 0; i < dates.length; i++) {
            BarEntry entry = new BarEntry(i,amount[i]);
            valueSet1.add(entry);
        }

        List<IBarDataSet> dataSets = new ArrayList<>();
        BarDataSet barDataSet = new BarDataSet(valueSet1, "Daily Expenses");
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSets.add(barDataSet);

        BarData data2 = new BarData(dataSets);
        mChart.setData(data2);
        mChart.invalidate();
    }
}
