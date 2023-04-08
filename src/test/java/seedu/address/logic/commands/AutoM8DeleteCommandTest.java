package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.AutoM8CommandTestUtil.assertFailure;
import static seedu.address.logic.commands.DeleteAppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS;
import static seedu.address.logic.commands.DeleteCustomerCommand.MESSAGE_DELETE_CUSTOMER_SUCCESS;
import static seedu.address.logic.commands.DeletePartCommand.MESSAGE_DELETE_PART_SUCCESS;
import static seedu.address.logic.commands.DeleteServiceCommand.MESSAGE_DELETE_SERVICE_SUCCESS;
import static seedu.address.logic.commands.DeleteTechnicianCommand.MESSAGE_DELETE_TECHNICIAN_SUCCESS;
import static seedu.address.logic.commands.DeleteVehicleCommand.MESSAGE_DELETE_VEHICLE_SUCCESS;


import org.junit.jupiter.api.Test;

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

    /**
     * Delete existing Customer and check cascade
     */
    @Test
    public void deleteCustomer_success() throws CommandException {
        /**
         *  Attempt to run command normally.
         *  Check if cascades.
         */

        Model model = new TypicalShop().getTypicalModel();

        try {
            int customerId = 1;
            Customer markForDelete = AutoM8CommandTestUtil.getCustomer(customerId, model);
            DeleteCustomerCommand deleteXCommand = new DeleteCustomerCommand(markForDelete.getId());
            CommandResult result = deleteXCommand.execute(model);

            assertEquals(result, new CommandResult(String.format(MESSAGE_DELETE_CUSTOMER_SUCCESS, customerId),
                    Tab.CUSTOMERS));

            try {
                AutoM8CommandTestUtil.getCustomer(customerId, model);
                throw new AssertionError("Command reported customer delete success but customer exists!");
            } catch (CustomerNotFoundException e) {
                assertEquals(e.getMessage(), new CustomerNotFoundException(customerId).getMessage());
            }

            int vehicleId = 2;

            try {
                AutoM8CommandTestUtil.getVehicle(vehicleId, model);
                throw new AssertionError("Delete Customer command not cascading to vehicle deletes!");
            } catch (VehicleNotFoundException e) {
                assertEquals(e.getMessage(), new VehicleNotFoundException(vehicleId).getMessage());
            }

            int serviceId = 3;

            try {
                AutoM8CommandTestUtil.getService(serviceId, model);
                throw new AssertionError("Delete Customer command not cascading to service deletes!");
            } catch (ServiceNotFoundException e) {
                assertEquals(e.getMessage(), new ServiceNotFoundException(serviceId).getMessage());
            }

            int appointmentId = 4;
            try {
                AutoM8CommandTestUtil.getAppointment(appointmentId, model);
                throw new AssertionError("Delete Customer command not cascading to appointment deletes!");
            } catch (AppointmentNotFoundException e) {
                assertEquals(e.getMessage(), new AppointmentNotFoundException(appointmentId).getMessage());
            }

        } catch (CustomerNotFoundException | CommandException e) {
            throw new CommandException("JUnitTest: deleteCustomer_success - Failed. Message: " + e.getMessage());
        }
    }

    /**
     * Delete existing Technician and check cascade
     */
    @Test
    public void deleteTechnician_success() throws CommandException {

        Model model = new TypicalShop().getTypicalModel();

        try {
            int technicianId = 1;
            Technician markForDelete = AutoM8CommandTestUtil.getTechnician(technicianId, model);
            DeleteTechnicianCommand deleteXCommand = new DeleteTechnicianCommand(markForDelete.getId());
            CommandResult result = deleteXCommand.execute(model);

            assertEquals(result, new CommandResult(String.format(MESSAGE_DELETE_TECHNICIAN_SUCCESS, technicianId),
                    Tab.TECHNICIANS));

            try {
                AutoM8CommandTestUtil.getTechnician(technicianId, model);
                throw new AssertionError("Command reported technician delete success but Technician exists!");
            } catch (TechnicianNotFoundException e) {
                assertEquals(e.getMessage(), new TechnicianNotFoundException(technicianId).getMessage());
            }

            int serviceId = 5;

            try {
                Service service = AutoM8CommandTestUtil.getService(serviceId, model);

                if (service.getAssignedToIds().contains(serviceId)) {
                    throw new AssertionError("Delete Technician command not cascading to service!");
                }
            } catch (ServiceNotFoundException e) {
                assertEquals(e.getMessage(), new ServiceNotFoundException(serviceId).getMessage());
            }

            int appointmentId = 4;
            try {
                Appointment appointment = AutoM8CommandTestUtil.getAppointment(appointmentId, model);
                if (appointment.getStaffIds().contains(appointmentId)) {
                    throw new AssertionError("Delete Technician command not cascading to appointment!");
                }
            } catch (AppointmentNotFoundException e) {
                assertEquals(e.getMessage(), new AppointmentNotFoundException(appointmentId).getMessage());
            }

        } catch (TechnicianNotFoundException | CommandException e) {
            throw new CommandException("JUnitTest: deleteTechnician_success - Failed. Message: " + e.getMessage());
        }
    }

    /**
     * Delete existing Appointment and check cascade
     */
    @Test
    public void deleteAppointment_success() throws CommandException {

        Model model = new TypicalShop().getTypicalModel();

        try {
            int appointmentId = 3;
            Appointment markForDelete = AutoM8CommandTestUtil.getAppointment(appointmentId, model);
            DeleteAppointmentCommand deleteXCommand = new DeleteAppointmentCommand(markForDelete.getId());
            CommandResult result = deleteXCommand.execute(model);

            assertEquals(result, new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS, appointmentId),
                    Tab.APPOINTMENTS));

            try {
                AutoM8CommandTestUtil.getAppointment(appointmentId, model);
                throw new AssertionError("Command reported appointment delete success " +
                        "but Appointment exists!");
            } catch (AppointmentNotFoundException e) {
                assertEquals(e.getMessage(), new AppointmentNotFoundException(appointmentId).getMessage());
            }

            int customerId = 1;

            try {
                Customer customer = AutoM8CommandTestUtil.getCustomer(customerId, model);
                if (customer.getAppointmentIds().contains(appointmentId)) {
                    throw new AssertionError("Delete Appointment command not cascading to customer ");
                }
            } catch (CustomerNotFoundException e) {
                throw new AssertionError("JUnitTest: deleteAppointment_success - Failed. Cascading " +
                        "check on Customer failed." + e.getMessage());
            }

            // check if staff affected
            int staffId = 1;

            try {
                Technician technician = AutoM8CommandTestUtil.getTechnician(staffId, model);
                if (technician.getAppointmentIds().contains(appointmentId)) {
                    throw new AssertionError("Delete Appointment command not cascading to technician");
                }
            } catch (TechnicianNotFoundException e) {
                throw new AssertionError("JUnitTest: deleteAppointment_success - Failed. Cascading " +
                        "check on Technician failed." + e.getMessage());
            }

        } catch (AppointmentNotFoundException | CommandException e) {
            throw new CommandException("JUnitTest: deleteAppointment_success - Failed. Message: " + e.getMessage());
        }
    }

    @Test
    public void deleteVehicle_success() throws CommandException {
        Model model = new TypicalShop().getTypicalModel();

        try {
            int vehicle_id = 4;
            Vehicle markForDelete = AutoM8CommandTestUtil.getVehicle(vehicle_id, model);
            DeleteVehicleCommand deleteXCommand = new DeleteVehicleCommand(markForDelete.getId());
            CommandResult result = deleteXCommand.execute(model);

            assertEquals(result, new CommandResult(String.format(MESSAGE_DELETE_VEHICLE_SUCCESS, vehicle_id),
                    Tab.VEHICLES));

            try {
                AutoM8CommandTestUtil.getVehicle(vehicle_id, model);
                throw new AssertionError("Command reported vehicle delete success " +
                        "but Vehicle exists!");
            } catch (VehicleNotFoundException e) {
                assertEquals(e.getMessage(), new VehicleNotFoundException(vehicle_id).getMessage());
            }

            int customerId = 3;

            try {
                Customer customer = AutoM8CommandTestUtil.getCustomer(customerId, model);
                if (customer.getVehicleIds().contains(vehicle_id)) {
                    throw new AssertionError("Delete Vehicle command not cascading to customer ");
                }
            } catch (CustomerNotFoundException e) {
                throw new AssertionError("JUnitTest: deleteVehicle_success - Failed. Cascading " +
                        "check on Customer failed." + e.getMessage());
            }

            // check if service affected
            int serviceId = 1;

            try {
                Service service = AutoM8CommandTestUtil.getService(serviceId, model);
                if (service.getVehicleId() == vehicle_id) {
                    throw new AssertionError("Delete Vehicle command not cascading to service ");
                }
            } catch (ServiceNotFoundException e) {
                throw new AssertionError("JUnitTest: deleteVehicle_success - Failed. Cascading " +
                        "check on Technician failed." + e.getMessage());
            }

        } catch (VehicleNotFoundException | CommandException e) {
            throw new CommandException("JUnitTest: deleteVehicle_success - Failed. Message: " + e.getMessage());
        }
    }

    @Test
    public void deleteService_success() throws CommandException {
        Model model = new TypicalShop().getTypicalModel();

        try {
            int serviceId = 6;
            Service markForDelete = AutoM8CommandTestUtil.getService(serviceId, model);
            DeleteServiceCommand deleteXCommand = new DeleteServiceCommand(markForDelete.getId());
            CommandResult result = deleteXCommand.execute(model);

            assertEquals(result, new CommandResult(String.format(MESSAGE_DELETE_SERVICE_SUCCESS, serviceId),
                    Tab.SERVICES));

            try {
                AutoM8CommandTestUtil.getService(serviceId, model);
                throw new AssertionError("Command reported vehicle delete success " +
                        "but Vehicle exists!");
            } catch (ServiceNotFoundException e) {
                assertEquals(e.getMessage(), new ServiceNotFoundException(serviceId).getMessage());
            }

            int vehicleId = 6;

            try {
                Vehicle vehicle = AutoM8CommandTestUtil.getVehicle(vehicleId, model);
                if (vehicle.getServiceIds().contains(serviceId)) {
                    throw new AssertionError("Delete Service command not cascading to vehicle. ");
                }
            } catch (VehicleNotFoundException e) {
                throw new AssertionError("JUnitTest: deleteService_success - Failed. Cascading " +
                        "check on Vehicle failed." + e.getMessage());
            }

            // check if service affected
            int technicianId = 1;

            try {
                Technician technician = AutoM8CommandTestUtil.getTechnician(technicianId, model);
                if (technician.getServiceIds().contains(serviceId)) {
                    throw new AssertionError("Delete Service command not cascading to Technicians ");
                }
            } catch (TechnicianNotFoundException e) {
                throw new AssertionError("JUnitTest: deleteService_success - Failed. Cascading " +
                        "check on Technician failed." + e.getMessage());
            }

        } catch (ServiceNotFoundException | CommandException e) {
            throw new CommandException("JUnitTest: deleteService_success - Failed. Message: " + e.getMessage());
        }
    }

    @Test
    public void deletePart_success() throws CommandException{
        Model model = new TypicalShop().getTypicalModel();

        try {
            String partName = "Brake Pads";
            AutoM8CommandTestUtil.getPartQty(partName, model);

            DeletePartCommand deleteXCommand = new DeletePartCommand(partName);
            CommandResult result = deleteXCommand.execute(model);

            assertEquals(result, new CommandResult(String.format(MESSAGE_DELETE_PART_SUCCESS, partName),
                    Tab.PARTS));

            try {
                AutoM8CommandTestUtil.getPartQty(partName, model);
                throw new AssertionError("Command reported part delete success " +
                        "but Part exists!");
            } catch (PartNotFoundException e) {
                assertEquals(e.getMessage(), new PartNotFoundException(partName).getMessage());
            } catch (EmptyInputException e) {
                throw new CommandException("JUnitTest: deletePart_success - Failed. Message: " + e.getMessage());
            }

        } catch (PartNotFoundException | EmptyInputException | CommandException e) {
            throw new CommandException("JUnitTest: deletePart_success - Failed. Message: " + e.getMessage());
        }
    }
}
