package heyalex.com.miet_schedule.util;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mac on 11.05.17.
 */

public class DateMietHelper {

    final static int mSemestr1_1 = 6;
    final static int mSemestr1_2 = 26;
    final static int mSemestr2_1 = 35;
    final static int mSemestr2_2 = 2;
    final static int mFullWeeks = 52;


    public static int getWeekByDay(DateTime dateTime) {
        int currentYearWeek = dateTime.getWeekOfWeekyear();
        if (currentYearWeek >= mSemestr2_1 && currentYearWeek <= mFullWeeks)
            return currentYearWeek + 1 - mSemestr2_1;
        else if (currentYearWeek >= mSemestr1_1 && currentYearWeek <= mSemestr1_2)
            return 21 + currentYearWeek - mSemestr1_2;
        else if (currentYearWeek > 0 && currentYearWeek <= mSemestr2_2)
            return 20 + currentYearWeek - 2;
        else return 1;
    }

    public static int getDayInWeek(DateTime dateTime){
        return dateTime.getDayOfWeek();
    }
}
