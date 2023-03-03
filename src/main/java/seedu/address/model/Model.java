package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.pilot.Pilot;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to false
     */
    Predicate<Pilot> PREDICATE_SHOW_ALL_PILOTS = unused -> false;

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

    /**
     * Returns the AddressBook
     */
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

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns the person manager.
     *
     * @return the person manager.
     */
    ReadOnlyIdentifiableManager<Pilot> getPilotManager();

    // ================ Pilot methods ==============================

    /**
     * Returns the pilot manager file path.
     *
     * @return the pilot manager file path.
     */
    Path getPilotManagerFilePath();

    /**
     * Sets the pilot manager file path.
     *
     * @param pilotManagerFilePath the pilot manager file path.
     */
    void setPilotManagerFilePath(Path pilotManagerFilePath);

    /**
     * Replaces pilot manager data with the data in {@code pilotManager}.
     *
     * @param pilotManager the pilot manager to replace with.
     */
    void setPilotManager(ReadOnlyIdentifiableManager<Pilot> pilotManager);

    /**
     * Returns true if a pilot with the same identity as {@code pilot} exists
     * in the address book.
     *
     * @param pilot the pilot to check.
     * @return true if the pilot exists, false otherwise.
     */
    boolean hasPilot(Pilot pilot);

    /**
     * Deletes the given pilot.
     * The pilot must exist in the address book.
     *
     * @param target the pilot to delete.
     */
    void deletePilot(Pilot target);

    /**
     * Deletes the pilot with the given id.
     */
    void deletePilot(String id);

    /**
     * Adds the given pilot.
     *
     * @param pilot the pilot to add.
     */
    void addPilot(Pilot pilot);

    /**
     * Replaces the given pilot {@code target} with {@code editedPilot}.
     * {@code target} must exist in the address book.
     * The pilot identity of {@code editedPilot} must not be the same as another existing pilot in the address book.
     *
     * @param target      the pilot to replace.
     * @param editedPilot the pilot to replace with.
     */
    void setPilot(Pilot target, Pilot editedPilot);

    /**
     * Returns an unmodifiable view of the filtered pilot list
     */
    ObservableList<Pilot> getFilteredPilotList();

    /**
     * Updates the filter of the filtered pilot list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPilotList(Predicate<Pilot> predicate);
}
