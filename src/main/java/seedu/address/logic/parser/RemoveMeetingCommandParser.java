package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class RemoveMeetingCommandParser implements Parser<RemoveMeetingCommand> {
    public RemoveMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new RemoveMeetingCommand(index);
        } catch (ParseException parseErr) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMeetingCommand.MESSAGE_USAGE, parseErr)
            );
        }
    }
}
