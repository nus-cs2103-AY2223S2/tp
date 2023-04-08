package taa.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taa.model.assignment.exceptions.InvalidGradeException;
import taa.model.student.Name;
import taa.model.student.Student;

class SubmissionTest {
    private Student student;
    private Assignment assignment;
    private Submission submission;

    @BeforeEach
    void setUp() {
        student = new Student(new Name("John Doe"), "0;0;0;0;0;0;0;0;0;0;0;0",
                "-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1", new ArrayList<>(), new HashSet<>());
        assignment = new Assignment("Test Assignment", 10);
        submission = new Submission(student, assignment);
        student.addSubmission(submission);
    }

    @Test
    void testGetStudent() {
        assertEquals(student, submission.getStudent());
    }

    @Test
    void testGrade() throws InvalidGradeException {
        // test grading with valid marks, no late submission
        submission.grade(8, false);
        assertTrue(submission.getScore().isPresent());
        assertEquals(8, submission.getScore().get());
        assertTrue(submission.toString().contains("8/10"));

        // test late submission
        submission.grade(10, true);
        assertEquals(10, submission.getScore().get());
        assertTrue(submission.toString().contains("Late Submission"));

        // test grading with invalid marks
        assertThrows(InvalidGradeException.class, () -> submission.grade(-1, false));
        assertThrows(InvalidGradeException.class, () -> submission.grade(11, true));
    }

    @Test
    void testUngrade() throws InvalidGradeException {
        submission.grade(10, true);
        assertTrue(submission.toString().contains("Late Submission"));
        assertTrue(submission.toString().contains("10/10"));
        submission.ungrade();
        assertFalse(submission.getScore().isPresent());
        assertFalse(submission.toString().contains("Late Submission"));
        assertFalse(submission.toString().contains("10/10"));
    }

    @Test
    void testDescribeSubmission() throws InvalidGradeException {
        // test ungraded submission
        String description = submission.describeSubmission();
        assertTrue(description.contains("Test Assignment"));
        assertTrue(description.contains("Ungraded"));

        // test graded submission
        submission.grade(8, false);
        description = submission.describeSubmission();
        assertTrue(description.contains("Test Assignment"));
        assertTrue(description.contains("8/10"));

        // test late submission
        submission.grade(8, true);
        description = submission.describeSubmission();
        assertTrue(description.contains("(*Late Submission*)"));
    }

    @Test
    void testIsForAssignment() {
        assertTrue(submission.isForAssignment("Test Assignment"));
        assertFalse(submission.isForAssignment("Other Assignment"));
    }

    @Test
    void testGetScore() throws InvalidGradeException {
        assertFalse(submission.getScore().isPresent());
        submission.grade(8, false);
        assertTrue(submission.getScore().isPresent());
        assertEquals(8, submission.getScore().get());
    }

    @Test
    void testToString() throws InvalidGradeException {
        // test ungraded submission
        String submissionString = submission.toString();
        assertTrue(submissionString.contains("[ ]"));
        assertTrue(submissionString.contains("John Doe"));
        assertTrue(submissionString.contains("0/10 marks"));

        // test graded submission
        submission.grade(8, false);
        submissionString = submission.toString();
        assertTrue(submissionString.contains("[X]"));
        assertTrue(submissionString.contains("John Doe"));
        assertTrue(submissionString.contains("8/10 marks"));

        // test late submission
        submission.grade(8, true);
        submissionString = submission.toString();
        assertTrue(submissionString.contains("(*Late Submission*)"));
    }

    @Test
    void testToStorageString() throws InvalidGradeException {
        // test ungraded submission
        String storageString = submission.toStorageString();
        assertEquals("Test Assignment,0,0,0", storageString);

        // test graded submission
        submission.grade(8, false);
        storageString = submission.toStorageString();
        assertEquals("Test Assignment,1,0,8", storageString);

        // test late submission
        submission.grade(8, true);
        storageString = submission.toStorageString();
        assertEquals("Test Assignment,1,1,8", storageString);
    }
}
