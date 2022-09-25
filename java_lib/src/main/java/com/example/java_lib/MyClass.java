package com.example.java_lib;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TreeMap;

public class MyClass
{
    public static void main(String[] args)
    {
        System.out.println("==========================");

        TreeMap<Long, String> map = EpgUtils.create24hTimes(2, false);

        for (String item : map.values())
        {
            System.out.println("date : " + item);
        }

        System.out.println("==========================");
    }
}
