package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.person.Person;
import seedu.address.model.video.Video;

/**
 * The API of the Model component.
 */
public interface Model {
    // TODO: Remove this
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<? super ReadOnlyModule> PREDICATE_SHOW_ALL_MODULES = unused -> true;

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

    // TODO: Replace this
    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    // TODO: Replace this
    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces tracker data with the data in {@code tracker}.
     */
    void setTracker(ReadOnlyTracker tracker);

    /**
     * Returns the Tracker.
     */
    ReadOnlyTracker getTracker();

    /**
     * Returns true if a module with the same code as {@code module} exists in the tracker.
     */
    boolean hasModule(ReadOnlyModule module);

    /**
     * Deletes the given module.
     * The module must exist in the tracker.
     */
    void deleteModule(ReadOnlyModule target);

    /**
     * Adds the given module.
     */
    void addModule(Module module);

    /**
     * Replaces the given module {@code target} with {@code editedModule}.
     * {@code target} must exist in the tracker.
     * The module code of {@code editedModule} must not be the same as another existing module in the tracker.
     */
    void setModule(ReadOnlyModule target, Module editedModule);

    /**
     * Returns true if a lecture with the same name as {@code lecture} exists in {@code module}.
     */
    boolean hasLecture(ReadOnlyModule module, ReadOnlyLecture lecture);

    /**
     * Deletes the given lecture {@code target}.
     * The lecture must exist in {@code module}.
     */
    void deleteLecture(ReadOnlyModule module, ReadOnlyLecture target);

    /**
     * Adds the given lecture to {@code module}.
     */
    void addLecture(ReadOnlyModule module, Lecture lecture);

    /**
     * Replaces the given lecture {@code target} with {@code editedLecture}.
     * {@code target} must exist in {@code module}.
     * The name of {@code editedLecture} must not be the same as another existing lecture in {@code module}.
     */
    void setLecture(ReadOnlyModule module, ReadOnlyLecture target, Lecture editedLecture);

    /**
     * Returns true if a video with the same name as {@code video} exists in {@code lecture}.
     */
    boolean hasVideo(ReadOnlyLecture lecture, Video video);

    /**
     * Deletes the given video {@code target}.
     * The video must exist in {@code lecture}.
     */
    void deleteVideo(ReadOnlyLecture lecture, Video video);

    /**
     * Adds the given video to {@code lecture}.
     */
    void addVideo(ReadOnlyLecture lecture, Video video);

    /**
     * Replaces the given video {@code target} with {@code editedVideo}.
     * {@code target} must exist in {@code lecture}.
     * The name of {@code editedVideo} must not be the same as another existing video in {@code lecture}.
     */
    void setVideo(ReadOnlyLecture lecture, Video target, Video editedVideo);

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<? extends ReadOnlyModule> getFilteredModuleList();

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<? super ReadOnlyModule> predicate);

    // TODO: Remove this
    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    // TODO: Remove this
    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    // TODO: Remove this
    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    // TODO: Remove this
    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    // TODO: Remove this
    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    // TODO: Remove this
    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    // TODO: Remove this
    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    // TODO: Remove this
    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);
}
