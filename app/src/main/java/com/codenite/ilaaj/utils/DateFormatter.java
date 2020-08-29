package com.codenite.ilaaj.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    Date date;
    SimpleDateFormat dateFormat;
    public DateFormatter(Date date) {
        this.date = date;
    }
    public DateFormatter(String isoString){
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        try {
            date = df1.parse(isoString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getDate(){
        dateFormat = new SimpleDateFormat("dd MMM yy");
        return dateFormat.format(date);
    }

    public String getTime(){
        dateFormat = new SimpleDateFormat("hh:mm:aa");
        return dateFormat.format(date);
    }

    public String getDateISO(){
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        return  dateFormat.format(date);
    }

    public String getDateAndTime(){
        dateFormat = new SimpleDateFormat("hh:mm dd MMM yy");
        return dateFormat.format(date);
    }
}
