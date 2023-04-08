package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AutoM8CommandTestUtil.assertFailure;
//import static seedu.address.logic.commands.AutoM8CommandTestUtil.assertSuccess;
import static seedu.address.logic.commands.DeleteCustomerCommand.MESSAGE_DELETE_CUSTOMER_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.shop.exception.AppointmentNotFoundException;
import seedu.address.model.entity.shop.exception.CustomerNotFoundException;
import seedu.address.model.entity.shop.exception.PartNotFoundException;
import seedu.address.model.entity.shop.exception.ServiceNotFoundException;
import seedu.address.model.entity.shop.exception.TechnicianNotFoundException;
import seedu.address.model.entity.shop.exception.VehicleNotFoundException;
import seedu.address.testutil.TypicalShop;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class AutoM8DeleteCommandTest {

    /**
     * Attempt to delete based on non-existing values in a typical shop
     */
    @Test
    public void invalidValues_failure() {

        Model model = new TypicalShop().getTypicalModel();

        assertFailure(new DeleteCustomerCommand(-1), model, new CustomerNotFoundException(-1));
        assertFailure(new DeleteCustomerCommand(9), model, new CustomerNotFoundException(9));

        assertFailure(new DeleteTechnicianCommand(-1), model, new TechnicianNotFoundException(-1));
        assertFailure(new DeleteTechnicianCommand(9), model, new TechnicianNotFoundException(9));

        assertFailure(new DeleteAppointmentCommand(-1), model, new AppointmentNotFoundException(-1));
        assertFailure(new DeleteAppointmentCommand(9), model, new AppointmentNotFoundException(9));

        assertFailure(new DeleteVehicleCommand(-1), model, new VehicleNotFoundException(-1));
        assertFailure(new DeleteVehicleCommand(9), model, new VehicleNotFoundException(9));

        assertFailure(new DeletePartCommand("Woox Walk DCB"), model, new PartNotFoundException("Woox Walk DCB"));
    }

    @Test
    public void deleteCustomer_success() throws CommandException {
        /**
         * Attempt to delete customer and check for cascading.
         */

        Model model = new TypicalShop().getTypicalModel();

        try {
            int id = 1;
            Customer markForDeleteCustomer = AutoM8CommandTestUtil.getCustomer(id, model);
            DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(markForDeleteCustomer.getId());
            CommandResult result = deleteCustomerCommand.execute(model);

            assertEquals(result, new CommandResult(String.format(MESSAGE_DELETE_CUSTOMER_SUCCESS, id), Tab.CUSTOMERS));

            try {
                AutoM8CommandTestUtil.getCustomer(id, model);
                throw new AssertionError("Command reported customer delete success but customer exists!");
            } catch (CustomerNotFoundException e) {
                assertEquals(e.getMessage(), new CustomerNotFoundException(id).getMessage());
            }

            id = 2;
            try {
                AutoM8CommandTestUtil.getVehicle(id, model);
                throw new AssertionError("Delete Customer command not cascading to vehicle deletes!");
            } catch (VehicleNotFoundException e) {
                assertEquals(e.getMessage(), new VehicleNotFoundException(id).getMessage());
            }

            id = 3;

            try {
                AutoM8CommandTestUtil.getService(id, model);
                throw new AssertionError("Delete Customer command not cascading to service deletes!");
            } catch (ServiceNotFoundException e) {
                assertEquals(e.getMessage(), new ServiceNotFoundException(id).getMessage());
            }

            id = 4;
            try {
                AutoM8CommandTestUtil.getAppointment(id, model);
                throw new AssertionError("Delete Customer command not cascading to service deletes!");
            } catch (AppointmentNotFoundException e) {
                assertEquals(e.getMessage(), new AppointmentNotFoundException(id).getMessage());
            }

        } catch (CustomerNotFoundException | CommandException e) {
            throw new CommandException("JUnitTest: deleteCustomer_success - Failed. Message: " + e.getMessage());
        }
    }


}
