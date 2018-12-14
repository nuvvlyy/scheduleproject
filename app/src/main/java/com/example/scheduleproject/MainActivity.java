package com.example.scheduleproject;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import static com.example.scheduleproject.database.DatabaseHelper.COL_VENUE;
import static com.example.scheduleproject.database.DatabaseHelper.COL_ENDDATE;
import static com.example.scheduleproject.database.DatabaseHelper.COL_DATE;
import static com.example.scheduleproject.database.DatabaseHelper.COL_TITLE;
import static com.example.scheduleproject.database.DatabaseHelper.TABLE_NAME;
import static com.example.scheduleproject.database.DatabaseHelper.COL_ID;

import com.example.scheduleproject.Model.Addschedule;

import com.example.scheduleproject.database.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
   private List<ItemAppoi> mAppoimentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelper = new DatabaseHelper(MainActivity.this);
        mDb = mHelper.getWritableDatabase();

        Button addItemButton = findViewById(R.id.add_item_button);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Addschedule.class);
                startActivity(intent);
            }
        });



    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();

        loadData();
        setupListView();
    }
    private void loadData() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);

        mAppoimentList = new ArrayList<>();
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(COL_ID));
            String title = c.getString(c.getColumnIndex(COL_TITLE));
            String venue = c.getString(c.getColumnIndex(COL_VENUE));
            String startDay = c.getString(c.getColumnIndex(COL_DATE));
            String endDay = c.getString(c.getColumnIndex(COL_ENDDATE));

            ItemAppoi item = new ItemAppoi(id,title, venue,startDay, endDay);
            mAppoimentList.add(item);
        }
        c.close();
    }
    private void setupListView() {
        ListAdapter adapter = new ListAdapter(
                MainActivity.this,
                R.layout.item_appoi,
                mAppoimentList

        );
        ListView lv = findViewById(R.id.result_list_view);
        lv.setAdapter(adapter);
    }



}
