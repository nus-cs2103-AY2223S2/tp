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

    public static final String MESSAGE_USAGE = VaccinationParser.FEATURE_NAME + " " + COMMAND_WORD
            + ": Adds a vaccination to the system\n"
            + "Syntax: "
            + "vaccination add VAX_NAME [--g ...GROUP...] [--lal MIN_AGE] [--ual MAX_AGE] "
            + "[--i ...INGREDIENT...]... [--h HISTORY_REQ]...\n"
            + "Example: "
            + "vaccination add ABC VAX --g ABC, VACCINATION --lal 5 --ual 50 --i ALC-0315, ALC-0159 --h NONE::ABC";


    @Override
    public AddVaxTypeCommand parse(ArgumentMultimap argsMap) throws ParseException {
        try {
            GroupName name;
            try {
                name = ParserUtil.parseGroupName(argsMap.getPreamble());
            } catch (ParseException parseEx) {
                throw new ParseException(String.format("VAX_NAME: %s", parseEx.getMessage()));
            }
            VaxTypeBuilder builder = parseBuilderNoRename(argsMap);
            return new AddVaxTypeCommand(name, builder);
        } catch (ParseException parseEx) {
            throw new ParseException(String.format("%s\n%s", parseEx.getMessage(), MESSAGE_USAGE));
        }
    }
}
