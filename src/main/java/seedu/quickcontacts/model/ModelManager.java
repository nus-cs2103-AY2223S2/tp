package seedu.quickcontacts.model;

import static java.util.Objects.requireNonNull;
import static seedu.quickcontacts.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.quickcontacts.commons.core.GuiSettings;
import seedu.quickcontacts.commons.core.LogsCenter;
import seedu.quickcontacts.commons.core.index.Index;
import seedu.quickcontacts.model.meeting.DateTime;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.model.person.Name;
import seedu.quickcontacts.model.person.Person;

/**
 * Represents the in-memory model of the quick book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final QuickBook quickBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Meeting> filteredMeetings;

    /**
     * Initializes a ModelManager with the given quickBook and userPrefs.
     */
    public ModelManager(ReadOnlyQuickBook quickBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(quickBook, userPrefs);

        logger.fine("Initializing with quick book: " + quickBook + " and user prefs " + userPrefs);

        this.quickBook = new QuickBook(quickBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.quickBook.getPersonList());
        filteredMeetings = new FilteredList<>(this.quickBook.getMeetingList());
    }

    public ModelManager() {
        this(new QuickBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getQuickBookFilePath() {
        return userPrefs.getQuickBookFilePath();
    }

    @Override
    public void setQuickBookFilePath(Path quickBookFilePath) {
        requireNonNull(quickBookFilePath);
        userPrefs.setQuickBookFilePath(quickBookFilePath);
    }

    //=========== QuickBook ================================================================================

    @Override
    public ReadOnlyQuickBook getQuickBook() {
        return quickBook;
    }

    @Override
    public void setQuickBook(ReadOnlyQuickBook quickBook) {
        this.quickBook.resetData(quickBook);
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return quickBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        quickBook.removePerson(target);
    }

    @Override
    public List<Person> getPersonsByIndexes(List<Index> indexList) {
        List<Person> persons = new ArrayList<>();
        for (Index index : indexList) {
            persons.add(filteredPersons.get(
                    index.getZeroBased())
            );
        }
        return persons;
    }

    @Override
    public List<Meeting> getMeetingsByIndexesAndStartEnd(List<Index> indexList, DateTime start, DateTime end) {
        List<Meeting> meetings = new ArrayList<>();
        for (Index index : indexList) {
            meetings.add(filteredMeetings.get(
                    index.getZeroBased())
            );
        }
        if (start != null || end != null) {
            for (Meeting meeting : getMeetingsList()) {
                if (!meetings.contains(meeting) && meeting.isBetween(start, end)) {
                    meetings.add(meeting);
                }
            }
        }
        return meetings;
    }

    @Override
    public void addPerson(Person person) {
        quickBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        quickBook.setPerson(target, editedPerson);
    }

    @Override
    public HashMap<String, String> indexAttendees(Person personToEdit, Person target) {
        requireAllNonNull(personToEdit);

        return quickBook.indexAttendees(personToEdit, target);
    }

    @Override
    public Person getPersonByName(Name personName) {
        return quickBook.getPersonByName(personName);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedQuickBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }


    @Override
    public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
        requireNonNull(predicate);
        filteredMeetings.setPredicate(predicate);
    }

    @Override
    public void sortFilteredMeetingList(Comparator comparator) {
        quickBook.sortMeeting(comparator);
    }

    @Override
    public void removeMeeting(Meeting key) {
        quickBook.removeMeeting(key);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return quickBook.equals(other.quickBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

    //================= Meeting accessors ==================//
    @Override
    public void addMeeting(Meeting meeting) {
        quickBook.addMeeting(meeting);
        updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
    }

    @Override
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return quickBook.hasMeeting(meeting);
    }

    @Override
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);
        quickBook.setMeeting(target, editedMeeting);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Meeting} backed by the internal list of
     * {@code versionedQuickBook}
     */
    @Override
    public ObservableList<Meeting> getMeetingsList() {
        return filteredMeetings;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Meeting} backed by the internal list of
     * {@code versionedQuickBook}
     */
    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return filteredMeetings;
    }

    @Override
    public void markMeetingsAsDone(List<Index> indexList) throws IndexOutOfBoundsException {
        for (Index index : indexList) {
            // check if indexes all exist
            if (index.getZeroBased() >= filteredMeetings.size()) {
                throw new IndexOutOfBoundsException();
            }
        }
        for (Index index : indexList) {
            Meeting meeting = this.filteredMeetings.get(index.getZeroBased());
            setMeeting(meeting, new Meeting(meeting.getTitle(), meeting.getDateTime(), meeting.getAttendees(),
                    meeting.getLocation(), meeting.getDescription(), true));
        }
    }

    @Override
    public void markMeetingsAsNotDone(List<Index> indexList) throws IndexOutOfBoundsException {
        for (Index index : indexList) {
            // check if indexes all exist
            if (index.getZeroBased() >= filteredMeetings.size()) {
                throw new IndexOutOfBoundsException();
            }
        }

        for (Index index : indexList) {
            Meeting meeting = filteredMeetings.get(index.getZeroBased());
            setMeeting(meeting, new Meeting(meeting.getTitle(), meeting.getDateTime(), meeting.getAttendees(),
                    meeting.getLocation(), meeting.getDescription(), false));
        }
    }
}
