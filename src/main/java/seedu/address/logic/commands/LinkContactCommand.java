package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.UniqueContactList;
import seedu.address.model.person.Address;
import seedu.address.model.person.Event;
import seedu.address.model.person.Mark;
import seedu.address.model.person.Name;
import seedu.address.model.person.Rate;
import seedu.address.model.person.Time;
import seedu.address.model.tag.Tag;

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
            Event eventToLink = createLinkedEvent(toAdd, contactToAdd);
            model.linkContact(toAdd, eventToLink);
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
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

    /**
     * Creates and returns an {@code Event} with details of
     * newly linked contact.
     */
    public Event createLinkedEvent(Event eventToEdit, Contact toAdd) {
        Name name = eventToEdit.getName();
        Rate rate = eventToEdit.getRate();
        Address address = eventToEdit.getAddress();
        Time startTime = eventToEdit.getStartTime();
        Time endTime = eventToEdit.getEndTime();
        Mark mark = eventToEdit.getMark();
        Set<Tag> tags = eventToEdit.getTags();

        Event updatedEvent = new Event(
                name, rate, address, startTime, endTime, tags);
        if (mark.isDone()) {
            updatedEvent.mark();
        }
        updatedEvent.linkContact(toAdd);
        return updatedEvent;
    }
}
