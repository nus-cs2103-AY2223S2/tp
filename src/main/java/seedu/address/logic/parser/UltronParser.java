package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommandNew;
import seedu.address.logic.commands.ClearCommandNew;
import seedu.address.logic.commands.CommandNew;
import seedu.address.logic.commands.DeleteCommandNew;
import seedu.address.logic.commands.EditCommandNew;
import seedu.address.logic.commands.ExitCommandNew;
import seedu.address.logic.commands.FindCommandNew;
import seedu.address.logic.commands.HelpCommandNew;
import seedu.address.logic.commands.ListCommandNew;
import seedu.address.logic.commands.RemarkCommandNew;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class UltronParser {

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
    public CommandNew parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommandNew.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommandNew.COMMAND_WORD:
            return new AddCommandParserNew().parse(arguments);

        case EditCommandNew.COMMAND_WORD:
            return new EditCommandParserNew().parse(arguments);

        case DeleteCommandNew.COMMAND_WORD:
            return new DeleteCommandParserNew().parse(arguments);

        case ClearCommandNew.COMMAND_WORD:
            return new ClearCommandNew();

        case FindCommandNew.COMMAND_WORD:
            return new FindCommandParserNew().parse(arguments);

        case ListCommandNew.COMMAND_WORD:
            return new ListCommandNew();

        case ExitCommandNew.COMMAND_WORD:
            return new ExitCommandNew();

        case HelpCommandNew.COMMAND_WORD:
            return new HelpCommandNew();

        case RemarkCommandNew.COMMAND_WORD:
            return new RemarkCommandParserNew().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
