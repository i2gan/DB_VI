package com.example.dbvi;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import java.util.LinkedList;

public class Graphics extends Activity {
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
        dbHelper = new DBHelper(this);
    }

    class DrawView extends View {
        Paint p;
        public DrawView(Context context) {
            super(context);
            p = new Paint();
        }
        @Override
        protected void onDraw(Canvas canvas) {

            LinkedList<Data> list = dbHelper.getAll();
            int listSize = list.size();
            float[] weights = new float[listSize];
            int count = 0;
            for (Data d: list) {
                weights[count] = d.weight;
                count++;
            }

            p.setColor(Color.GRAY);
            p.setStrokeWidth(5);
            for (int i = 0; i < listSize-1; i++) {
                float x1, y1, x2, y2;
                x1 = 30 + i * 50;
                y1 = 1200 - weights[i]*10;
                x2 = 30 + (i+1)*50;
                y2 = 1200 - weights[i+1]*10;
                canvas.drawLine(x1, y1, x2, y2, p);
            }

//            float x = 50;
//            p.setColor(Color.BLUE);
//            p.setStrokeWidth(40);
//            for (int i = 1; i < 6; i++) {
//                for (int j = 1; j < 11; j++) {
//                    canvas.drawPoint(x * j, i * x, p);
//                }
//            }
        }
    }
}