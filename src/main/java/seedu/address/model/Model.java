package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
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
     *
     * @param userPrefs The data to replace the current user prefs data.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     *
     * @return The user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     *
     * @return The user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     *
     * @param guiSettings The GUI settings to set the user prefs's GUI settings to.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' tracker file path.
     *
     * @return The user prefs' tracker file path.
     */
    Path getTrackerFilePath();

    /**
     * Sets the user prefs' tracker file path.
     *
     * @param trackerFilePath The file path to set the user prefs' tracker file path to.
     */
    void setTrackerFilePath(Path trackerFilePath);

    /**
     * Replaces tracker data with the data in {@code tracker}.
     *
     * @param tracker The tracker whose data will replace the current tracker data.
     */
    void setTracker(ReadOnlyTracker tracker);

    /**
     * Returns the tracker.
     *
     * @return The tracker.
     */
    ReadOnlyTracker getTracker();

    /**
     * Returns true if a module with the same code as {@code module} exists in the tracker.
     *
     * @param module The module to check if exist.
     * @return True if a module with the same code as {@code module} exists in the tracker. Otherwise, false.
     */
    boolean hasModule(ReadOnlyModule module);

    /**
     * Returns true if a module which has {@code moduleCode} exists in the tracker.
     *
     * @param moduleCode The code of the module to check if it exist.
     * @return True if a module that has {@code moduleCode} exists in the tracker. Otherwise, false.
     */
    boolean hasModule(ModuleCode moduleCode);

    /**
     * Returns a module in the tracker with the specified {@code moduleCode}.
     *
     * @param moduleCode The code of the module to check if it exist.
     * @return a module in the tracker with the specified {@code moduleCode}.
     */
    ReadOnlyModule getModule(ModuleCode moduleCode);


    /**
     * Deletes the given module. <p>
     * The module must exist in the tracker.
     *
     * @param target The module to be deleted.
     */
    void deleteModule(ReadOnlyModule target);

    /**
     * Adds the given module.
     *
     * @param module The module to be added.
     */
    void addModule(Module module);

    /**
     * Replaces the given module {@code target} with {@code editedModule}.<p>
     * {@code target} must exist in the tracker.<p>
     * The module code of {@code editedModule} must not be the same as another existing module in the tracker.
     *
     * @param target The module to be replaced.
     * @param editedModule The module that will replace.
     */
    void setModule(ReadOnlyModule target, Module editedModule);

    /**
     * Returns true if a lecture with the same name as {@code lecture} exists in {@code module}.
     *
     * @param module The module to check if it contains the lecture.
     * @param lecture The lecture to check if exist.
     * @return True if a lecture with the same name as {@code lecture} exists in {@code module}. Otherwise, false.
     */
    boolean hasLecture(ReadOnlyModule module, ReadOnlyLecture lecture);

    /**
     * Deletes the given lecture {@code target}.<p>
     * The lecture must exist in {@code module}.
     *
     * @param module The module to delete the lecture from.
     * @param target The lecture to be deleted.
     */
    void deleteLecture(ReadOnlyModule module, ReadOnlyLecture target);

    /**
     * Adds the given lecture to {@code module}.
     *
     * @param module The module to add the lecture to.
     * @param lecture The lecture to add.
     */
    void addLecture(ReadOnlyModule module, Lecture lecture);

    /**
     * Replaces the given lecture {@code target} with {@code editedLecture}.<p>
     * {@code target} must exist in {@code module}.<p>
     * The name of {@code editedLecture} must not be the same as another existing lecture in {@code module}.
     *
     * @param module The module whose lecture is to be replaced.
     * @param target The lecture to be replaced.
     * @param editedLecture The lecture that will replace.
     */
    void setLecture(ReadOnlyModule module, ReadOnlyLecture target, Lecture editedLecture);

    /**
     * Returns true if a video with the same name as {@code video} exists in {@code lecture}.
     *
     * @param lecture The lecture to check if it contains the video.
     * @param video The video to check if exist.
     * @return True if a video with the same name as {@code video} exists in {@code lecture}. Otherwise, false.
     */
    boolean hasVideo(ReadOnlyLecture lecture, Video video);

    /**
     * Deletes the given video {@code target}.<p>
     * The video must exist in {@code lecture}.
     *
     * @param lecture The lecture to delete the video from.
     * @param video The video to be deleted.
     */
    void deleteVideo(ReadOnlyLecture lecture, Video video);

    /**
     * Adds the given video to {@code lecture}.
     *
     * @param lecture The lecture to add the video to.
     * @param video The video to add.
     */
    void addVideo(ReadOnlyLecture lecture, Video video);

    /**
     * Replaces the given video {@code target} with {@code editedVideo}.<p>
     * {@code target} must exist in {@code lecture}.<p>
     * The name of {@code editedVideo} must not be the same as another existing video in {@code lecture}.
     *
     * @param lecture The lecture whose video is to be replaced.
     * @param target The video to be replaced.
     * @param editedVideo The video that will replace.
     */
    void setVideo(ReadOnlyLecture lecture, Video target, Video editedVideo);


    ObservableList<? extends ReadOnlyModule> getFilteredModuleList();

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     *
     * @param predicate The predicate to filter modules by.
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
