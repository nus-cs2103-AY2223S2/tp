package seedu.quickcontacts.logic.parser;

import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.quickcontacts.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.quickcontacts.logic.commands.AddCommand;
import seedu.quickcontacts.logic.commands.AddMeetingCommand;
import seedu.quickcontacts.logic.commands.AutocompleteResult;
import seedu.quickcontacts.logic.commands.ClearCommand;
import seedu.quickcontacts.logic.commands.Command;
import seedu.quickcontacts.logic.commands.DeleteCommand;
import seedu.quickcontacts.logic.commands.DeleteMeetingCommand;
import seedu.quickcontacts.logic.commands.EditCommand;
import seedu.quickcontacts.logic.commands.EditMeetingsCommand;
import seedu.quickcontacts.logic.commands.ExitCommand;
import seedu.quickcontacts.logic.commands.ExportMeetingsCommand;
import seedu.quickcontacts.logic.commands.ExportPersonsCommand;
import seedu.quickcontacts.logic.commands.FindCommand;
import seedu.quickcontacts.logic.commands.FindMeetingCommand;
import seedu.quickcontacts.logic.commands.HelpCommand;
import seedu.quickcontacts.logic.commands.ImportMeetingsCommand;
import seedu.quickcontacts.logic.commands.ImportPersonsCommand;
import seedu.quickcontacts.logic.commands.ListCommand;
import seedu.quickcontacts.logic.commands.SortMeetingCommand;
import seedu.quickcontacts.logic.commands.ViewMeetingsCommand;
import seedu.quickcontacts.logic.parser.exceptions.ParseException;
import seedu.quickcontacts.model.command.CommandHistory;

/**
 * Parses user input.
 */
public class QuickContactsParser {
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
        CommandHistory.push(userInput);
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

        case AddMeetingCommand.COMMAND_WORD:
            return new AddMeetingCommandParser().parse(arguments);

        case FindMeetingCommand.COMMAND_WORD:
            return new FindMeetingCommandParser().parse(arguments);

        case ViewMeetingsCommand.COMMAND_WORD:
            return new ViewMeetingsCommand();

        case EditMeetingsCommand.COMMAND_WORD:
            return new EditMeetingParser().parse(arguments);

        case DeleteMeetingCommand.COMMAND_WORD:
            return new DeleteMeetingCommandParser().parse(arguments);

        case ExportPersonsCommand.COMMAND_WORD:
            return new ExportPersonsParser().parse(arguments);

        case ImportPersonsCommand.COMMAND_WORD:
            return new ImportPersonsParser().parse(arguments);

        case SortMeetingCommand.COMMAND_WORD:
            return new SortMeetingParser().parse(arguments);

        case ExportMeetingsCommand.COMMAND_WORD:
            return new ExportMeetingsParser().parse(arguments);

        case ImportMeetingsCommand.COMMAND_WORD:
            return new ImportMeetingsParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses user input into command for autocomplete suggestion.
     *
     * @param userInput full user input string
     * @return the {@code AutocompleteResult} based on the user input
     */
    public AutocompleteResult getAutocompleteSuggestion(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new AutocompleteResult(null, false);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().getAutocompleteSuggestion(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().getAutocompleteSuggestion(arguments);

        case AddMeetingCommand.COMMAND_WORD:
            return new AddMeetingCommandParser().getAutocompleteSuggestion(arguments);

        case EditMeetingsCommand.COMMAND_WORD:
            return new EditMeetingParser().getAutocompleteSuggestion(arguments);
        case ExportPersonsCommand.COMMAND_WORD:
            return new ExportPersonsParser().getAutocompleteSuggestion(arguments);
        case ExportMeetingsCommand.COMMAND_WORD:
            return new ExportMeetingsParser().getAutocompleteSuggestion(arguments);
        default:
            return new AutocompleteResult(null, false);
        }
    }

}
