package arb.logic.parser;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Set;
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

        if (isCommandWord(AddClientCommand.getCommandWords(), commandWord)) {
            return new AddClientCommandParser().parse(arguments);
        } else if (isCommandWord(AddProjectCommand.getCommandWords(), commandWord)) {
            return new AddProjectCommandParser().parse(arguments);
        } else if (isCommandWord(MarkProjectCommand.getCommandWords(), commandWord)) {
            return new MarkProjectCommandParser().parse(arguments);
        } else if (isCommandWord(UnmarkProjectCommand.getCommandWords(), commandWord)) {
            return new UnmarkProjectCommandParser().parse(arguments);
        } else if (isCommandWord(EditClientCommand.getCommandWords(), commandWord)) {
            return new EditClientCommandParser().parse(arguments);
        } else if (isCommandWord(EditProjectCommand.getCommandWords(), commandWord)) {
            return new EditProjectCommandParser().parse(arguments);
        } else if (isCommandWord(DeleteClientCommand.getCommandWords(), commandWord)) {
            return new DeleteClientCommandParser().parse(arguments);
        } else if (isCommandWord(DeleteProjectCommand.getCommandWords(), commandWord)) {
            return new DeleteProjectCommandParser().parse(arguments);
        } else if (isCommandWord(ClearClientCommand.getCommandWords(), commandWord)) {
            return new ClearClientCommand();
        } else if (isCommandWord(ClearProjectCommand.getCommandWords(), commandWord)) {
            return new ClearProjectCommand();
        } else if (isCommandWord(FindClientCommand.getCommandWords(), commandWord)) {
            return new FindClientCommandParser().parse(arguments);
        } else if (isCommandWord(FindProjectCommand.getCommandWords(), commandWord)) {
            return new FindProjectCommandParser().parse(arguments);
        } else if (isCommandWord(ListClientCommand.getCommandWords(), commandWord)) {
            return new ListClientCommand();
        } else if (isCommandWord(ListProjectCommand.getCommandWords(), commandWord)) {
            return new ListProjectCommand();
        } else if (isCommandWord(ListTagCommand.getCommandWords(), commandWord)) {
            return new ListTagCommand();
        } else if (isCommandWord(SortClientCommand.getCommandWords(), commandWord)) {
            return new SortClientCommand();
        } else if (isCommandWord(SortProjectCommand.getCommandWords(), commandWord)) {
            return new SortProjectCommandParser().parse(arguments);
        } else if (isCommandWord(ExitCommand.getCommandWords(), commandWord)) {
            return new ExitCommand();
        } else if (isCommandWord(HelpCommand.getCommandWords(), commandWord)) {
            return new HelpCommand();
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    public static boolean isCommandWord(Set<String> commandWords, String commandWord) {
        return commandWords.contains(commandWord);
    }
}
