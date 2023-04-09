package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Note;
import seedu.address.model.event.Tutorial;
import seedu.address.model.person.Person;

/**
 * Makes the API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Tutorial> PREDICATE_SHOW_ALL_TUTORIALS = unused -> true;
    Predicate<Consultation> PREDICATE_SHOW_ALL_CONSULTATIONS = unused -> true;
    Predicate<Lab> PREDICATE_SHOW_ALL_LABS = unused -> true;


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
    void updateSortAllPersonList(String metric, boolean increasingOrder);

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

    /**
     * Adds a student to a tutorial.
     *
     * @param toAdd The index of the student within the AddressBook's internal UniquePersonList to be added.
     * @param tutIndex The index of the tutorial that the student will be added into.
     */
    void addStudentToTutorial(Index toAdd, Index tutIndex) throws CommandException;

    /**
     * Deletes a student from an event
     *
     * @param toDel the index of the student within the AddressBook's internal UniquePersonList to be deleted.
     * @param eventIndex the index of the event that the student will be deleted from.
     * @param eventType the type of event that the student will be deleted from.
     */
    void deleteStudentFromEvent(Index toDel, Index eventIndex, String eventType) throws CommandException;

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

    /**
     * Adds a student to a lab session.
     *
     * @param toAdd The index of the student within the AddressBook's internal UniquePersonList to be added.
     * @param labIndex The index of the lab session that the student will be added into.
     */
    void addStudentToLab(Index toAdd, Index labIndex) throws CommandException;

    /** Returns an unmodifiable view of the filtered lab list */
    ObservableList<Lab> getFilteredLabList();

    /**
     * Updates the filter of the filtered lab list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredLabList(Predicate<Lab> predicate);

    /**
     * Returns true if a lab with the same identity as {@code lab} exists in the address book.
     */
    boolean hasConsultation(Consultation consultation);

    /**
     * Deletes the given consultation.
     * The consultation must exist in the address book.
     */
    void deleteConsultation(Consultation target);

    /**
     * Adds the given consultation.
     * {@code consultation} must not already exist in the address book.
     */
    void addConsultation(Consultation consultation);

    /**
     * Replaces the given consultation {@code target} with {@code editedConsultation}.
     * {@code target} must exist in the address book.
     * The consultation identity of {@code editedConsultation} must not be the same as another
     * existing consultation in the address book.
     */
    void setConsultation(Consultation target, Consultation editedConsultation);

    /**
     * Adds a student to a consultation session.
     *
     * @param toAdd The index of the student within the AddressBook's internal UniquePersonList to be added.
     * @param consultationIndex The index of the consultation session that the student will be added into.
     */
    void addStudentToConsultation(Index toAdd, Index consultationIndex) throws CommandException;

    /** Returns an unmodifiable view of the filtered lab list */
    ObservableList<Consultation> getFilteredConsultationList();

    /**
     * Updates the filter of the filtered consultation list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredConsultationList(Predicate<Consultation> predicate);

    boolean editNoteFromTutorial(Index index, Note note, String nameOfEvent);
    boolean editNoteFromLab(Index index, Note note, String nameOfEvent);
    boolean editNoteFromConsultation(Index index, Note note, String nameOfEvent);

    boolean removeNoteFromTutorial(Index index, String nameOfEvent);
    boolean removeNoteFromLab(Index index, String nameOfEvent);
    boolean removeNoteFromConsultation(Index index, String nameOfEvent);

    /**
     * Append notes to an existing tutorial note list
     * @param note The note to add
     */
    boolean addNoteToTutorial(Note note, String nameOfEvent);

    /**
     * Append notes to an existing lab note list
     * @param note The note to add
     */
    boolean addNoteToLab(Note note, String nameOfEvent);

    /**
     * Append notes to an existing consultation note list
     * @param note The note to add
     */
    boolean addNoteToConsultation(Note note, String nameOfEvent);

    void updateSortTutorialPersonList(String metric, boolean isIncreasing);

    void updateSortLabPersonList(String metric, boolean isIncreasing);

    void updateSortConsultationPersonList(String metric, boolean isIncreasing);
}
