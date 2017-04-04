package heyalex.com.miet_schedule.util;

/**
 * Don't want to add Guava dependency
 */
public class Preconditions {

    /**
     * Tests whether provided reference is null.
     * If it's null {@link NullPointerException} will be thrown
     * @param reference to check
     * @param msg optional message for {@link NullPointerException}
     * @param <T> type of reference
     * @return reference
     */
    public static <T> T checkNotNull(T reference, String msg) {
        if (reference == null) {
            throw new NullPointerException(msg);
        } else {
            return reference;
        }
    }

    /**
     * Tests whether provided reference is null.
     * If it's null {@link NullPointerException} will be thrown
     * @param reference to check
     * @param <T> type of reference
     * @return reference
     */
    public static <T> T checkNotNull(T reference) {
        return checkNotNull(reference, null);
    }


}
