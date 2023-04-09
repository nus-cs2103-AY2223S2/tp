package taa.model.student;

import static taa.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static taa.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static taa.logic.commands.CommandTestUtil.VALID_TAG_LAB02;
import static taa.logic.commands.CommandTestUtil.VALID_TAG_TUT_15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import taa.model.assignment.Assignment;
import taa.model.assignment.Submission;
import taa.model.assignment.exceptions.AssignmentNotFoundException;
import taa.model.assignment.exceptions.InvalidGradeException;
import taa.model.assignment.exceptions.SubmissionNotFoundException;
import taa.model.tag.Tag;
import taa.testutil.Assert;
import taa.testutil.PersonBuilder;
import taa.testutil.TypicalPersons;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new PersonBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> student.getClassTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        Assertions.assertTrue(TypicalPersons.ALICE.isSameStudent(TypicalPersons.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.isSameStudent(null));

        // same name, all other attributes different -> returns true
        Student editedAlice = new PersonBuilder(TypicalPersons.ALICE)
                .withTags(VALID_TAG_TUT_15).build();
        Assertions.assertTrue(TypicalPersons.ALICE.isSameStudent(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.isSameStudent(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Student editedBob = new PersonBuilder(TypicalPersons.BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        Assertions.assertFalse(TypicalPersons.BOB.isSameStudent(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(TypicalPersons.BOB).withName(nameWithTrailingSpaces).build();
        Assertions.assertFalse(TypicalPersons.BOB.isSameStudent(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new PersonBuilder(TypicalPersons.ALICE).build();
        Assertions.assertTrue(TypicalPersons.ALICE.equals(aliceCopy));

        // same object -> returns true
        Assertions.assertTrue(TypicalPersons.ALICE.equals(TypicalPersons.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.equals(null));

        // different type -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.equals(5));

        // different student -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.equals(TypicalPersons.BOB));

        // different name -> returns false
        Student editedAlice = new PersonBuilder(TypicalPersons.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withTags(VALID_TAG_TUT_15).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));
    }

    @Test
    public void getName() {
        // Test pre-built Students
        Assertions.assertTrue(TypicalPersons.AMY.getName().equals(new Name(VALID_NAME_AMY)));
        Assertions.assertTrue(TypicalPersons.BOB.getName().equals(new Name(VALID_NAME_BOB)));

        // Test fresh copies of Students
        Student amyCopy = new PersonBuilder(TypicalPersons.AMY).build();
        Assertions.assertTrue(amyCopy.getName().equals(new Name(VALID_NAME_AMY)));

        Student bobCopy = new PersonBuilder(TypicalPersons.BOB).build();
        Assertions.assertTrue(bobCopy.getName().equals(new Name(VALID_NAME_BOB)));
    }

    @Test
    public void getClassTags() {
        // no tags provided --> size of set returned should be 0
        Assertions.assertEquals(TypicalPersons.CARL.getClassTags().size(), 0);

        // 1 tag provided --> returned set should contain one and only one tag with the given name
        Set<Tag> amyClassTags = TypicalPersons.AMY.getClassTags();
        Assertions.assertEquals(amyClassTags.size(), 1);
        Assertions.assertTrue(amyClassTags.contains(new Tag(VALID_TAG_LAB02)));

        // 2 tags provided --> both tags should be present in returned set, and nothing else
        Set<Tag> bobClassTags = TypicalPersons.BOB.getClassTags();
        Assertions.assertEquals(bobClassTags.size(), 2);
        Assertions.assertTrue(bobClassTags.contains(new Tag(VALID_TAG_LAB02)));
        Assertions.assertTrue(bobClassTags.contains(new Tag(VALID_TAG_TUT_15)));
    }

    @Test
    public void getClassTags_modifySet_throwsUnsupportedOperationException() {
        Set<Tag> amyClassTags = TypicalPersons.AMY.getClassTags();
        Assert.assertThrows(UnsupportedOperationException.class, ()
            -> amyClassTags.add(new Tag(VALID_TAG_TUT_15)));
    }

    @Test
    public void getLatestSubmission() {
        Student amyCopy = new PersonBuilder(TypicalPersons.AMY).build();

        // add other submissions
        amyCopy.addSubmission(new Submission(amyCopy, new Assignment("test1", 100)));
        amyCopy.addSubmission(new Submission(amyCopy, new Assignment("test2", 100)));

        // keep track of latest submission
        Submission latestSubmission = new Submission(amyCopy, new Assignment("test3", 100));
        amyCopy.addSubmission(latestSubmission);

        // check if returned value is indeed latest submission
        Assertions.assertTrue(amyCopy.getLatestSubmission().equals(latestSubmission));
    }

    @Test
    public void getLatestSubmission_noSubmissions_returnsNull() {
        Assertions.assertEquals(TypicalPersons.AMY.getLatestSubmission(), null);
    }

    @Test
    public void updateAttendanceCounter() {
        Student amyCopy = new PersonBuilder(TypicalPersons.AMY).build();

        // mark attendance for weeks 1, 3, 5, 7, 9
        // use List for its .contains() method
        List<Integer> markedWeekNumbers = List.of(1, 3, 5, 7, 9);

        for (int markedWeek : markedWeekNumbers) {
            amyCopy.getAtd().markAttendance(markedWeek);
        }

        // use running count instead of 0 to ensure updates by updateAttendanceCounter is not by coincidence
        int[] fakeCounter = new int[Attendance.NUM_WEEKS];
        int[] expectedCounter = new int[Attendance.NUM_WEEKS];
        for (int i = 0; i < Attendance.NUM_WEEKS; i++) {
            fakeCounter[i] = i;
            expectedCounter[i] = markedWeekNumbers.contains(i) ? i + 1 : i;
        }


        amyCopy.updateAttendanceCounter(fakeCounter);
        for (int i = 0; i < Attendance.NUM_WEEKS; i++) {
            Assertions.assertEquals(fakeCounter[i], expectedCounter[i]);
        }
    }

    @Test
    public void updateAttendanceCounter_invalidCounterLength_doesNothing() {
        Student amyCopy = new PersonBuilder(TypicalPersons.AMY).build();

        int[] fakeCounter = new int[Attendance.NUM_WEEKS + 1];
        for (int i = 0; i < Attendance.NUM_WEEKS + 1; i++) {
            fakeCounter[i] = i;
        }

        // mark attendance for weeks 1, 3, 5, 7, 9
        List<Integer> markedWeekNumbers = List.of(1, 3, 5, 7, 9);
        for (int markedWeek : markedWeekNumbers) {
            amyCopy.getAtd().markAttendance(markedWeek);
        }

        // try to update with counter that is too long -> should not change it
        int[] testCounter = Arrays.copyOf(fakeCounter, Attendance.NUM_WEEKS + 1);
        amyCopy.updateAttendanceCounter(testCounter);

        for (int i = 0; i < Attendance.NUM_WEEKS + 1; i++) {
            Assertions.assertEquals(testCounter[i], fakeCounter[i]);
        }
    }

    @Test
    public void getGradesForAssignment() {
        Student amyCopy = new PersonBuilder(TypicalPersons.AMY).build();

        // create assignment, and let Amy submit the assignment
        Assignment testAssignment = new Assignment("test1", 100);
        testAssignment.addStudent(amyCopy);

        // grade the assignment
        try {
            testAssignment.gradeSubmission(amyCopy, 80, false);
        } catch (Exception e) {
            Assertions.fail("Assignment::gradeSubmission failed: " + e.getMessage());
        }

        // get grades for Amy's submission for this assignment
        Optional<Integer> grades = Optional.empty();
        try {
            grades = amyCopy.getGradesForAssignment(testAssignment.getName());
        } catch (Exception e) {
            Assertions.fail("Student::getGradesForAssignment failed: " + e.getMessage());
        }

        Assertions.assertEquals(grades, Optional.of(80));
    }

    @Test
    public void getGradesForAssignment_noSuchGrading_returnsEmptyOptional() {
        Student amyCopy = new PersonBuilder(TypicalPersons.AMY).build();

        // create assignment, and let Amy submit the assignment
        Assignment testAssignment = new Assignment("test1", 100);
        testAssignment.addStudent(amyCopy);

        // do not grade the assignment, then
        // get grades for Amy's submission for this assignment
        Optional<Integer> grades = Optional.of(80);
        try {
            grades = amyCopy.getGradesForAssignment(testAssignment.getName());
        } catch (Exception e) {
            Assertions.fail("Student::getGradesForAssignment failed: " + e.getMessage());
        }

        Assertions.assertEquals(grades, Optional.empty());
    }

    @Test
    public void getGradesForAssignment_noSuchAssignment_throwsAssignmentNotFoundException() {
        Assertions.assertThrows(AssignmentNotFoundException.class, () -> TypicalPersons.AMY.getGradesForAssignment("nonexistent"));
    }
}
