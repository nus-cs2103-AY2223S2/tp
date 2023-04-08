package taa.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import taa.model.assignment.exceptions.AssignmentNotFoundException;
import taa.model.assignment.exceptions.DuplicateAssignmentException;
import taa.model.assignment.exceptions.InvalidGradeException;
import taa.model.assignment.exceptions.SubmissionNotFoundException;
import taa.model.student.Name;
import taa.model.student.Student;

class AssignmentListTest {

    private AssignmentList assignmentList;
    private FilteredList<Student> studentList;

    @BeforeEach
    void setUp() {
        // Initialize a new instance of AssignmentList before each test
        assignmentList = new AssignmentList();
        studentList = new FilteredList<>(FXCollections.observableArrayList());
    }

    @AfterEach
    void tearDown() {
        // Clean up any resources used by the assignmentList instance after each test
        assignmentList = null;
        studentList = null;
    }

    @Test
    void checkValidStorage() {
        // Add test here.
        assertTrue(1 == 1);
    }

    @Test
    void testValidCsvSubmissions() {
        // Add test here.
        assertTrue(1 == 1);
    }

    @Test
    void add() throws DuplicateAssignmentException {
        // Test the method that adds a new assignment to the list
        assignmentList.add("Assignment 1", studentList, 10);
        assertTrue(assignmentList.contains("Assignment 1"));
        assertEquals(1, assignmentList.getAssignments().length);
        assertEquals("Assignment 1", assignmentList.getAssignments()[0].getName());
        assertThrows(DuplicateAssignmentException.class, () -> assignmentList.add("Assignment 1", studentList, 10));
    }

    @Test
    void delete() throws DuplicateAssignmentException, AssignmentNotFoundException {
        // Test the method that deletes an assignment from the list
        assignmentList.add("Assignment 1", studentList, 10);
        assignmentList.delete("Assignment 1");
        assertFalse(assignmentList.contains("Assignment 1"));
        assertEquals(0, assignmentList.getAssignments().length);
        assertThrows(AssignmentNotFoundException.class, () -> assignmentList.delete("Assignment 1"));
    }

    @Test
    void grade() throws DuplicateAssignmentException, InvalidGradeException,
            AssignmentNotFoundException, SubmissionNotFoundException {
        // Test the method that grades a submission
        Student student = new Student(new Name("John Doe"), "0;0;0;0;0;0;0;0;0;0;0;0",
                "-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1", new ArrayList<>(), new HashSet<>());
        assignmentList.add("Assignment 1", studentList, 100);
        assignmentList.addStudent(student);
        assignmentList.grade("Assignment 1", student, 80, false);
        assertThrows(AssignmentNotFoundException.class, () -> assignmentList.grade("Unknown", student, 80, false));
        assertThrows(InvalidGradeException.class, () -> assignmentList.grade("Assignment 1", student, -1, false));
        assertThrows(InvalidGradeException.class, () -> assignmentList.grade("Assignment 1", student, 101, false));
    }

    @Test
    void ungrade() throws DuplicateAssignmentException, InvalidGradeException,
            AssignmentNotFoundException, SubmissionNotFoundException {
        // Test the method that removes a grade from a submission
        Student student = new Student(new Name("John Doe"), "0;0;0;0;0;0;0;0;0;0;0;0",
                "-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1", new ArrayList<>(), new HashSet<>());
        assignmentList.add("Assignment 1", studentList, 100);
        assignmentList.addStudent(student);
        assignmentList.grade("Assignment 1", student, 80, false);
        assignmentList.ungrade("Assignment 1", student);
        assertThrows(AssignmentNotFoundException.class, () -> assignmentList.ungrade("Unknown", student));
    }

    @Test
    void list() throws DuplicateAssignmentException {
        // Test case for listing all assignments
        Student alice = new Student(new Name("Alice"), "0;0;0;0;0;0;0;0;0;0;0;0", "-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1",
                new ArrayList<>(), new HashSet<>());
        Student bob = new Student(new Name("Bob"), "0;0;0;0;0;0;0;0;0;0;0;0", "-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1",
                new ArrayList<>(), new HashSet<>());
        assignmentList.add("Assignment 1", studentList, 100);
        assignmentList.add("Assignment 2", studentList, 100);
        assignmentList.addStudent(alice);
        assignmentList.addStudent(bob);

        assertTrue(assignmentList.list().contains("Assignment 1"));
        assertTrue(assignmentList.list().contains("Assignment 2"));
        assertFalse(assignmentList.list().contains("Assignment 3"));
        assertTrue(assignmentList.list().split("Alice").length == 3);
        assertTrue(assignmentList.list().split("Bob").length == 3);
    }
    @Test
    void deleteStudentTest() throws DuplicateAssignmentException {
        Student alice = new Student(new Name("Alice"), "0;0;0;0;0;0;0;0;0;0;0;0", "-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1",
                new ArrayList<>(), new HashSet<>());
        Student bob = new Student(new Name("Bob"), "0;0;0;0;0;0;0;0;0;0;0;0", "-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1",
                new ArrayList<>(), new HashSet<>());
        assignmentList.add("Assignment 1", studentList, 100);
        assignmentList.add("Assignment 2", studentList, 100);
        assignmentList.addStudent(alice);
        assignmentList.addStudent(bob);
        assertTrue(assignmentList.getAssignments()[0].getSubmissions().size() == 2);
        assignmentList.deleteStudent(alice);
        assertTrue(assignmentList.getAssignments()[0].getSubmissions().size() == 1);
    }

    @Test
    void addStudentTest() throws DuplicateAssignmentException {
        Student alice = new Student(new Name("Alice"), "0;0;0;0;0;0;0;0;0;0;0;0", "-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1",
                new ArrayList<>(), new HashSet<>());
        Student bob = new Student(new Name("Bob"), "0;0;0;0;0;0;0;0;0;0;0;0", "-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1",
                new ArrayList<>(), new HashSet<>());
        assignmentList.add("Assignment 1", studentList, 100);
        assignmentList.add("Assignment 2", studentList, 100);
        assignmentList.addStudent(alice);
        assertTrue(assignmentList.getAssignments()[0].getSubmissions().size() == 1);
        assignmentList.addStudent(bob);
        assertTrue(assignmentList.getAssignments()[1].getSubmissions().size() == 2);
    }

    @Test
    void initFromStorageTest() {
        // Add test here.
        assertTrue(1 == 1);
    }

    @Test
    void containsTest() throws DuplicateAssignmentException {
        assignmentList.add("Assignment 1", studentList, 100);
        assertTrue(assignmentList.contains("Assignment 1"));
        assertFalse(assignmentList.contains("Unknown"));
    }

    @Test
    void getAssignmentsTest() throws DuplicateAssignmentException {
        assignmentList.add("Assignment 1", studentList, 100);
        assignmentList.add("Assignment 2", studentList, 100);
        assertEquals(2, assignmentList.getAssignments().length);
    }
}
