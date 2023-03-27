package seedu.vms.logic.parser.vaccination;

import seedu.vms.logic.commands.vaccination.AddVaxTypeCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.GroupName;
import seedu.vms.model.vaccination.VaxTypeBuilder;


/**
 * Parser of vaccination type add command arguments.
 */
public class AddVaxTypeParser extends VaxTypeBuilderParser {
    public static final String COMMAND_WORD = "add";


    @Override
    public AddVaxTypeCommand parse(ArgumentMultimap argsMap) throws ParseException {
        GroupName name = ParserUtil.parseGroupName(argsMap.getPreamble());
        VaxTypeBuilder builder = parseBuilderNoRename(argsMap);
        return new AddVaxTypeCommand(name, builder);
    }
}
