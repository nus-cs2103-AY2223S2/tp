package seedu.vms.logic.parser.vaccination;

import seedu.vms.commons.core.Retriever;
import seedu.vms.logic.commands.vaccination.DetailVaxTypeCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.vaccination.VaxType;


/**
 * Parser for {@link DetailVaxTypeCommand}.
 */
public class DetailVaxTypeParser implements CommandParser {
    public static final String COMMAND_WORD = "detail";

    public static final String MESSAGE_USAGE = VaccinationParser.FEATURE_NAME + " " + COMMAND_WORD
            + ": Details the specified vaccination\n"
            + "Syntax: "
            + "vaccination detail VACCINATION\n"
            + "Example: "
            + "vaccination detail Dose 1 (Moderna)";


    @Override
    public DetailVaxTypeCommand parse(ArgumentMultimap argsMap) throws ParseException {
        Retriever<String, VaxType> retriever;
        try {
            retriever = ParserUtil.parseVaxRetriever(argsMap.getPreamble());
        } catch (ParseException parseEx) {
            throw new ParseException(String.format("VACCINATION: %s\n%s", parseEx.getMessage(), MESSAGE_USAGE));
        }
        return new DetailVaxTypeCommand(retriever);
    }
}
