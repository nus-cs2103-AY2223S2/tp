package seedu.vms.logic.parser.patient;

import static seedu.vms.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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
        try {
            Index index = ParserUtil.parseIndex(argsMap.getPreamble());
            return new DetailCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DetailCommand.MESSAGE_USAGE), pe);
        }
    }
}
