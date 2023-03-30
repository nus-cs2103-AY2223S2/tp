package taa.model.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import taa.assignment.Submission;
import taa.assignment.exceptions.AssignmentNotFoundException;

/**
 * A list of Submissions belonging to a Student.
 */
public class Submissions {
    private List<Submission> submissions;
    private Student student;

    public Submissions() {
        this.submissions = new ArrayList<>();
    }

    /**
     * @param submissions A list containing Submissions.
     */
    public Submissions(List<Submission> submissions, Student student) {
        this.submissions = submissions;
        this.student = student;
    }

    /**
     * Adds a submission.
     */
    public void addSubmission(Submission submission) {
        this.submissions.add(submission);
    }

    /**
     * Removes a submission.
     */
    public void deleteSubmission(Submission submission) {
        this.submissions.remove(submission);
    }

    /**
     * Returns the latest submission.
     */
    public Submission getLatestSubmission() {
        Submission latestSubmission = null;

        for (Submission submission : this.submissions) {
            if (latestSubmission == null || submission.compareTo(latestSubmission) > 0) {
                latestSubmission = submission;
            }
        }

        return latestSubmission;
    }

    /**
     * Returns the score for the student's submission for the given assignment name.
     * If the submission is not graded yet, an Optional.empty() is returned.
     * @throws AssignmentNotFoundException if an assignment with the specified name cannot be found.
     */
    public Optional<Integer> getSubmissionScore(String assignmentName) throws AssignmentNotFoundException {
        Submission submission = getSubmissionByAssignmentName(assignmentName)
                .orElseThrow(() -> new AssignmentNotFoundException(assignmentName));
        return submission.getScore();
    }

    /**
     * Returns this Student's submission for a given assignment name.
     * If it does not exist, an Optional.empty() is returned.
     */
    private Optional<Submission> getSubmissionByAssignmentName(String assignmentName) {
        Submission result = null;

        for (Submission submission : this.submissions) {
            if (submission.isForAssignment(assignmentName)) {
                result = submission;
            }
        }

        return Optional.ofNullable(result);
    }

}
