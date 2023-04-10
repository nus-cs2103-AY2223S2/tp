package seedu.address.logic.commands.navigation;

import java.util.function.Predicate;

import seedu.address.model.Navigation;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.navigation.NavigationContext;
import seedu.address.model.video.Video;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;

/**
 * A Model stub where all methods not related to navigation throw {@code AssertionError}s.
 * <p>
 * Test classes that require a Model stub can inherit this class and overwrite the necessary methods.
 */
public class ModelStubWithNavigation extends ModelStub {
    private Navigation nav;
    private Module mod = TypicalModules.getCs2040s();
    private Lecture lec = TypicalLectures.getCs2040sWeek1();

    public ModelStubWithNavigation(Navigation nav) {
        this.nav = nav;
    }

    @Override
    public boolean hasModule(ModuleCode moduleCode) {
        return moduleCode.equals(mod.getCode());
    }

    @Override
    public boolean hasLecture(ModuleCode moduleCode, LectureName lectureName) {
        return lectureName.equals(lec.getName());
    }

    @Override
    public void navigateToRoot() {
        nav.goToRoot();
    }

    @Override
    public void navigateBack() {
        nav.back();
    }

    @Override
    public void navigateToModFromRoot(ModuleCode code) {
        nav.navigateToModFromRoot(code);
    }

    @Override
    public void navigateToLecFromMod(LectureName lectureName) {
        nav.navigateToLecFromMod(lectureName);
    }

    @Override
    public void navigateTo(ModuleCode moduleCode) {
        nav.navigateTo(moduleCode);
    }

    @Override
    public void navigateTo(ModuleCode moduleCode, LectureName lectureName) {
        nav.navigateTo(moduleCode, lectureName);
    }

    @Override
    public NavigationContext getCurrentNavContext() {
        return nav.getCurrentContext();
    }

    @Override
    public ReadOnlyLecture getLecture(ModuleCode moduleCode, LectureName lectureName) {
        // For list tests.
        return lec;
    }

    @Override
    public ReadOnlyModule getModule(ModuleCode moduleCode) {
        // For list tests.
        return mod;
    }

    @Override
    public void updateFilteredLectureList(Predicate<? super ReadOnlyLecture> predicate, ReadOnlyModule module) {
        // Required for list command but list tests not included.
    }

    @Override
    public void updateFilteredModuleList(Predicate<? super ReadOnlyModule> predicate) {
        // Required for list command but list tests not included.
    }

    @Override
    public void updateFilteredVideoList(Predicate<Video> predicate, ModuleCode moduleCode, ReadOnlyLecture lecture) {
        // Required for list command but list tests not included.
    }
}
