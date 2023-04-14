package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.LoadCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code LoadCommand} object.
 */
public class LoadCommandParser implements Parser<LoadCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code LoadCommand}
     * and returns a {@code LoadCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public LoadCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index index;
        try {
            index = ParserUtil.parseIndex(args);
        } catch (IllegalValueException | IllegalArgumentException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LoadCommand.MESSAGE_USAGE), ive);
        }

        return new LoadCommand(index);
    }
}
