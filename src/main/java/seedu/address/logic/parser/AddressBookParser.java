package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindDetailsCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAppointmentsCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.TodayCommand;
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
        switch (commandWord) {
        case AddCommand.COMMAND_ALIAS:
            // fall through to AddCommand.COMMAND_WORD
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case AddAppointmentCommand.COMMAND_ALIAS:
            // fall through to AddAppointmentCommand.COMMAND_WORD
        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parse(arguments);

        case EditCommand.COMMAND_ALIAS:
            // fall through to RemarkCommand.COMMAND_WORD
        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_ALIAS:
            // fall through to DeleteCommand.COMMAND_WORD
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteAppointmentCommand.COMMAND_ALIAS:
            // fall through to DeleteAppointmentCommand.COMMAND_WORD
        case DeleteAppointmentCommand.COMMAND_WORD:
            return new DeleteAppointmentCommandParser().parse(arguments);

        case ClearCommand.COMMAND_ALIAS:
            // fall through to RemarkCommand.COMMAND_WORD
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_ALIAS:
            // fall through to FindCommand.COMMAND_WORD
        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindDetailsCommand.COMMAND_ALIAS:
            // fall through to FindDetailsCommand.COMMAND_WORD
        case FindDetailsCommand.COMMAND_WORD:
            return new FindDetailsCommandParser().parse(arguments);

        case FindAppointmentCommand.COMMAND_ALIAS:
            // fall through to FindAppointmentCommand.COMMAND_WORD
        case FindAppointmentCommand.COMMAND_WORD:
            return new FindAppointmentCommandParser().parse(arguments);

        case ListCommand.COMMAND_ALIAS:
            // fall through to ListCommand.COMMAND_WORD
        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListAppointmentsCommand.COMMAND_ALIAS:
            // fall through to ListAppointmentsCommand.COMMAND_WORD
        case ListAppointmentsCommand.COMMAND_WORD:
            return new ListAppointmentsCommand();

        case EditAppointmentCommand.COMMAND_ALIAS:
            // fall through to EditAppointmentCommand.COMMAND_WORD
        case EditAppointmentCommand.COMMAND_WORD:
            return new EditAppointmentCommandParser().parse(arguments);

        case ExitCommand.COMMAND_ALIAS:
            // fall through to ExitCommand.COMMAND_WORD
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_ALIAS:
            // fall through to HelpCommand.COMMAND_WORD
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case RemarkCommand.COMMAND_ALIAS:
            // fall through to RemarkCommand.COMMAND_WORD
        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser().parse(arguments);

        case TodayCommand.COMMAND_WORD:
            return new TodayCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
