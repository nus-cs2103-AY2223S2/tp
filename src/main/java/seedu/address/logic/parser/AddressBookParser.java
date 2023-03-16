package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteCustomerCommand;
import seedu.address.logic.commands.DeleteVehicleCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.ViewAppointmentCommand;
import seedu.address.logic.commands.ViewCustomerCommand;
import seedu.address.logic.commands.ViewPartCommand;
import seedu.address.logic.commands.ViewVehicleCommand;
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

        // todo: port to new parser ? (i.e. shopParser)
        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case ViewVehicleCommand.COMMAND_WORD:
            return new ViewVehicleCommandParser().parse(arguments);

        case ViewCustomerCommand.COMMAND_WORD:
            return new ViewCustomerCommandParser().parse(arguments);

        case ViewPartCommand.COMMAND_WORD:
            return new ViewPartCommandParser().parse(arguments);

        case ViewAppointmentCommand.COMMAND_WORD:
            return new ViewAppointmentCommandParser().parse(arguments);

        case DeleteVehicleCommand.COMMAND_WORD:
            return new DeleteVehicleCommandParser().parse(arguments);

        case DeleteCustomerCommand.COMMAND_WORD:
            return new DeleteCustomerCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
