package seedu.techtrack.logic.parser;

import static seedu.techtrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.techtrack.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.techtrack.logic.commands.AddCommand;
import seedu.techtrack.logic.commands.ClearCommand;
import seedu.techtrack.logic.commands.Command;
import seedu.techtrack.logic.commands.CompanyCommand;
import seedu.techtrack.logic.commands.DeadlineCommand;
import seedu.techtrack.logic.commands.DeleteCommand;
import seedu.techtrack.logic.commands.EditCommand;
import seedu.techtrack.logic.commands.ExitCommand;
import seedu.techtrack.logic.commands.HelpCommand;
import seedu.techtrack.logic.commands.ListCommand;
import seedu.techtrack.logic.commands.NameCommand;
import seedu.techtrack.logic.commands.SalaryCommand;
import seedu.techtrack.logic.commands.TagCommand;
import seedu.techtrack.logic.commands.ViewCommand;
import seedu.techtrack.logic.commands.exceptions.exceptions.ParseException;

/**
 * Parses user input.
 */
public class RoleBookParser {

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

        case NameCommand.COMMAND_WORD:
            return new NameCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommandParser().parse(arguments);

        case SalaryCommand.COMMAND_WORD:
            return new SalaryCommandParser().parse(arguments);

        case CompanyCommand.COMMAND_WORD:
            return new CompanyCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case TagCommand.COMMAND_WORD:
            return new TagCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
