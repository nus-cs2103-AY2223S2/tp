package vimification.model.task;

import java.util.Optional;

/**
 * Represents possible status of a task in the application.
 */
public enum Status {

    IN_PROGRESS, COMPLETED, NOT_DONE;

    /**
     * Possible helper function used by the parser. Help parser processes Integer input (as an
     * alternative input) to return the corresponding Status enum. 0 will be treated as "Not done"
     * status; 1 being the "In progress" status; 2 being the "Completed" status.
     *
     * @param statusCode the integer to be converted
     * @return an optional of the status corresponding to the given integer
     */
    public static Optional<Status> fromInt(int statusCode) {
        switch (statusCode) {
        case 0:
            return Optional.of(NOT_DONE);
        case 1:
            return Optional.of(IN_PROGRESS);
        case 2:
            return Optional.of(COMPLETED);
        default:
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return name().toLowerCase().replace('_', ' ');
    }
}

