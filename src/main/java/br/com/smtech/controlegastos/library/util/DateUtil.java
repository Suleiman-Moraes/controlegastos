package br.com.smtech.controlegastos.library.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    
    public static Date getDateNowAdd(int field, int value){
        Calendar calendar = Calendar.getInstance();
        calendar.add(field, value);
        return calendar.getTime();
    }
}
