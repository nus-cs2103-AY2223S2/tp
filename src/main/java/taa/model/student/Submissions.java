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
        if (submissions.isEmpty()) {
            return null;
        }
        return submissions.get(submissions.size() - 1);
    }

    /**
     * @return ArrayList of storage string representation for the submissions
     */
    public ArrayList<String> submissionStorageString() {
        ArrayList<String> res = new ArrayList<>();
        for (Submission sub: this.submissions) {
            res.add(sub.toStorageString());
        }
        return res;
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
