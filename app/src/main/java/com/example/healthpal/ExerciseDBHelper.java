package com.example.healthpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.DateStepsModel;

import java.util.ArrayList;
import java.util.Calendar;

public class ExerciseDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME="ExerciseDatabase";
    private static final String TABLE_EXER_SUMMARY="ExerciseSummary";
    private static final String ID="id";
    private static final String EHOURS_COUNT="ehourscount";
    private static final String CREATION_DATE="creationdate";

    private static final String CREATE_TABLE_EXER_SUMMARY = "CREATE TABLE "  + TABLE_EXER_SUMMARY+ "("+ID+ "INTEGER PRIMARY KEY AUTOINCREMENT,"+ CREATION_DATE+ "TEXT,"+ EHOURS_COUNT+"INTEGER"+")";


    public ExerciseDBHelper(Context context)
    {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EXER_SUMMARY);
    }
    public boolean createEHoursEntry()
    {
        boolean isDateAlreadyPresent = false;
        boolean createSuccessful = false;
        int currentDateEHoursCounts = 0;
        Calendar calendar = Calendar.getInstance();
        String todayDate = String.valueOf(calendar.get(Calendar.MONTH))+"/" + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+"/"+String.valueOf(calendar.get(Calendar.YEAR));
        String selectQuery = "SELECT " + EHOURS_COUNT + " FROM " + TABLE_EXER_SUMMARY + " WHERE " + CREATION_DATE +" = '"+ todayDate+"'";
        try {
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor c=db.rawQuery(selectQuery,null);
            if(c.moveToFirst())
            {
                do {
                    isDateAlreadyPresent=true;
                    currentDateEHoursCounts=c.getInt((c.getColumnIndex(EHOURS_COUNT)));
                }
                while (c.moveToNext());
            }
            db.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        try {
            SQLiteDatabase db=this.getWritableDatabase() ;
            ContentValues values=new ContentValues();
            values.put(CREATION_DATE, todayDate);
            if(isDateAlreadyPresent)
            {
                values.put(EHOURS_COUNT, ++currentDateEHoursCounts);
                int row=db.update(TABLE_EXER_SUMMARY,values,CREATION_DATE+" = '"+ todayDate+",",null);
                if(row==1)
                {
                    createSuccessful=true;
                }
                db.close();
            }
            else {
                values.put(EHOURS_COUNT,1);
                long row=db.insert(TABLE_EXER_SUMMARY,null,values);
                if(row!=-1)
                {
                    createSuccessful=true;
                }
                db.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  createSuccessful;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public ArrayList<DateEHoursModel> readEHourEntries() {
        ArrayList<DateEHoursModel>ETimeList=new ArrayList<DateEHoursModel>();
        String selectQuery="SELECT * FROM " + TABLE_EXER_SUMMARY;
        try {
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor c=db.rawQuery(selectQuery,null);
            if(c.moveToFirst()){
                do {
                    DateEHoursModel dateEHoursModel=new DateEHoursModel();
                    dateEHoursModel.date=c.getString(Integer.parseInt((c.getString(c.getColumnIndex(CREATION_DATE)))));
                    dateEHoursModel.ehoursCount=c.getInt((c.getColumnIndex(EHOURS_COUNT)));
                    ETimeList.add(dateEHoursModel);
                }while (c.moveToNext());
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ETimeList;
    }
}
