package com.example.dbvi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DBHelper dbHelper;

    Calendar calendar = Calendar.getInstance();

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

    int morningTeeth, oneBread, sugar, smoking, eveningTeeth;
    float weight;

    TextView tv, tv_output;
    CheckBox chb_morningTeeth, chb_oneBread, chb_noSugar, chb_noSmoking, chb_eveningTeeth;
    EditText et_weight;

    Button btn_add, btn_del, btn_output, btn_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        // Вывод даты на экран
        tv = findViewById(R.id.tv);
        String monthStr = fillInt(month);
        String day = fillInt(dayOfMonth);
        tv.setText("Today is " + day + "." + monthStr + "." + year);

        chb_morningTeeth = findViewById(R.id.chb_eveningTeeth);
        chb_oneBread = findViewById(R.id.chb_oneBread);
        chb_noSugar = findViewById(R.id.chb_noSugar);
        chb_noSmoking = findViewById(R.id.chb_noSmoking);
        chb_eveningTeeth = findViewById(R.id.chb_eveningTeeth);

        et_weight = findViewById(R.id.et_weight);

        btn_add = findViewById(R.id.btn_add);
        btn_del = findViewById(R.id.btn_del);
        btn_output = findViewById(R.id.btn_output);
        btn_go = findViewById(R.id.btn_go);

        btn_add.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_output.setOnClickListener(this);
        btn_go.setOnClickListener(this);

        tv_output = findViewById(R.id.tv_output);
    }

    public String fillInt(int x) {
        String s = "";
        if (x < 10)
            s = "0" + x;
        else
            s = "" + x;
        return s;
    }

    @Override
    public void onClick(View view) {
        String text = "";
        switch (view.getId()) {
            case R.id.btn_del:
                dbHelper.deleteAll();
                break;
            case R.id.btn_add:

                if (chb_morningTeeth.isChecked())
                    morningTeeth = 1;
                else
                    morningTeeth = 0;
                if (chb_oneBread.isChecked())
                    oneBread = 1;
                else
                    oneBread = 0;
                if (chb_noSugar.isChecked())
                    sugar = 1;
                else
                    sugar = 0;
                if (chb_noSmoking.isChecked())
                    smoking = 1;
                else
                    smoking = 0;
                if (chb_eveningTeeth.isChecked())
                    eveningTeeth = 1;
                else
                    eveningTeeth = 0;

                weight = Float.parseFloat(et_weight.getText().toString());

                Data data = new Data(dayOfMonth, month, year, morningTeeth,
                        oneBread, sugar, smoking, eveningTeeth, weight);

                dbHelper.addOne(data);
                break;
            case R.id.btn_output:
                LinkedList<Data> list = dbHelper.getAll();

                for (Data d: list) {
                    text = text + d.day + "." + d.month + "." + d.year + " " +
                            d.morningTeeth + " " + d.oneBread + " " + d.sugar +
                            " " + d.smoking + " " + d.eveningTeeth + " " + d.weight + "\n";
                }
                break;
            case R.id.btn_go:
                Intent intent = new Intent(this, Graphics.class);
                startActivity(intent);
                break;
        }
        tv_output.setText(text);
    }
}