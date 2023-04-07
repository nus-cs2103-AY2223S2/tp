package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddDoctorCommand;
import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.AssignPatientCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteDoctorCommand;
import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.logic.commands.EditDoctorCommand;
import seedu.address.logic.commands.EditPatientCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindDoctorCommand;
import seedu.address.logic.commands.FindPatientCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListDoctorCommand;
import seedu.address.logic.commands.ListPatientCommand;
import seedu.address.logic.commands.SelectDoctorCommand;
import seedu.address.logic.commands.SelectPatientCommand;
import seedu.address.logic.commands.UnassignPatientCommand;
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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.getCommandUsage()));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {


        case AddDoctorCommand.SHORTHAND_COMMAND_WORD:
        case AddDoctorCommand.COMMAND_WORD:
            return new AddDoctorCommandParser().parse(arguments);

        case AddPatientCommand.SHORTHAND_COMMAND_WORD:
        case AddPatientCommand.COMMAND_WORD:
            return new AddPatientCommandParser().parse(arguments);

        case AssignPatientCommand.SHORTHAND_COMMAND_WORD:
        case AssignPatientCommand.COMMAND_WORD:
            return new AssignPatientCommandParser().parse(arguments);

        case UnassignPatientCommand.SHORTHAND_COMMAND_WORD:
        case UnassignPatientCommand.COMMAND_WORD:
            return new UnassignPatientCommandParser().parse(arguments);

        case EditDoctorCommand.SHORTHAND_COMMAND_WORD:
        case EditDoctorCommand.COMMAND_WORD:
            return new EditDoctorCommandParser().parse(arguments);

        case EditPatientCommand.SHORTHAND_COMMAND_WORD:
        case EditPatientCommand.COMMAND_WORD:
            return new EditPatientCommandParser().parse(arguments);

        case DeleteDoctorCommand.SHORTHAND_COMMAND_WORD:
        case DeleteDoctorCommand.COMMAND_WORD:
            return new DeleteDoctorCommandParser().parse(arguments);

        case DeletePatientCommand.SHORTHAND_COMMAND_WORD:
        case DeletePatientCommand.COMMAND_WORD:
            return new DeletePatientCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindDoctorCommand.SHORTHAND_COMMAND_WORD:
        case FindDoctorCommand.COMMAND_WORD:
            return new FindDoctorCommandParser().parse(arguments);

        case FindPatientCommand.SHORTHAND_COMMAND_WORD:
        case FindPatientCommand.COMMAND_WORD:
            return new FindPatientCommandParser().parse(arguments);

        case ListDoctorCommand.SHORTHAND_COMMAND_WORD:
        case ListDoctorCommand.COMMAND_WORD:
            return new ListDoctorCommand();

        case ListPatientCommand.SHORTHAND_COMMAND_WORD:
        case ListPatientCommand.COMMAND_WORD:
            return new ListPatientCommand();

        case SelectDoctorCommand.COMMAND_WORD:
            return new SelectDoctorCommandParser().parse(arguments);

        case SelectPatientCommand.COMMAND_WORD:
            return new SelectPatientCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
