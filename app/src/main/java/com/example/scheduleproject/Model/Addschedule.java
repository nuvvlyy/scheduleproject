package com.example.scheduleproject.Model;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import com.example.scheduleproject.R;

import static com.example.scheduleproject.database.DatabaseHelper.COL_VENUE;
import static com.example.scheduleproject.database.DatabaseHelper.COL_ENDDATE;
import static com.example.scheduleproject.database.DatabaseHelper.COL_DATE;
import static com.example.scheduleproject.database.DatabaseHelper.COL_TITLE;
import static com.example.scheduleproject.database.DatabaseHelper.TABLE_NAME;
import com.example.scheduleproject.database.DatabaseHelper;
public class Addschedule extends AppCompatActivity {
//.Model.Addschedule"


    private static final String TAG = "Addschedule";
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private TextView mDisplayDate;
    private TextView mDisplayendDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mendDateSetListener;
    private int pYear;
    private int pMonth;
       private int pDay;
    private int sYear;
    private int sMonth;
    private int sDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addschedule);
        mDisplayDate = (TextView) findViewById(R.id.Date_edit_text);
        mDisplayendDate = (TextView) findViewById(R.id.end_Date_edit_text);
        Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
         pMonth = cal.get(Calendar.MONTH);
         pDay = cal.get(Calendar.DAY_OF_MONTH);
        sYear = pYear;
        sMonth = pMonth;
        sDay =pDay;

        mHelper = new DatabaseHelper(this);
        mDb = mHelper.getWritableDatabase();

        mDisplayDate.setText(pDay+"/"+(pMonth+1)+"/"+pYear);
        mDisplayendDate.setText(pDay+"/"+(pMonth+1)+"/"+pYear);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Addschedule.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow();
                dialog.show();
            }
        });
        mDisplayendDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Addschedule.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                        mendDateSetListener,
                        year,month,day);
                dialog.getWindow();
                //.setBackgroundDrawable(new ColorDrawable(Color.rgb(255,255,255)));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                if(year<pYear){
                    mDisplayDate.setText(pDay+"/"+(pMonth+1)+"/"+pYear);
                    Log.d(TAG, "date is incorrect");

                }else if(month<pMonth){
                    mDisplayDate.setText(pDay+"/"+(pMonth+1)+"/"+pYear);

                    Log.d(TAG, "date is incorrect");
                }else if(day<pDay){
                    mDisplayDate.setText(pDay+"/"+(pMonth+1)+"/"+pYear);

                    Log.d(TAG, "date is incorrect");
                }else{
                    Log.d(TAG, "onDateSet: mm/dd/yyy: " +  day  + "/" + month+ "/" + year);
                    String date = day  + "/" + month+ "/" + year;
                    mDisplayDate.setText(date);
                    sYear = year;
                    sMonth = month;
                    sDay =day;
                }

            }
        };
        mendDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                if(year<sYear){
                    mDisplayendDate.setText(pDay+"/"+(pMonth+1)+"/"+pYear);
                    Log.d(TAG, "date is incorrect");
                }else if(month<sMonth){
                    mDisplayendDate.setText(pDay+"/"+(pMonth+1)+"/"+pYear);
                    Log.d(TAG, "date is incorrect");
                }else if(day<sDay){
                    mDisplayendDate.setText(pDay+"/"+(pMonth+1)+"/"+pYear);
                    Log.d(TAG, "date is incorrect");
                }else{
                    Log.d(TAG, "onDateSet: mm/dd/yyy: " +  day  + "/" + month+ "/" + year);
                    String date = day  + "/" + month+ "/" + year;
                    mDisplayendDate.setText(date);
                }

            }
        };
        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doInsertPhoneItem();
            }
        });
    }
    private void doInsertPhoneItem() {
        EditText titleEditText = findViewById(R.id.title_edit_text);
        EditText venueEditText = findViewById(R.id.venue_edit_text);
        TextView startDayEditText = findViewById(R.id.Date_edit_text);
        TextView endDayEditText = findViewById(R.id.end_Date_edit_text);
        String title = titleEditText.getText().toString();
        String number = venueEditText.getText().toString();
        String startDay =startDayEditText.getText().toString();
        String endDay =endDayEditText.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE, title);
        cv.put(COL_VENUE, number);
        cv.put(COL_DATE, startDay);
        cv.put(COL_ENDDATE, endDay);
        mDb.insert(TABLE_NAME, null, cv);

        finish();
    }

}
