package com.example.scheduleproject.Model;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import static com.example.scheduleproject.database.DatabaseHelper.COL_VENUE;
import static com.example.scheduleproject.database.DatabaseHelper.COL_ENDDATE;
import static com.example.scheduleproject.database.DatabaseHelper.COL_DATE;
import static com.example.scheduleproject.database.DatabaseHelper.COL_TITLE;
import static com.example.scheduleproject.database.DatabaseHelper.TABLE_NAME;
import static com.example.scheduleproject.database.DatabaseHelper.COL_ID;

import com.example.scheduleproject.R;

import com.example.scheduleproject.Model.AppoimentList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase mDb;
   private List<AppoimentList> mAppoimentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addItemButton = findViewById(R.id.add_item_button);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Addschedule.class);
                startActivity(intent);
            }
        });



}
//    @Override
//    protected void onStart() {
//        super.onStart();
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        loadData();
//       //    setupListView();
//    }
    private void loadData() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);

        mAppoimentList = new ArrayList<>();
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(COL_ID));
            String title = c.getString(c.getColumnIndex(COL_TITLE));
            String venue = c.getString(c.getColumnIndex(COL_VENUE));
            String startDay = c.getString(c.getColumnIndex(COL_DATE));
            String endDay = c.getString(c.getColumnIndex(COL_ENDDATE));

            AppoimentList item = new AppoimentList(id,title, venue,startDay, endDay);
            mAppoimentList.add(item);
        }
        c.close();
    }
    private void setupListView() {
        ListAdapter adapter = new ListAdapter(
                MainActivity.this,
                R.layout.activity_addschedule,
                mAppoimentList

        );
        ListView lv = findViewById(R.id.result_list_view);
        lv.setAdapter(adapter);
    }



}
