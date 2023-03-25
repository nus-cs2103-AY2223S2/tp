package seedu.address.logic.commands.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Navigation;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.navigation.NavigationContext;
import seedu.address.model.video.Video;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;

public class RootNavCommandTest {

    @Test
    void execute_navAtRoot_noChange() throws CommandException {
        Model model = new ModelStubWithNavigation(new Navigation());
        RootNavCommand cmd = new RootNavCommand();

        CommandResult result = cmd.execute(model);

        assertEquals(NavCommand.getSuccessfulNavMessage(new NavigationContext()),
                result.getFeedbackToUser());
    }

    @Test
    void execute_navAtMod_shouldBeAtRoot() throws CommandException {
        Module mod = TypicalModules.getCs2040s();

        Navigation nav = new Navigation();
        nav.navigateTo(mod.getCode());
        Model model = new ModelStubWithNavigation(nav);

        RootNavCommand cmd = new RootNavCommand();
        CommandResult result = cmd.execute(model);

        assertEquals(NavCommand.getSuccessfulNavMessage(new NavigationContext()),
                result.getFeedbackToUser());
    }

    @Test
    void execute_navAtLec_shouldBeAtRoot() throws CommandException {
        Module mod = TypicalModules.getCs2040s();
        Lecture lec = TypicalLectures.getCs2040sWeek1();

        Navigation nav = new Navigation();
        nav.navigateTo(mod.getCode(), lec.getName());
        Model model = new ModelStubWithNavigation(nav);

        RootNavCommand cmd = new RootNavCommand();
        CommandResult result = cmd.execute(model);

        assertEquals(NavCommand.getSuccessfulNavMessage(new NavigationContext()),
                result.getFeedbackToUser());
    }

    private class ModelStubWithNavigation extends ModelStub {
        private Navigation nav;

        public ModelStubWithNavigation(Navigation nav) {
            this.nav = nav;
        }

        @Override
        public void navigateToRoot() {
            nav.goToRoot();
        }

        @Override
        public NavigationContext getCurrentNavContext() {
            return nav.getCurrentContext();
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
