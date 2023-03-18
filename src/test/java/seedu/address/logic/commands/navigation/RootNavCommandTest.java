package seedu.address.logic.commands.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Navigation;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.module.Module;
import seedu.address.model.navigation.NavigationContext;
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
        Module mod = TypicalModules.CS2040S;

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
        Module mod = TypicalModules.CS2040S;
        Lecture lec = TypicalLectures.CS2040S_WEEK_1;

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
    }
}
