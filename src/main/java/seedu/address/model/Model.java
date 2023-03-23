package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Comparator} that sorts by contact index */
    Comparator<Person> COMPARATOR_CONTACT_INDEX = Comparator.comparing(Person::getContactIndex);

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
    Path getEduMateFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setEduMateFilePath(Path eduMateFilePath);

    /**
     * Replaces address book data with the data in {@code eduMate}.
     */
    void setEduMate(ReadOnlyEduMate eduMate);

    /** Returns the EduMate */
    ReadOnlyEduMate getEduMate();

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
    Person addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Clears the person list.
     * Used for sorting the list, as the list needs to be cleared before it can be sorted.
     */
    void resetPersons();

    /**
     * Returns the user object.
     * User will not be null.
     */
    User getUser();

    /**
     * Sets the user object to the EduMate object.
     * @param user {@code User} must not be null.
     */
    void setUser(User user);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getObservablePersonList();

    /**
     * Updates the filter of the observable person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateObservablePersonList(Predicate<Person> predicate);

    /**
     * Updates the sort comparator of the observable person list to sort by the given {@code comparator}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateObservablePersonList(Comparator<Person> comparator);

    /**
     * Updates the sort and filter of the observable person list to default.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateObservablePersonList();
}
