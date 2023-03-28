package seedu.vms.logic.parser.vaccination;

import seedu.vms.commons.core.Retriever;
import seedu.vms.logic.commands.vaccination.DeleteVaxTypeCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CliSyntax;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.vaccination.VaxType;


/**
 * Parser of vaccination type delete command.
 */
public class DeleteVaxTypeParser implements CommandParser {
    public static final String COMMAND_WORD = "delete";

    private static final String FIELD_NAME_VAX_NAME = "Vaccination name";


    @Override
    public DeleteVaxTypeCommand parse(ArgumentMultimap argsMap) throws ParseException {
        Retriever<String, VaxType> retriever;
        try {
            retriever = ParserUtil.parseVaxRetriever(argsMap.getPreamble());
        } catch (ParseException parseEx) {
            throw new ParseException(String.format("%s: %s", FIELD_NAME_VAX_NAME, parseEx.getMessage()));
        }
        boolean isForce = argsMap
                .getValue(CliSyntax.PREFIX_FORCE)
                .map(input -> ParserUtil.parseBoolean(input))
                .orElse(false);
        return new DeleteVaxTypeCommand(retriever, isForce);
    }
}
