package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ShowRemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class ShowRemarkCommandParser implements Parser<ShowRemarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowRemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (args.isBlank() || args.trim().contains(" ")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowRemarkCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(args);
        return new ShowRemarkCommand(index);
    }
}
