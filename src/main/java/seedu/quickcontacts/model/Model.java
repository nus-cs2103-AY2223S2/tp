package seedu.quickcontacts.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.quickcontacts.commons.core.GuiSettings;
import seedu.quickcontacts.commons.core.index.Index;
import seedu.quickcontacts.model.meeting.DateTime;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.model.person.Name;
import seedu.quickcontacts.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {

    /** {@code Predicate} that always evaluate to true */
    Predicate<Meeting> PREDICATE_SHOW_ALL_MEETINGS = unused -> true;
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    Predicate<Meeting> PREDICATE_UNDONE_MEETINGS = meeting -> !meeting.getIsCompleted() && !meeting.hasPassed();

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' quick book file path.
     */
    Path getQuickBookFilePath();

    /**
     * Sets the user prefs' quick book file path.
     */
    void setQuickBookFilePath(Path quickBookFilePath);

    /**
     * Returns the QuickBook
     */
    ReadOnlyQuickBook getQuickBook();

    /**
     * Replaces quick book data with the data in {@code quickBook}.
     */
    void setQuickBook(ReadOnlyQuickBook quickBook);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the quick book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a meeting with the same identity as {@code person} exists in the quick book.
     */
    boolean hasMeeting(Meeting meeting);

    /**
     * Deletes the given person.
     * The person must exist in the quick book.
     */
    void deletePerson(Person target);

    /**
     * Gets persons at the corresponding indexes
     * @param indexList list of indexes to retrieve
     * @return persons at those indexes
     */
    List<Person> getPersonsByIndexes(List<Index> indexList);
    /**
     * Gets meetings at the corresponding indexes
     * @param indexList list of indexes to retrieve
     * @return meetings at those indexes
     */
    List<Meeting> getMeetingsByIndexesAndStartEnd(List<Index> indexList, DateTime start, DateTime end);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the quick book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the quick book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the quick book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the quick book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the quick book.
     */
    HashMap<String, String> indexAttendees(Person personToEdit, Person target);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the quick book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the quick book.
     */
    void setMeeting(Meeting target, Meeting editedMeeting);


    /**
     * Gets the Person matching the name from the quick book.
     *
     * @param personName name of the person to be retrieved.
     * @return the person with the given name.
     */
    Person getPersonByName(Name personName);

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
     * Updates the filter of the filtered meeting list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMeetingList(Predicate<Meeting> predicate);

    /**
     * Updates the filter of the filtered meeting list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void sortFilteredMeetingList(Comparator comparator);

    /**
     * Returns an unmodifiable view of the meetings list
     * Adds the given meeting.
     * {@code meeting} must not already exist in the meeting list.
     */
    void addMeeting(Meeting meeting);

    /**
     * Removes {@code key} from this {@code QuickBook}.
     * {@code key} must exist in the quick book.
     */
    void removeMeeting(Meeting meeting);

    /**
     * Returns an unmodifiable view of the meetings list
     */
    ObservableList<Meeting> getMeetingsList();

    /**
     * @return an unmodifiable view of the filtered list of meetings
     */
    ObservableList<Meeting> getFilteredMeetingList();
    void markMeetingsAsDone(List<Index> index) throws IndexOutOfBoundsException;
    void markMeetingsAsNotDone(List<Index> index) throws IndexOutOfBoundsException;

}
