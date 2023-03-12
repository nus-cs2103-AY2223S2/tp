package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.UniqueContactList;
import seedu.address.model.person.Event;

/**
 * Links a contact to an event
 */
public class LinkContactCommand extends Command {

    public static final String COMMAND_WORD = "linkcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Links event identified by the index number to the"
            + " contact specified. "
            + "Parameters: "
            + "INDEX PHONE (index must be positive integer and contact must be in contact list.)\n"
            + "Example: " + COMMAND_WORD + " 2" + "91234567";

    public static final String MESSAGE_LINK_CONTACT_SUCCESS = "Successfully linked! %1$s";

    private final Index eventIndex;
    private final String addContact;

    /**
     * Creates a LinkContactCommand to link the specified {@code Contact} to event
     * specified by the {@code Index}.
     */
    public LinkContactCommand(Index index, String contact) {
        requireNonNull(index);
        requireNonNull(contact);
        eventIndex = index;
        addContact = contact;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredPersonList();
        List<Contact> contactList = model.getContactList().getContactList();

        if (eventIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event toAdd = lastShownList.get(eventIndex.getZeroBased());

        try {
            Contact contactToAdd = contactList.get(UniqueContactList.getNumberMap().get(addContact));
            model.linkContact(toAdd, contactToAdd);
        } catch (NullPointerException iobe) {
            throw new CommandException(Messages.MESSAGE_CONTACT_NOT_FOUND);
        }

        return new CommandResult(String.format(MESSAGE_LINK_CONTACT_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof LinkContactCommand
                && eventIndex.equals(((LinkContactCommand) other).eventIndex)
                && addContact.equals(((LinkContactCommand) other).addContact));
    }
}
