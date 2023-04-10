package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.dengue.logic.commands.AddCommand;
import seedu.dengue.logic.commands.CheckoutCommand;
import seedu.dengue.logic.commands.ClearCommand;
import seedu.dengue.logic.commands.Command;
import seedu.dengue.logic.commands.DeleteCommand;
import seedu.dengue.logic.commands.EditCommand;
import seedu.dengue.logic.commands.ExitCommand;
import seedu.dengue.logic.commands.ExportCommand;
import seedu.dengue.logic.commands.FindCommand;
import seedu.dengue.logic.commands.HelpCommand;
import seedu.dengue.logic.commands.ImportCommand;
import seedu.dengue.logic.commands.ListCommand;
import seedu.dengue.logic.commands.OverviewCommand;
import seedu.dengue.logic.commands.RedoCommand;
import seedu.dengue.logic.commands.SortCommand;
import seedu.dengue.logic.commands.UndoCommand;
import seedu.dengue.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class DengueHotspotTrackerParser {

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
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case OverviewCommand.COMMAND_WORD:
            return new OverviewCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommandParser().parse(arguments);

        case RedoCommand.COMMAND_WORD:
            return new RedoCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ImportCommand.COMMAND_WORD:
            return new ImportCommandParser().parse(arguments);

        case ExportCommand.COMMAND_WORD:
            return new ExportCommandParser().parse(arguments);

        case CheckoutCommand.COMMAND_WORD:
            return new CheckoutCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
