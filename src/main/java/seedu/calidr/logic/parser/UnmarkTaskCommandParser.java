package seedu.calidr.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.calidr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.calidr.commons.core.index.Index;
import seedu.calidr.logic.commands.UnmarkTaskCommand;
import seedu.calidr.logic.parser.exceptions.ParseException;

public class UnmarkTaskCommandParser implements Parser<UnmarkTaskCommand> {
    public UnmarkTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkTaskCommand.MESSAGE_USAGE), pe);
        }

        return new UnmarkTaskCommand(index);
    }
}
