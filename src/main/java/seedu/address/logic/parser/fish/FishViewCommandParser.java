package seedu.address.logic.parser.fish;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.fish.FishViewCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code TaskDeleteCommand} object.
 */
public class FishViewCommandParser implements Parser<FishViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code TaskDeleteCommand}
     * and returns a {@code TaskDeleteCommand} object for execution.
     * @throws ParseException If the user input does not conform with the expected format.
     */
    public FishViewCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new FishViewCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FishViewCommand.MESSAGE_USAGE), pe);
        }
    }

}
