package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.patientist.logic.commands.AddPatientCommand;
import seedu.patientist.logic.commands.AddPatientStatusCommand;
import seedu.patientist.logic.commands.AddPatientToDoCommand;
import seedu.patientist.logic.commands.AddStaffCommand;
import seedu.patientist.logic.commands.AddWardCommand;
import seedu.patientist.logic.commands.ClearCommand;
import seedu.patientist.logic.commands.Command;
import seedu.patientist.logic.commands.DeleteCommand;
import seedu.patientist.logic.commands.DeletePatientCommand;
import seedu.patientist.logic.commands.DeletePatientStatusCommand;
import seedu.patientist.logic.commands.DeletePatientToDoCommand;
import seedu.patientist.logic.commands.DeleteStaffCommand;
import seedu.patientist.logic.commands.DeleteWardCommand;
import seedu.patientist.logic.commands.EditCommand;
import seedu.patientist.logic.commands.ExitCommand;
import seedu.patientist.logic.commands.FindCommand;
import seedu.patientist.logic.commands.FindPatientCommand;
import seedu.patientist.logic.commands.FindStaffCommand;
import seedu.patientist.logic.commands.HelpCommand;
import seedu.patientist.logic.commands.ListCommand;
import seedu.patientist.logic.commands.ListPatientsCommand;
import seedu.patientist.logic.commands.ListStaffCommand;
import seedu.patientist.logic.commands.ListWardPatientsCommand;
import seedu.patientist.logic.commands.ListWardStaffCommand;
import seedu.patientist.logic.commands.ListWardsCommand;
import seedu.patientist.logic.commands.UpdateWardCommand;
import seedu.patientist.logic.commands.ViewCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;


/**
 * Parses user input.
 */
public class PatientistParser {

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

        case AddPatientCommand.COMMAND_WORD:
            return new AddPatientCommandParser().parse(arguments);

        case AddStaffCommand.COMMAND_WORD:
            return new AddStaffCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ListPatientsCommand.COMMAND_WORD:
            return new ListPatientsCommand();

        case ListWardPatientsCommand.COMMAND_WORD:
            return new ListWardPatientsCommandParser().parse(arguments);

        case ListWardStaffCommand.COMMAND_WORD:
            return new ListWardStaffCommandParser().parse(arguments);

        case ListStaffCommand.COMMAND_WORD:
            return new ListStaffCommand();

        case FindPatientCommand.COMMAND_WORD:
            return new FindPatientCommandParser().parse(arguments);

        case FindStaffCommand.COMMAND_WORD:
            return new FindStaffCommandParser().parse(arguments);

        case AddWardCommand.COMMAND_WORD:
            return new AddWardCommandParser().parse(arguments);

        case DeleteWardCommand.COMMAND_WORD:
            return new DeleteWardCommandParser().parse(arguments);

        case UpdateWardCommand.COMMAND_WORD:
            return new UpdateWardParser().parse(arguments);

        case AddPatientStatusCommand.COMMAND_WORD:
            return new AddPatientStatusCommandParser().parse(arguments);

        case AddPatientToDoCommand.COMMAND_WORD:
            return new AddPatientToDoCommandParser().parse(arguments);

        case DeletePatientStatusCommand.COMMAND_WORD:
            return new DeletePatientStatusCommandParser().parse(arguments);

        case DeletePatientToDoCommand.COMMAND_WORD:
            return new DeletePatientToDoCommandParser().parse(arguments);

        case DeletePatientCommand.COMMAND_WORD:
            return new DeletePatientCommandParser().parse(arguments);

        case DeleteStaffCommand.COMMAND_WORD:
            return new DeleteStaffCommandParser().parse(arguments);

        case ListWardsCommand.COMMAND_WORD:
            return new ListWardsCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
