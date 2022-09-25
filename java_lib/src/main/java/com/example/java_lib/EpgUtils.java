package com.example.java_lib;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

public class EpgUtils
{
    // create 12h time line (every 30 min)
    public static TreeMap<String, String> create12hTimes(int dates)
    {
        TreeMap<String, String> map = new TreeMap<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM, Calendar.PM);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd hh:mm a", Locale.getDefault());
        SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());

        for (int date = 0; date < dates; date++)
        {
            if (date != 0)
            {
                calendar.add(Calendar.DATE, 1);
            }
            for (int hour = 0; hour < 24; hour++)
            {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                Date time = calendar.getTime();
                map.put(dateFormat.format(time), hourFormat.format(time));
            }
        }
        return map;
    }

    // create 24h time line (every 30 min)
    public static TreeMap<Long, String> create24hTimes(int dates, boolean is12h)
    {
        TreeMap<Long, String> map = new TreeMap<>();

        SimpleDateFormat timeFormat;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (is12h)
        {
            timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        }
        else
        {
            timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        }

        int total = dates * 24;

        for (int time = 0; time < total; time++)
        {
            if (time != 0)
            {
                calendar.add(Calendar.HOUR, 1);
            }
            map.put(calendar.getTimeInMillis(), timeFormat.format(calendar.getTime()));
        }
        return map;
    }
}
