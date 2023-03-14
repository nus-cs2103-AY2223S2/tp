package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;

/**
 * Creates a new contact in the contact list.
 */
public class NewContactCommand extends Command {

    public static final String COMMAND_WORD = "newcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the contact list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Deborah Tan "
            + PREFIX_PHONE + "91234567";

    public static final String MESSAGE_SUCCESS = "New contact added: %1$s";

    public static final String MESSAGE_DUPLICATE_CONTACT = "This contact already exists";

    private final Contact newContact;

    /**
     * Creates an NewContactCommand to add the specified {@code Contact}
     */
    public NewContactCommand(Contact contact) {
        requireNonNull(contact);
        newContact = contact;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasContact(newContact)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
        }

        model.addContact(newContact);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newContact));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NewContactCommand // instanceof handles nulls
                && newContact.equals(((NewContactCommand) other).newContact));
    }
}
