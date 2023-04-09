package bookopedia.logic.commands;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_PARCEL_DISPLAYED_INDEX;
import static bookopedia.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static bookopedia.commons.core.Messages.MESSAGE_NO_PARCELS;
import static bookopedia.logic.commands.CommandTestUtil.assertCommandFailure;
import static bookopedia.logic.commands.CommandTestUtil.assertCommandSuccess;
import static bookopedia.logic.commands.MarkParcelCommand.MESSAGE_DONE_STATUS_MARK_PC;
import static bookopedia.logic.commands.MarkParcelCommand.MESSAGE_RETURN_STATUS_MARK_PC;
import static bookopedia.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static bookopedia.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static bookopedia.testutil.TypicalPersons.ALICE;
import static bookopedia.testutil.TypicalPersons.AMY;
import static bookopedia.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bookopedia.commons.core.index.Index;
import bookopedia.model.AddressBook;
import bookopedia.model.DeliveryStatus;
import bookopedia.model.Model;
import bookopedia.model.ModelManager;
import bookopedia.model.ParcelStatus;
import bookopedia.model.UserPrefs;
import bookopedia.model.person.Person;
import bookopedia.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkParcelCommand.
 */
public class MarkParcelCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_markFragile_success() {
        ParcelStatus parcelStatus = ParcelStatus.FRAGILE;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        MarkParcelCommand markParcelCommand = new MarkParcelCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_PERSON, parcelStatus);
        String expectedMessage = String.format(MarkParcelCommand.MESSAGE_SUCCESS,
                personToMark.getName(), "[shopee]" , parcelStatus);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(
                INDEX_FIRST_PERSON.getZeroBased()), personToMark);

        assertCommandSuccess(markParcelCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markBulky_success() {
        ParcelStatus parcelStatus = ParcelStatus.BULKY;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        MarkParcelCommand markParcelCommand = new MarkParcelCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_PERSON, parcelStatus);
        String expectedMessage = String.format(MarkParcelCommand.MESSAGE_SUCCESS,
                personToMark.getName(), "[shopee]" , parcelStatus);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(
                INDEX_FIRST_PERSON.getZeroBased()), personToMark);

        assertCommandSuccess(markParcelCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        MarkParcelCommand markParcelCommand = new MarkParcelCommand(outOfBoundIndex,
                outOfBoundIndex, ParcelStatus.FRAGILE);

        assertCommandFailure(markParcelCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidParcelIndexUnfilteredList_failure() {
        MarkParcelCommand markParcelCommand = new MarkParcelCommand(INDEX_FIRST_PERSON,
                Index.fromOneBased(100), ParcelStatus.FRAGILE);

        assertCommandFailure(markParcelCommand, model, String.format(MESSAGE_INVALID_PARCEL_DISPLAYED_INDEX, 1));
    }

    @Test
    public void execute_noParcels_failure() {
        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark).withParcels().build();
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(
                INDEX_FIRST_PERSON.getZeroBased()), updatedPersonToMark);

        MarkParcelCommand markParcelCommand = new MarkParcelCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON,
                ParcelStatus.FRAGILE);

        assertCommandFailure(markParcelCommand, expectedModel, String.format(MESSAGE_NO_PARCELS, ALICE.getName()));
    }

    @Test
    public void execute_markParcelOnDoneDelivery_failure() {
        Person personDone = new PersonBuilder(AMY).withDeliveryStatus(DeliveryStatus.DONE)
                .withParcels("Parcel1").build();
        model.addPerson(personDone);
        Index personToMarkIndex = Index.fromOneBased(model.getFilteredPersonList().size());

        MarkParcelCommand markParcelCommand = new MarkParcelCommand(personToMarkIndex, Index.fromOneBased(1),
                ParcelStatus.FRAGILE);

        assertCommandFailure(markParcelCommand, model,
                String.format(MESSAGE_DONE_STATUS_MARK_PC, ALICE.getName()));
    }

    @Test
    public void execute_markParcelOnReturnDelivery_failure() {
        Person personDone = new PersonBuilder(AMY).withDeliveryStatus(DeliveryStatus.RETURN)
                .withParcels("Parcel1").build();
        model.addPerson(personDone);
        Index personToMarkIndex = Index.fromOneBased(model.getFilteredPersonList().size());

        MarkParcelCommand markParcelCommand = new MarkParcelCommand(personToMarkIndex, Index.fromOneBased(1),
                ParcelStatus.FRAGILE);

        assertCommandFailure(markParcelCommand, model,
                String.format(MESSAGE_RETURN_STATUS_MARK_PC, ALICE.getName()));
    }

    @Test
    public void equals() {
        MarkParcelCommand addFirstFirstFragileCommand = new MarkParcelCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_PERSON, ParcelStatus.FRAGILE);
        MarkParcelCommand addSecondFirstFragileCommand = new MarkParcelCommand(INDEX_SECOND_PERSON,
                INDEX_FIRST_PERSON, ParcelStatus.FRAGILE);

        // same object -> returns true
        assertTrue(addFirstFirstFragileCommand.equals(addFirstFirstFragileCommand));

        // same values -> returns true
        MarkParcelCommand addFirstFirstFragileCommandCopy = new MarkParcelCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_PERSON, ParcelStatus.FRAGILE);
        assertTrue(addFirstFirstFragileCommand.equals(addFirstFirstFragileCommandCopy));

        // different types -> returns false
        assertFalse(addFirstFirstFragileCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstFirstFragileCommand.equals(null));

        // different person -> returns false
        assertFalse(addFirstFirstFragileCommand.equals(addSecondFirstFragileCommand));
    }

}
