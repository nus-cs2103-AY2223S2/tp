package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Tutorial;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a tutorial with the same identity as {@code tutorial} exists in the address book.
     */
    boolean hasTutorial(Tutorial tutorial);

    /**
     * Deletes the given tutorial.
     * The tutorial must exist in the address book.
     */
    void deleteTutorial(Tutorial target);

    /**
     * Adds the given tutorial.
     * {@code tutorial} must not already exist in the address book.
     */
    void addTutorial(Tutorial tutorial);

    /**
     * Replaces the given tutorial {@code target} with {@code editedTutorial}.
     * {@code target} must exist in the address book.
     * The tutorial identity of {@code editedTutorial} must not be the same as another
     * existing tutorial in the address book.
     */
    void setTutorial(Tutorial target, Tutorial editedTutorial);

    /** Returns an unmodifiable view of the filtered tutorial list */
    ObservableList<Tutorial> getFilteredTutorialList();

    /**
     * Updates the filter of the filtered tutorial list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTutorialList(Predicate<Tutorial> predicate);

    /**
     * Returns true if a lab with the same identity as {@code lab} exists in the address book.
     */
    boolean hasLab(Lab lab);

    /**
     * Deletes the given lab.
     * The lab must exist in the address book.
     */
    void deleteLab(Lab target);

    /**
     * Adds the given lab.
     * {@code lab} must not already exist in the address book.
     */
    void addLab(Lab lab);

    /**
     * Replaces the given lab {@code target} with {@code editedLab}.
     * {@code target} must exist in the address book.
     * The lab identity of {@code editedLab} must not be the same as another
     * existing lab in the address book.
     */
    void setLab(Lab target, Lab editedLab);

    /** Returns an unmodifiable view of the filtered lab list */
    ObservableList<Lab> getFilteredLabList();

    /**
     * Updates the filter of the filtered lab list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredLabList(Predicate<Lab> predicate);
}
