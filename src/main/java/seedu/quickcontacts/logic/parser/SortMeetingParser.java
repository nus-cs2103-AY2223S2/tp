package seedu.quickcontacts.logic.parser;

import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.quickcontacts.logic.commands.AutocompleteResult;
import seedu.quickcontacts.logic.commands.SortMeetingCommand;
import seedu.quickcontacts.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddMeetingCommand object.
 */
public class SortMeetingParser implements Parser<SortMeetingCommand> {
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesValidUnique(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        if (argumentMultimap.getLength() != 2) {
            return false;
        }
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    @Override
    public AutocompleteResult getAutocompleteSuggestion(String args) {
        return new AutocompleteResult();
    }

    @Override
    public SortMeetingCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(userInput, PREFIX_MEETING_TITLE, PREFIX_DATETIME,
                PREFIX_LOCATION, PREFIX_DESCRIPTION);
        if (!arePrefixesValidUnique(argMultimap, PREFIX_MEETING_TITLE, PREFIX_DATETIME, PREFIX_LOCATION,
            PREFIX_DESCRIPTION)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortMeetingCommand.MESSAGE_USAGE));
        }
        Prefix prefix = argMultimap.getFirstKey();
        Optional<String> param = argMultimap.getValue(prefix);
        System.out.println(param.get());
        if (param.get().equals("r")) {
            return new SortMeetingCommand(prefix, true);
        } else if (param.get().equals("")) {
            return new SortMeetingCommand(prefix, false);
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortMeetingCommand.MESSAGE_USAGE));
    }
}
