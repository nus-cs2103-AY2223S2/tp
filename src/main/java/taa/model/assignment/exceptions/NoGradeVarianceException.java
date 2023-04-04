package taa.model.assignment.exceptions;

/**
 * Signals that a normal distribution curve cannot be drawn due to variance being 0.
 */
public class NoGradeVarianceException extends Exception {
    /**
     * @param assignmentName Name of the assignment with no variance in submission grades.
     * @param mean Mean of the grade submissions.
     */
    public NoGradeVarianceException(String assignmentName, double mean) {
        super(String.format("No variance found for assignment %s! \n"
                + "All graded submissions have a score of %.2f", assignmentName, mean));
    }
}
