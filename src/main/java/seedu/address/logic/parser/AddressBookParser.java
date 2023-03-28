package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import seedu.address.logic.commands.FreezeCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MassOpCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.UnfreezeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // if the user's command fits a keyword for a command, create a parser
        if (AddCommand.COMMAND_WORDS.contains(commandWord)) {
            return new AddCommandParser().parse(arguments);
        } else if (EditCommand.COMMAND_WORDS.contains(commandWord)) {
            return new EditCommandParser().parse(arguments);
        } else if (DeleteCommand.COMMAND_WORDS.contains(commandWord)) {
            return new DeleteCommandParser().parse(arguments);
        } else if (DeleteTagCommand.COMMAND_WORDS.contains(commandWord)) {
            return new DeleteTagCommandParser().parse(arguments);
        } else if (ClearCommand.COMMAND_WORDS.contains(commandWord)) {
            return new ClearCommand();
        } else if (FilterCommand.COMMAND_WORDS.contains(commandWord)) {
            return new FilterCommandParser().parse(arguments);
        } else if (FreezeCommand.COMMAND_WORDS.contains(commandWord)) {
            return new FreezeCommand();
        } else if (UnfreezeCommand.COMMAND_WORDS.contains(commandWord)) {
            return new UnfreezeCommand();
        } else if (UndoCommand.COMMAND_WORDS.contains(commandWord)) {
            return new UndoCommandParser().parse(arguments);
        } else if (RedoCommand.COMMAND_WORDS.contains(commandWord)) {
            return new RedoCommandParser().parse(arguments);
        } else if (FindCommand.COMMAND_WORD.contains(commandWord)) {
            return new FindCommandParser().parse(arguments);
        } else if (ListCommand.COMMAND_WORDS.contains(commandWord)) {
            return new ListCommand();
        } else if (ExitCommand.COMMAND_WORDS.contains(commandWord)) {
            return new ExitCommand();
        } else if (HelpCommand.COMMAND_WORDS.contains(commandWord)) {
            return new HelpCommand();
        } else if (TagCommand.COMMAND_WORDS.contains(commandWord)) {
            return new TagCommandParser().parse(arguments);
        } else if (ExportCommand.COMMAND_WORDS.contains(commandWord)) {
            return new ExportCommand();
        } else if (ImportCommand.COMMAND_WORDS.contains(commandWord)) {
            return new ImportCommand();
        } else if (MassOpCommand.COMMAND_WORDS.contains(commandWord)) {
            return new MassOpCommandParser().parse(arguments);
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
