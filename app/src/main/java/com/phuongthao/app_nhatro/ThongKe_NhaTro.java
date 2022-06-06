package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThongKe_NhaTro extends AppCompatActivity {

    int conphong, daduocdat,hetphong;

    String auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke__nha_tro);

        getSupportActionBar().setTitle("THỐNG KÊ NHÀ TRỌ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences sharedPreferences = getSharedPreferences("AuthID", Context.MODE_PRIVATE);
        auth = sharedPreferences.getString("AUTH_ID", "aaaaaa");
        Log.d("key", auth);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            conphong = bundle.getInt("ConPhong");
            daduocdat = bundle.getInt("DaDuocDat");
            hetphong = bundle.getInt("HetPhong");
            Log.d("conphong", String.valueOf(conphong));
            Log.d("hetphong", String.valueOf(hetphong));
            Log.d("daduocdat", String.valueOf(daduocdat));
        }


        BarChart chart = findViewById(R.id.chart);

        AxisBase axisBase = chart.getAxis(YAxis.AxisDependency.LEFT);
        axisBase.setDrawGridLines(false);
        axisBase.setDrawAxisLine(false);
        HashMap<String, Integer> dataObjects = new HashMap<String, Integer>();
        dataObjects.put("Còn phòng", conphong);
        dataObjects.put("Đã được đặt", daduocdat);
        dataObjects.put("Hết phòng", hetphong);
        List<BarEntry> entries = new ArrayList<BarEntry>();
        int x = 0;
        for (HashMap.Entry<String, Integer> data : dataObjects.entrySet()) {

            // turn your data into Entry objects
            entries.add(new BarEntry(x, data.getValue()));
            x++;
        }

        BarDataSet dataSet = new BarDataSet(entries, "Đã được đặt,Còn phòng,Hết Phòng"); // add entries to dataset
//        dataSet.setColor(Color.BLUE);
//        dataSet.setValueTextColor(Color.CYAN); // styling, ...
//        dataSet.setStackLabels();
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet.setValueTextColor(Color.CYAN);
        dataSet.setValueTextSize(15f);

        BarData barData = new BarData(dataSet);
        chart.setData(barData);
        chart.invalidate(); // refresh




    }
}
