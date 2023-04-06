package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    //    @Test
    //    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
    //        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
    //        Student validPerson = new StudentBuilder().build();
    //
    //        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);
    //
    //        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
    //        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    //    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Student validPerson = new StudentBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_STUDENT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Student alice = new StudentBuilder().withName("Alice").build();
        Student bob = new StudentBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
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
        public void addPerson(Student person) {
            throw new AssertionError("This method should not be called.");
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
        public boolean hasPerson(Student person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDuplicateName(String name) {
            throw new AssertionError("this method should not be called.");
        }
        @Override
        public boolean hasExtendedName(String name) {
            throw new AssertionError("this method should not be called.");
        }

        @Override
        public boolean noSuchStudent(String name) {
            throw new AssertionError("this method should not be called.");
        }
        @Override
        public boolean hasDuplicateNameEdit(String name, Integer index) {
            throw new AssertionError("this method should not be called.");
        }
        @Override
        public boolean hasExtendedNameEdit(String name, Integer index) {
            throw new AssertionError("this method should not be called.");
        }
        @Override
        public boolean hasDuplicateNameAdd(String name) {
            throw new AssertionError("this method should not be called.");
        }
        @Override
        public boolean hasConflictingLessonTime(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasConflictingExamTime(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Student person;

        ModelStubWithPerson(Student person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Student person) {
            requireNonNull(person);
            return this.person.isSameStudent(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Student> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Student person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSameStudent);
        }

        @Override
        public void addPerson(Student person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public boolean hasConflictingLessonTime(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasConflictingExamTime(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }
    }

}
