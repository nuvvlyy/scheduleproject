package com.example.scheduleproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import com.example.scheduleproject.Model.ItemAppoi;
import com.example.scheduleproject.*;

import java.util.List;

public class ListAdapter  extends ArrayAdapter<ItemAppoi> {



        private Context mContext;
        private int mResource;
        private List<ItemAppoi> mItemList;

        public ListAdapter(@NonNull Context context,
                                int resource,
                                @NonNull List<ItemAppoi> AppoimentList) {
            super(context, resource,  AppoimentList);
            this.mContext = context;
            this.mResource = resource;
            this.mItemList = AppoimentList;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(mResource, parent, false);

            TextView titleTextView = view.findViewById(R.id.title_text_view);
            TextView venueTextView = view.findViewById(R.id.venue_text_view);
            TextView startDayTextView = view.findViewById(R.id.Date_text_view);
            TextView startMonthTextView =view.findViewById(R.id.month_text_view);
            TextView startYearTextView =view.findViewById(R.id.year_text_view);

            ItemAppoi Item = mItemList.get(position);
            String title = Item.title;
            String venue = Item.venue;
            String sDay =Item.startDay;


           String Month []= {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
          String [] date = sDay.split("/");



            titleTextView.setText(title);
            venueTextView.setText(venue);
           startDayTextView.setText(" " +date[0]);
           startMonthTextView.setText(Month[Integer.parseInt(date[1])-1]);
            startYearTextView.setText(date[2]);



            return view;
        }




    public ListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}