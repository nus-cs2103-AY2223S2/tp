package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args); //, PREFIX_REMARK);
        assert argMultimap.getPreamble() != null;
        String[] tokens = argMultimap.getPreamble().split(" ", 2);

        Index index;
        try {
            index = ParserUtil.parseIndex(tokens[0]); //argMultimap.getPreamble());
        } catch (IllegalValueException | IndexOutOfBoundsException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE), e);
        }

        Remark remark;
        try {
            remark = new Remark(tokens[1]);
        } catch (IndexOutOfBoundsException e) {
            remark = null;
        }
        ;
        /*.
        }getValue(PREFIX_REMARK).isPresent()
                ? new Remark(argMultimap.getValue(PREFIX_REMARK).get())
                : null;*/
        return new RemarkCommand(index, remark);
    }
}
