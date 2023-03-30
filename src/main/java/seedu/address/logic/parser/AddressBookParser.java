package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.*;
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

        // AutoM8 commands

        // Add

        case AddCustomerCommand.COMMAND_WORD:
            return new AddCustomerCommandParser().parse(arguments);

        case AddVehicleCommand.COMMAND_WORD:
            return new AddVehicleCommandParser().parse(arguments);

        case AddServiceCommand.COMMAND_WORD:
            return new AddServiceCommandParser().parse(arguments);

        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parse(arguments);

        case AddPartCommand.COMMAND_WORD:
            return new AddPartCommandParser().parse(arguments);

        case AddPartToServiceCommand.COMMAND_WORD:
            return new AddPartToServiceCommandParser().parse(arguments);

        case AddTechnicianCommand.COMMAND_WORD:
            return new AddTechnicianCommandParser().parse(arguments);

        case AddTechnicianToServiceCommand.COMMAND_WORD:
            return new AddTechnicianToServiceCommandParser().parse(arguments);

        // List

        case ListCustomersCommand.COMMAND_WORD:
            return new ListCustomersCommand();

        case ListVehiclesCommand.COMMAND_WORD:
            return new ListVehiclesCommand();

        case ListServicesCommand.COMMAND_WORD:
            return new ListServicesCommand();

        case ListAppointmentsCommand.COMMAND_WORD:
            return new ListAppointmentsCommand();

        // View

        case ViewPartCommand.COMMAND_WORD:
            return new ViewPartCommand(arguments.trim());

        case ViewVehicleCommand.COMMAND_WORD:
            return new ViewVehicleCommandParser().parse(arguments.trim());

        case ViewAppointmentCommand.COMMAND_WORD:
            return new ViewAppointmentCommandParser().parse(arguments.trim());

        case ViewCustomerCommand.COMMAND_WORD:
            return new ViewCustomerCommandParser().parse(arguments.trim());

        case ViewTechnicianCommand.COMMAND_WORD:
            return new ViewTechnicianCommandParser().parse(arguments.trim());

        case ViewServiceCommand.COMMAND_WORD:
            return new ViewServiceCommandParser().parse(arguments.trim());

        // Delete

        case DeleteVehicleCommand.COMMAND_WORD:
            return new DeleteVehicleCommandParser().parse(arguments.trim());

        case DeleteCustomerCommand.COMMAND_WORD:
            return new DeleteCustomerCommandParser().parse(arguments.trim());

        case DeleteServiceCommand.COMMAND_WORD:
            return new DeleteServiceCommandParser().parse(arguments.trim());

        case DeleteAppointmentCommand.COMMAND_WORD:
            return new DeleteAppointmentCommandParser().parse(arguments.trim());

        case DeleteTechnicianCommand.COMMAND_WORD:
            return new DeleteTechnicianCommandParser().parse(arguments.trim());

        // Sort

        case SortCustomersCommand.COMMAND_WORD:
            return new SortCustomersCommandParser().parse(arguments);

        case SortVehiclesCommand.COMMAND_WORD:
            return new SortVehiclesCommandParser().parse(arguments);

        case SortServicesCommand.COMMAND_WORD:
            return new SortServicesCommandParser().parse(arguments);

        case SortTechniciansCommand.COMMAND_WORD:
            return new SortTechniciansCommandParser().parse(arguments);

        case SortAppointmentsCommand.COMMAND_WORD:
            return new SortAppointmentsCommandParser().parse(arguments);

        case TotalAppointmentCommand.COMMAND_WORD:
            return new TotalAppointmentCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
