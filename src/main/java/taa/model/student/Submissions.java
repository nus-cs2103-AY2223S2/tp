package taa.model.student;

import java.util.List;
import java.util.Optional;

import taa.model.assignment.AssignmentList;
import taa.model.assignment.Submission;
import taa.model.assignment.exceptions.AssignmentNotFoundException;

/**
 * A list of Submissions belonging to a Student.
 */
public class Submissions {
    public static final String STR_SEP = ";";
    private final List<Submission> submissions;

    /** @param submissions A list containing Submissions. */
    public Submissions(List<Submission> submissions) {
        this.submissions = submissions;
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

    public int getSubmissionIndex(Submission s) {
        return this.submissions.indexOf(s);
    }

    /**
     * Returns the latest submission.
     */
    public Submission getLatestSubmission() {
        if (submissions.isEmpty()) {
            return null;
        }
        final String name = AssignmentList.INSTANCE.getLatestAsgnName();
        if (name == null) {
            return null;
        }
        for (Submission submission : submissions) {
            if (submission.isForAssignment(name)) {
                return submission;
            }
        }
        return submissions.get(submissions.size() - 1);
    }

    /**
     * @return Storage string representation for the submissions
     */
    public String getStorageString() {
        StringBuilder ans = new StringBuilder();
        for (Submission sub : this.submissions) {
            ans.append(sub.toStorageString()).append(STR_SEP);
        }
        return ans.toString();
    }

    /**
     * Returns the score for the student's submission for the given assignment name. If the submission is not graded
     * yet, an Optional.empty() is returned.
     *
     * @throws AssignmentNotFoundException if an assignment with the specified name cannot be found.
     */
    public Optional<Integer> getSubmissionScore(String assignmentName) throws AssignmentNotFoundException {
        Submission submission = getSubmissionByAssignmentName(assignmentName)
                .orElseThrow(() -> new AssignmentNotFoundException(assignmentName));
        return submission.getScore();
    }

    /**
     * Returns this Student's submission for a given assignment name. If it does not exist, an Optional.empty() is
     * returned.
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
