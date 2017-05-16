package heyalex.com.miet_schedule.search;

import java.util.List;

/**
 * Created by alexf on 16.05.2017.
 */

public interface DataFilter<T> {
    List<T> filter(List<T> all, String query);
}