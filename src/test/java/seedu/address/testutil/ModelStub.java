package seedu.address.testutil;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
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
    public boolean canReplacePerson(Person toBeReplaced, Person replacement) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePerson(Person target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ArrayList<String> getExistingEducationValues() {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public ArrayList<String> getExistingModuleValues() {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public ArrayList<String> getExistingTagValues() {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public boolean checkUndoable() {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public boolean checkRedoable() {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public String undoAddressBook() {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public String redoAddressBook() {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public void commitAddressBook(String lastExecutedCommand) {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public void updateShowPerson(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public ObservableList<Person> getShowPerson() {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public void setDefaultShowPerson() {
        throw new AssertionError("This method should not be called");
    }
}
