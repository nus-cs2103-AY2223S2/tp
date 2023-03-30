package seedu.address.logic.parser.tank.readings;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tank.readings.ReadingsDeleteLastCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ReadingDeleteLastCommand} object.
 */
public class ReadingDeleteLastCommandParser implements Parser<ReadingsDeleteLastCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ReadingsDeleteLastCommand}
     * and returns a {@code ReadingsDeleteLastCommand} object for execution.
     * @throws ParseException If the user input does not conform with the expected format.
     */
    public ReadingsDeleteLastCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ReadingsDeleteLastCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                            ReadingsDeleteLastCommand.MESSAGE_USAGE), pe);
        }
    }
}
