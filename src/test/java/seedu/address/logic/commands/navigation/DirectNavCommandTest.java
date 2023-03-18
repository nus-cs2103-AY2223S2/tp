package seedu.address.logic.commands.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

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

public class DirectNavCommandTest {
    private Module mod = TypicalModules.CS2040S;
    private Lecture lec = TypicalLectures.CS2040S_WEEK_1;

    @Test
    void execute_fromRootToMod_success() throws CommandException {
        Navigation nav = new Navigation();
        Model model = new ModelStubWithNavigation(nav);
        Optional<ModuleCode> modOpt = Optional.of(mod.getCode());
        Optional<LectureName> lecOpt = Optional.empty();

        DirectNavCommand cmd = new DirectNavCommand(modOpt, lecOpt);
        CommandResult result = cmd.execute(model);

        NavigationContext expectedContext = new NavigationContext().addModule(mod.getCode());

        assertEquals(NavCommand.getSuccessfulNavMessage(expectedContext),
                result.getFeedbackToUser());
    }

    @Test
    void execute_fromRootToLec_success() throws CommandException {
        Navigation nav = new Navigation();
        Model model = new ModelStubWithNavigation(nav);
        Optional<ModuleCode> modOpt = Optional.of(mod.getCode());
        Optional<LectureName> lecOpt = Optional.of(lec.getName());

        DirectNavCommand cmd = new DirectNavCommand(modOpt, lecOpt);
        CommandResult result = cmd.execute(model);

        NavigationContext expectedContext =
                new NavigationContext().addModule(mod.getCode()).addLecture(lec.getName());

        assertEquals(NavCommand.getSuccessfulNavMessage(expectedContext),
                result.getFeedbackToUser());

    }

    @Test
    void execute_goToLectureNoModSpecifiedFromRoot_throwsCommandException()
            throws CommandException {
        Navigation nav = new Navigation();
        Model model = new ModelStubWithNavigation(nav);
        Optional<ModuleCode> modOpt = Optional.empty();
        Optional<LectureName> lecOpt = Optional.of(lec.getName());

        DirectNavCommand cmd = new DirectNavCommand(modOpt, lecOpt);
        assertThrows(CommandException.class, () -> cmd.execute(model));
    }

    @Test
    void execute_missingModule_throwsCommandException() throws CommandException {
        Navigation nav = new Navigation();
        Model model = new ModelStubWithNavigation(nav);
        Optional<ModuleCode> modOpt = Optional.of(TypicalModules.CS2107.getCode());
        Optional<LectureName> lecOpt = Optional.empty();

        DirectNavCommand cmd = new DirectNavCommand(modOpt, lecOpt);
        assertThrows(CommandException.class, () -> cmd.execute(model));
    }

    @Test
    void execute_missingLecture_throwsCommandException() throws CommandException {
        Navigation nav = new Navigation();
        Model model = new ModelStubWithNavigation(nav);
        Optional<ModuleCode> modOpt = Optional.of(TypicalModules.CS2040S.getCode());
        Optional<LectureName> lecOpt = Optional.of(TypicalLectures.CS2040S_WEEK_2.getName());

        DirectNavCommand cmd = new DirectNavCommand(modOpt, lecOpt);
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
    }
}
