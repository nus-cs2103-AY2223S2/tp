package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.person.Address;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Email;
import seedu.address.model.entity.person.Name;
import seedu.address.model.entity.person.Phone;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.service.Service;
import seedu.address.model.service.ServiceStatus;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.VehicleType;
import seedu.address.model.service.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.testutil.TypicalShop;



/**
 * @@author 9fc70c892 reused
 *         Solution adapted from
 *         https://github.com/AY2223S2-CS2103-W17-4/tp/pull/160
 */

public class AutoM8RedoCommandTest {

    private static final int customerIdA = 1;
    private static final int technicianIdA = 1;

    private static final int vehicleIdA = 1;
    private static final int serviceIdA = 1;

    private static final Name sampleName = new Name("MrYanDao");

    private static final Phone samplePhone = new Phone("180078279277");

    private static final Email sampleEmail = new Email("damith@gmail.com");

    private static final Address sampleAddress = new Address("1007 Mountain Drive");

    private static final String samplePlate = "ZU 0666',0,0); DROP DATABASE TABLICE";

    private static final String sampleColor = "Metallic Midnight Blue";

    private static final String sampleBrand = "Pegassi";

    private static final VehicleType sampleVehicleType = VehicleType.MOTORBIKE;

    private static final LocalDate sampleEntryDate = LocalDate.now();
    private static final LocalDate sampleFinishDate = sampleEntryDate.plusDays(7);

    private static final ServiceStatus sampleStatus = ServiceStatus.ON_HOLD;

    private static final String sampleDescription = "This is your garage... your empty garage. "
                                                            + "Not much I can do for ya.";

    /**
     * Test method that checks for a successful undo of an add command
     * Test if model we modified is equal to
     */
    @Test
    public void execute_listIsFiltered_undoEditCustomerCommand() throws CommandException, AppointmentNotFoundException {
        Model model = new TypicalShop().getTypicalModel();
        Model expectedModel = new TypicalShop().getTypicalModel();
        EditCustomerCommand editXCommand = new EditCustomerCommand(customerIdA, Optional.of(sampleName),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        UndoCommand undo = new UndoCommand();
        RedoCommand redo = new RedoCommand();
        editXCommand.execute(model);
        undo.execute(model);
        redo.execute(model);

        Customer customer = model.getShop().getCustomerList().stream().filter(c -> c.getId() == customerIdA)
                                    .findFirst().get();

        assertTrue(customer.getName().equals(sampleName));
    }

    @Test
    public void execute_listIsFiltered_undoEditVehicleCommand() throws CommandException, AppointmentNotFoundException {
        Model model = new TypicalShop().getTypicalModel();
        Model expectedModel = new TypicalShop().getTypicalModel();
        EditVehicleCommand editXCommand = new EditVehicleCommand(vehicleIdA, Optional.of(2),
                Optional.of(samplePlate), Optional.of(sampleColor), Optional.of(sampleBrand),
                Optional.of(sampleVehicleType));
        UndoCommand undo = new UndoCommand();
        RedoCommand redo = new RedoCommand();
        editXCommand.execute(model);
        undo.execute(model);
        redo.execute(model);

        Vehicle vehicle = model.getShop().getVehicleList().stream().filter(v -> v.getId() == vehicleIdA)
                          .findFirst().get();

        assertTrue(vehicle.getOwnerId() == 2);
        assertTrue(vehicle.getPlateNumber().equals(samplePlate));
        assertTrue(vehicle.getColor().equals(sampleColor));
        assertTrue(vehicle.getBrand().equals(sampleBrand));
        assertTrue(vehicle.getType().equals(sampleVehicleType));
    }

    @Test
    public void execute_listIsFiltered_undoEditTechnicianCommand() throws CommandException {
        Model model = new TypicalShop().getTypicalModel();
        EditTechnicianCommand editXCommand = new EditTechnicianCommand(technicianIdA, Optional.of(sampleName),
                Optional.of(samplePhone), Optional.of(sampleEmail), Optional.of(sampleAddress),
                Optional.empty());
        UndoCommand undo = new UndoCommand();
        RedoCommand redo = new RedoCommand();
        editXCommand.execute(model);
        undo.execute(model);
        redo.execute(model);

        Technician technician = model.getShop().getTechnicianList().stream().filter(c -> c.getId() == technicianIdA)
                                        .findFirst().get();

        assertTrue(technician.getName().equals(sampleName));
        assertTrue(technician.getPhone().equals(samplePhone));
        assertTrue(technician.getEmail().equals(sampleEmail));
        assertTrue(technician.getAddress().equals(sampleAddress));
    }

    @Test
    public void execute_listIsFiltered_undoEditServiceCommand() throws CommandException {
        Model model = new TypicalShop().getTypicalModel();
        Model expectedModel = new TypicalShop().getTypicalModel();
        EditServiceCommand editXCommand = new EditServiceCommand(serviceIdA, Optional.of(2),
                Optional.of(sampleEntryDate),
                Optional.of(sampleFinishDate), Optional.of(sampleStatus), Optional.of(sampleDescription));
        UndoCommand undo = new UndoCommand();
        RedoCommand redo = new RedoCommand();
        editXCommand.execute(model);
        undo.execute(model);
        redo.execute(model);

        Service service = model.getShop().getServiceList().stream().filter(s -> s.getId() == serviceIdA)
                                  .findFirst().get();

        assertTrue(service.getVehicleId() == 2);
        assertTrue(service.getEntryDate().equals(sampleEntryDate));
        assertTrue(service.getEstimatedFinishDate().equals(sampleFinishDate));
        assertTrue(service.getStatus().equals(sampleStatus));
        assertTrue(service.getDescription().equals(sampleDescription));
    }
}
