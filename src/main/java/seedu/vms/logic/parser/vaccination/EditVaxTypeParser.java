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


    @Override
    public EditVaxTypeCommand parse(ArgumentMultimap argsMap) throws ParseException {
        Retriever<String, VaxType> retriever = ParserUtil.parseVaxRetriever(argsMap.getPreamble());
        VaxTypeBuilder builder = parseBuilder(argsMap);
        boolean isSet = argsMap.getValue(CliSyntax.PREFIX_SET)
                .map(input -> ParserUtil.parseBoolean(input))
                .orElse(false);
        return new EditVaxTypeCommand(retriever, builder, isSet);
    }
}
