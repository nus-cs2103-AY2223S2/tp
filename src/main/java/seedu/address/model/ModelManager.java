package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.navigation.NavigationContext;
import seedu.address.model.person.Person;
import seedu.address.model.video.Video;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Tracker tracker;
    private final UserPrefs userPrefs;
    private final Navigation navigation;
    private final FilteredList<? extends ReadOnlyModule> filteredModules;

    private AddressBook addressBook; // TODO: Remove this
    private FilteredList<Person> filteredPersons; // TODO: Remove this

    /**
     * Constructs a {@code ModelManager} using the provided {@code tracker} and {@code userPrefs}.
     *
     * @param tracker The tracker.
     * @param userPrefs The user prefs.
     */
    public ModelManager(ReadOnlyTracker tracker, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(tracker, userPrefs);

        logger.fine("Initializing with tracker: " + tracker + " and user prefs " + userPrefs);

        this.tracker = new Tracker(tracker);
        this.userPrefs = new UserPrefs(userPrefs);
        this.navigation = new Navigation();
        filteredModules = new FilteredList<>(this.tracker.getModuleList());

        addressBook = new AddressBook();
        filteredPersons = new FilteredList<>(addressBook.getPersonList());
    }

    /**
     * Constructs a {@code ModelManager}.
     */
    public ModelManager() {
        this(new Tracker(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTrackerFilePath() {
        return userPrefs.getTrackerFilePath();
    }

    @Override
    public void setTrackerFilePath(Path trackerFilePath) {
        userPrefs.setTrackerFilePath(trackerFilePath);
    }

    //=========== Tracker ====================================================================================

    @Override
    public void setTracker(ReadOnlyTracker tracker) {
        this.tracker.resetData(tracker);
    }

    @Override
    public ReadOnlyTracker getTracker() {
        return tracker;
    }

    @Override
    public boolean hasModule(ReadOnlyModule module) {
        return tracker.hasModule(module);
    }

    @Override
    public boolean hasModule(ModuleCode moduleCode) {
        return tracker.getModule(moduleCode) != null;
    }

    @Override
    public ReadOnlyModule getModule(ModuleCode moduleCode) {
        return tracker.getModule(moduleCode);
    }

    @Override
    public void deleteModule(ReadOnlyModule target) {
        tracker.removeModule(target);
    }

    @Override
    public void addModule(Module module) {
        tracker.addModule(module);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public void setModule(ReadOnlyModule target, Module editedModule) {
        tracker.setModule(target, editedModule);
    }

    @Override
    public boolean hasLecture(ReadOnlyModule module, ReadOnlyLecture lecture) {
        requireNonNull(module);
        return module.hasLecture(lecture);
    }

    @Override
    public boolean hasLecture(ModuleCode moduleCode, LectureName lectureName) {
        ReadOnlyModule mod = tracker.getModule(moduleCode);
        return mod != null && mod.getLecture(lectureName) != null;
    }

    @Override
    public ReadOnlyLecture getLecture(ModuleCode moduleCode, LectureName lectureName) {
        ReadOnlyModule mod = tracker.getModule(moduleCode);
        return mod.getLecture(lectureName);
    }
    @Override
    public void deleteLecture(ReadOnlyModule module, ReadOnlyLecture target) {
        requireNonNull(module);
        //CHECKSTYLE.OFF: SeparatorWrap
        ((Module) module).removeLecture(target);
        //CHECKSTYLE.ON: SeparatorWrap
    }

    @Override
    public void addLecture(ReadOnlyModule module, Lecture lecture) {
        requireNonNull(module);
        //CHECKSTYLE.OFF: SeparatorWrap
        ((Module) module).addLecture(lecture);
        //CHECKSTYLE.ON: SeparatorWrap
    }

    @Override
    public void setLecture(ReadOnlyModule module, ReadOnlyLecture target, Lecture editedLecture) {
        requireNonNull(module);
        //CHECKSTYLE.OFF: SeparatorWrap
        ((Module) module).setLecture(target, editedLecture);
        //CHECKSTYLE.ON: SeparatorWrap
    }

    @Override
    public boolean hasVideo(ReadOnlyLecture lecture, Video video) {
        requireNonNull(lecture);
        return lecture.hasVideo(video);
    }

    @Override
    public void deleteVideo(ReadOnlyLecture lecture, Video video) {
        requireNonNull(lecture);
        //CHECKSTYLE.OFF: SeparatorWrap
        ((Lecture) lecture).removeVideo(video);
        //CHECKSTYLE.ON: SeparatorWrap
    }

    @Override
    public void addVideo(ReadOnlyLecture lecture, Video video) {
        requireNonNull(lecture);
        //CHECKSTYLE.OFF: SeparatorWrap
        ((Lecture) lecture).addVideo(video);
        //CHECKSTYLE.ON: SeparatorWrap
    }

    @Override
    public void setVideo(ReadOnlyLecture lecture, Video target, Video editedVideo) {
        requireNonNull(lecture);
        //CHECKSTYLE.OFF: SeparatorWrap
        ((Lecture) lecture).setVideo(target, editedVideo);
        //CHECKSTYLE.ON: SeparatorWrap
    }


    //=========== Filtered Module List Accessors =============================================================

    @Override
    public ObservableList<? extends ReadOnlyModule> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public void updateFilteredModuleList(Predicate<? super ReadOnlyModule> predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
    }

    //=========== Navigation =================================================================================

    @Override
    public ReadOnlyNavigation getNavigation() {
        return navigation;
    }

    @Override
    public NavigationContext getCurrentNavContext() {
        return navigation.getCurrentContext();
    }

    @Override
    public void navigateTo(ModuleCode moduleCode) {
        navigation.navigateTo(moduleCode);
    }

    @Override
    public void navigateTo(ModuleCode moduleCode, LectureName lectureName) {
        navigation.navigateTo(moduleCode, lectureName);
    }

    @Override
    public void navigateToModFromRoot(ModuleCode moduleCode) {
        navigation.navigateToModFromRoot(moduleCode);
    }

    @Override
    public void navigateToLecFromMod(LectureName lectureName) {
        navigation.navigateToLecFromMod(lectureName);
    }

    @Override
    public void navigateBack() {
        navigation.back();;
    }

    @Override
    public void navigateToRoot() {
        navigation.goToRoot();
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
        return tracker.equals(other.tracker)
            && userPrefs.equals(other.userPrefs)
            && filteredModules.equals(other.filteredModules);
    }

    // TODO: Remove all code beyond this point
    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
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
}
