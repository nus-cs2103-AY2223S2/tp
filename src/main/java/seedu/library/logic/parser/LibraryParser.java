package seedu.library.logic.parser;

import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.library.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.library.logic.commands.AddCommand;
import seedu.library.logic.commands.AddTagCommand;
import seedu.library.logic.commands.ClearCommand;
import seedu.library.logic.commands.Command;
import seedu.library.logic.commands.DeleteCommand;
import seedu.library.logic.commands.DeleteTagCommand;
import seedu.library.logic.commands.EditCommand;
import seedu.library.logic.commands.ExitCommand;
import seedu.library.logic.commands.FindCommand;
import seedu.library.logic.commands.GenreCommand;
import seedu.library.logic.commands.GoToCommand;
import seedu.library.logic.commands.HelpCommand;
import seedu.library.logic.commands.ListCommand;
import seedu.library.logic.commands.ListTagsCommand;
import seedu.library.logic.commands.SortCommand;
import seedu.library.logic.commands.ViewCommand;
import seedu.library.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class LibraryParser {

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

        case AddTagCommand.COMMAND_WORD:
            return new AddTagCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case GoToCommand.COMMAND_WORD:
            return new GoToCommandParser().parse(arguments);

        case DeleteTagCommand.COMMAND_WORD:
            return new DeleteTagCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListTagsCommand.COMMAND_WORD:
            return new ListTagsCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case GenreCommand.COMMAND_WORD:
            return new GenreCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
