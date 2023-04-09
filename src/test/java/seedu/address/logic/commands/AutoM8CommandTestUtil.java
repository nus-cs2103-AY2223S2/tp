package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeFormatter;

import org.opentest4j.AssertionFailedError;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.shop.exception.AppointmentNotFoundException;
import seedu.address.model.entity.shop.exception.CustomerNotFoundException;
import seedu.address.model.entity.shop.exception.EmptyInputException;
import seedu.address.model.entity.shop.exception.PartNotFoundException;
import seedu.address.model.entity.shop.exception.ServiceNotFoundException;
import seedu.address.model.entity.shop.exception.TechnicianNotFoundException;
import seedu.address.model.entity.shop.exception.VehicleNotFoundException;
import seedu.address.model.service.Service;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.appointment.Appointment;

/**
 * Contains helper methods for testing commands.
 */
public class AutoM8CommandTestUtil {

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

    public static void assertFailure(Command command, Model model, Exception exception) {
        assertFailure(command, model, exception.getMessage());
    }

    /**
     * Given command, execute it with model. To catch Command Exeception and return if the same msg.
     * Does not test if the nature of the program is correct or not.
     *
     * @param command The command to be executed
     * @param model The model to reference
     * @param msg Expected CommandResult
     */

    public static void assertFailure(Command command, Model model, String msg) {
        CommandResult result = null;

        try {
            result = command.execute(model);
            throw new AssertionFailedError("Assert Failure returns non-failure condition!");
        } catch (CommandException ce) {
            String errMsg = ce.getMessage();

            assertTrue(errMsg != null && msg != null
                    && errMsg.hashCode() == msg.hashCode()
                    && errMsg.equals(msg));
        }
    }

    public static void assertSuccess(Command command, Model model, Exception exception) {
        assertSuccess(command, model, exception.getMessage());
    }

    /**
     * Given command, execute it with model. Expect to be a successful execution and return if the same msg.
     * Does not test if the nature of the program is correct or not.
     *
     * @param command The command to be executed
     * @param model The model to reference
     * @param msg Expected CommandResult
     */
    public static void assertSuccess(Command command, Model model, String msg) {
        CommandResult result = null;
        try {
            result = command.execute(model);
            String posMsg = result.getFeedbackToUser();
            assertTrue(posMsg != null && msg != null
                    && posMsg.hashCode() == msg.hashCode()
                    && posMsg.equals(msg));

        } catch (CommandException ce) {
            throw new AssertionFailedError("Assert Success returns failure condition!");
        }
    }

    /**
     * @param id ID of the customer.
     * @return Customer with specified id.
     * @throws CustomerNotFoundException if the customer is not found.
     */
    public static Customer getCustomer(int id, Model model) throws CustomerNotFoundException {
        return model.getShop().getCustomerList().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    /**
     * @param id ID of the vehicle.
     * @return Vehicle with specified id.
     * @throws VehicleNotFoundException if the vehicle is not found.
     */
    public static Vehicle getVehicle(int id, Model model) throws VehicleNotFoundException {
        return model.getShop().getVehicleList().stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElseThrow(() -> new VehicleNotFoundException(id));
    }

    /**
     * @param id ID of the service.
     * @return Service with specified id.
     * @throws ServiceNotFoundException if the service is not found.
     */
    public static Service getService(int id, Model model) throws ServiceNotFoundException {
        return model.getShop().getServiceList().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ServiceNotFoundException(id));
    }

    /**
     * @param id ID of the technician.
     * @return Technician with specified id.
     * @throws TechnicianNotFoundException if the technician is not found.
     */
    public static Technician getTechnician(int id, Model model) throws TechnicianNotFoundException {
        return model.getShop().getTechnicianList().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TechnicianNotFoundException(id));
    }

    /**
     * @param id ID of the appointment.
     * @return Appointment with specified id.
     * @throws AppointmentNotFoundException if the appointment is not found.
     */
    public static Appointment getAppointment(int id, Model model) throws AppointmentNotFoundException {
        return model.getShop().getAppointmentList().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new AppointmentNotFoundException(id));
    }

    /**
     * @param partName Name of the part.
     * @return Quantity of the part.
     * @throws PartNotFoundException if the part is not found.
     */
    public static int getPartQty(String partName, Model model) throws EmptyInputException, PartNotFoundException {
        return model.getShop().getPartQty(partName);
    }

    public static DateTimeFormatter getDtf() {
        return dtf;
    }
}
