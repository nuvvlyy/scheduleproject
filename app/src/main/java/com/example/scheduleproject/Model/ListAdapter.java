package com.example.scheduleproject.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import com.example.scheduleproject.R;

import java.util.List;

public class ListAdapter  extends ArrayAdapter<AppoimentList> {



        private Context mContext;
        private int mResource;
        private List<AppoimentList> mItemList;

        public ListAdapter(@NonNull Context context,
                                int resource,
                                @NonNull List<AppoimentList> AppoimentList) {
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

            TextView titleTextView = view.findViewById(R.id.title_edit_text);
            TextView venueTextView = view.findViewById(R.id.venue_edit_text);
            TextView startDayTextView = view.findViewById(R.id.Date_edit_text);
            TextView endDayTextView = view.findViewById(R.id.end_Date_edit_text);

            AppoimentList Item = mItemList.get(position);
            String title = Item.title;
            String venue = Item.venue;
            String sDay =Item.startDay;
            String eDay =Item.endDay;

            titleTextView.setText(title);
            venueTextView.setText(venue);
            startDayTextView.setText(sDay);
            endDayTextView.setText(eDay);


            return view;
        }




    public ListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}