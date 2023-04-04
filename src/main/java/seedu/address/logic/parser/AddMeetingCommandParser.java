package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_START;

import java.util.stream.Stream;

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
        if (args.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_MISSING_ARGUMENTS, AddMeetingCommand.MESSAGE_USAGE)
            );
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
            PREFIX_MEETING_DESC, PREFIX_MEETING_START, PREFIX_MEETING_END);

        if (!arePrefixesPresent(argMultimap, PREFIX_MEETING_DESC, PREFIX_MEETING_START, PREFIX_MEETING_END)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddMeetingCommand.MESSAGE_USAGE), ive);
        }

        String description = argMultimap.getValue(PREFIX_MEETING_DESC).get();
        String start = argMultimap.getValue(PREFIX_MEETING_START).get();
        String end = argMultimap.getValue(PREFIX_MEETING_END).get();
        Meeting meeting = ParserUtil.parseMeeting(description, start, end);

        return new AddMeetingCommand(index, meeting);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
