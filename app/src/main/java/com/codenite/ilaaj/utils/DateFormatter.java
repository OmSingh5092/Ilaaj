package com.codenite.ilaaj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    Date date;
    SimpleDateFormat dateFormat;
    public DateFormatter(Date date) {
        this.date = date;
    }

    public String getDate(){
        dateFormat = new SimpleDateFormat("");
        return dateFormat.format(date);
    }

    public String getTime(){
        dateFormat = new SimpleDateFormat("");
        return dateFormat.format(date);
    }

    public String getDateISO(){
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        return  dateFormat.format(date);
    }

    public String getDateAndTime(){
        dateFormat = new SimpleDateFormat("hh:mm dd MMM yy");
        return dateFormat.format(date);
    }
}
