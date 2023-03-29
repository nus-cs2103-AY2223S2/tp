package seedu.vms.logic.parser.vaccination;

import seedu.vms.commons.core.Retriever;
import seedu.vms.logic.commands.vaccination.EditVaxTypeCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CliSyntax;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.model.vaccination.VaxTypeBuilder;


/**
 * Parser of vaccination type edit command.
 */
public class EditVaxTypeParser extends VaxTypeBuilderParser {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = VaccinationParser.FEATURE_NAME + " " + COMMAND_WORD
            + ": Updates a vaccination in the system\n"
            + "Syntax: "
            + "vaccination edit VACCINATION [--n VAX_NAME] [--g ...GROUP...] [--lal MIN_AGE] [--ual MAX_AGE] "
            + "[--i ...INGREDIENT...]... [--h HISTORY_REQ]... [--set IS_SET]\n"
            + "Example: "
            + "vaccination edit INDEX::1 --lal 5 --ual 25 --i NaOH --set true";


    @Override
    public EditVaxTypeCommand parse(ArgumentMultimap argsMap) throws ParseException {
        try {
            Retriever<String, VaxType> retriever;
            try {
                retriever = ParserUtil.parseVaxRetriever(argsMap.getPreamble());
            } catch (ParseException parseEx) {
                throw new ParseException(String.format("VACCINATION: %s", parseEx.getMessage()));
            }
            VaxTypeBuilder builder = parseBuilder(argsMap);
            boolean isSet = argsMap.getValue(CliSyntax.PREFIX_SET)
                    .map(input -> ParserUtil.parseBoolean(input))
                    .orElse(false);
            return new EditVaxTypeCommand(retriever, builder, isSet);
        } catch (ParseException parseEx) {
            throw new ParseException(String.format("%s\n%s", parseEx.getMessage(), MESSAGE_USAGE));
        }
    }
}
