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

    public static final String MESSAGE_USAGE = VaccinationParser.FEATURE_NAME + " " + COMMAND_WORD
            + ": Deletes the specified vaccination\n"
            + "Syntax: "
            + "vaccination delete VACCINATION [--force IS_FORCE]\n"
            + "Example: "
            + "vaccination delete INDEX::1";


    @Override
    public DeleteVaxTypeCommand parse(ArgumentMultimap argsMap) throws ParseException {
        Retriever<String, VaxType> retriever;
        try {
            retriever = ParserUtil.parseVaxRetriever(argsMap.getPreamble());
        } catch (ParseException parseEx) {
            throw new ParseException(String.format("VACCINATION: %s\n%s", parseEx.getMessage(), MESSAGE_USAGE));
        }
        boolean isForce = argsMap
                .getValue(CliSyntax.PREFIX_FORCE)
                .map(input -> ParserUtil.parseBoolean(input))
                .orElse(false);
        return new DeleteVaxTypeCommand(retriever, isForce);
    }
}
