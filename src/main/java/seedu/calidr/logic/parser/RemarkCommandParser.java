package seedu.calidr.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.calidr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.calidr.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.calidr.commons.core.index.Index;
import seedu.calidr.commons.exceptions.IllegalValueException;
import seedu.calidr.logic.commands.RemarkCommand;
import seedu.calidr.logic.parser.exceptions.ParseException;
import seedu.calidr.model.person.Remark;

/**
 * Parses input arguments and creates a new RemarkCommand object
 */
public class RemarkCommandParser implements Parser<RemarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemarkCommand
     * and returns a RemarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemarkCommand.MESSAGE_USAGE), ive);
        }

        String remarkString = argMultimap.getValue(PREFIX_REMARK).orElse("");
        Remark remark = new Remark(remarkString);

        return new RemarkCommand(index, remark);
    }

}
