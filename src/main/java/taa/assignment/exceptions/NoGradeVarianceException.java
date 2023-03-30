package taa.assignment.exceptions;

public class NoGradeVarianceException extends Exception {
    public NoGradeVarianceException(String assignmentName, double mean) {
        super(String.format("No variance found for assignment %s! \n"
                + "All graded submissions have a score of %.2f", assignmentName, mean));
    }
}
