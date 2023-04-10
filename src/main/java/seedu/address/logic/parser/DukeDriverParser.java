package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandGroup;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.StatisticsCommand;
import seedu.address.logic.commands.jobs.AddDeliveryJobCommand;
import seedu.address.logic.commands.jobs.CompleteDeliveryJobCommand;
import seedu.address.logic.commands.jobs.DeleteDeliveryJobCommand;
import seedu.address.logic.commands.jobs.EditDeliveryJobCommand;
import seedu.address.logic.commands.jobs.FindDeliveryJobCommand;
import seedu.address.logic.commands.jobs.ListDeliveryJobCommand;
import seedu.address.logic.commands.person.AddCommand;
import seedu.address.logic.commands.person.ClearCommand;
import seedu.address.logic.commands.person.DeleteCommand;
import seedu.address.logic.commands.person.EditCommand;
import seedu.address.logic.commands.person.FindCommand;
import seedu.address.logic.commands.person.ListCommand;
import seedu.address.logic.commands.reminder.AddReminderCommand;
import seedu.address.logic.commands.reminder.DeleteReminderCommand;
import seedu.address.logic.commands.reminder.ListReminderCommand;
import seedu.address.logic.commands.timetable.TimetableCommand;
import seedu.address.logic.commands.timetable.TimetableCompletedCommand;
import seedu.address.logic.commands.timetable.TimetableDateCommand;
import seedu.address.logic.commands.timetable.TimetableUnscheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.jobs.AddDeliveryJobCommandParser;
import seedu.address.logic.parser.jobs.CompleteDeliveryJobCommandParser;
import seedu.address.logic.parser.jobs.DeleteDeliveryJobCommandParser;
import seedu.address.logic.parser.jobs.EditDeliveryJobCommandParser;
import seedu.address.logic.parser.jobs.FindDeliveryJobCommandParser;
import seedu.address.logic.parser.person.AddCommandParser;
import seedu.address.logic.parser.person.DeleteCommandParser;
import seedu.address.logic.parser.person.EditCommandParser;
import seedu.address.logic.parser.person.FindCommandParser;
import seedu.address.logic.parser.reminder.AddReminderParser;
import seedu.address.logic.parser.reminder.DeleteReminderParser;
import seedu.address.logic.parser.timetable.TimetableDateCommandParser;

/**
 * Parses user input.
 */
public class DukeDriverParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Returns the command group before actual parsing.
     *
     * @param userInput full user input string
     * @return the command group on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public CommandGroup parseCommandGroup(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        switch (commandWord.toLowerCase()) {

        case AddCommand.COMMAND_WORD:
        case EditCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_WORD:
        case FindCommand.COMMAND_WORD:
        case ClearCommand.COMMAND_WORD:
            return FindCommand.COMMAND_GROUP;
        case ListCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_WORD:
        case HelpCommand.COMMAND_WORD:
            return HelpCommand.COMMAND_GROUP;
        case AddReminderCommand.COMMAND_WORD:
        case ListReminderCommand.COMMAND_WORD:
        case DeleteReminderCommand.COMMAND_WORD:
            return DeleteReminderCommand.COMMAND_GROUP;
        case TimetableCommand.COMMAND_WORD:
        case TimetableDateCommand.COMMAND_WORD:
        case TimetableUnscheduleCommand.COMMAND_WORD:
        case TimetableCompletedCommand.COMMAND_WORD:
            return TimetableCompletedCommand.COMMAND_GROUP;
        case ListDeliveryJobCommand.COMMAND_WORD:
        case AddDeliveryJobCommand.COMMAND_WORD:
        case EditDeliveryJobCommand.COMMAND_WORD:
        case FindDeliveryJobCommand.COMMAND_WORD:
        case DeleteDeliveryJobCommand.COMMAND_WORD:
        case CompleteDeliveryJobCommand.COMMAND_WORD_MARK:
        case CompleteDeliveryJobCommand.COMMAND_WORD_UNMARK:
            return CompleteDeliveryJobCommand.COMMAND_GROUP;
        case StatisticsCommand.COMMAND_WORD:
            return StatisticsCommand.COMMAND_GROUP;
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

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
        switch (commandWord.toLowerCase()) {

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

        case AddReminderCommand.COMMAND_WORD:
            return new AddReminderParser().parse(arguments);

        case ListReminderCommand.COMMAND_WORD:
            return new ListReminderCommand();

        case DeleteReminderCommand.COMMAND_WORD:
            return new DeleteReminderParser().parse(arguments);

        case TimetableCommand.COMMAND_WORD:
            return new TimetableCommand();

        case TimetableDateCommand.COMMAND_WORD:
            return new TimetableDateCommandParser().parse(arguments);

        case TimetableUnscheduleCommand.COMMAND_WORD:
            return new TimetableUnscheduleCommand();

        case TimetableCompletedCommand.COMMAND_WORD:
            return new TimetableCompletedCommand();

        case ListDeliveryJobCommand.COMMAND_WORD:
            return new ListDeliveryJobCommand();

        case AddDeliveryJobCommand.COMMAND_WORD:
            return new AddDeliveryJobCommandParser().parse(arguments);

        case EditDeliveryJobCommand.COMMAND_WORD:
            return new EditDeliveryJobCommandParser().parse(arguments);

        case FindDeliveryJobCommand.COMMAND_WORD:
            return new FindDeliveryJobCommandParser().parse(arguments);

        case DeleteDeliveryJobCommand.COMMAND_WORD:
            return new DeleteDeliveryJobCommandParser().parse(arguments);

        case CompleteDeliveryJobCommand.COMMAND_WORD_MARK:
            return new CompleteDeliveryJobCommandParser(true).parse(arguments);

        case CompleteDeliveryJobCommand.COMMAND_WORD_UNMARK:
            return new CompleteDeliveryJobCommandParser(false).parse(arguments);

        case StatisticsCommand.COMMAND_WORD:
            return new StatisticsCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
