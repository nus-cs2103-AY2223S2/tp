package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.loyaltylift.logic.commands.AddCustomerCommand;
import seedu.loyaltylift.logic.commands.AddPointsCommand;
import seedu.loyaltylift.logic.commands.ClearCommand;
import seedu.loyaltylift.logic.commands.Command;
import seedu.loyaltylift.logic.commands.DeleteCustomerCommand;
import seedu.loyaltylift.logic.commands.EditCustomerCommand;
import seedu.loyaltylift.logic.commands.ExitCommand;
import seedu.loyaltylift.logic.commands.FindCustomerCommand;
import seedu.loyaltylift.logic.commands.HelpCommand;
import seedu.loyaltylift.logic.commands.ListCustomerCommand;
import seedu.loyaltylift.logic.commands.SetPointsCommand;
import seedu.loyaltylift.logic.commands.ViewCustomerCommand;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;

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

        case AddCustomerCommand.COMMAND_WORD:
            return new AddCustomerCommandParser().parse(arguments);

        case EditCustomerCommand.COMMAND_WORD:
            return new EditCustomerCommandParser().parse(arguments);

        case DeleteCustomerCommand.COMMAND_WORD:
            return new DeleteCustomerCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCustomerCommand.COMMAND_WORD:
            return new FindCustomerCommandParser().parse(arguments);

        case ListCustomerCommand.COMMAND_WORD:
            return new ListCustomerCommand();

        case ViewCustomerCommand.COMMAND_WORD:
            return new ViewCustomerCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case SetPointsCommand.COMMAND_WORD:
            return new SetPointsCommandParser().parse(arguments);

        case AddPointsCommand.COMMAND_WORD:
            return new AddPointsCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
