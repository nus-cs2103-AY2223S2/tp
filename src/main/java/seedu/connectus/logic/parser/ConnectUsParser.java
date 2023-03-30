package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.connectus.logic.commands.AddCommand;
import seedu.connectus.logic.commands.AddTagToPersonCommand;
import seedu.connectus.logic.commands.ChatCommand;
import seedu.connectus.logic.commands.ClearCommand;
import seedu.connectus.logic.commands.Command;
import seedu.connectus.logic.commands.DeleteCommand;
import seedu.connectus.logic.commands.DeleteTagFromPersonCommand;
import seedu.connectus.logic.commands.EditCommand;
import seedu.connectus.logic.commands.ExitCommand;
import seedu.connectus.logic.commands.HelpCommand;
import seedu.connectus.logic.commands.ListCommand;
import seedu.connectus.logic.commands.OpenCommand;
import seedu.connectus.logic.commands.SearchCommand;
import seedu.connectus.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ConnectUsParser {

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

        case SearchCommand.COMMAND_WORD:
            return new SearchCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);

        case AddTagToPersonCommand.COMMAND_WORD:
            return new AddTagToPersonCommandParser().parse(arguments);

        case DeleteTagFromPersonCommand.COMMAND_WORD:
            return new DeleteTagFromPersonCommandParser().parse(arguments);

        case OpenCommand.COMMAND_WORD:
            return new OpenCommandParser().parse(arguments);

        case ChatCommand.COMMAND_WORD:
            return new ChatCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
