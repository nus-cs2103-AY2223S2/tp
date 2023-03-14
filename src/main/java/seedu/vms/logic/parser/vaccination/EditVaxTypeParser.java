package seedu.vms.logic.parser.vaccination;

import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.vaccination.EditVaxTypeCommand;
import seedu.vms.model.vaccination.VaxTypeBuilder;


/**
 * Parser of vaccination type edit command.
 */
public class EditVaxTypeParser extends VaxTypeValueParser {
    public static final String COMMAND_WORD = "edit";


    /**
     * Constructs an {@code EditVaxTypeParser}.
     */
    public EditVaxTypeParser() {
        super(true);
    }


    @Override
    protected Command getCommand(VaxTypeBuilder builder) {
        return new EditVaxTypeCommand(builder);
    }
}
