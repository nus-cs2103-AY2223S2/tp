package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
// import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Remark;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class RemarkCommandParser implements Parser<RemarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        assert !args.isBlank() : "'args' should not be blank";

        String[] splitArr = args.stripLeading().split("\\s+", 2);
        String indexStr = splitArr[0];
        Remark remark = splitArr.length != 2
            ? null
            : new Remark(splitArr[1]);

        Index index;
        try {
            index = ParserUtil.parseIndex(indexStr); //argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            if (ive.getMessage().equals(ParserUtil.MESSAGE_INVALID_INDEX)) {
                throw new ParseException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, ive);
            }
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE), ive);
        }

        return new RemarkCommand(index, remark);
    }
}
