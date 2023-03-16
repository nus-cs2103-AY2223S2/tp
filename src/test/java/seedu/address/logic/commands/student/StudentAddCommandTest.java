package seedu.address.logic.commands.student;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddCommandTest;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.UniqueParentList;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.UniqueStudentList;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.StudentBuilder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


class StudentAddCommandTest {

    @Test
    void execute_studentAcceptedByModel_addSuccessful() throws Exception {
        StudentAddCommandTest.ModelStubAcceptingStudentAdded modelStub =
                new StudentAddCommandTest.ModelStubAcceptingStudentAdded();
        Student validStudent = new StudentBuilder().build();
        System.out.println(validStudent);
        CommandResult commandResult = new StudentAddCommand(validStudent).execute(modelStub);

        assertEquals(String.format(StudentAddCommand.MESSAGE_SUCCESS, validStudent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStudent), modelStub.studentsAdded);

    }

    @Test
    void testEquals() {
    }

    @Test
    void testExecute() {
    }
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {

        }

        @Override
        public void addParent(Parent parent) {

        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student student) {
            return false;
        }

        @Override
        public boolean hasParent(Parent parent) {
            return false;
        }

        @Override
        public boolean canInitialize(Phone phone, Name parentName) {
            return true;
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudent(Student target) {

        }

        @Override
        public void deleteParent(Parent target) {

        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {

        }

        @Override
        public void setParent(Parent target, Parent editedParent) {

        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Parent> getFilteredParentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {

        }

        @Override
        public void updateFilteredParentList(Predicate<Parent> predicate) {

        }

    }
    private class ModelStubAcceptingStudentAdded extends StudentAddCommandTest.ModelStub {
        final ArrayList<Student> studentsAdded = new ArrayList<>();
        private final UniqueStudentList students = new UniqueStudentList();

        private final UniqueParentList parents = new UniqueParentList();

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return studentsAdded.stream().anyMatch(student::isSamePerson);
        }

        @Override
        public void addStudent(Student student) {
            requireNonNull(student);
            studentsAdded.add(student);
        }
        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return students.asUnmodifiableObservableList();
        }

        @Override
        public ObservableList<Parent> getFilteredParentList() {
            return parents.asUnmodifiableObservableList();
        }


        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
