package heyalex.com.miet_schedule.news;

import dagger.Component;
import heyalex.com.miet_schedule.ApplicationComponent;

/**
 * Created by mac on 03.05.17.
 */

@NewsScope
@Component(dependencies = ApplicationComponent.class)
public interface NewsComponent {
    void inject(NewsFragment fragment);
}
