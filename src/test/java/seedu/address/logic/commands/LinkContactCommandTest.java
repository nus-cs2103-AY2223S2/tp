package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.getTypicalContactList;
import static seedu.address.testutil.TypicalEvents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;

public class LinkContactCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalContactList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalContactList(), new UserPrefs());


    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(expectedModel.getFilteredPersonList().size() + 1);
        LinkContactCommand linkContactCommand = new LinkContactCommand(outOfBoundIndex, "91234567");

        assertCommandFailure(linkContactCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_contactNotFound_throwsCommandException() {
        Event eventToLinkContact = new EventBuilder().build();
        model.addPerson(eventToLinkContact);
        LinkContactCommand linkContactCommand = new LinkContactCommand(INDEX_FIRST_PERSON,
                "99999999"); // phone number that does not exist in contact list
        assertThrows(CommandException.class, () -> linkContactCommand.execute(model));
    }

    @Test
    public void execute_invalidContact_throwsCommandException() throws Exception {
        LinkContactCommand linkContactCommand = new LinkContactCommand(Index.fromOneBased(1), "91234568");

        assertThrows(CommandException.class, () -> linkContactCommand.execute(model));
    }

    @Test
    public void execute_validContact() {
        Event event = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Contact contact = model.getContactList().getContactList().get(0);
        Event linkedEvent = new EventBuilder(event).build();
        linkedEvent.linkContact(contact);
        LinkContactCommand linkContactCommand = new LinkContactCommand(INDEX_FIRST_PERSON, "94351253");
        Event linkEvent = linkContactCommand.createLinkedEvent(event, contact);

        assertTrue(linkEvent.equals(linkedEvent));
    }

    @Test
    public void execute_markValidContact() {
        Event event = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Contact contact = model.getContactList().getContactList().get(0);
        Event markEvent = new EventBuilder(event).build();
        markEvent.mark();
        model.markEvent(event);
        markEvent.linkContact(contact);
        LinkContactCommand linkContactCommand = new LinkContactCommand(INDEX_FIRST_PERSON, "94351253");
        Event linkEvent = linkContactCommand.createLinkedEvent(markEvent, contact);

        assertTrue(linkEvent.equals(markEvent));
    }

    @Test
    public void execute_unmarkValidContact() {
        Event event = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Contact contact = model.getContactList().getContactList().get(0);
        Event unmarkEvent = new EventBuilder(event).build();
        unmarkEvent.mark();
        unmarkEvent.unmark();
        model.markEvent(event);
        model.unmarkEvent(event);
        unmarkEvent.linkContact(contact);
        LinkContactCommand linkContactCommand = new LinkContactCommand(INDEX_FIRST_PERSON, "94351253");
        Event linkEvent = linkContactCommand.createLinkedEvent(unmarkEvent, contact);

        assertTrue(linkEvent.equals(unmarkEvent));
    }

    @Test
    public void equals() {
        final LinkContactCommand standardCommand = new LinkContactCommand(INDEX_FIRST_PERSON, "91234567");

        // same values -> returns true
        LinkContactCommand commandWithSameValues = new LinkContactCommand(INDEX_FIRST_PERSON, "91234567");
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new LinkContactCommand(INDEX_SECOND_PERSON, "91234567")));

        // different phone number -> returns false
        assertFalse(standardCommand.equals(new LinkContactCommand(INDEX_SECOND_PERSON, "91234568")));
    }


}
