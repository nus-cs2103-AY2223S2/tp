package arb.logic.parser;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import arb.logic.commands.Command;
import arb.logic.commands.ExitCommand;
import arb.logic.commands.HelpCommand;
import arb.logic.commands.client.AddClientCommand;
import arb.logic.commands.client.ClearClientCommand;
import arb.logic.commands.client.DeleteClientCommand;
import arb.logic.commands.client.EditClientCommand;
import arb.logic.commands.client.FindClientCommand;
import arb.logic.commands.client.ListClientCommand;
import arb.logic.commands.client.SortClientCommand;
import arb.logic.commands.project.AddProjectCommand;
import arb.logic.commands.project.ClearProjectCommand;
import arb.logic.commands.project.DeleteProjectCommand;
import arb.logic.commands.project.EditProjectCommand;
import arb.logic.commands.project.FindProjectCommand;
import arb.logic.commands.project.ListProjectCommand;
import arb.logic.commands.project.MarkProjectCommand;
import arb.logic.commands.project.SortProjectCommand;
import arb.logic.commands.project.UnmarkProjectCommand;
import arb.logic.commands.tag.ListTagCommand;
import arb.logic.parser.client.AddClientCommandParser;
import arb.logic.parser.client.DeleteClientCommandParser;
import arb.logic.parser.client.EditClientCommandParser;
import arb.logic.parser.client.FindClientCommandParser;
import arb.logic.parser.exceptions.ParseException;
import arb.logic.parser.project.AddProjectCommandParser;
import arb.logic.parser.project.DeleteProjectCommandParser;
import arb.logic.parser.project.EditProjectCommandParser;
import arb.logic.parser.project.FindProjectCommandParser;
import arb.logic.parser.project.MarkProjectCommandParser;
import arb.logic.parser.project.SortProjectCommandParser;
import arb.logic.parser.project.UnmarkProjectCommandParser;

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

        final String commandWord = matcher.group("commandWord").toLowerCase();
        final String arguments = matcher.group("arguments");

        if (AddClientCommand.isCommandWord(commandWord)) {
            return new AddClientCommandParser().parse(arguments);
        } else if (AddProjectCommand.isCommandWord(commandWord)) {
            return new AddProjectCommandParser().parse(arguments);
        } else if (MarkProjectCommand.isCommandWord(commandWord)) {
            return new MarkProjectCommandParser().parse(arguments);
        } else if (UnmarkProjectCommand.isCommandWord(commandWord)) {
            return new UnmarkProjectCommandParser().parse(arguments);
        } else if (EditClientCommand.isCommandWord(commandWord)) {
            return new EditClientCommandParser().parse(arguments);
        } else if (EditProjectCommand.isCommandWord(commandWord)) {
            return new EditProjectCommandParser().parse(arguments);
        } else if (DeleteClientCommand.isCommandWord(commandWord)) {
            return new DeleteClientCommandParser().parse(arguments);
        } else if (DeleteProjectCommand.isCommandWord(commandWord)) {
            return new DeleteProjectCommandParser().parse(arguments);
        } else if (ClearClientCommand.isCommandWord(commandWord)) {
            return new ClearClientCommand();
        } else if (ClearProjectCommand.isCommandWord(commandWord)) {
            return new ClearProjectCommand();
        } else if (FindClientCommand.isCommandWord(commandWord)) {
            return new FindClientCommandParser().parse(arguments);
        } else if (FindProjectCommand.isCommandWord(commandWord)) {
            return new FindProjectCommandParser().parse(arguments);
        } else if (ListClientCommand.isCommandWord(commandWord)) {
            return new ListClientCommand();
        } else if (ListProjectCommand.isCommandWord(commandWord)) {
            return new ListProjectCommand();
        } else if (ListTagCommand.isCommandWord(commandWord)) {
            return new ListTagCommand();
        } else if (SortClientCommand.isCommandWord(commandWord)) {
            return new SortClientCommand();
        } else if (SortProjectCommand.isCommandWord(commandWord)) {
            return new SortProjectCommandParser().parse(arguments);
        } else if (ExitCommand.isCommandWord(commandWord)) {
            return new ExitCommand();
        } else if (HelpCommand.isCommandWord(commandWord)) {
            return new HelpCommand();
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
