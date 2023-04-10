package seedu.address.logic.commands.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Navigation;
import seedu.address.model.NavigationStack;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.navigation.NavigationContext;
import seedu.address.testutil.ObjectUtil;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;

public class DirectNavCommandTest {
    private Module mod = TypicalModules.getCs2040s();
    private Lecture lec = TypicalLectures.getCs2040sWeek1();

    @Test
    void execute_fromRootWithNoModNoLec_failure() throws CommandException {
        Navigation nav = new NavigationStack();
        Model model = new ModelStubWithNavigation(nav);
        Optional<ModuleCode> modOpt = Optional.empty();
        Optional<LectureName> lecOpt = Optional.empty();

        DirectNavCommand cmd = new DirectNavCommand(modOpt, lecOpt);
        assertThrows(CommandException.class, () -> cmd.execute(model));
    }

    @Test
    void execute_fromRootWithModNoLec_success() throws CommandException {
        Navigation nav = new NavigationStack();
        Model model = new ModelStubWithNavigation(nav);
        Optional<ModuleCode> modOpt = Optional.of(mod.getCode());
        Optional<LectureName> lecOpt = Optional.empty();

        DirectNavCommand cmd = new DirectNavCommand(modOpt, lecOpt);
        CommandResult result = cmd.execute(model);

        NavigationContext expectedContext = new NavigationContext().addModule(mod.getCode());

        assertEquals(NavCommand.getSuccessfulNavMessage(expectedContext), result.getFeedbackToUser());
    }

    @Test
    void execute_fromRootWithLecAndMod_success() throws CommandException {
        Navigation nav = new NavigationStack();
        Model model = new ModelStubWithNavigation(nav);
        Optional<ModuleCode> modOpt = Optional.of(mod.getCode());
        Optional<LectureName> lecOpt = Optional.of(lec.getName());

        DirectNavCommand cmd = new DirectNavCommand(modOpt, lecOpt);
        CommandResult result = cmd.execute(model);

        NavigationContext expectedContext = new NavigationContext().addModule(mod.getCode()).addLecture(lec.getName());

        assertEquals(NavCommand.getSuccessfulNavMessage(expectedContext), result.getFeedbackToUser());
    }

    @Test
    void execute_fromRootWithLecNoMod_failure() throws CommandException {
        Navigation nav = new NavigationStack();
        Model model = new ModelStubWithNavigation(nav);
        Optional<ModuleCode> modOpt = Optional.empty();
        Optional<LectureName> lecOpt = Optional.of(lec.getName());

        DirectNavCommand cmd = new DirectNavCommand(modOpt, lecOpt);
        assertThrows(CommandException.class, () -> cmd.execute(model));
    }

    @Test
    void execute_fromLecWithLecNoMod_success() throws CommandException {
        Navigation nav = new NavigationStack();
        nav.navigateTo(mod.getCode(), lec.getName());
        Model model = new ModelStubWithNavigation(nav);

        Optional<ModuleCode> modOpt = Optional.empty();
        Optional<LectureName> lecOpt = Optional.of(lec.getName());
        NavigationContext expectedContext = new NavigationContext().addModule(mod.getCode()).addLecture(lec.getName());

        DirectNavCommand cmd = new DirectNavCommand(modOpt, lecOpt);
        CommandResult result = cmd.execute(model);
        assertEquals(NavCommand.getSuccessfulNavMessage(expectedContext), result.getFeedbackToUser());
    }


    @Test
    void execute_missingModule_throwsCommandException() throws CommandException {
        Navigation nav = new NavigationStack();
        Model model = new ModelStubWithNavigation(nav);
        Optional<ModuleCode> modOpt = Optional.of(TypicalModules.getCs2107().getCode());
        Optional<LectureName> lecOpt = Optional.empty();

        DirectNavCommand cmd = new DirectNavCommand(modOpt, lecOpt);
        assertThrows(CommandException.class, () -> cmd.execute(model));
    }

    @Test
    void execute_missingLecture_throwsCommandException() throws CommandException {
        Navigation nav = new NavigationStack();
        Model model = new ModelStubWithNavigation(nav);
        Optional<ModuleCode> modOpt = Optional.of(TypicalModules.getCs2040s().getCode());
        Optional<LectureName> lecOpt = Optional.of(TypicalLectures.getCs2040sWeek2().getName());

        DirectNavCommand cmd = new DirectNavCommand(modOpt, lecOpt);
        assertThrows(CommandException.class, () -> cmd.execute(model));
    }

    @Test
    void equals() {
        Optional<ModuleCode> modOpt = Optional.of(mod.getCode());
        Optional<LectureName> lecOpt = Optional.of(lec.getName());
        DirectNavCommand cmd = new DirectNavCommand(modOpt, lecOpt);
        DirectNavCommand cmdWithSameVal = new DirectNavCommand(modOpt, lecOpt);

        Optional<ModuleCode> mod2Opt = Optional.of(TypicalModules.getCs2107().getCode());
        DirectNavCommand cmdDiffMod = new DirectNavCommand(mod2Opt, lecOpt);

        Optional<LectureName> lec2Opt = Optional.of(TypicalLectures.getCs2040sWeek2().getName());
        DirectNavCommand cmdDiffLec = new DirectNavCommand(modOpt, lec2Opt);

        ObjectUtil.testEquals(cmd, cmdWithSameVal, 1, cmdDiffMod, cmdDiffLec);
    }
}
