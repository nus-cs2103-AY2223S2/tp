package seedu.address.logic.commands.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;

public class DirectNavCommandTest {
    private Module mod = TypicalModules.getCs2040s();
    private Lecture lec = TypicalLectures.getCs2040sWeek1();

    @Test
    void execute_fromRootWithMod_success() throws CommandException {
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
    void execute_fromRootWithLecNoMod_throwsCommandException() throws CommandException {
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
    void equals_success() {
        Optional<ModuleCode> modOpt = Optional.of(mod.getCode());
        Optional<LectureName> lecOpt = Optional.of(lec.getName());
        DirectNavCommand cmd = new DirectNavCommand(modOpt, lecOpt);

        assertTrue(cmd.equals(cmd));
        assertTrue(cmd.equals(new DirectNavCommand(modOpt, lecOpt)));
    }

    @Test
    void equals_notSameMod() {

        Optional<ModuleCode> mod1Opt = Optional.of(mod.getCode());
        Optional<ModuleCode> mod2Opt = Optional.of(TypicalModules.getCs2107().getCode());
        Optional<LectureName> lecOpt = Optional.empty();
        DirectNavCommand cmd1 = new DirectNavCommand(mod1Opt, lecOpt);
        DirectNavCommand cmd2 = new DirectNavCommand(mod2Opt, lecOpt);

        assertFalse(cmd1.equals(cmd2));
    }

    @Test
    void equals_notSameLec() {

        Optional<ModuleCode> modOpt = Optional.of(mod.getCode());
        Optional<LectureName> lec1Opt = Optional.of(lec.getName());
        Optional<LectureName> lec2Opt = Optional.of(TypicalLectures.getCs2040sWeek2().getName());
        DirectNavCommand cmd1 = new DirectNavCommand(modOpt, lec1Opt);
        DirectNavCommand cmd2 = new DirectNavCommand(modOpt, lec2Opt);

        assertFalse(cmd1.equals(cmd2));
    }
}
