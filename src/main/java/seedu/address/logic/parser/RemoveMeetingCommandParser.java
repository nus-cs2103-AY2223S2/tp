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
            String[] indexes = args.trim().split(" ");
            Index indexPerson = ParserUtil.parseIndex(indexes[0]);
            Index indexMeeting = ParserUtil.parseIndex(indexes[1]);
            return new RemoveMeetingCommand(indexPerson, indexMeeting);
        } catch (ParseException parseErr) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMeetingCommand.MESSAGE_USAGE, parseErr)
            );
        }
    }
}
