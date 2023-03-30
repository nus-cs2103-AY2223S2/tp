package seedu.address.logic.parser.timetable;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_TIMETABLE_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.timetable.TimetableCompletedCommand;
import seedu.address.logic.commands.timetable.TimetableDateCommand;
import seedu.address.logic.commands.timetable.TimetableUnscheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represent a parser for timetable command
 */
public class TimetableParser {

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
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case TimetableDateCommand.COMMAND_WORD:
            return new TimetableDateCommandParser().parse(arguments);

        case TimetableUnscheduleCommand.COMMAND_WORD:
            return new TimetableUnscheduleCommand();

        case TimetableCompletedCommand.COMMAND_WORD:
            return new TimetableCompletedCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_TIMETABLE_COMMAND);
        }
    }

}
