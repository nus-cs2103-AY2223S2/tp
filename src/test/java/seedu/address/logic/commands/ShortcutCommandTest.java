package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalElister;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ShortcutCommandParser.CommandType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ShortcutCommandTest {

    private Model model = new ModelManager(getTypicalElister(), new UserPrefs());

    private void purgeAlias(String alias) {
        AddCommand.commandWords.remove(alias);
        ClearCommand.commandWords.remove(alias);
        DeleteCommand.commandWords.remove(alias);
        DeleteTagCommand.commandWords.remove(alias);
        EditCommand.commandWords.remove(alias);
        ExitCommand.commandWords.remove(alias);
        ExportCommand.commandWords.remove(alias);
        FilterCommand.commandWords.remove(alias);
        FreezeCommand.commandWords.remove(alias);
        FindCommand.commandWords.remove(alias);
        HelpCommand.commandWords.remove(alias);
        ImportCommand.commandWords.remove(alias);
        ListCommand.commandWords.remove(alias);
        MassOpCommand.commandWords.remove(alias);
        RedoCommand.commandWords.remove(alias);
        ShortcutCommand.commandWords.remove(alias);
        TagCommand.commandWords.remove(alias);
        UndoCommand.commandWords.remove(alias);
        UnfreezeCommand.commandWords.remove(alias);
    }

    @Test
    public void execute_correctExecution_add() {
        String shortForm = "ad";
        CommandType add = CommandType.ADD;
        Command command = new ShortcutCommand(add, shortForm);
        purgeAlias(shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, add, shortForm),
                        false, true), model);
    }

    @Test
    public void execute_correctExecution_clear() {
        String shortForm = "cr";
        CommandType clear = CommandType.CLEAR;
        Command command = new ShortcutCommand(clear, shortForm);
        purgeAlias(shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, clear, shortForm),
                        false, true), model);
    }

    @Test
    public void execute_correctExecution_delete() {
        String shortForm = "del";
        CommandType delete = CommandType.DELETE;
        Command command = new ShortcutCommand(delete, shortForm);
        purgeAlias(shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, delete, shortForm),
                        false, true), model);
    }
    @Test
    public void execute_correctExecution_deleteTag() {
        String shortForm = "dtag";
        CommandType deleteTag = CommandType.DELETE_TAG;
        Command command = new ShortcutCommand(deleteTag, shortForm);
        purgeAlias(shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, deleteTag, shortForm),
                        false, true), model);
    }
    @Test
    public void execute_correctExecution_edit() {
        String shortForm = "et";
        CommandType edit = CommandType.EDIT;
        Command command = new ShortcutCommand(edit, shortForm);
        purgeAlias(shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, edit, shortForm),
                        false, true), model);
    }
    @Test
    public void execute_correctExecution_export() {
        String shortForm = "expo";
        CommandType export = CommandType.EXPORT;
        Command command = new ShortcutCommand(export, shortForm);
        purgeAlias(shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, export, shortForm),
                        false, true), model);
    }

    @Test
    public void execute_correctExecution_filter() {
        String shortForm = "fer";
        CommandType filter = CommandType.FILTER;
        Command command = new ShortcutCommand(filter, shortForm);
        purgeAlias(shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, filter, shortForm),
                        false, true), model);
    }
    @Test
    public void execute_correctExecution_find() {
        String shortForm = "fd";
        CommandType find = CommandType.FIND;
        Command command = new ShortcutCommand(find, shortForm);
        purgeAlias(shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, find, shortForm),
                        false, true), model);
    }
    @Test
    public void execute_correctExecution_help() {
        String shortForm = "hp";
        CommandType help = CommandType.HELP;
        Command command = new ShortcutCommand(help, shortForm);
        purgeAlias(shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, help, shortForm),
                        false, true), model);
    }
    @Test
    public void execute_correctExecution_import() {
        String shortForm = "it";
        CommandType importType = CommandType.IMPORT;
        Command command = new ShortcutCommand(importType, shortForm);
        purgeAlias(shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, importType, shortForm),
                        false, true), model);
    }
    @Test
    public void execute_correctExecution_list() {
        String shortForm = "lst";
        CommandType list = CommandType.LIST;
        Command command = new ShortcutCommand(list, shortForm);
        purgeAlias(shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, list, shortForm),
                        false, true), model);
    }
    @Test
    public void execute_correctExecution_massOp() {
        String shortForm = "mop";
        CommandType massOp = CommandType.MASS_OP;
        Command command = new ShortcutCommand(massOp, shortForm);
        purgeAlias(shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, massOp, shortForm),
                        false, true), model);
    }
    @Test
    public void execute_correctExecution_redo() {
        String shortForm = "rd";
        CommandType redo = CommandType.REDO;
        Command command = new ShortcutCommand(redo, shortForm);
        purgeAlias(shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, redo, shortForm),
                        false, true), model);
    }
    @Test
    public void execute_correctExecution_shortcut() {
        String shortForm = "st";
        CommandType shortcut = CommandType.SHORTCUT;
        Command command = new ShortcutCommand(shortcut, shortForm);
        purgeAlias(shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, shortcut, shortForm),
                        false, true), model);
    }
    @Test
    public void execute_correctExecution_tag() {
        String shortForm = "tg";
        CommandType tag = CommandType.TAG;
        Command command = new ShortcutCommand(tag, shortForm);
        purgeAlias(shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, tag, shortForm),
                        false, true), model);
    }
    @Test
    public void execute_correctExecution_undo() {
        String shortForm = "ud";
        CommandType undo = CommandType.UNDO;
        Command command = new ShortcutCommand(undo, shortForm);
        purgeAlias(shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, undo, shortForm),
                        false, true), model);
    }
}
