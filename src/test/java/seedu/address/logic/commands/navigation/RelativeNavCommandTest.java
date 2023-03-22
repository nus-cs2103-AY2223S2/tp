package seedu.address.logic.commands.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Navigation;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.navigation.NavigationContext;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;

public class RelativeNavCommandTest {
    private Module mod = TypicalModules.CS2040S;
    private Lecture lec = TypicalLectures.CS2040S_WEEK_1;

    @Test
    void execute_fromRootToLec_success() throws CommandException {
        Navigation nav = new Navigation();
        Model model = new ModelStubWithNavigation(nav);

        new RelativeNavCommand(mod.getCode().toString()).execute(model);
        CommandResult result = new RelativeNavCommand(lec.getName().toString()).execute(model);

        NavigationContext expectedContext =
                new NavigationContext().addModule(mod.getCode()).addLecture(lec.getName());

        assertEquals(NavCommand.getSuccessfulNavMessage(expectedContext),
                result.getFeedbackToUser());
    }

    @Test
    void execute_invalidModule_throwsCommandException() throws CommandException {
        Navigation nav = new Navigation();
        Model model = new ModelStubWithNavigation(nav);

        RelativeNavCommand cmd = new RelativeNavCommand("invalid");
        assertThrows(CommandException.class, () -> cmd.execute(model));
    }

    @Test
    void execute_missingModule_throwsCommandException() throws CommandException {
        Navigation nav = new Navigation();
        Model model = new ModelStubWithNavigation(nav);

        String missingMod = TypicalModules.CS2107.getCode().toString();
        RelativeNavCommand cmd = new RelativeNavCommand(missingMod);

        assertThrows(CommandException.class, () -> cmd.execute(model));
    }

    @Test
    void execute_invalidLecture_throwsCommandException() throws CommandException {
        Navigation nav = new Navigation();
        Model model = new ModelStubWithNavigation(nav);
        nav.navigateTo(mod.getCode());

        RelativeNavCommand cmd = new RelativeNavCommand("invalid");

        assertThrows(CommandException.class, () -> cmd.execute(model));
    }

    @Test
    void execute_missingLecture_throwsCommandException() throws CommandException {
        Navigation nav = new Navigation();
        Model model = new ModelStubWithNavigation(nav);
        nav.navigateTo(mod.getCode());

        String missingLec = TypicalLectures.CS2040S_WEEK_2.getName().toString();

        RelativeNavCommand cmd = new RelativeNavCommand(missingLec);

        assertThrows(CommandException.class, () -> cmd.execute(model));
    }

    @Test
    void execute_atLecLayer_throwsCommandException() throws CommandException {
        Navigation nav = new Navigation();
        Model model = new ModelStubWithNavigation(nav);

        nav.navigateTo(mod.getCode(), lec.getName());

        RelativeNavCommand cmd = new RelativeNavCommand("invalid");

        assertThrows(CommandException.class, () -> cmd.execute(model));
    }


    private class ModelStubWithNavigation extends ModelStub {
        private Navigation nav;
        private Module mod = TypicalModules.CS2040S;
        private Lecture lec = TypicalLectures.CS2040S_WEEK_1;

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
    }
}
