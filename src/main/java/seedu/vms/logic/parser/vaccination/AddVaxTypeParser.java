package seedu.vms.logic.parser.vaccination;

import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.vaccination.AddVaxTypeCommand;
import seedu.vms.model.vaccination.VaxTypeBuilder;


/**
 * Parser of vaccination type add command arguments.
 */
public class AddVaxTypeParser extends VaxTypeValueParser {
    public static final String COMMAND_WORD = "add";


    /**
     * Constructs an {@code AddVaxTypeParser}.
     */
    public AddVaxTypeParser() {
        super(false);
    }


    @Override
    protected Command getCommand(VaxTypeBuilder builder) {
        return new AddVaxTypeCommand(builder);
    }
}
