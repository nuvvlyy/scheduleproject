package com.example.scheduleproject.Model;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import com.example.scheduleproject.R;

public class Addschedule extends AppCompatActivity {
//.Model.Addschedule"


    private static final String TAG = "Addschedule";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private int pYear;
       private int pMonth;
       private int pDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addschedule);
        mDisplayDate = (TextView) findViewById(R.id.Date_edit_text);
        Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
         pMonth = cal.get(Calendar.MONTH);
         pDay = cal.get(Calendar.DAY_OF_MONTH);

        mDisplayDate.setText(pDay+"/"+(pMonth+1)+"/"+pYear);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Addschedule.this,R.style.Theme_AppCompat_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(	255,	255,	255)));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " +  day  + "/" + month+ "/" + year);

                String date = day  + "/" + month+ "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }

}
