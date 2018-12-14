package com.example.scheduleproject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.scheduleproject.database.DatabaseHelper;

import java.util.Calendar;



import static com.example.scheduleproject.database.DatabaseHelper.COL_TITLE;

import static com.example.scheduleproject.database.DatabaseHelper.*;
import com.example.scheduleproject.database.DatabaseHelper;

public class Editschedule extends AppCompatActivity {

    private static final String TAG = "Addschedule";

    private EditText mTitleEditText;
    private EditText mVenueEditText;
    private TextView mstartDayEditText;
    private Button mSaveButton;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private long mId;
    private int pYear;
    private int pMonth;
    private int pDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editschedule);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String venue = intent.getStringExtra("venue");
        String sDay = intent.getStringExtra("startDay");
        mId = intent.getLongExtra("id", 0);

        mTitleEditText = findViewById(R.id.title_edit_text);
        mVenueEditText = findViewById(R.id.venue_edit_text);
        mstartDayEditText = findViewById(R.id.Date_edit_text);
        mSaveButton = findViewById(R.id.save_button);

        mTitleEditText.setText(title);
        mVenueEditText.setText(venue);
        mstartDayEditText.setText(sDay);

        mstartDayEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Editschedule.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow();
                dialog.show();
            }
        });
        Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                if(year<pYear){
                    mstartDayEditText.setText(pDay+"/"+(pMonth+1)+"/"+pYear);
                    Log.d(TAG, "date is incorrect");

                }else if(month<pMonth){
                    mstartDayEditText.setText(pDay+"/"+(pMonth+1)+"/"+pYear);

                    Log.d(TAG, "date is incorrect");
                }else if(day<pDay){
                    mstartDayEditText.setText(pDay+"/"+(pMonth+1)+"/"+pYear);

                    Log.d(TAG, "date is incorrect");
                }else{
                    Log.d(TAG, "onDateSet: mm/dd/yyy: " +  day  + "/" + month+ "/" + year);
                    String date = day  + "/" + month+ "/" + year;
                    mstartDayEditText.setText(date);

                }

            }
        };

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: บันทึกข้อมูลใหม่ลง db
                DatabaseHelper helper = new DatabaseHelper(Editschedule.this);
                SQLiteDatabase db = helper.getWritableDatabase();

                String newTitle = mTitleEditText.getText().toString().trim();
                String newVenue = mVenueEditText.getText().toString().trim();
                String newsDay = mstartDayEditText.getText().toString().trim();

                ContentValues cv = new ContentValues();
                cv.put(COL_TITLE, newTitle);
                cv.put(COL_VENUE, newVenue);
                cv.put(COL_DATE, newsDay);
                db.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(mId)}
                );
                finish();
            }
        });
    }
}
