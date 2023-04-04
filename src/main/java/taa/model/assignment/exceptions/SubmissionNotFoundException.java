package taa.model.assignment.exceptions;

/**
 * Exception where the submission is not found.
 */
public class SubmissionNotFoundException extends AssignmentException {
    public SubmissionNotFoundException(String studentName) {
        super("Submission of " + studentName + " not found");
    }
}
