package com.example.scheduleproject.Model;

import java.util.Locale;

public class ItemAppoi {


    public final long _id;
    public String title ;
    public String venue;
    public String startDay;


    public ItemAppoi(long _id, String title, String venue, String startDay) {
        this._id = _id;
        this.title = title;
        this.venue = venue ;
        this.startDay = startDay;

    }

    @Override
    public String toString() {
        String msg = String.format(
                Locale.getDefault(),
                "%s (%s)",
                this.title,
                this.startDay
        );
        return msg;
    }

}
