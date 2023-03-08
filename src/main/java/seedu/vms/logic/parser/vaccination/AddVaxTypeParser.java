package seedu.vms.logic.parser.vaccination;

import seedu.vms.logic.commands.vaccination.AddVaxTypeCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.ArgumentTokenizer;
import seedu.vms.logic.parser.Parser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.vaccination.VaxName;
import seedu.vms.model.vaccination.VaxTypeBuilder;


/**
 * Parser of vaccination type add command arguments.
 */
public class AddVaxTypeParser implements Parser<AddVaxTypeCommand> {
    public static final String COMMAND_WORD = "add";


    @Override
    public AddVaxTypeCommand parse(String args) throws ParseException {
        ArgumentMultimap argsMap = ArgumentTokenizer.tokenize(args);

        VaxName name = ParserUtil.parseVaxName(argsMap.getPreamble());

        VaxTypeBuilder builder = VaxTypeBuilder.of(name);

        return new AddVaxTypeCommand(builder);
    }
}
