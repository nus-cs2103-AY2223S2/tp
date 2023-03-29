package seedu.library.model;

/**
 * Order of the bookmark list by rating.
 */
public class RatingOrder {

    public static final String ASC = "asc";

    public static final String DESC = "desc";

    public static final String MESSAGE_CONSTRAINT = "Sort by either asc or desc";

    /**
     * Checks if the valid rating order is either asc or desc.
     *
     * @param order Order specified by user.
     * @return true if rating order is valid and false otherwise.
     */
    public static boolean isValidRatingOrder(String order) {
        return order.equals(ASC) || order.equals(DESC);
    }
}
