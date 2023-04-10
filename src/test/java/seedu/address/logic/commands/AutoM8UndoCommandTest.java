package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.AutoM8EditCommandTest.getTagSet;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.person.Address;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Email;
import seedu.address.model.entity.person.Name;
import seedu.address.model.entity.person.Phone;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.shop.exception.CustomerNotFoundException;
import seedu.address.model.entity.shop.exception.TechnicianNotFoundException;
import seedu.address.model.entity.shop.exception.VehicleNotFoundException;
import seedu.address.model.service.ServiceStatus;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.VehicleType;
import seedu.address.model.service.appointment.Appointment;
import seedu.address.model.service.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalShop;



/**
 * @@author 9fc70c892 reused
 *         Solution adapted from
 *         https://github.com/AY2223S2-CS2103-W17-4/tp/pull/160
 */

public class AutoM8UndoCommandTest {


    private static final int vehicleIdA = 1;
    private static final int serviceIdA = 1;

    private static final Name sampleName = new Name("MrYanDao");

    private static final Phone samplePhone = new Phone("180078279277");

    private static final Email sampleEmail = new Email("damith@gmail.com");

    private static final Address sampleAddress = new Address("1007 Mountain Drive");
    private static final Set<Tag> customerTagA = getTagSet("regular");
    private static final Set<Tag> technicianTagA = getTagSet("big boss");


    private static String samplePlate = "ZU 0666',0,0); DROP DATABASE TABLICE";

    private static String sampleColor = "Metallic Midnight Blue";

    private static final String sampleBrand = "Pegassi";
    private static final VehicleType vehicleTypeA = VehicleType.CAR;
    private static final VehicleType sampleVehicleType = VehicleType.MOTORBIKE;


    private static final LocalDate sampleEntryDate = LocalDate.now();
    private static final LocalDate sampleFinishDate = sampleEntryDate.plusDays(7);
    private static final ServiceStatus serviceStatusA = ServiceStatus.COMPLETE;
    private static final ServiceStatus sampleStatus = ServiceStatus.ON_HOLD;
    private static final String sampleDescription = "This is your garage... your empty garage. "
                                                            + "Not much I can do for ya.";

    // undo delete command=======================================================
    /**
     * Test method that checks for a successful undo of a delete command
     * Test if model we modified is equal to
     */
    @Test
    public void execute_listIsFiltered_undoDeleteCustomerCommand() throws CommandException, CustomerNotFoundException {
        Model model = new TypicalShop().getTypicalModel();
        Model expectedModel = new TypicalShop().getTypicalModel();
        int customerId = 1;
        Customer markForDelete = AutoM8CommandTestUtil.getCustomer(customerId, model);
        DeleteCustomerCommand deleteXCommand = new DeleteCustomerCommand(markForDelete.getId());
        UndoCommand undo = new UndoCommand();
        deleteXCommand.execute(model);
        undo.execute(model);
        assertEquals(model , expectedModel);
    }

    @Test
    public void execute_listIsFiltered_undoDeleteVehicleCommand() throws CommandException, VehicleNotFoundException {
        Model model = new TypicalShop().getTypicalModel();
        Model expectedModel = new TypicalShop().getTypicalModel();
        int vehicleId = 1;
        Vehicle markForDelete = AutoM8CommandTestUtil.getVehicle(vehicleId, model);
        DeleteVehicleCommand deleteXCommand = new DeleteVehicleCommand(markForDelete.getId());
        UndoCommand undo = new UndoCommand();
        deleteXCommand.execute(model);
        undo.execute(model);
        assertEquals(model , expectedModel);
    }

    @Test
    public void execute_listIsFiltered_undoDeleteAppointmentCommand() throws CommandException,
                                                                                         AppointmentNotFoundException,
                                              seedu.address.model.entity.shop.exception.AppointmentNotFoundException {
        Model model = new TypicalShop().getTypicalModel();
        Model expectedModel = new TypicalShop().getTypicalModel();
        int appointmentId = 1;
        Appointment markForDelete = AutoM8CommandTestUtil.getAppointment(appointmentId, model);
        DeleteAppointmentCommand deleteXCommand = new DeleteAppointmentCommand(markForDelete.getId());
        UndoCommand undo = new UndoCommand();
        deleteXCommand.execute(model);
        undo.execute(model);
        assertEquals(model , expectedModel);
    }

    @Test
    public void execute_listIsFiltered_undoDeleteTechnicianCommand() throws CommandException,
                                                                                        TechnicianNotFoundException {
        Model model = new TypicalShop().getTypicalModel();
        Model expectedModel = new TypicalShop().getTypicalModel();
        int technicianId = 1;
        Technician markForDelete = AutoM8CommandTestUtil.getTechnician(technicianId, model);
        DeleteTechnicianCommand deleteXCommand = new DeleteTechnicianCommand(markForDelete.getId());
        UndoCommand undo = new UndoCommand();
        deleteXCommand.execute(model);
        undo.execute(model);
        assertEquals(model , expectedModel);
    }

    // undo edit command=======================================================
    /**
     * Test method that checks for a successful undo of an add command
     * Test if model we modified is equal to
     */
    @Test
    public void execute_listIsFiltered_undoAddCustomerCommand() throws CommandException {
        Model model = new TypicalShop().getTypicalModel();
        Model expectedModel = new TypicalShop().getTypicalModel();
        AddCustomerCommand addXCommand = new AddCustomerCommand(sampleName, samplePhone, sampleEmail, sampleAddress,
                customerTagA);
        UndoCommand undo = new UndoCommand();
        addXCommand.execute(model);
        undo.execute(model);
        assertEquals(model , expectedModel);
    }

    @Test
    public void execute_listIsFiltered_undoAddVehicleCommand() throws CommandException {
        Model model = new TypicalShop().getTypicalModel();
        Model expectedModel = new TypicalShop().getTypicalModel();
        AddVehicleCommand addXCommand = new AddVehicleCommand(vehicleIdA, samplePlate, sampleColor,
                sampleBrand, sampleVehicleType);
        UndoCommand undo = new UndoCommand();
        addXCommand.execute(model);
        undo.execute(model);
        assertEquals(model , expectedModel);
    }

    @Test
    public void execute_listIsFiltered_undoAddTechnicianCommand() throws CommandException {
        Model model = new TypicalShop().getTypicalModel();
        Model expectedModel = new TypicalShop().getTypicalModel();
        AddTechnicianCommand addXCommand = new AddTechnicianCommand(sampleName, samplePhone, sampleEmail, sampleAddress,
                technicianTagA);
        UndoCommand undo = new UndoCommand();
        addXCommand.execute(model);
        undo.execute(model);
        assertEquals(model , expectedModel);
    }

    @Test
    public void execute_listIsFiltered_undoAddServiceCommand() throws CommandException {
        Model model = new TypicalShop().getTypicalModel();
        Model expectedModel = new TypicalShop().getTypicalModel();
        AddServiceCommand addXCommand = new AddServiceCommand(serviceIdA, Optional.of(sampleEntryDate),
                sampleDescription, Optional.of(sampleFinishDate), Optional.of(sampleStatus));
        UndoCommand undo = new UndoCommand();
        addXCommand.execute(model);
        undo.execute(model);
        assertEquals(model , expectedModel);
    }
}
