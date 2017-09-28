package heyalex.com.miet_schedule.data.shortcut;


/**
 * ShortcutPreference helps do manage with shortcuts
 */
public interface ShortcutPreference {

    /**
     * Add new dynamic shortcut if device on Android N
     *
     * @param groupName associated with schedule
     */
    void addNewDynamicShortcut(String groupName);

    /**
     * Add new static shortcut on home screen
     *
     * @param groupName associated with schedule
     */
    void addStaticShortcut(String groupName);

    /**
     * Delete dynamic shortcut if device on Android N and this group name in shortcut list
     *
     * @param groupName associated with schedule
     */
    void deleteDynamicShortcut(String groupName);

    /**
     * Delete static shortcut on home screen
     *
     * @param groupName associated with schedule
     */
    void deleteStaticShortcut(String groupName);


}
