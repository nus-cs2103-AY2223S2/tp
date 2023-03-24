package seedu.vms.logic.parser.vaccination;

import seedu.vms.logic.commands.vaccination.DetailVaxTypeCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.GroupName;

public class DetailVaxTypeParser implements CommandParser {
    public static final String COMMAND_WORD = "detail";

    private static final String FIELD_NAME_VAX_NAME = "Vaccination name";


    @Override
    public DetailVaxTypeCommand parse(ArgumentMultimap argsMap) throws ParseException {
        GroupName vaxName;
        try {
            vaxName = ParserUtil.parseGroupName(argsMap.getPreamble());
        } catch (ParseException parseEx) {
            throw new ParseException(String.format("%s: %s", FIELD_NAME_VAX_NAME, parseEx.getMessage()));
        }
        return new DetailVaxTypeCommand(vaxName);
    }
}
