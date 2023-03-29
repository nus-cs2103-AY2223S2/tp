package expresslibrary.logic.parser;

import static expresslibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static expresslibrary.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expresslibrary.logic.commands.AddBookCommand;
import expresslibrary.logic.commands.AddPersonCommand;
import expresslibrary.logic.commands.BorrowCommand;
import expresslibrary.logic.commands.ClearCommand;
import expresslibrary.logic.commands.Command;
import expresslibrary.logic.commands.DeleteBookCommand;
import expresslibrary.logic.commands.DeletePersonCommand;
import expresslibrary.logic.commands.EditBookCommand;
import expresslibrary.logic.commands.EditPersonCommand;
import expresslibrary.logic.commands.ExitCommand;
import expresslibrary.logic.commands.FindBookCommand;
import expresslibrary.logic.commands.FindPersonCommand;
import expresslibrary.logic.commands.HelpCommand;
import expresslibrary.logic.commands.ListBookCommand;
import expresslibrary.logic.commands.ListPersonCommand;
import expresslibrary.logic.commands.ReturnCommand;
import expresslibrary.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ExpressLibraryParser {

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

        case AddBookCommand.COMMAND_WORD:
            return new AddBookCommandParser().parse(arguments);

        case AddPersonCommand.COMMAND_WORD:
            return new AddPersonCommandParser().parse(arguments);

        case EditBookCommand.COMMAND_WORD:
            return new EditBookCommandParser().parse(arguments);

        case EditPersonCommand.COMMAND_WORD:
            return new EditPersonCommandParser().parse(arguments);

        case DeleteBookCommand.COMMAND_WORD:
            return new DeleteBookCommandParser().parse(arguments);

        case DeletePersonCommand.COMMAND_WORD:
            return new DeletePersonCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindPersonCommand.COMMAND_WORD:
            return new FindPersonCommandParser().parse(arguments);

        case ListPersonCommand.COMMAND_WORD:
            return new ListPersonCommand();

        case ListBookCommand.COMMAND_WORD:
            return new ListBookCommand();

        case BorrowCommand.COMMAND_WORD:
            return new BorrowCommandParser().parse(arguments);

        case ReturnCommand.COMMAND_WORD:
            return new ReturnCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case FindBookCommand.COMMAND_WORD:
            return new FindBookCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
