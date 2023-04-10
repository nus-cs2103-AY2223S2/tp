package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddIsolatedEventCommand;
import seedu.address.logic.commands.AddRecurringEventCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteIsolatedEventCommand;
import seedu.address.logic.commands.DeleteRecurringEventCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditIsolatedEventCommand;
import seedu.address.logic.commands.EditRecurringEventCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindTimeCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.group.GroupCreateCommand;
import seedu.address.logic.commands.group.GroupDeleteCommand;
import seedu.address.logic.commands.group.GroupFindCommand;
import seedu.address.logic.commands.group.GroupListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.group.GroupCreateCommandParser;
import seedu.address.logic.parser.group.GroupDeleteCommandParser;
import seedu.address.logic.parser.group.GroupFindCommandParser;

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

        case AddIsolatedEventCommand.COMMAND_WORD:
            return new AddIsolatedEventCommandParser().parse(arguments);

        case DeleteIsolatedEventCommand.COMMAND_WORD:
            return new DeleteIsolatedEventParser().parse(arguments);

        case EditIsolatedEventCommand.COMMAND_WORD:
            return new EditIsolatedEventCommandParser().parse(arguments);

        case AddRecurringEventCommand.COMMAND_WORD:
            return new AddRecurringEventCommandParser().parse(arguments);

        case DeleteRecurringEventCommand.COMMAND_WORD:
            return new DeleteRecurringEventParser().parse(arguments);

        case EditRecurringEventCommand.COMMAND_WORD:
            return new EditRecurringEventCommandParser().parse(arguments);

        case GroupListCommand.COMMAND_WORD:
            return new GroupListCommand();

        case GroupCreateCommand.COMMAND_WORD:
            return new GroupCreateCommandParser().parse(arguments);

        case GroupFindCommand.COMMAND_WORD:
            return new GroupFindCommandParser().parse(arguments);

        case GroupDeleteCommand.COMMAND_WORD:
            return new GroupDeleteCommandParser().parse(arguments);

        case FindTimeCommand.COMMAND_WORD:
            return new FindTimeCommandParser().parse(arguments);

        case ExportCommand.COMMAND_WORD:
            return new ExportCommandParser().parse(arguments);

        case ImportCommand.COMMAND_WORD:
            return new ImportCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
