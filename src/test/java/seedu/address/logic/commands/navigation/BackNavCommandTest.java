package seedu.address.logic.commands.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
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


public class BackNavCommandTest {

    @Test
    void execute_backFromModule_navToRoot() throws CommandException {
        Module mod = TypicalModules.getCs2040s();

        Navigation nav = new Navigation();
        nav.navigateTo(mod.getCode());
        Model model = new ModelStubWithNavigation(nav);

        BackNavCommand cmd = new BackNavCommand();
        CommandResult result = cmd.execute(model);

        assertEquals(NavCommand.getSuccessfulNavMessage(new NavigationContext()),
                result.getFeedbackToUser());
    }

    @Test
    void execute_backFromLecture_navToMod() throws CommandException {
        Module mod = TypicalModules.getCs2040s();
        Lecture lec = TypicalLectures.getCs2040sWeek1();

        Navigation nav = new Navigation();
        nav.navigateTo(mod.getCode(), lec.getName());
        Model model = new ModelStubWithNavigation(nav);

        BackNavCommand cmd = new BackNavCommand();
        CommandResult result = cmd.execute(model);

        NavigationContext expectedContext = new NavigationContext().addModule(mod.getCode());
        assertEquals(NavCommand.getSuccessfulNavMessage(expectedContext),
                result.getFeedbackToUser());
    }

    @Test
    void execute_backAtRoot_noChange() throws CommandException {
        Navigation nav = new Navigation();
        Model model = new ModelStubWithNavigation(nav);

        BackNavCommand cmd = new BackNavCommand();
        CommandResult result = cmd.execute(model);

        assertEquals(NavCommand.getSuccessfulNavMessage(new NavigationContext()),
                result.getFeedbackToUser());
    }

    private class ModelStubWithNavigation extends ModelStub {
        private Navigation nav;
        private Module mod = TypicalModules.getCs2040s();
        private Lecture lec = TypicalLectures.getCs2040sWeek1();

        public ModelStubWithNavigation(Navigation nav) {
            this.nav = nav;
        }

        @Override
        public void navigateBack() {
            nav.back();
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
        public void navigateToModFromRoot(ModuleCode code) {
            nav.navigateToModFromRoot(code);
        }

        @Override
        public void navigateToLecFromMod(LectureName lectureName) {
            nav.navigateToLecFromMod(lectureName);
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
        public void updateFilteredVideoList(Predicate<Video> predicate, ReadOnlyLecture lecture) {
            // Required for list command but list tests not included.
        }
    }
}
