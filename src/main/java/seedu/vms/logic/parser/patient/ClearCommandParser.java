package seedu.vms.logic.parser.patient;

import seedu.vms.logic.commands.patient.ClearCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CliSyntax;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;

/**
 * Parser for {@link ClearCommand}.
 */
public class ClearCommandParser implements CommandParser {
    @Override
    public ClearCommand parse(ArgumentMultimap argsMap) throws ParseException {
        boolean isForce = argsMap.getValue(CliSyntax.PREFIX_FORCE)
                .map(input -> ParserUtil.parseBoolean(input))
                .orElse(false);
        return new ClearCommand(isForce);
    }
}
