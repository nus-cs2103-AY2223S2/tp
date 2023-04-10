package seedu.address.logic.parser.fish;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.fish.FishDeleteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FishDeleteCommand object
 */
public class FishDeleteCommandParser implements Parser<FishDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FishDeleteCommand
     * and returns a FishDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FishDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new FishDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FishDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
