package seedu.quickcontacts.logic.parser;

import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_PERSON;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.quickcontacts.logic.commands.AddMeetingCommand;
import seedu.quickcontacts.logic.commands.AutocompleteResult;
import seedu.quickcontacts.logic.parser.exceptions.ParseException;
import seedu.quickcontacts.model.meeting.DateTime;
import seedu.quickcontacts.model.meeting.Description;
import seedu.quickcontacts.model.meeting.Location;
import seedu.quickcontacts.model.meeting.Title;
import seedu.quickcontacts.model.person.Name;

/**
 * Parses input arguments and creates a new AddMeetingCommand object.
 */
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {
    public static final Prefix[] PREFIXES = {PREFIX_MEETING_TITLE, PREFIX_DATETIME, PREFIX_PERSON,
        PREFIX_LOCATION, PREFIX_DESCRIPTION};

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    @Override
    public AddMeetingCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(userInput, PREFIXES);

        if (!arePrefixesPresent(argMultimap, PREFIX_MEETING_TITLE, PREFIX_DATETIME)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }
        Title title = null;
        if (argMultimap.getValue(PREFIX_MEETING_TITLE).isPresent()) {
            title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_MEETING_TITLE).get());
        }
        assert title != null;
        DateTime dateTime = null;
        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            dateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get());
        }
        assert dateTime != null;
        Set<Name> attendeeNames = new HashSet<>();
        Location location = null;
        Description description = null;
        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            dateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get());
        }
        if (argMultimap.getValue(PREFIX_PERSON).isPresent()) {
            attendeeNames = ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_PERSON));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        }

        return new AddMeetingCommand(title, dateTime, attendeeNames, location, description);
    }

    @Override
    public AutocompleteResult getAutocompleteSuggestion(String args) {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIXES);

        for (Prefix p : PREFIXES) {
            if (argMultimap.getValue(p).isEmpty()) {
                return new AutocompleteResult(p, false);
            }
        }

        return new AutocompleteResult();
    }
}
