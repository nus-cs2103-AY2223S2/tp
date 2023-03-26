package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.ShortcutCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new TagCommand object
 */
public class ShortcutCommandParser implements Parser<ShortcutCommand> {
    private static final int COMMAND_INDEX = 0;
    private static final int SHORT_FORM_INDEX = 1;

    public enum CommandType {
        ADD, CLEAR, DELETE, DELETE_TAG, EDIT, EXIT, EXPORT, FILTER, FIND, HELP, IMPORT, LIST, REDO, SHORTCUT,
        TAG, UNDO
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ShortcutCommand
     * and returns a ShortcutCommand object for execution.
     *
     * @param args The arguments to the ShortcutCommand
     * @return The parsed ShortcutCommand
     * @throws ParseException if {@code args} does not conform the expected format
     */
    public ShortcutCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShortcutCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split(" ");
        String commandWord = nameKeywords[COMMAND_INDEX];
        String shortForm = nameKeywords[SHORT_FORM_INDEX];

        CommandType commandToChange;

        // Choose the right type
        if (commandWord.equals(AddCommand.COMMAND_WORDS.get(0))) {
            commandToChange = CommandType.ADD;
        } else if (commandWord.equals(ClearCommand.COMMAND_WORDS.get(0))) {
            commandToChange = CommandType.CLEAR;
        } else if (commandWord.equals(DeleteCommand.COMMAND_WORDS.get(0))) {
            commandToChange = CommandType.DELETE;
        } else if (commandWord.equals(DeleteTagCommand.COMMAND_WORDS.get(0))) {
            commandToChange = CommandType.DELETE_TAG;
        } else if (commandWord.equals(EditCommand.COMMAND_WORDS.get(0))) {
            commandToChange = CommandType.EDIT;
        } else if (commandWord.equals(ExitCommand.COMMAND_WORDS.get(0))) {
            commandToChange = CommandType.EXIT;
        } else if (commandWord.equals(ExportCommand.COMMAND_WORDS.get(0))) {
            commandToChange = CommandType.EXPORT;
        } else if (commandWord.equals(FilterCommand.COMMAND_WORDS.get(0))) {
            commandToChange = CommandType.FILTER;
        } else if (commandWord.equals(FindCommand.COMMAND_WORDS.get(0))) {
            commandToChange = CommandType.FIND;
        } else if (commandWord.equals(HelpCommand.COMMAND_WORDS.get(0))) {
            commandToChange = CommandType.HELP;
        } else if (commandWord.equals(ImportCommand.COMMAND_WORDS.get(0))) {
            commandToChange = CommandType.IMPORT;
        } else if (commandWord.equals(ListCommand.COMMAND_WORDS.get(0))) {
            commandToChange = CommandType.LIST;
        } else if (commandWord.equals(RedoCommand.COMMAND_WORDS.get(0))) {
            commandToChange = CommandType.REDO;
        } else if (commandWord.equals(ShortcutCommand.COMMAND_WORDS.get(0))) {
            commandToChange = CommandType.SHORTCUT;
        } else if (commandWord.equals(TagCommand.COMMAND_WORDS.get(0))) {
            commandToChange = CommandType.TAG;
        } else if (commandWord.equals(UndoCommand.COMMAND_WORDS.get(0))) {
            commandToChange = CommandType.UNDO;
        } else {
            throw new ParseException(ShortcutCommand.MESSAGE_INVALID_SHORTCUT);
        }

        return new ShortcutCommand(commandToChange, shortForm);
    }

}
