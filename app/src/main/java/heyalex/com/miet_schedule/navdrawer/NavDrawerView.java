package heyalex.com.miet_schedule.navdrawer;

/**
 * An interface for {@link NavDrawerActivity}
 */
/*package*/ interface NavDrawerView {

    /**
     * Show news fragment
     */
    void navigateToNews();

    /**
     * Show groups fragment
     */
    void navigateToScheduleGroups();

    /**
     * Show web fragment
     */
    void navigateToOrioks();

    void navigateToSettings();

    /**
     * Show last position of navigation drawer list
     * @param position last position
     */
    void showCurrentPosition(int position);
}
