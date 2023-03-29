package seedu.vms.logic.parser.patient;

import seedu.vms.logic.commands.patient.ListCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.exceptions.ParseException;

/**
 * Parser for {@link ListCommand}.
 */
public class ListCommandParser implements CommandParser {
    @Override
    public ListCommand parse(ArgumentMultimap argsMap) throws ParseException {
        return new ListCommand();
    }
}
