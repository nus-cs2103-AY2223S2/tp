package seedu.address.logic.parser.tank;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tank.TankDeleteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code TaskDeleteCommand} object.
 */
public class TankDeleteCommandParser implements Parser<TankDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code TaskDeleteCommand}
     * and returns a {@code TaskDeleteCommand} object for execution.
     * @throws ParseException If the user input does not conform with the expected format.
     */
    public TankDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new TankDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TankDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}

