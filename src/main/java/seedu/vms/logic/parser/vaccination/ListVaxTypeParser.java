package seedu.vms.logic.parser.vaccination;

import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.vaccination.ListVaxTypeCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.exceptions.ParseException;


/**
 * Parser of vaccination list command.
 */
public class ListVaxTypeParser implements CommandParser {
    public static final String COMMAND_WORD = "list";


    @Override
    public Command parse(ArgumentMultimap args) throws ParseException {
        return new ListVaxTypeCommand();
    }
}
