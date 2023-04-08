package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AutoM8CommandTestUtil.assertFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.shop.exception.AppointmentNotFoundException;
import seedu.address.model.entity.shop.exception.CustomerNotFoundException;
import seedu.address.model.entity.shop.exception.PartNotFoundException;
import seedu.address.model.entity.shop.exception.TechnicianNotFoundException;
import seedu.address.model.entity.shop.exception.VehicleNotFoundException;
import seedu.address.testutil.TypicalShop;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class AutoM8DeleteCommandTest {
    @Test
    public void invalidValues_failure() {
        /**
         * Attempt to delete based on non-existing values in a typical shop
         */

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
        System.out.println("deleteCustomer_success");

        Model model = new TypicalShop().getTypicalModel();

        Customer markForDeleteCustomer = model.getFilteredCustomerList().get(1);
        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(markForDeleteCustomer.getId());

        CommandResult result = null;

        try {
            result = deleteCustomerCommand.execute(model);
        } catch (CommandException ce) {
            throw new CommandException("JUnitTest: deleteCustomer_success - Failed. Message: " + ce.getMessage());
        }

//        System.out.println(result);

//        assertEquals(result, );

    }
}
