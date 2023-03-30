package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ShortcutCommandParser.CommandType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ShortcutCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_correctExecution_add() {
        String shortForm = "ad";
        CommandType add = CommandType.ADD;
        Command command = new ShortcutCommand(add, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, add, shortForm),
                        true, true), model);
    }

    @Test
    public void execute_correctExecution_clear() {
        String shortForm = "cr";
        CommandType clear = CommandType.CLEAR;
        Command command = new ShortcutCommand(clear, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, clear, shortForm),
                        true, true), model);
    }

    @Test
    public void execute_correctExecution_delete() {
        String shortForm = "del";
        CommandType delete = CommandType.DELETE;
        Command command = new ShortcutCommand(delete, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, delete, shortForm),
                        true, true), model);
    }
    @Test
    public void execute_correctExecution_deleteTag() {
        String shortForm = "dtag";
        CommandType deleteTag = CommandType.DELETE_TAG;
        Command command = new ShortcutCommand(deleteTag, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, deleteTag, shortForm),
                        true, true), model);
    }
    @Test
    public void execute_correctExecution_edit() {
        String shortForm = "et";
        CommandType edit = CommandType.EDIT;
        Command command = new ShortcutCommand(edit, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, edit, shortForm),
                        true, true), model);
    }
    @Test
    public void execute_correctExecution_export() {
        String shortForm = "expo";
        CommandType export = CommandType.EXPORT;
        Command command = new ShortcutCommand(export, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, export, shortForm),
                        true, true), model);
    }

    @Test
    public void execute_correctExecution_filter() {
        String shortForm = "fer";
        CommandType filter = CommandType.FILTER;
        Command command = new ShortcutCommand(filter, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, filter, shortForm),
                        true, true), model);
    }
    @Test
    public void execute_correctExecution_find() {
        String shortForm = "fd";
        CommandType find = CommandType.FIND;
        Command command = new ShortcutCommand(find, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, find, shortForm),
                        true, true), model);
    }
    @Test
    public void execute_correctExecution_help() {
        String shortForm = "hp";
        CommandType help = CommandType.HELP;
        Command command = new ShortcutCommand(help, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, help, shortForm),
                        true, true), model);
    }
    @Test
    public void execute_correctExecution_import() {
        String shortForm = "it";
        CommandType importType = CommandType.IMPORT;
        Command command = new ShortcutCommand(importType, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, importType, shortForm),
                        true, true), model);
    }
    @Test
    public void execute_correctExecution_list() {
        String shortForm = "lst";
        CommandType list = CommandType.LIST;
        Command command = new ShortcutCommand(list, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, list, shortForm),
                        true, true), model);
    }
    @Test
    public void execute_correctExecution_massOp() {
        String shortForm = "mop";
        CommandType massOp = CommandType.MASS_OP;
        Command command = new ShortcutCommand(massOp, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, massOp, shortForm),
                        true, true), model);
    }
    @Test
    public void execute_correctExecution_redo() {
        String shortForm = "rd";
        CommandType redo = CommandType.REDO;
        Command command = new ShortcutCommand(redo, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, redo, shortForm),
                        true, true), model);
    }
    @Test
    public void execute_correctExecution_shortcut() {
        String shortForm = "st";
        CommandType shortcut = CommandType.SHORTCUT;
        Command command = new ShortcutCommand(shortcut, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, shortcut, shortForm),
                        true, true), model);
    }
    @Test
    public void execute_correctExecution_tag() {
        String shortForm = "tg";
        CommandType tag = CommandType.TAG;
        Command command = new ShortcutCommand(tag, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, tag, shortForm),
                        true, true), model);
    }
    @Test
    public void execute_correctExecution_undo() {
        String shortForm = "ud";
        CommandType undo = CommandType.UNDO;
        Command command = new ShortcutCommand(undo, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, undo, shortForm),
                        true, true), model);
    }
}
