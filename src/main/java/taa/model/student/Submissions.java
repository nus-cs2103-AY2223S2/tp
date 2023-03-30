package taa.model.student;

import java.util.ArrayList;
import java.util.List;

import taa.assignment.Submission;

/**
 * A list of Submissions belonging to a Student.
 */
public class Submissions {
    private List<Submission> submissions;

    /**
     * @param submissions A list containing Submissions.
     */
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
}
