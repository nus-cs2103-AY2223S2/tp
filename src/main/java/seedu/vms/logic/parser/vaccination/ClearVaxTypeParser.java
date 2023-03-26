package seedu.vms.logic.parser.vaccination;

import seedu.vms.logic.commands.vaccination.ClearVaxTypeCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.exceptions.ParseException;


/**
 * Parser to for {@link ClearVaxTypeCommand}.
 */
public class ClearVaxTypeParser implements CommandParser {
    public static final String COMMAND_WORD = "clear";


    @Override
    public ClearVaxTypeCommand parse(ArgumentMultimap argsMap) throws ParseException {
        return new ClearVaxTypeCommand();
    }
}
