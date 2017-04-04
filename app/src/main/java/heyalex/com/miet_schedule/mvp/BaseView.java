package heyalex.com.miet_schedule.mvp;


/**
 * Base interface for views.
 * Mvp architectural pattern
 *
 * @param <P> type of presenter which will be associated with this view
 */
public interface BaseView<P> {
    /**
     * Associates this view with provided presenter
     * @param presenter
     */
    void setPresenter(P presenter);
}
