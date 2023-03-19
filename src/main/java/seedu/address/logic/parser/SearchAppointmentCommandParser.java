package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.SearchAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class SearchAppointmentCommandParser implements Parser<SearchAppointmentCommand>{

    public SearchAppointmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        return new SearchAppointmentCommand();
    }
}
