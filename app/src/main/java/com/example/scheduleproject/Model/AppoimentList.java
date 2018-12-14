package com.example.scheduleproject.Model;

import java.util.Locale;

public class AppoimentList {


        public final long _id;
        public String title ;
        public String venue;
        public String startDay;
        public String endDay ;

        public AppoimentList(long _id, String title, String venue, String startDay, String endDay) {
            this._id = _id;
            this.title = title;
            this.venue = venue ;
            this.startDay = startDay;
            this.endDay = endDay;
        }

        @Override
        public String toString() {
            String msg = String.format(
                    Locale.getDefault(),
                    "%s (%s-%s)",
                    this.title,
                    this.startDay,this.endDay
            );
            return msg;
        }

}
