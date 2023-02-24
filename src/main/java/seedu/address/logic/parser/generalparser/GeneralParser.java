package seedu.address.logic.parser.generalparser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.generalcommand.ExitCommand;
import seedu.address.logic.commands.generalcommand.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input
 */
public class GeneralParser {

    public static final String OPERATION_TYPE = "g";

    /**
     * Parses user input into command for execution.
     * @param commandWord user command
     * @return the command based on the command word
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String commandWord) throws ParseException {
        switch (commandWord) {
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
