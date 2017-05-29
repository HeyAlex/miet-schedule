package heyalex.com.miet_schedule.util;

import org.joda.time.DateTime;

import timber.log.Timber;

/**
 * Util that used for get a week and day offline for schedule
 * That class compute current week and day by current date
 */
public class DateMietHelper {

    private final static int semestr1_1 = 6;
    private final static int semestr1_2 = 26;
    private final static int semestr2_1 = 35;
    private final static int semestr2_2 = 2;
    private final static int fullWeeks = 52;

    /**
     * This method compute a current semester (Autumn and Spring semester) and returns a number of
     * week in global schedule.
     * For better understanding: Year - 52 weeks. Spring - from 6 to 26 weeks,
     * Autumn - from 35 to 2 weeks
     *
     * @param dateTime date
     * @return number of current week in schedule like 1..20
     */
    private static int getWeekByDay(DateTime dateTime) {
        int currentYearWeek = dateTime.getWeekOfWeekyear();
        if (currentYearWeek >= semestr2_1 && currentYearWeek <= fullWeeks)
            return currentYearWeek + 1 - semestr2_1;
        else if (currentYearWeek >= semestr1_1 && currentYearWeek <= semestr1_2)
            return 21 + currentYearWeek - semestr1_2 - 1;
        else if (currentYearWeek > 0 && currentYearWeek <= semestr2_2)
            return 20 + currentYearWeek - 2;
        else return 1;
    }

    /**
     * 0 - first numerator
     * 1 - first denumerator
     * 2 - second numerator
     * 3 - second denumerator
     *
     * @param dateTime date
     * @return number of current week in schedule like 0..3
     */
    public static int getWeek(DateTime dateTime) {
        return getStudyWeek(getWeekByDay(dateTime) % 4);
    }

    private static int getStudyWeek(int week) {
        switch (week % 4) {
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
            case 3: {
                return 2;
            }
            case 0: {
                return 3;
            }
            default:
                return 0;
        }
    }

    /**
     * @param dateTime date
     * @return number of current day of week
     */
    public static int getDayInWeek(DateTime dateTime) {
        return dateTime.getDayOfWeek();
    }
}
