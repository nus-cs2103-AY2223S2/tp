package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.commands.AutocompleteResult;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.Title;
import seedu.address.model.person.Name;

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

        if (!arePrefixesPresent(argMultimap, PREFIX_MEETING_TITLE, PREFIX_DATETIME, PREFIX_PERSON, PREFIX_LOCATION,
            PREFIX_DESCRIPTION)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_MEETING_TITLE).get());
        Set<Name> attendeeNames = ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_PERSON));
        DateTime dateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get());
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

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

        return new AutocompleteResult(null, false);
    }
}
