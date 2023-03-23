package seedu.address.logic.parser;

import java.time.LocalDate;

import seedu.address.logic.commands.SearchAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SearchAppointmentCommand object
 */
public class SearchAppointmentCommandParser implements Parser<SearchAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchAppointmentCommand
     * and returns an SearchAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchAppointmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        LocalDate toSearch = ParserUtil.parseDate(trimmedArgs);
        return new SearchAppointmentCommand(toSearch);
    }
}
