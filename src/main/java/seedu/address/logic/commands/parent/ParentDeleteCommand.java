package seedu.address.logic.commands.parent;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMAGEPARENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENTAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONEPARENT;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Phone;
import seedu.address.model.person.parent.Parent;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class ParentDeleteCommand extends ParentCommand {

    public static final String COMMAND_WORD = "delete";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the parent identified by their phone number used in the displayed person list.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONEPARENT + "PHONE "
            + "["
            + PREFIX_PARENTAGE + "AGE "
            + PREFIX_IMAGEPARENT + "IMAGE PARENT "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "]\n"
            + "Example: \n" + "parent " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tan Ah Niu "
            + PREFIX_PHONEPARENT + "91234567 "
            + PREFIX_PARENTAGE + "30 "
            + PREFIX_IMAGEPARENT + "C:// "
            + PREFIX_EMAIL + "tanahcow@gmail.com "
            + PREFIX_ADDRESS + "Blk 456 Ang Mo Kio Avenue 6 #11-800 S(560456)";

    public static final String MESSAGE_DELETE_PARENT_SUCCESS = "Deleted Parent: %1$s";

    private final Phone phoneNumber;

    /**
     * Creates a ParentDeleteCommand to delete the specified {@code Parent}
     */
    public ParentDeleteCommand(Phone phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Parent> parents = model.getFilteredParentList();
        for (Parent parent : parents) {
            if (parent.getPhone().equals(phoneNumber)) {
                model.deleteParent(parent);
                return new CommandResult(String.format(MESSAGE_DELETE_PARENT_SUCCESS, parent));
            }
        }

        throw new CommandException(Messages.MESSAGE_INVALID_PARENT_DISPLAYED_NUMBER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParentDeleteCommand // instanceof handles nulls
                && phoneNumber.equals(((ParentDeleteCommand) other).phoneNumber)); // state check
    }
}
