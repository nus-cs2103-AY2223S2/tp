package seedu.event.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.event.commons.core.Messages;
import seedu.event.commons.core.index.Index;
import seedu.event.logic.commands.exceptions.CommandException;
import seedu.event.model.Model;
import seedu.event.model.contact.Contact;
import seedu.event.model.contact.ContactPhone;
import seedu.event.model.event.Address;
import seedu.event.model.event.Event;
import seedu.event.model.event.Mark;
import seedu.event.model.event.Name;
import seedu.event.model.event.Rate;
import seedu.event.model.event.Time;
import seedu.event.model.tag.Tag;

/**
 * Links a contact to an event
 */
public class LinkContactCommand extends Command {

    public static final String COMMAND_WORD = "linkcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Links event identified by the index number to the"
            + " contact specified. \n"
            + "Parameters: "
            + "INDEX PHONE (index must be positive integer, below 1,000,000 and contact must be in contact list.)\n"
            + "Example: " + COMMAND_WORD + " 2" + " 91234567";

    public static final String MESSAGE_LINK_CONTACT_SUCCESS = "Successfully linked! %1$s";

    private final Index eventIndex;
    private final ContactPhone addContact;

    /**
     * Creates a LinkContactCommand to link the specified {@code Contact} to event
     * specified by the {@code Index}.
     */
    public LinkContactCommand(Index index, ContactPhone contact) {
        requireNonNull(index);
        requireNonNull(contact);
        eventIndex = index;
        addContact = contact;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();
        List<Contact> contactList = model.getFilteredContactList();

        if (eventIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event toAdd = lastShownList.get(eventIndex.getZeroBased());

        try {
            Contact contactToAdd = getMatchingContact(addContact, contactList);
            Event eventToLink = createLinkedEvent(toAdd, contactToAdd);
            model.linkContact(toAdd, eventToLink);
            model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);
        } catch (NullPointerException iobe) {
            throw new CommandException(Messages.MESSAGE_CONTACT_NOT_FOUND);
        }

        return new CommandResult(String.format(MESSAGE_LINK_CONTACT_SUCCESS, toAdd));
    }

    private Contact getMatchingContact(ContactPhone addContact, List<Contact> contactList) {
        return contactList.stream()
                .filter(contact -> contact.isSameContactPhone(addContact))
                .findFirst()
                .orElse(null);
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
