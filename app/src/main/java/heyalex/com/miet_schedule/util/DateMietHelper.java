package heyalex.com.miet_schedule.util;

import org.joda.time.DateTime;

/**
 * Created by mac on 11.05.17.
 */

public class DateMietHelper {

    private final static int semestr1_1 = 6;
    private final static int semestr1_2 = 26;
    private final static int semestr2_1 = 35;
    private final static int semestr2_2 = 2;
    private final static int fullWeeks = 52;


    public static int getWeekByDay(DateTime dateTime) {
        int currentYearWeek = dateTime.getWeekOfWeekyear();
        if (currentYearWeek >= semestr2_1 && currentYearWeek <= fullWeeks)
            return currentYearWeek + 1 - semestr2_1;
        else if (currentYearWeek >= semestr1_1 && currentYearWeek <= semestr1_2)
            return 21 + currentYearWeek - semestr1_2;
        else if (currentYearWeek > 0 && currentYearWeek <= semestr2_2)
            return 20 + currentYearWeek - 2;
        else return 1;
    }

    public static int getDayInWeek(DateTime dateTime) {
        return dateTime.getDayOfWeek();
    }
}
