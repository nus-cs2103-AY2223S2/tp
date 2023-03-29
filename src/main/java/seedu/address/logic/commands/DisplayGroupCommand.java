package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;




/**
 * Lists all groups in the address book to the user.
 */
public class DisplayGroupCommand extends Command {
    public static final String COMMAND_WORD = "display";

    public static final String MESSAGE_SUCCESS = "Listed all groups";

    public static final String MESSAGE_TEMPORARY_OUTPUT = "Here are all existing groups: ";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String results = model.getAddressBook().getTagList().toString();
        return new CommandResult(MESSAGE_TEMPORARY_OUTPUT + results);
    }
}
