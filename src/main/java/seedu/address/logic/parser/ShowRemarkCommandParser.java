package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

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
        String[] splitArr = args.stripLeading().split("\\s+", 2);
        assert splitArr.length >= 1 : "'splitArr' should have at least 1 element";

        String indexStr = splitArr[0];
        Index index;

        if (splitArr.length > 1 || indexStr.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowRemarkCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(
                    MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, pe
            );
        }
        return new ShowRemarkCommand(index);
    }
}
