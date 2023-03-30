package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static mycelium.mycelium.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.AddClientCommand;
import mycelium.mycelium.logic.commands.AddProjectCommand;
import mycelium.mycelium.logic.commands.Command;
import mycelium.mycelium.logic.commands.DeleteClientCommand;
import mycelium.mycelium.logic.commands.DeleteProjectCommand;
import mycelium.mycelium.logic.commands.UpdateClientCommand;
import mycelium.mycelium.logic.commands.UpdateProjectCommand;
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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Messages.MESSAGE_CHECK_USER_GUIDE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddClientCommand.COMMAND_ACRONYM:
            return new AddClientCommandParser().parse(arguments);

        case AddProjectCommand.COMMAND_ACRONYM:
            return new AddProjectCommandParser().parse(arguments);

        case DeleteClientCommand.COMMAND_ACRONYM:
            return new DeleteClientCommandParser().parse(arguments);

        case DeleteProjectCommand.COMMAND_ACRONYM:
            return new DeleteProjectCommandParser().parse(arguments);

        case UpdateClientCommand.COMMAND_ACRONYM:
            return new UpdateClientCommandParser().parse(arguments);

        case UpdateProjectCommand.COMMAND_ACRONYM:
            return new UpdateProjectCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
