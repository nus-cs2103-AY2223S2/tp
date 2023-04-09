package vimification.model.task;

/**
 * Represents different priority levels of a task in the application.
 */
public enum Priority {

    UNKNOWN, VERY_URGENT, URGENT, NOT_URGENT;

    /**
     * Possible helper function used by the parser. Help parser processes Integer input (as an
     * alternative input) to return the corresponding Priority enum. 1 being the highest priority, 3
     * being the lowest priority. Other integers will be treated as unknown priority.
     *
     * @param level the integer representation of the priority level
     * @return the corresponding Priority enum
     * @throws IllegalArgumentException if the integer is not between 0 and 3
     */
    public static Priority fromInt(int level) throws IllegalArgumentException {
        if (level < 0 || level > 3) {
            throw new IllegalArgumentException("Priority level must be between 0 and 3");
        }
        switch (level) {
        case 1:
            return VERY_URGENT;
        case 2:
            return URGENT;
        case 3:
            return NOT_URGENT;
        default: // 0
            return UNKNOWN;
        }
    }

    /**
     * Transforms this instance into a compact string representation.
     *
     * @return a compact string representation of this instance
     */
    public String asEnding() {
        switch (this) {
        case NOT_URGENT:
            return " !";
        case URGENT:
            return " !!";
        case VERY_URGENT:
            return " !!!";
        default:
            return " .";
        }
    }

    @Override
    public String toString() {
        return name().toLowerCase().replace('_', ' ');
    }
}
