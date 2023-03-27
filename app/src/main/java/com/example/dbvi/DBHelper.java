package com.example.dbvi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.LinkedList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String MY_TABLE = "MY_TABLE";
    private static final String DAY = "DAY";
    private static final String MONTH = "MONTH";
    private static final String MORNING_TEETH = "MORNING_TEETH";
    private static final String ONE_BREAD = "ONE_BREAD";
    private static final String NO_SUGAR = "NO_SUGAR";
    private static final String NO_SMOKING = "NO_SMOKING";
    private static final String EVENING_TEETH = "EVENING_TEETH";
    private static final String WEIGHT = "WEIGHT";
    private static final String YEAR = "YEAR";

    public DBHelper(@Nullable Context context) {
        super(context, "example.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + MY_TABLE + " (" +
                DAY + " INTEGER, " +
                MONTH + " INTEGER, " +
                YEAR + " INTEGER, " +
                MORNING_TEETH + " INTEGER, " +
                ONE_BREAD + " INTEGER, " +
                NO_SUGAR + " INTEGER, " +
                NO_SMOKING + " INTEGER, " +
                EVENING_TEETH + " INTEGER, " +
                WEIGHT + " REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MY_TABLE, null, null);
        db.close();
    }

    public void addOne(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DAY, data.day);
        cv.put(MONTH, data.month);
        cv.put(YEAR, data.year);
        cv.put(MORNING_TEETH, data.morningTeeth);
        cv.put(ONE_BREAD, data.oneBread);
        cv.put(NO_SUGAR, data.sugar);
        cv.put(NO_SMOKING, data.smoking);
        cv.put(EVENING_TEETH, data.eveningTeeth);
        cv.put(WEIGHT, data.weight);

        db.insert(MY_TABLE, null, cv);

        db.close();
    }

    public LinkedList<Data> getAll() {

        LinkedList<Data> list = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(MY_TABLE, null, null,
                null, null, null, null);

        if (cursor.moveToFirst())
            do {
                int id_day = cursor.getColumnIndex(DAY);
                int id_month = cursor.getColumnIndex(MONTH);
                int id_year = cursor.getColumnIndex(YEAR);
                int id_morningTeeth = cursor.getColumnIndex(MORNING_TEETH);
                int id_oneBread = cursor.getColumnIndex(ONE_BREAD);
                int id_noSugar = cursor.getColumnIndex(NO_SUGAR);
                int id_noSmoking = cursor.getColumnIndex(NO_SMOKING);
                int id_eveningTeeth = cursor.getColumnIndex(EVENING_TEETH);
                int id_weight = cursor.getColumnIndex(WEIGHT);

                Data data = new Data(cursor.getInt(id_day),
                        cursor.getInt(id_month),
                        cursor.getInt(id_year),
                        cursor.getInt(id_morningTeeth),
                        cursor.getInt(id_oneBread),
                        cursor.getInt(id_noSugar),
                        cursor.getInt(id_noSmoking),
                        cursor.getInt(id_eveningTeeth),
                        cursor.getDouble(id_weight));
                list.add(data);
            } while (cursor.moveToNext());
        db.close();
        return list;
    }
}
