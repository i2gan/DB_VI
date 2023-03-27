package com.example.dbvi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;

public class Graphics extends AppCompatActivity {
    DBHelper dbHelper;

    TextView tv_graphics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        dbHelper = new DBHelper(this);
        tv_graphics = findViewById(R.id.tv_graphics);

        LinkedList<Data> list = dbHelper.getAll();

        String text = "";
        int listSize = list.size();
        double[] weights = new double[listSize];
        int count = 0;
        for (Data d: list) {
            weights[count] = d.weight;
            text = text + d.day + "." + d.month + "." + d.year + " " +
                    d.morningTeeth + " " + d.oneBread + " " + d.sugar +
                    " " + d.smoking + " " + d.eveningTeeth + " " + d.weight + "\n";
            count++;
        }
        text = text + "\n " + list.size();
        String weightStr = "";
        for (int i = 0; i < listSize; i++)
            weightStr += " " + weights[i];
        tv_graphics.setText(weightStr);

    }
}