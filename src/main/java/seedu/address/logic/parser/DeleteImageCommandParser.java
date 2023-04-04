package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteImageCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteImageCommand object
 */
public class DeleteImageCommandParser implements Parser<DeleteImageCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of
     * the DeleteImageCommand and returns a DeleteImageCommand object
     * for execution.
     *
     * @param args The string of arguments
     * @return The DeleteImageCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteImageCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteImageCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage());
        }
    }
}
