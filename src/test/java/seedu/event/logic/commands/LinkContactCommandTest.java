package seedu.event.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.event.logic.commands.CommandTestUtil.VALID_PHONE_ALICE;
import static seedu.event.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.event.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.event.testutil.Assert.assertThrows;
import static seedu.event.testutil.TypicalContacts.getTypicalContactList;
import static seedu.event.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.event.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.event.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import seedu.event.commons.core.Messages;
import seedu.event.commons.core.index.Index;
import seedu.event.logic.commands.exceptions.CommandException;
import seedu.event.model.Model;
import seedu.event.model.ModelManager;
import seedu.event.model.UserPrefs;
import seedu.event.model.contact.Contact;
import seedu.event.model.contact.ContactPhone;
import seedu.event.model.event.Event;
import seedu.event.testutil.EventBuilder;

public class LinkContactCommandTest {

    private static final ContactPhone VALID_CONTACTPHONE = new ContactPhone(VALID_PHONE_ALICE);
    private static final ContactPhone WRONG_NUMBER_CONTACTPHONE = new ContactPhone(VALID_PHONE_BOB);
    private static final ContactPhone NOT_FOUND_CONTACTPHONE = new ContactPhone("99999999");

    private Model model = new ModelManager(getTypicalEventBook(), getTypicalContactList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalEventBook(), getTypicalContactList(), new UserPrefs());


    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(expectedModel.getFilteredEventList().size() + 1);
        LinkContactCommand linkContactCommand = new LinkContactCommand(
                outOfBoundIndex, VALID_CONTACTPHONE);

        assertCommandFailure(linkContactCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_contactNotFound_throwsCommandException() {
        Event eventToLinkContact = new EventBuilder().build();
        model.addEvent(eventToLinkContact);
        LinkContactCommand linkContactCommand = new LinkContactCommand(INDEX_FIRST_EVENT,
                NOT_FOUND_CONTACTPHONE); // phone number that does not exist in contact list
        assertThrows(CommandException.class, () -> linkContactCommand.execute(model));
    }

    @Test
    public void execute_validContact() {
        Event event = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        Contact contact = model.getContactList().getContactList().get(0);
        Event linkedEvent = new EventBuilder(event).build();
        linkedEvent.linkContact(contact);
        LinkContactCommand linkContactCommand = new LinkContactCommand(INDEX_FIRST_EVENT, VALID_CONTACTPHONE);
        Event linkEvent = linkContactCommand.createLinkedEvent(event, contact);

        assertTrue(linkEvent.equals(linkedEvent));
    }

    @Test
    public void execute_markValidContact() {
        Event event = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        Contact contact = model.getContactList().getContactList().get(0);
        Event markEvent = new EventBuilder(event).build();
        markEvent.mark();
        model.markEvent(event);
        markEvent.linkContact(contact);
        LinkContactCommand linkContactCommand = new LinkContactCommand(INDEX_FIRST_EVENT, VALID_CONTACTPHONE);
        Event linkEvent = linkContactCommand.createLinkedEvent(markEvent, contact);

        assertTrue(linkEvent.equals(markEvent));
    }

    @Test
    public void execute_unmarkValidContact() {
        Event event = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        Contact contact = model.getContactList().getContactList().get(0);
        Event unmarkEvent = new EventBuilder(event).build();
        unmarkEvent.mark();
        unmarkEvent.unmark();
        model.markEvent(event);
        model.unmarkEvent(event);
        unmarkEvent.linkContact(contact);
        LinkContactCommand linkContactCommand = new LinkContactCommand(INDEX_FIRST_EVENT, VALID_CONTACTPHONE);
        Event linkEvent = linkContactCommand.createLinkedEvent(unmarkEvent, contact);

        assertTrue(linkEvent.equals(unmarkEvent));
    }

    @Test
    public void equals() {
        final LinkContactCommand standardCommand = new LinkContactCommand(INDEX_FIRST_EVENT, VALID_CONTACTPHONE);

        // same values -> returns true
        LinkContactCommand commandWithSameValues = new LinkContactCommand(INDEX_FIRST_EVENT, VALID_CONTACTPHONE);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new LinkContactCommand(INDEX_SECOND_EVENT, VALID_CONTACTPHONE)));

        // different phone number -> returns false
        assertFalse(standardCommand.equals(new LinkContactCommand(INDEX_SECOND_EVENT, WRONG_NUMBER_CONTACTPHONE)));
    }


}
