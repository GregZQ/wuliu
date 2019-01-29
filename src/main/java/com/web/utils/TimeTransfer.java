package com.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jone on 2018-04-05.
 */
public class TimeTransfer {

    public static Date stringToDate(String date,String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);

        Date trueDaate=simpleDateFormat.parse(date);
        return trueDaate;
    }

    public static String dateToString(Date date,String pattern){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
        String value=simpleDateFormat.format(date);
        return value;
    }

    public static Date getCurrentDate(){
        Date date=new Date();
        return date;
    }
}
