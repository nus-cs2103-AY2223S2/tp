package vimification.model.task;

import java.util.Optional;

/**
 * Represents different priority levels of a task in the application.
 */
public enum Priority {

    VERY_URGENT, URGENT, NOT_URGENT, UNKNOWN;

    /**
     * Possible helper function used by the parser. Help parser processes Integer input (as an
     * alternative input) to return the corresponding Priority enum. 0 will be treated as unknown
     * priority. 1 being the highest priority, 3 being the lowest priority.
     *
     * @param level the integer representation of the priority level
     * @return an Optional containing the corresponding Priority enum
     */
    public static Optional<Priority> fromInt(int level) {
        switch (level) {
        case 0:
            return Optional.of(UNKNOWN);
        case 1:
            return Optional.of(VERY_URGENT);
        case 2:
            return Optional.of(URGENT);
        case 3:
            return Optional.of(NOT_URGENT);
        default:
            return Optional.empty();
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
