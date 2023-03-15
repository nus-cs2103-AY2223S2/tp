package seedu.modtrek.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.modtrek.testutil.Assert.assertThrows;
import static seedu.modtrek.testutil.TypicalModules.CS1101S;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.commands.AddCommand;
import seedu.modtrek.logic.commands.ClearCommand;
import seedu.modtrek.logic.commands.DeleteCommand;
import seedu.modtrek.logic.commands.EditCommand;
import seedu.modtrek.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.modtrek.logic.commands.ExitCommand;
import seedu.modtrek.logic.commands.FindCommand;
import seedu.modtrek.logic.commands.HelpCommand;
import seedu.modtrek.logic.commands.ListCommand;
import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.ModuleCodePredicate;
import seedu.modtrek.testutil.EditModuleDescriptorBuilder;
import seedu.modtrek.testutil.ModuleBuilder;
import seedu.modtrek.testutil.ModuleUtil;

public class ModTrekParserTest {

    private final ModTrekParser parser = new ModTrekParser();

    @Test
    public void parseCommand_add() throws Exception {
        Module module = new ModuleBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ModuleUtil.getAddCommand(module));
        assertEquals(new AddCommand(module), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + "/m CS1101S");
        Set<Code> codesToDelete = new HashSet<>();
        codesToDelete.add(CS1101S.getCode());
        assertEquals(new DeleteCommand(false, codesToDelete), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Module module = new ModuleBuilder().build();
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(module).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + "CS1101S" + " " + ModuleUtil.getEditModuleDescriptorDetails(descriptor));
        assertTrue(new EditCommand(new Code("CS1101S"), descriptor).equals(command));
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        Code findCode = new Code("CS1101S");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + "CS1101S");
        assertEquals(new FindCommand(new ModuleCodePredicate(findCode)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " add") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
