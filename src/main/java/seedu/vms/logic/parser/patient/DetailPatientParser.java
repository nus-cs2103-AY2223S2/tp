package seedu.vms.logic.parser.patient;

import seedu.vms.commons.core.index.Index;
import seedu.vms.logic.commands.patient.DetailPatientCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;


/**
 * Parser for {@link DetailPatientCommand}.
 */
public class DetailPatientParser implements CommandParser {
    public static final String COMMAND_WORD = "detail";


    @Override
    public DetailPatientCommand parse(ArgumentMultimap argsMap) throws ParseException {
        Index index = ParserUtil.parseIndex(argsMap.getPreamble());
        return new DetailPatientCommand(index);
    }
}
