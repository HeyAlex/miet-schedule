package heyalex.com.miet_schedule;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.hey.mietunoff.mietunofficial.util.DateMietHelper;

/**
 * Created by mac on 8/13/17.
 */

public class DateTimeUtilTest {

    @Test
    public void addition_isCorrect() throws Exception {
        for (int i = 1; i <= 365; i++) {
            System.out.println(LocalDate.now().withDayOfYear(i)
                    .toDateTimeAtStartOfDay().toString() + " : " +
                    DateMietHelper.getWeek(LocalDate.now().withDayOfYear(i).toDateTimeAtStartOfDay()));
        }
    }
}
