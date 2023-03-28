package seedu.internship.logic.parser;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.internship.logic.commands.AddCommand;
import seedu.internship.logic.commands.CalendarCommand;
import seedu.internship.logic.commands.ClashCommand;
import seedu.internship.logic.commands.ClearCommand;
import seedu.internship.logic.commands.Command;
import seedu.internship.logic.commands.DeleteCommand;
import seedu.internship.logic.commands.EditCommand;
import seedu.internship.logic.commands.ExitCommand;
import seedu.internship.logic.commands.HelpCommand;
import seedu.internship.logic.commands.HomeCommand;
import seedu.internship.logic.commands.ListCommand;
import seedu.internship.logic.commands.SelectCommand;
import seedu.internship.logic.commands.StatsCommand;
import seedu.internship.logic.commands.event.EventCommand;
import seedu.internship.logic.parser.event.EventCatalogueParser;
import seedu.internship.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class InternshipCatalogueParser {

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
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case SelectCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);
        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);
        case HomeCommand.COMMAND_WORD:
            return new HomeCommand();
        case StatsCommand.COMMAND_WORD:
            return new StatsCommand();
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        case EventCommand.COMMAND_WORD:
            return new EventCatalogueParser().parse(arguments);
        case ClashCommand.COMMAND_WORD:
            return new ClashCommand();
        case CalendarCommand.COMMAND_WORD:
            return new CalendarCommand();
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
