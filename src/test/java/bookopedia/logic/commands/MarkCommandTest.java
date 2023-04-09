package bookopedia.logic.commands;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static bookopedia.logic.commands.CommandTestUtil.assertCommandFailure;
import static bookopedia.logic.commands.CommandTestUtil.assertCommandSuccess;
import static bookopedia.logic.commands.MarkCommand.MESSAGE_DONE_STATUS_CHANGED;
import static bookopedia.logic.commands.MarkCommand.MESSAGE_RETURN_STATUS_CHANGED;
import static bookopedia.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static bookopedia.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bookopedia.commons.core.index.Index;
import bookopedia.model.AddressBook;
import bookopedia.model.DeliveryStatus;
import bookopedia.model.Model;
import bookopedia.model.ModelManager;
import bookopedia.model.UserPrefs;
import bookopedia.model.person.Person;
import bookopedia.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkCommand.
 */
public class MarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_markOtw_success() {
        DeliveryStatus deliveryStatus = DeliveryStatus.OTW;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark).withDeliveryStatus(deliveryStatus).build();

        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);
        String expectedMessage = String.format(MarkCommand.MESSAGE_SUCCESS, personToMark.getName(), deliveryStatus);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(
                INDEX_FIRST_PERSON.getZeroBased()), updatedPersonToMark);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markFailed_success() {
        DeliveryStatus deliveryStatus = DeliveryStatus.FAILED;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark)
                .withDeliveryStatus(deliveryStatus)
                .withNoOfDeliveryAttempts(1)
                .build();

        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);
        String expectedMessage = String.format(MarkCommand.MESSAGE_SUCCESS, personToMark.getName(), deliveryStatus);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(
                INDEX_FIRST_PERSON.getZeroBased()), updatedPersonToMark);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markPending_success() {
        DeliveryStatus deliveryStatus = DeliveryStatus.PENDING;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark).withDeliveryStatus(deliveryStatus).build();

        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);
        String expectedMessage = String.format(MarkCommand.MESSAGE_SUCCESS, personToMark.getName(), deliveryStatus);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(
                INDEX_FIRST_PERSON.getZeroBased()), updatedPersonToMark);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markDone_success() {
        DeliveryStatus deliveryStatus = DeliveryStatus.DONE;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark).withDeliveryStatus(deliveryStatus).build();

        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);
        String expectedMessage = String.format(MarkCommand.MESSAGE_SUCCESS, personToMark.getName(), deliveryStatus);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(
                INDEX_FIRST_PERSON.getZeroBased()), updatedPersonToMark);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markReturn_success() {
        DeliveryStatus deliveryStatus = DeliveryStatus.RETURN;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person newPersonToMark = new PersonBuilder(personToMark)
                .withDeliveryStatus(DeliveryStatus.OTW)
                .build();
        Person updatedPersonToMark = new PersonBuilder(personToMark).withDeliveryStatus(deliveryStatus).build();

        model.setPerson(personToMark, newPersonToMark);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);
        String expectedMessage = String.format(MarkCommand.MESSAGE_SUCCESS, personToMark.getName(), deliveryStatus);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(
                INDEX_FIRST_PERSON.getZeroBased()), updatedPersonToMark);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markFailedHitNoOfAttemptBeforeReturn_changeStatusToReturn() {
        DeliveryStatus deliveryStatus = DeliveryStatus.FAILED;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person newPersonToMark = new PersonBuilder(personToMark)
                .withDeliveryStatus(DeliveryStatus.OTW)
                .withNoOfDeliveryAttempts(DeliveryStatus.NO_OF_ATTEMPTS_BEFORE_RETURN - 1)
                .build();
        Person updatedPersonToMark = new PersonBuilder(personToMark)
                .withDeliveryStatus(DeliveryStatus.RETURN)
                .withNoOfDeliveryAttempts(DeliveryStatus.NO_OF_ATTEMPTS_BEFORE_RETURN)
                .build();

        model.setPerson(personToMark, newPersonToMark);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);
        String expectedMessage = String.format(MarkCommand.MESSAGE_SUCCESS, personToMark.getName(),
                DeliveryStatus.FAILED);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(
                INDEX_FIRST_PERSON.getZeroBased()), updatedPersonToMark);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markOtwOnDoneDelivery_failed() {
        DeliveryStatus deliveryStatus = DeliveryStatus.OTW;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark)
                .withDeliveryStatus(DeliveryStatus.DONE)
                .build();

        model.setPerson(personToMark, updatedPersonToMark);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);

        assertCommandFailure(markCommand, model, MESSAGE_DONE_STATUS_CHANGED);
    }

    @Test
    public void execute_markDoneOnDoneDelivery_failed() {
        DeliveryStatus deliveryStatus = DeliveryStatus.DONE;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark)
                .withDeliveryStatus(DeliveryStatus.DONE)
                .build();

        model.setPerson(personToMark, updatedPersonToMark);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);

        assertCommandFailure(markCommand, model, MESSAGE_DONE_STATUS_CHANGED);
    }

    @Test
    public void execute_markFailedOnDoneDelivery_failed() {
        DeliveryStatus deliveryStatus = DeliveryStatus.FAILED;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark)
                .withDeliveryStatus(DeliveryStatus.DONE)
                .build();

        model.setPerson(personToMark, updatedPersonToMark);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);

        assertCommandFailure(markCommand, model, MESSAGE_DONE_STATUS_CHANGED);
    }

    @Test
    public void execute_markPendingOnDoneDelivery_failed() {
        DeliveryStatus deliveryStatus = DeliveryStatus.OTW;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark)
                .withDeliveryStatus(DeliveryStatus.DONE)
                .build();

        model.setPerson(personToMark, updatedPersonToMark);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);

        assertCommandFailure(markCommand, model, MESSAGE_DONE_STATUS_CHANGED);
    }

    @Test
    public void execute_markReturnOnDoneDelivery_failed() {
        DeliveryStatus deliveryStatus = DeliveryStatus.RETURN;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark)
                .withDeliveryStatus(DeliveryStatus.DONE)
                .build();

        model.setPerson(personToMark, updatedPersonToMark);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);

        assertCommandFailure(markCommand, model, MESSAGE_DONE_STATUS_CHANGED);
    }

    @Test
    public void execute_markOtwOnReturnedDelivery_failed() {
        DeliveryStatus deliveryStatus = DeliveryStatus.OTW;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark)
                .withDeliveryStatus(DeliveryStatus.RETURN)
                .withNoOfDeliveryAttempts(DeliveryStatus.NO_OF_ATTEMPTS_BEFORE_RETURN)
                .build();

        model.setPerson(personToMark, updatedPersonToMark);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);

        assertCommandFailure(markCommand, model, MESSAGE_RETURN_STATUS_CHANGED);
    }

    @Test
    public void execute_markDoneOnReturnedDelivery_failed() {
        DeliveryStatus deliveryStatus = DeliveryStatus.DONE;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark)
                .withDeliveryStatus(DeliveryStatus.RETURN)
                .withNoOfDeliveryAttempts(DeliveryStatus.NO_OF_ATTEMPTS_BEFORE_RETURN)
                .build();

        model.setPerson(personToMark, updatedPersonToMark);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);

        assertCommandFailure(markCommand, model, MESSAGE_RETURN_STATUS_CHANGED);
    }

    @Test
    public void execute_markFailedOnReturnedDelivery_failed() {
        DeliveryStatus deliveryStatus = DeliveryStatus.FAILED;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark)
                .withDeliveryStatus(DeliveryStatus.RETURN)
                .withNoOfDeliveryAttempts(DeliveryStatus.NO_OF_ATTEMPTS_BEFORE_RETURN)
                .build();

        model.setPerson(personToMark, updatedPersonToMark);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);

        assertCommandFailure(markCommand, model, MESSAGE_RETURN_STATUS_CHANGED);
    }

    @Test
    public void execute_markPendingOnReturnedDelivery_failed() {
        DeliveryStatus deliveryStatus = DeliveryStatus.OTW;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark)
                .withDeliveryStatus(DeliveryStatus.RETURN)
                .withNoOfDeliveryAttempts(DeliveryStatus.NO_OF_ATTEMPTS_BEFORE_RETURN)
                .build();

        model.setPerson(personToMark, updatedPersonToMark);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);

        assertCommandFailure(markCommand, model, MESSAGE_RETURN_STATUS_CHANGED);
    }

    @Test
    public void execute_markReturnOnReturnedDelivery_failed() {
        DeliveryStatus deliveryStatus = DeliveryStatus.RETURN;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark)
                .withDeliveryStatus(DeliveryStatus.RETURN)
                .withNoOfDeliveryAttempts(DeliveryStatus.NO_OF_ATTEMPTS_BEFORE_RETURN)
                .build();

        model.setPerson(personToMark, updatedPersonToMark);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);

        assertCommandFailure(markCommand, model, MESSAGE_RETURN_STATUS_CHANGED);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        MarkCommand markCommand = new MarkCommand(outOfBoundIndex, DeliveryStatus.OTW);

        assertCommandFailure(markCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkCommand addFirstOtwCommand = new MarkCommand(INDEX_FIRST_PERSON, DeliveryStatus.OTW);
        MarkCommand addFirstFailCommand = new MarkCommand(INDEX_FIRST_PERSON, DeliveryStatus.FAILED);

        // same object -> returns true
        assertTrue(addFirstOtwCommand.equals(addFirstOtwCommand));

        // same values -> returns true
        MarkCommand addFirstOtwCommandCopy = new MarkCommand(INDEX_FIRST_PERSON, DeliveryStatus.OTW);
        assertTrue(addFirstOtwCommand.equals(addFirstOtwCommandCopy));

        // different types -> returns false
        assertFalse(addFirstFailCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstOtwCommand.equals(null));

        // different person -> returns false
        assertFalse(addFirstOtwCommand.equals(addFirstFailCommand));
    }
}
