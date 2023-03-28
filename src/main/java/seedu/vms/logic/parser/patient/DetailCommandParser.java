package seedu.vms.logic.parser.patient;

import seedu.vms.commons.core.index.Index;
import seedu.vms.logic.commands.patient.DetailCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;

/**
 * Parser for {@link DetailCommand}.
 */
public class DetailCommandParser implements CommandParser {
    @Override
    public DetailCommand parse(ArgumentMultimap argsMap) throws ParseException {
        Index index = ParserUtil.parseIndex(argsMap.getPreamble());
        return new DetailCommand(index);
    }
}
