package com.example.scheduleproject;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


import static com.example.scheduleproject.database.DatabaseHelper.COL_VENUE;
import static com.example.scheduleproject.database.DatabaseHelper.COL_ENDDATE;
import static com.example.scheduleproject.database.DatabaseHelper.COL_DATE;
import static com.example.scheduleproject.database.DatabaseHelper.COL_TITLE;
import static com.example.scheduleproject.database.DatabaseHelper.TABLE_NAME;
import static com.example.scheduleproject.database.DatabaseHelper.COL_ID;

import com.example.scheduleproject.Model.ItemAppoi;
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

            ItemAppoi item = new ItemAppoi(id,title, venue,startDay);
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
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ItemAppoi item = mAppoimentList.get(position);
                String[] date = item.startDay.split("/");
                String Month []= {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("place: "+item.venue+" date: "+date[0]+" "+Month[Integer.parseInt(date[1])-1]+" "+date[2])
                        .setTitle(item.title)
                        .setPositiveButton("ตกลง", null)
                        .show();
            }

        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                final String[] items = new String[]{
                        "Edit",
                        "Delete"
                };

                new AlertDialog.Builder(MainActivity.this)
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final ItemAppoi Item = mAppoimentList.get(position);

                                switch (i) {
                                    case 0: // Edit
                                        Intent intent = new Intent(MainActivity.this, Editschedule.class);
                                        intent.putExtra("title",Item.title);
                                        intent.putExtra("venue", Item.venue);
                                        intent.putExtra("startDay", Item.startDay);
                                        intent.putExtra("id", Item._id);
                                        startActivity(intent);
                                        break;
                                    case 1: // Delete
                                        new AlertDialog.Builder(MainActivity.this)
                                                .setMessage("ต้องการลบข้อมูลนี้ ใช่หรือไม่")
                                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        mDb.delete(
                                                                TABLE_NAME,
                                                                COL_ID + " = ?",
                                                                new String[]{String.valueOf(Item._id)}
                                                        );
                                                        loadData();
                                                        setupListView();
                                                    }
                                                })
                                                .setNegativeButton("No", null)
                                                .show();
                                        break;
                                }
                            }
                        })
                        .show();

                return true;
            }
        });
    }



}
