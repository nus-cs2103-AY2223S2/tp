package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.SampleDataUtil;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteAutoM8CommandTest {

    private final Model model = new ModelManager(SampleDataUtil.getSampleAddressBook(), new UserPrefs(),
            SampleDataUtil.getSampleShop());

    @Test
    public void execute_invalidValue_throwException() {
        assertCommandFailure(new DeleteCustomerCommand(-1), model,
                DeleteCustomerCommand.MESSAGE_CUSTOMER_NOT_FOUND);

        assertCommandFailure(new DeleteCustomerCommand(Integer.MAX_VALUE), model,
                DeleteCustomerCommand.MESSAGE_CUSTOMER_NOT_FOUND);

        assertCommandFailure(new DeleteTechnicianCommand(-1), model,
                DeleteTechnicianCommand.MESSAGE_TECHNICIAN_NOT_FOUND);

        assertCommandFailure(new DeleteTechnicianCommand(Integer.MAX_VALUE), model,
                DeleteTechnicianCommand.MESSAGE_TECHNICIAN_NOT_FOUND);

        assertCommandFailure(new DeleteVehicleCommand(-1), model,
                DeleteVehicleCommand.MESSAGE_VEHICLE_NOT_FOUND);

        assertCommandFailure(new DeleteVehicleCommand(Integer.MAX_VALUE), model,
                DeleteVehicleCommand.MESSAGE_VEHICLE_NOT_FOUND);

        assertCommandFailure(new DeleteServiceCommand(-1), model,
                DeleteServiceCommand.MESSAGE_SERVICE_NOT_FOUND);

        assertCommandFailure(new DeleteServiceCommand(Integer.MAX_VALUE), model,
                DeleteServiceCommand.MESSAGE_SERVICE_NOT_FOUND);

        assertCommandFailure(new DeleteAppointmentCommand(-1), model,
                DeleteAppointmentCommand.MESSAGE_APPOINTMENT_NOT_FOUND);

        assertCommandFailure(new DeleteAppointmentCommand(Integer.MAX_VALUE), model,
                DeleteAppointmentCommand.MESSAGE_APPOINTMENT_NOT_FOUND);

        assertCommandFailure(new DeletePartCommand("WooxWalk Crossbow"), model,
                Messages.MESSAGE_INVAID_PART_REQUESTED);

    }

    // SAMPLE
    //    @Test
    //    public void equals() {
    //        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
    //        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);
    //
    //        // same object -> returns true
    //        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));
    //
    //        // same values -> returns true
    //        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
    //        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));
    //
    //        // different types -> returns false
    //        assertFalse(deleteFirstCommand.equals(1));
    //
    //        // null -> returns false
    //        assertFalse(deleteFirstCommand.equals(null));
    //
    //        // different person -> returns false
    //        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    //    }
    //
    //    /**
    //     * Updates {@code model}'s filtered list to show no one.
    //     */
    //    private void showNoPerson(Model model) {
    //        model.updateFilteredPersonList(p -> false);
    //
    //        assertTrue(model.getFilteredPersonList().isEmpty());
    //    }
}
