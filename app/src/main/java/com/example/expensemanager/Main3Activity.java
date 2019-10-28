package com.example.expensemanager;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    public static final String TAG= "Main3Activity";

    DatabaseHelper myDB;
    int y[];
    int year[]=new int[30];
    int sum[]=new int[30];
    int year1[]=new int[30];
    ArrayList<Integer> ye = new ArrayList<Integer>();
    ArrayList<Integer> su = new ArrayList<Integer>();
    //ArrayList<String> year = new ArrayList<String>();
    int k;
    BarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getSupportActionBar().setTitle("Bar Graph(Year vs Amount)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        chart = (BarChart) findViewById(R.id.bargraph);

        myDB = new DatabaseHelper(this);

        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        y = new int[numRows];
        if (numRows == 0) {
            Toast.makeText(Main3Activity.this, "The Database is empty .", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                String date1=data.getString(4);
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date yourDate = formatter.parse(date1);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(yourDate);
                    y[i]=calendar.get(Calendar.YEAR);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getInt(3)+" "+data.getString(4));
                i++;
            }
        }


        System.out.println(Arrays.toString(y));
        for (int i = 0; i < y.length; i++)
        {
            int j;
            for (j = 0; j < i; j++)
                if (y[i] == y[j])
                    break;
            if (i == j && y[i]!=0)
                //ye.add(i,y[i]);
                year[i]=y[i];

        }
        System.out.println(Arrays.toString(year));

        //Collections.sort(ye);
        Arrays.sort(year);
        int y1=0;

        for(int i=0;i<year.length;i++)
        {
            if(year[i]!=0)
            {
                year1[y1++]=year[i];
            }
        }

        System.out.println(Arrays.toString(year1));


        for(int i=0;i<year1.length;i++) {

            if(year1[i]!=0)
            sum[i]=myDB.distyears(year1[i]);
        }

        System.out.println(Arrays.toString(sum));



        int[] numArr = {1, 2, 3, 4, 5, 6};
        List<BarEntry> entries = new ArrayList<BarEntry>();
        int i=0;
        for (int num : year1) {
            if(num!=0)
            entries.add(new BarEntry(num, sum[i++]));
        }
        chart.animateY(1000);
        BarDataSet dataSet = new BarDataSet(entries, "Years");
        BarData data1 = new BarData(dataSet);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        ValueFormatter xAxisFormatter = new DayAxisValueFormatter(chart);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);

        chart.setData(data1);
        chart.invalidate();
    }

    public class DayAxisValueFormatter extends ValueFormatter {
        private final BarLineChartBase<?> chart;
        public DayAxisValueFormatter(BarLineChartBase<?> chart) {
            this.chart = chart;
        }
        @Override
        public String getFormattedValue(float value) {
            return "" + value;
        }
    }




        /*ArrayList<BarEntry> barEntries= new ArrayList<>();
        for(int i=0;i<su.size();i++){
            barEntries.add(new BarEntry(su.get(i),i));
        }

        for(int i=0;i<ye.size();i++)
        {
            year.add(ye.get(i).toString());
        }

        BarDataSet barDataSet = new BarDataSet(barEntries,"Amount");

        BarData theData= new BarData(barDataSet);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(theData);

*/


        /*for(int i=0;i<year.length;i++)
        {
            if(year[i]==0)
                break;
            barEntries.add(new Entry(sum[i],i));
        }


        final String[] years= new String[30];
        for(int i=0;i<year.length;i++)
        {
            years[i]= Integer.toString(year[i]);
        }*/






    //}


}
