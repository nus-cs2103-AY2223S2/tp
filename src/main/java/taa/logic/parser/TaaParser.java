package taa.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import taa.commons.core.Messages;
import taa.logic.commands.AddStudentCommand;
import taa.logic.commands.ClearCommand;
import taa.logic.commands.Command;
import taa.logic.commands.CreateClassCommand;
import taa.logic.commands.DeleteStudentCommand;
import taa.logic.commands.EditStudentCommand;
import taa.logic.commands.ExitCommand;
import taa.logic.commands.FindCommand;
import taa.logic.commands.HelpCommand;
import taa.logic.commands.ListByClassCommand;
import taa.logic.commands.ListCommand;
import taa.logic.commands.MarkAttendanceCommand;
import taa.logic.commands.RemarkCommand;
import taa.logic.commands.UnmarkAttendanceCommand;


import taa.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TaaParser {

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
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommand();

        case MarkAttendanceCommand.COMMAND_WORD:
            return new MarkAttendanceParser().parse(arguments);

        case UnmarkAttendanceCommand.COMMAND_WORD:
            return new UnmarkAttendanceParser().parse(arguments);

        case AddStudentCommand.COMMAND_WORD:
            return new AddStudentCommandParser().parse(arguments);

        case EditStudentCommand.COMMAND_WORD:
            return new EditStudentCommandParser().parse(arguments);

        case DeleteStudentCommand.COMMAND_WORD:
            return new DeleteStudentCommandParser().parse(arguments);

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

        case ListByClassCommand.COMMAND_WORD:
            return new ListByClassCommandParser().parse(arguments);

        case CreateClassCommand.COMMAND_WORD:
            return new CreateClassCommandParser().parse(arguments);
        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
