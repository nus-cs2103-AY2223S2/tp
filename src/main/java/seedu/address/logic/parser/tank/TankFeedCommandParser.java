package seedu.address.logic.parser.tank;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tank.TankFeedCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code TankFeedCommand} object.
 */
public class TankFeedCommandParser implements Parser<TankFeedCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code TankFeedCommand}
     * and returns a {@code TankFeedCommand} object for execution.
     * @throws ParseException If the user input does not conform with the expected format.
     */
    public TankFeedCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new TankFeedCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TankFeedCommand.MESSAGE_USAGE), pe);
        }
    }

}


