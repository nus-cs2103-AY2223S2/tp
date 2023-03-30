package taa.assignment.exceptions;

/**
 * Signals that there are submissions to display statistics for.
 */
public class NoSubmissionsFoundException extends Exception {
    public NoSubmissionsFoundException(String assignmentName) {
        super(String.format(
                "No submissions found for assignment %s in the current class list!",
                assignmentName));
    }

}
