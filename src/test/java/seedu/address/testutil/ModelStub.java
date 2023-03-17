package seedu.address.testutil;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyNavigation;
import seedu.address.model.ReadOnlyTracker;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.navigation.NavigationContext;
import seedu.address.model.person.Person;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;

/**
 * A Model stub where all methods throw {@code AssertionError}s.<p>
 * Test classes that require a Model stub can inherit this class and overwrite the necessary methods.
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
    public ReadOnlyNavigation getNavigation() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public NavigationContext getCurrentNavContext() {
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
    public Path getTrackerFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTrackerFilePath(Path trackerFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTracker(ReadOnlyTracker tracker) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyTracker getTracker() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void clearTracker() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasModule(ReadOnlyModule module) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasModule(ModuleCode moduleCode) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyModule getModule(ModuleCode moduleCode) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteModule(ReadOnlyModule target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addModule(Module module) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setModule(ReadOnlyModule target, Module editedModule) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasLecture(ReadOnlyModule module, ReadOnlyLecture lecture) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasLecture(ModuleCode moduleCode, LectureName lectureName) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyLecture getLecture(ModuleCode moduleCode, LectureName lectureName) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteLecture(ReadOnlyModule module, ReadOnlyLecture target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addLecture(ReadOnlyModule module, Lecture lecture) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setLecture(ReadOnlyModule module, ReadOnlyLecture target, Lecture editedLecture) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasVideo(ReadOnlyLecture lecture, Video video) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasVideo(ReadOnlyLecture lecture, VideoName videoName) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Video getVideo(ReadOnlyLecture lecture, VideoName videoName) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteVideo(ReadOnlyLecture lecture, Video video) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addVideo(ReadOnlyLecture lecture, Video video) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setVideo(ReadOnlyLecture lecture, Video target, Video editedVideo) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<? extends ReadOnlyModule> getFilteredModuleList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<? extends ReadOnlyLecture> getFilteredLectureList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<? extends Video> getFilteredVideoList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredModuleList(Predicate<? super ReadOnlyModule> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredLectureList(Predicate<? super ReadOnlyLecture> predicate, ReadOnlyModule module) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredVideoList(Predicate<? super Video> predicate, ReadOnlyLecture lecture) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateAllFilteredListAsHidden() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void navigateBack() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void navigateToRoot() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void navigateTo(ModuleCode moduleCode) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void navigateTo(ModuleCode moduleCode, LectureName lectureName) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void navigateToModFromRoot(ModuleCode moduleCode) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void navigateToLecFromMod(LectureName lectureName) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
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
    public void deletePerson(Person target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPerson(Person person) {
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

}
