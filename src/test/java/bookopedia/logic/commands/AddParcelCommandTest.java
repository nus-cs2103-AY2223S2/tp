package bookopedia.logic.commands;

import static bookopedia.logic.commands.AddParcelCommand.MESSAGE_DONE_STATUS_ADD_PC;
import static bookopedia.logic.commands.AddParcelCommand.MESSAGE_RETURN_STATUS_ADD_PC;
import static bookopedia.logic.commands.CommandTestUtil.VALID_PARCEL_LAZADA;
import static bookopedia.logic.commands.CommandTestUtil.VALID_PARCEL_SHOPEE;
import static bookopedia.logic.commands.CommandTestUtil.assertCommandFailure;
import static bookopedia.logic.commands.CommandTestUtil.assertCommandSuccess;
import static bookopedia.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static bookopedia.testutil.TypicalPersons.ALICE;
import static bookopedia.testutil.TypicalPersons.AMY;
import static bookopedia.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import bookopedia.commons.core.Messages;
import bookopedia.commons.core.index.Index;
import bookopedia.model.AddressBook;
import bookopedia.model.DeliveryStatus;
import bookopedia.model.Model;
import bookopedia.model.ModelManager;
import bookopedia.model.UserPrefs;
import bookopedia.model.parcel.Parcel;
import bookopedia.model.person.Person;
import bookopedia.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddParcelCommand.
 */
public class AddParcelCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addParcel_success() {
        Person personToAddParcel = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Parcel newParcel = new Parcel(VALID_PARCEL_LAZADA);
        AddParcelCommand addParcelCommand = new AddParcelCommand(INDEX_FIRST_PERSON, newParcel);

        String expectedMessage = String.format(AddParcelCommand.MESSAGE_SUCCESS, newParcel,
                personToAddParcel.getName());

        final Set<Parcel> updatedParcels = new HashSet<>();
        for (Parcel parcel : personToAddParcel.getParcels()) {
            updatedParcels.add(parcel);
        }
        updatedParcels.add(newParcel);

        Person updatedPersonToAddParcel = new Person(personToAddParcel.getName(), personToAddParcel.getPhone(),
                personToAddParcel.getEmail(), personToAddParcel.getAddress(), updatedParcels,
                personToAddParcel.getDeliveryStatus(), personToAddParcel.getNoOfDeliveryAttempts());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(
                INDEX_FIRST_PERSON.getZeroBased()), updatedPersonToAddParcel);

        assertCommandSuccess(addParcelCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Parcel newParcel = new Parcel(VALID_PARCEL_LAZADA);
        AddParcelCommand addParcelCommand = new AddParcelCommand(outOfBoundIndex, newParcel);

        assertCommandFailure(addParcelCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_sameParcel_throwsCommandException() {
        Parcel existingParcel = new Parcel(VALID_PARCEL_SHOPEE);
        AddParcelCommand addParcelCommand = new AddParcelCommand(INDEX_FIRST_PERSON, existingParcel);

        assertCommandFailure(addParcelCommand, model, String.format(Messages.MESSAGE_EXISTING_PARCEL, existingParcel));
    }

    @Test
    public void execute_addParcelOnDoneDelivery_failure() {
        Person personDone = new PersonBuilder(AMY).withDeliveryStatus(DeliveryStatus.DONE)
                .withParcels("Parcel1").build();
        model.addPerson(personDone);
        Index personToMarkIndex = Index.fromOneBased(model.getFilteredPersonList().size());

        AddParcelCommand addParcelCommand = new AddParcelCommand(personToMarkIndex, new Parcel("Parcel2"));

        assertCommandFailure(addParcelCommand, model,
                String.format(MESSAGE_DONE_STATUS_ADD_PC, ALICE.getName()));
    }

    @Test
    public void execute_addParcelOnReturnDelivery_failure() {
        Person personDone = new PersonBuilder(AMY).withDeliveryStatus(DeliveryStatus.RETURN)
                .withParcels("Parcel1").build();
        model.addPerson(personDone);
        Index personToMarkIndex = Index.fromOneBased(model.getFilteredPersonList().size());

        AddParcelCommand addParcelCommand = new AddParcelCommand(personToMarkIndex, new Parcel("Parcel2"));

        assertCommandFailure(addParcelCommand, model,
                String.format(MESSAGE_RETURN_STATUS_ADD_PC, ALICE.getName()));
    }
}
