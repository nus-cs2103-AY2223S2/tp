package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Meeting;

/**
 * Parse input arguments and create a new AddMeetingCommand object
 */
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddMeetingCommand
     *
     * @param args String of arguments to be parsed
     * @return AddMeetingCommand object
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
            PREFIX_MEETING);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddMeetingCommand.MESSAGE_USAGE), ive);
        }

        Meeting meeting = ParserUtil.parseMeeting(argMultimap.getValue(PREFIX_MEETING).orElse(""));

        return new AddMeetingCommand(index, meeting);
    }
}
