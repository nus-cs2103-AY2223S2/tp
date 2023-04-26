package taa.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import taa.commons.core.GuiSettings;
import taa.logic.commands.enums.ChartType;
import taa.logic.commands.exceptions.CommandException;
import taa.model.ClassList;
import taa.model.Model;
import taa.model.ReadOnlyUserPrefs;
import taa.model.alarm.Alarm;
import taa.model.assignment.Assignment;
import taa.model.student.Student;
import taa.storage.TaaData;
import taa.testutil.Assert;
import taa.testutil.PersonBuilder;

public class AddStudentCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new AddStudentCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Student validStudent = new PersonBuilder().build();

        CommandResult commandResult = new AddStudentCommand(validStudent).execute(modelStub);

        assertEquals(String.format(AddStudentCommand.MESSAGE_SUCCESS, validStudent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStudent), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Student validStudent = new PersonBuilder().build();
        AddStudentCommand addStudentCommand = new AddStudentCommand(validStudent);
        ModelStub modelStub = new ModelStubWithPerson(validStudent);

        Assert.assertThrows(
                CommandException.class, AddStudentCommand.MESSAGE_DUPLICATE_STUDENT, ()
                        -> addStudentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Student alice = new PersonBuilder().withName("Alice").build();
        Student bob = new PersonBuilder().withName("Bob").build();
        AddStudentCommand addAliceCommand = new AddStudentCommand(alice);
        AddStudentCommand addBobCommand = new AddStudentCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddStudentCommand addAliceCommandCopy = new AddStudentCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different student -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTaaDataFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaaDataFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public TaaData getTaaData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaaData(TaaData newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClassList(ClassList tocheck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getClassListSize() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClassList(ClassList toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredClassList() {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredClassLists(Predicate<ClassList> predicate) {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public void addStudentToTaggedClasses(Student student) {
            requireNonNull(student);
        }

        @Override
        public boolean hasAssignment(String assignmentName) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void addAssignment(String toAdd, int totalMarks) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAssignment(String assignmentName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void grade(String assignmentName, int studentId, int marks, boolean isLateSubmission) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String listAssignments() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void ungrade(String assignmentName, int studentId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAlarm(Alarm alarm) throws CommandException {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void deleteStudentSubmission(Student studentToDelete) {
        }

        @Override
        public void initAssignmentsFromStorage(Assignment[] asgnArr) {
        }

        @Override
        public void addStudentAssignment(Student stu) {
        }

        @Override
        public void displayChart(ChartType chart, String... args) {
            throw new AssertionError("This method should not be called");
        }

        public String listAlarms() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void deleteAlarm(int index) throws CommandException {
            throw new AssertionError("This method should not be called");

        }
    }

    /**
     * A Model stub that contains a single student.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Student student;

        ModelStubWithPerson(Student student) {
            requireNonNull(student);
            this.student = student;
        }

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return this.student.isSameStudent(student);
        }
    }

    /**
     * A Model stub that always accept the student being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Student> personsAdded = new ArrayList<>();

        @Override
        public void addStudent(Student student) {
            requireNonNull(student);
            personsAdded.add(student);
        }

        @Override
        public TaaData getTaaData() {
            return new TaaData();
        }

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return personsAdded.stream().anyMatch(student::isSameStudent);
        }

    }

}
