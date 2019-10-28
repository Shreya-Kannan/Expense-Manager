package com.example.expensemanager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;

public class Main6Activity extends AppCompatActivity {

    DatabaseHelper db;
    int[] arr = new int[5];

    private static String TAG = "Main6Activity";

    String[] category={"Food","Travel","Grocery","Clothing","Others"};
    PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        getSupportActionBar().setTitle("Pie Chart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pieChart = (PieChart) findViewById(R.id.idPieChart);



        db= new DatabaseHelper(this);

       /* sumFood=db.sumCategory("Food");
        sumTravel=db.sumCategory("Travel");
        sumGrocery=db.sumCategory("Grocery");
        sum*/

        System.out.println(db.sumCategory("Food"));


        for(int i=0;i<arr.length;i++)
        {
            arr[i]=0;
        }

        for(int i=0;i<arr.length;i++)
        {
            arr[i]= db.sumCategory(category[i]);
        }

        System.out.println(Arrays.toString(arr));

        Log.d(TAG, "onCreate: starting to create chart");



        pieChart.setRotationEnabled(true);
        pieChart.animateY(1000);
        pieChart.getDescription().setText("Amount spent in each Category");
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(40f);
        pieChart.setTransparentCircleAlpha(1);
        pieChart.setCenterText("Category");
        pieChart.setCenterTextSize(10);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!

        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());


                int pos1 = e.toString().indexOf("y: ");
                String amount = e.toString().substring(pos1 + 3);

                for(int i = 0; i < arr.length; i++){
                    if(arr[i] == Float.parseFloat(amount)){
                        pos1 = i;
                        break;
                    }
                }
                String cat = category[pos1];
                Toast.makeText(Main6Activity.this, "Category " + cat + "\n" + "Amount: Rs." + amount , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){
            yEntrys.add(new PieEntry(arr[i] , i));
        }

        for(int i = 1; i < category.length; i++){
            xEntrys.add(category[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Category statistics");
        pieDataSet.setSliceSpace(4);
        pieDataSet.setValueTextSize(15);

        /*//add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);*/

        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
