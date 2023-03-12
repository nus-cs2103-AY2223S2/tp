package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static mycelium.mycelium.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mycelium.mycelium.logic.commands.AddCommand;
import mycelium.mycelium.logic.commands.ClearCommand;
import mycelium.mycelium.logic.commands.Command;
import mycelium.mycelium.logic.commands.DeleteCommand;
import mycelium.mycelium.logic.commands.EditCommand;
import mycelium.mycelium.logic.commands.ExitCommand;
import mycelium.mycelium.logic.commands.FindCommand;
import mycelium.mycelium.logic.commands.HelpCommand;
import mycelium.mycelium.logic.commands.ListCommand;
import mycelium.mycelium.logic.commands.AddClientCommand;
import mycelium.mycelium.logic.commands.AddProjectCommand;
import mycelium.mycelium.logic.commands.DeleteClientCommand;
import mycelium.mycelium.logic.commands.DeleteProjectCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;

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

        case AddClientCommand.COMMAND_ACRONYM:
            return new AddClientCommandParser().parse(arguments);

        case AddProjectCommand.COMMAND_ACRONYM:
            return new AddProjectCommandParser().parse(arguments);

        case DeleteClientCommand.COMMAND_ACRONYM:
            return new DeleteClientCommandParser().parse(arguments);

        case DeleteProjectCommand.COMMAND_ACRONYM:
            return new DeleteProjectCommandParser().parse(arguments);


        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
