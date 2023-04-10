package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

class ViewProfileCommandTest {
    private class ModelStub implements Model {

        /**
         * Replaces user prefs data with the data in {@code userPrefs}.
         *
         * @param userPrefs
         */
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {

        }

        /**
         * Returns the user prefs.
         */
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            return null;
        }

        /**
         * Returns the user prefs' GUI settings.
         */
        @Override
        public GuiSettings getGuiSettings() {
            return null;
        }

        /**
         * Sets the user prefs' GUI settings.
         *
         * @param guiSettings
         */
        @Override
        public void setGuiSettings(GuiSettings guiSettings) {

        }

        /**
         * Returns the user prefs' address book file path.
         */
        @Override
        public Path getAddressBookFilePath() {
            return null;
        }

        /**
         * Sets the user prefs' address book file path.
         *
         * @param addressBookFilePath
         */
        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {

        }

        /**
         * Replaces address book data with the data in {@code addressBook}.
         *
         * @param addressBook
         */
        @Override
        public void setAddressBook(ReadOnlyAddressBook addressBook) {

        }

        /**
         * Returns the AddressBook
         */
        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return null;
        }

        /**
         * Returns true if a person with the same identity as {@code person} exists in the address book.
         *
         * @param person
         */
        @Override
        public boolean hasPerson(Student person) {
            return false;
        }

        /**
         * Deletes the given person.
         * The person must exist in the address book.
         *
         * @param target
         */
        @Override
        public void deletePerson(Student target) {

        }

        /**
         * Adds the given person.
         * {@code person} must not already exist in the address book.
         *
         * @param person
         */
        @Override
        public void addPerson(Student person) {

        }

        /**
         * Replaces the given person {@code target} with {@code editedPerson}.
         * {@code target} must exist in the address book.
         *
         * @param target
         * @param editedPerson
         */
        @Override
        public void setStudent(Student target, Student editedPerson) {

        }

        /**
         * Returns an unmodifiable view of the filtered person list
         */
        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return null;
        }

        /**
         * Updates the filter of the filtered person list to filter by the given {@code predicate}.
         *
         * @param predicate
         * @throws NullPointerException if {@code predicate} is null.
         */
        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {

        }

        @Override
        public boolean hasDuplicateName(String name) {
            return false;
        }

        @Override
        public boolean hasDuplicateNameEdit(String name, Integer index) {
            return false;
        }

        @Override
        public boolean hasExtendedName(String name) {
            return false;
        }

        @Override
        public boolean hasExtendedNameEdit(String name, Integer index) {
            return false;
        }

        @Override
        public boolean noSuchStudent(String name) {
            return false;
        }

        @Override
        public boolean hasDuplicateNameAdd(String toString) {
            return false;
        }

        @Override
        public boolean hasConflictingLessonTime(Lesson lesson) {
            return false;
        }

        @Override
        public boolean hasConflictingExamTime(Lesson lesson) {
            return false;
        }
    }
    private class NoSuchStudent extends ModelStub {
        @Override
        public boolean noSuchStudent(String name) {
            return true;
        }
    }
    private class NamesExistButDuplicate extends ModelStub {
        public boolean hasDuplicateName(String name) {
            return true;
        }
    }

    private class ContainsDefaultStudent extends ModelStub {

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            StudentBuilder sb = new StudentBuilder();
            Student student = sb.build();
            return FXCollections.observableList(List.of(student));
        }
    }

    private List<String> names = List.of("name 1", "name 2", "name 3");
    private Predicate<Student> returnTrue = (x)->true;
    @Test
    void constructor_validParams_success() {
        new ViewProfileCommand(names, returnTrue);
    }

    @Test
    void execute_noSuchStudentCheck_commandExceptionThrown() {
        ViewProfileCommand command = new ViewProfileCommand(names, returnTrue);
        assertThrows(CommandException.class, ()->command.execute(new NoSuchStudent()));
    }

    @Test
    void execute_duplicateNames_commandExceptionThrown() {
        ViewProfileCommand command = new ViewProfileCommand(names, returnTrue);
        assertThrows(CommandException.class, ()->command.execute(new NamesExistButDuplicate()));
    }

    @Test
    void execute_singleStudentMatchedToOneName_success() throws CommandException {
        String correctCommandResult = "1 students listed!\n"
                + "--------------------------------------------------\n"
                + "Amy Bee:\n"
                + "Phone: 85355255\n"
                + "Address: 123, Jurong West Ave 6, #08-111\n"
                + "Email: amy@gmail.com\n"
                + "Tags: \n"
                + "--------------------------------------------------\n";
        ViewProfileCommand command = new ViewProfileCommand(List.of(StudentBuilder.DEFAULT_NAME), returnTrue);
        CommandResult cr = command.execute(new ContainsDefaultStudent());
        assertEquals(cr.getFeedbackToUser(), correctCommandResult);
    }
}
