package seedu.vms.logic.parser.vaccination;

import seedu.vms.logic.commands.vaccination.DeleteVaxTypeCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.ArgumentTokenizer;
import seedu.vms.logic.parser.Parser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.GroupName;


/**
 * Parser of vaccination type delete command.
 */
public class DeleteVaxTypeParser implements Parser<DeleteVaxTypeCommand> {
    public static final String COMMAND_WORD = "delete";

    private static final String FIELD_NAME_VAX_NAME = "Vaccination name";


    @Override
    public DeleteVaxTypeCommand parse(String args) throws ParseException {
        ArgumentMultimap argsMap = ArgumentTokenizer.tokenize(args);

        GroupName vaxName;
        try {
            vaxName = ParserUtil.parseGroupName(argsMap.getPreamble());
        } catch (ParseException parseEx) {
            throw new ParseException(String.format("%s: %s", FIELD_NAME_VAX_NAME, parseEx.getMessage()));
        }
        return new DeleteVaxTypeCommand(vaxName);
    }
}
