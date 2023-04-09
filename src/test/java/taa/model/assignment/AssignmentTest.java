package taa.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taa.model.assignment.exceptions.InvalidGradeException;
import taa.model.assignment.exceptions.SubmissionNotFoundException;
import taa.model.student.Name;
import taa.model.student.Student;

class AssignmentTest {

    private Assignment assignment;
    private Student student;

    @BeforeEach
    void setUp() {
        // Create a new assignment with total marks of 100.
        assignment = new Assignment("Test Assignment", 100);
        student = new Student(new Name("John Doe"), "0;0;0;0;0;0;0;0;0;0;0;0",
                "-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1", new ArrayList<>(), new HashSet<>());
    }

    @AfterEach
    void tearDown() {
        // Delete the assignment after each test.
        assignment.delete();
    }

    @Test
    void testConstructor() {
        // Test that the constructor sets the name and totalMarks correctly.
        assertEquals("Test Assignment", assignment.getName());
        assertEquals(100, assignment.getTotalMarks());
    }

    @Test
    void testAddStudent() {
        // Test that a student is added to the assignment correctly.
        assignment.addStudent(student);
        assertEquals(1, assignment.getSubmissions().size());
        assertTrue(assignment.getSubmissions().get(0).toString().contains("John Doe"));
    }

    @Test
    void testAddStudentSubmission() {
        // Test that a student submission is added to the assignment correctly.
        String submissionString = "Test submission,1,0,80,100";
        assignment.addStudentSubmission(student, submissionString);
        assertEquals(1, assignment.getSubmissions().size());
        assertTrue(assignment.getSubmissions().get(0).toString().contains("John Doe"));
    }

    @Test
    void testGradeSubmission() throws SubmissionNotFoundException, InvalidGradeException {
        // Test that a submission can be graded correctly.
        assignment.addStudent(student);
        assignment.gradeSubmission(student, 80, false);
        assertEquals(80, assignment.getSubmissions().get(0).getScore().get());
        assertFalse(assignment.getSubmissions().get(0).toString().contains("Late Submission"));
    }

    @Test
    void testUngradeSubmission() throws SubmissionNotFoundException, InvalidGradeException {
        // Test that a submission can be ungraded correctly.
        assignment.addStudent(student);
        assignment.gradeSubmission(student, 80, false);
        assignment.ungradeSubmission(student);
        assertFalse(assignment.getSubmissions().get(0).getScore().isPresent());
        assertFalse(assignment.getSubmissions().get(0).toString().contains("Ungraded"));
    }

    @Test
    void testDeleteStudentSubmission() {
        // Test that a student submission can be deleted correctly.
        assignment.addStudent(student);
        assignment.deleteStudentSubmission(student);
        assertEquals(0, assignment.getSubmissions().size());
    }

    @Test
    void testIsValidAssignmentName() {
        assertTrue(Assignment.isValidAssignmentName("Lab 1"));
        assertFalse(Assignment.isValidAssignmentName("Lab_1"));
    }

    @Test
    void testIsValidAssignmentMarks() {
        assertTrue(Assignment.isValidAssignmentMarks(100));
        assertFalse(Assignment.isValidAssignmentMarks(-1));
    }
}
