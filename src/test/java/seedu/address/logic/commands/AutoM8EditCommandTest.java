package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AutoM8CommandTestUtil.assertFailure;
import static seedu.address.logic.commands.AutoM8CommandTestUtil.assertSuccess;
import static seedu.address.logic.commands.EditCustomerCommand.MESSAGE_EDIT_PERSON_SUCCESS;
import static seedu.address.logic.commands.EditVehicleCommand.MESSAGE_EDIT_VEHICLE_SUCCESS;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.person.Address;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Email;
import seedu.address.model.entity.person.Name;
import seedu.address.model.entity.person.Phone;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.shop.CaseInsensitiveHashMap;
import seedu.address.model.entity.shop.exception.AppointmentNotFoundException;
import seedu.address.model.entity.shop.exception.CustomerNotFoundException;
import seedu.address.model.entity.shop.exception.DuplicateEmailException;
import seedu.address.model.entity.shop.exception.DuplicatePhoneNumberException;
import seedu.address.model.entity.shop.exception.DuplicatePlateNumberException;
import seedu.address.model.entity.shop.exception.EmptyInputException;
import seedu.address.model.entity.shop.exception.InsufficientPartException;
import seedu.address.model.entity.shop.exception.NoFieldEditedException;
import seedu.address.model.entity.shop.exception.PartNotFoundException;
import seedu.address.model.entity.shop.exception.TechnicianNotFoundException;
import seedu.address.model.entity.shop.exception.VehicleNotFoundException;
import seedu.address.model.service.Service;
import seedu.address.model.service.ServiceStatus;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.VehicleType;
import seedu.address.model.service.appointment.Appointment;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalShop;

public class AutoM8EditCommandTest {

    private static final int customerIdA = 1;
    private static final int technicianIdA = 1;
    private static final int appointmentIdA = 1;
    private static final int vehicleIdA = 1;
    private static final int serviceIdA = 1;
    private static final Name customerNameA = new Name("Alex Yeoh");
    private static final Name technicianNameA = new Name("James Tan");
    private static final Name sampleName = new Name("MrYanDao");
    private static final Phone customerPhoneA = new Phone("87438807");
    private static final Phone technicianPhoneA = new Phone("89764362");
    private static final Phone samplePhone = new Phone("180078279277");
    private static final Email customerEmailA = new Email("alexyeoh@example.com");
    private static final Email technicianEmailA = new Email("jamestan@example.com");
    private static final Email sampleEmail = new Email("damith@gmail.com");
    private static final Address customerAddressA = new Address("Blk 30 Geylang Street 29, #06-40");
    private static final Address technicianAddressA = new Address("Blk 586 Bedok Street 23, #08-46");
    private static final Address sampleAddress = new Address("1007 Mountain Drive");
    private static final Set<Tag> customerTagA = getTagSet("regular");
    private static final Set<Tag> technicianTagA = getTagSet("big boss");
    //    private static final Set<Integer> customerVehicleIdA = getIntegerSet(1, 2);
    //    private static final Set<Integer> customerAppointmentIdA = getIntegerSet(1, 2, 3, 4);
    //    private static final Set<Integer> technicianServiceIdA = getIntegerSet(1, 4, 5, 6);
    //    private static final Set<Integer> technicianAppointmentIdA = getIntegerSet(1, 4);
    private static final LocalDateTime appointmentLocalDateTimeA = LocalDateTime.parse("10/12/2022 02:00 PM",
            AutoM8CommandTestUtil.getDtf());
    private static final LocalDateTime sampleLocalDateTime = LocalDateTime.parse("29/04/2020 01:00 AM",
            AutoM8CommandTestUtil.getDtf());
    private static final String vehiclePlateNumberA = "SKA1234A";
    private static String samplePlate = "ZU 0666',0,0); DROP DATABASE TABLICE";
    private static final String vehicleColorA = "Red";
    private static String sampleColor = "Metallic Midnight Blue";
    private static final String vehicleBrandA = "Toyota Corolla";
    private static final String sampleBrand = "Pegassi";
    private static final VehicleType vehicleTypeA = VehicleType.CAR;
    private static final VehicleType sampleVehicleType = VehicleType.MOTORBIKE;

    //    private static final Set<Integer> vehicleServiceIdsA = getIntegerSet(1, 5);
    private static final Set<Tag> noTag = new HashSet<>();
    private static final Set<Integer> noValueIntegerSet = new HashSet<>();

    private static final Map<String, Integer> originalRequiredParts = new CaseInsensitiveHashMap<>();

    private static final LocalDate serviceEntryDateA = LocalDate.parse("02/03/2022",
            AutoM8CommandTestUtil.getDtfServices());
    private static final LocalDate serviceFinishDateA = LocalDate.parse("10/04/2022",
            AutoM8CommandTestUtil.getDtfServices());
    private static final LocalDate sampleEntryDate = LocalDate.now();
    private static final LocalDate sampleFinishDate = sampleEntryDate.plusDays(7);
    private static final ServiceStatus serviceStatusA = ServiceStatus.COMPLETE;
    private static final ServiceStatus sampleStatus = ServiceStatus.ON_HOLD;
    private static final String serviceDescriptionA = "Engine oil leak";
    private static final String sampleDescription = "This is your garage... your empty garage. "
            + "Not much I can do for ya.";
    private static final int servicePartQuantity = 1;
    private static final int sampleJokePartQuantity = 500;
    private static final int samplePartQuantity = 50;
    private static final String servicePartName = "Engine";
    private static final String sampleJokePartName = "500 sheets of wide-ruled notebook paper";
    private static final String partNameA = "Brake Pads";

    public AutoM8EditCommandTest() {
        originalRequiredParts.put("Engine", 1);
    }

    /**
     * Attempt to edit based on non-existing values in a typical shop
     */
    @Test
    public void invalidValues_failure() throws CommandException {

        Model model = new TypicalShop().getTypicalModel();

        invalidCustomer(model);
        invalidTechnician(model);
        invalidAppointment(model);
        invalidVehicle(model);

        // There is no such thing called edit part
    }

    private void invalidVehicle(Model model) {
        assertFailure(new EditVehicleCommand(-1, Optional.of(customerIdA), Optional.of(vehiclePlateNumberA),
                        Optional.of(vehicleColorA), Optional.of(vehicleBrandA), Optional.of(vehicleTypeA)),
                model, new VehicleNotFoundException(-1));

        assertFailure(new EditVehicleCommand(99, Optional.of(customerIdA), Optional.of(vehiclePlateNumberA),
                        Optional.of(vehicleColorA), Optional.of(vehicleBrandA), Optional.of(vehicleTypeA)),
                model, new VehicleNotFoundException(99));

        assertFailure(new EditVehicleCommand(vehicleIdA, Optional.of(99) , Optional.of(vehiclePlateNumberA),
                        Optional.of(vehicleColorA), Optional.of(vehicleBrandA), Optional.of(vehicleTypeA)),
            model, new CustomerNotFoundException(99));

        assertFailure(new EditVehicleCommand(2, Optional.of(customerIdA), Optional.of(vehiclePlateNumberA),
                        Optional.of(vehicleColorA), Optional.of(vehicleBrandA), Optional.of(vehicleTypeA)),
                model, new DuplicatePlateNumberException(vehiclePlateNumberA));

        assertFailure(new EditVehicleCommand(vehicleIdA, Optional.of(customerIdA), Optional.of(""),
                        Optional.of(vehicleColorA), Optional.of(vehicleBrandA), Optional.of(vehicleTypeA)),
                model, new EmptyInputException("PlateNumber cannot be blank"));

        assertFailure(new EditVehicleCommand(vehicleIdA, Optional.of(customerIdA), Optional.of(vehiclePlateNumberA),
                        Optional.of(""), Optional.of(vehicleBrandA), Optional.of(vehicleTypeA)),
                model, new EmptyInputException("Color cannot be blank"));

        assertFailure(new EditVehicleCommand(vehicleIdA, Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty()),
                model, new NoFieldEditedException());

    }

    private void invalidAppointment(Model model) {
        // Fail condition is only id
        // Does not check if time < time

        assertFailure(new EditAppointmentCommand(-1, Optional.of(customerIdA), Optional.of(appointmentLocalDateTimeA)),
                model, new AppointmentNotFoundException(-1));

        assertFailure(new EditAppointmentCommand(99, Optional.of(customerIdA), Optional.of(appointmentLocalDateTimeA)),
                model, new AppointmentNotFoundException(99));

        assertFailure(new EditAppointmentCommand(appointmentIdA, Optional.empty(), Optional.empty()),
                model, new NoFieldEditedException());
    }

    private void invalidCustomer(Model model) {
        assertFailure(new EditCustomerCommand(-1, Optional.of(customerNameA), Optional.of(customerPhoneA),
                        Optional.of(customerEmailA), Optional.of(customerAddressA), Optional.of(customerTagA)), model,
                new CustomerNotFoundException(-1));

        assertFailure(new EditCustomerCommand(99, Optional.of(customerNameA), Optional.of(customerPhoneA),
                        Optional.of(customerEmailA), Optional.of(customerAddressA), Optional.of(customerTagA)), model,
                new CustomerNotFoundException(99));

        // Email takes precedence
        assertFailure(new EditCustomerCommand(2, Optional.of(customerNameA), Optional.of(samplePhone),
                        Optional.of(customerEmailA), Optional.of(customerAddressA), Optional.of(customerTagA)), model,
                new DuplicateEmailException(customerEmailA));

        assertFailure(new EditCustomerCommand(2, Optional.of(customerNameA), Optional.of(customerPhoneA),
                Optional.of(sampleEmail), Optional.of(customerAddressA),
                Optional.of(customerTagA)), model, new DuplicatePhoneNumberException(customerPhoneA));

        assertFailure(new EditCustomerCommand(customerIdA, Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty()), model,
                        new NoFieldEditedException());
    }

    private void invalidTechnician(Model model) {
        assertFailure(new EditTechnicianCommand(-1, Optional.of(technicianNameA), Optional.of(technicianPhoneA),
                        Optional.of(technicianEmailA), Optional.of(technicianAddressA), Optional.of(technicianTagA)),
                model, new TechnicianNotFoundException(-1));

        assertFailure(new EditTechnicianCommand(99, Optional.of(technicianNameA), Optional.of(technicianPhoneA),
                        Optional.of(technicianEmailA), Optional.of(technicianAddressA), Optional.of(technicianTagA)),
                        model, new TechnicianNotFoundException(99));

        // Email takes precedence
        assertFailure(new EditTechnicianCommand(2, Optional.of(technicianNameA), Optional.of(new
                        Phone("180078279277")), Optional.of(technicianEmailA), Optional.of(technicianAddressA),
                        Optional.of(technicianTagA)), model, new DuplicateEmailException(technicianEmailA));

        assertFailure(new EditTechnicianCommand(2, Optional.of(technicianNameA), Optional.of(technicianPhoneA),
                        Optional.of(new Email("damith@gmail.com")), Optional.of(technicianAddressA),
                        Optional.of(technicianTagA)), model, new DuplicatePhoneNumberException(technicianPhoneA));

        assertFailure(new EditCustomerCommand(technicianIdA, Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty()), model,
                new NoFieldEditedException());
    }

    @Test
    public void validCustomer_success() {
        Model model = new TypicalShop().getTypicalModel();
        assertSuccess(new EditCustomerCommand(customerIdA, Optional.of(sampleName),
                        Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty()), model, new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS,
                customerIdA), Tab.CUSTOMERS).getFeedbackToUser());

        Customer customer = model.getShop().getCustomerList().stream().filter(c -> c.getId() == customerIdA)
                        .findFirst().get();

        assertTrue(customer.getName().equals(sampleName));

        assertSuccess(new EditCustomerCommand(customerIdA, Optional.empty(),
                Optional.of(samplePhone), Optional.empty(), Optional.empty(),
                Optional.empty()), model, new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS,
                customerIdA), Tab.CUSTOMERS).getFeedbackToUser());

        customer = model.getShop().getCustomerList().stream().filter(c -> c.getId() == customerIdA)
                        .findFirst().get();
        assertTrue(customer.getPhone().equals(samplePhone));

        assertSuccess(new EditCustomerCommand(customerIdA, Optional.empty(),
                Optional.empty(), Optional.of(sampleEmail), Optional.empty(),
                Optional.empty()), model, new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS,
                customerIdA), Tab.CUSTOMERS).getFeedbackToUser());

        customer = model.getShop().getCustomerList().stream().filter(c -> c.getId() == customerIdA)
                        .findFirst().get();
        assertTrue(customer.getEmail().equals(sampleEmail));

        assertSuccess(new EditCustomerCommand(customerIdA, Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.of(sampleAddress),
                Optional.empty()), model, new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS,
                customerIdA), Tab.CUSTOMERS).getFeedbackToUser());

        customer = model.getShop().getCustomerList().stream().filter(c -> c.getId() == customerIdA)
                        .findFirst().get();
        assertTrue(customer.getAddress().equals(sampleAddress));
    }

    @Test
    public void validTechnician_success() {
        Model model = new TypicalShop().getTypicalModel();
        assertSuccess(new EditTechnicianCommand(technicianIdA, Optional.of(sampleName),
                Optional.of(samplePhone), Optional.of(sampleEmail), Optional.of(sampleAddress),
                Optional.empty()), model, new CommandResult(String.format(EditTechnicianCommand
                .MESSAGE_EDIT_PERSON_SUCCESS, technicianIdA), Tab.TECHNICIANS).getFeedbackToUser());

        Technician technician = model.getShop().getTechnicianList().stream().filter(c -> c.getId() == technicianIdA)
                        .findFirst().get();

        assertTrue(technician.getName().equals(sampleName));
        assertTrue(technician.getPhone().equals(samplePhone));
        assertTrue(technician.getEmail().equals(sampleEmail));
        assertTrue(technician.getAddress().equals(sampleAddress));

        // Cascade check
        // Unneeded, all references to Technician is by ID number.

        // Other methods that edits Technician
        // Add Technician to Appointment
        int appointmentId = 2;
        Appointment appointment = model.getShop().getAppointmentList().stream().filter(c -> c.getId() == appointmentId)
                .findFirst().get();

        assertFalse(appointment.getStaffIds().contains(technicianIdA));

        assertSuccess(new AddTechnicianToAppointmentCommand(technicianIdA, appointmentId), model,
                new CommandResult(String.format(AddTechnicianToAppointmentCommand.MESSAGE_SUCCESS_FORMAT, technicianIdA,
                        appointmentId)).getFeedbackToUser());

        appointment = model.getShop().getAppointmentList().stream().filter(a -> a.getId() == appointmentId)
                .findFirst().get();

        assertTrue(appointment.getStaffIds().contains(technicianIdA));

        // Add Technician to Service
        int serviceId = 2;

        Service service = model.getShop().getServiceList().stream().filter(s -> s.getId() == serviceId)
                .findFirst().get();

        assertFalse(service.getAssignedToIds().contains(technicianIdA));

        assertSuccess(new AddTechnicianToServiceCommand(technicianIdA, serviceId), model,
                new CommandResult(String.format(AddTechnicianToServiceCommand.MESSAGE_SUCCESS_FORMAT, technicianIdA,
                        serviceId), Tab.SERVICES).getFeedbackToUser());

        assertTrue(service.getAssignedToIds().contains(technicianIdA));
    }

    @Test
    public void validVehicle_success() {
        Model model = new TypicalShop().getTypicalModel();

        Vehicle vehicle = model.getShop().getVehicleList().stream().filter(v -> v.getId() == vehicleIdA)
                .findFirst().get();

        int originalOwnerId = vehicle.getOwnerId();

        assertSuccess(new EditVehicleCommand(vehicleIdA, Optional.of(2),
                Optional.of(samplePlate), Optional.of(sampleColor), Optional.of(sampleBrand),
                Optional.of(sampleVehicleType)), model, new CommandResult(String.format(MESSAGE_EDIT_VEHICLE_SUCCESS,
                vehicleIdA), Tab.VEHICLES).getFeedbackToUser());

        vehicle = model.getShop().getVehicleList().stream().filter(v -> v.getId() == vehicleIdA)
                .findFirst().get();

        assertTrue(vehicle.getOwnerId() == 2);
        assertTrue(vehicle.getPlateNumber().equals(samplePlate));
        assertTrue(vehicle.getColor().equals(sampleColor));
        assertTrue(vehicle.getBrand().equals(sampleBrand));
        assertTrue(vehicle.getType().equals(sampleVehicleType));

        Customer originalCustomer = model.getShop().getCustomerList().stream().filter(c -> c.getId() == originalOwnerId)
                .findFirst().get();

        assertFalse(originalCustomer.getVehicleIds().contains(vehicleIdA));
    }

    @Test
    public void validService_success() {
        Model model = new TypicalShop().getTypicalModel();
        Service service = model.getShop().getServiceList().stream().filter(s -> s.getId() == serviceIdA)
                .findFirst().get();

        assertFalse(vehicleIdA == 2);
        assertFalse(service.getEntryDate().equals(sampleEntryDate));
        assertFalse(service.getEstimatedFinishDate().equals(sampleFinishDate));
        assertFalse(service.getStatus().equals(sampleStatus));
        assertFalse(service.getDescription().equals(sampleDescription));

        assertSuccess(new EditServiceCommand(serviceIdA, Optional.of(2), Optional.of(sampleEntryDate),
                Optional.of(sampleFinishDate), Optional.of(sampleStatus), Optional.of(sampleDescription)), model,
                new CommandResult(String.format(EditServiceCommand.MESSAGE_EDIT_SERVICE_SUCCESS, serviceIdA),
                        Tab.SERVICES).getFeedbackToUser());

        service = model.getShop().getServiceList().stream().filter(s -> s.getId() == serviceIdA)
                .findFirst().get();

        assertTrue(service.getVehicleId() == 2);
        assertTrue(service.getEntryDate().equals(sampleEntryDate));
        assertTrue(service.getEstimatedFinishDate().equals(sampleFinishDate));
        assertTrue(service.getStatus().equals(sampleStatus));
        assertTrue(service.getDescription().equals(sampleDescription));

        // Other functions related

        assertNull(service.getRequiredParts().get(sampleJokePartName));

        // Minitest if add non-existing part how
        assertFailure(new AddPartToServiceCommand(serviceIdA, sampleJokePartName, sampleJokePartQuantity), model,
                new PartNotFoundException(sampleJokePartName));

        assertFailure(new AddPartToServiceCommand(serviceIdA, partNameA, sampleJokePartQuantity), model,
                new InsufficientPartException(partNameA, samplePartQuantity));

        int reasonableQuantity = 4;
        assertSuccess(new AddPartToServiceCommand(serviceIdA, partNameA, reasonableQuantity), model,
                new CommandResult(String.format(AddPartToServiceCommand.MESSAGE_SUCCESS_FORMAT, reasonableQuantity,
                        partNameA, serviceIdA), Tab.SERVICES).getFeedbackToUser());

        service = model.getShop().getServiceList().stream().filter(s -> s.getId() == serviceIdA)
                .findFirst().get();

        assertTrue(service.getRequiredParts().get(partNameA) == reasonableQuantity);

        try {
            assertTrue(model.getShop().getPartQty(partNameA) == (samplePartQuantity - reasonableQuantity));
        } catch (EmptyInputException | PartNotFoundException e) {
            throw new AssertionError(e);
        }


    }

    @Test
    public void validAppointment_success() {
        Model model = new TypicalShop().getTypicalModel();
        Appointment appointment = model.getShop().getAppointmentList().stream().filter(a -> a.getId() == appointmentIdA)
                .findFirst().get();

        assertFalse(appointment.getCustomerId() == 2);
        assertFalse(appointment.getTimeDate().equals(sampleLocalDateTime));

        assertSuccess(new EditAppointmentCommand(appointmentIdA, Optional.of(2),Optional.of(sampleLocalDateTime)),
                model, new CommandResult(String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                        appointmentIdA), Tab.APPOINTMENTS).getFeedbackToUser());

        appointment = model.getShop().getAppointmentList().stream().filter(a -> a.getId() == appointmentIdA)
                .findFirst().get();
        assertTrue(appointment.getCustomerId() == 2);
        assertTrue(appointment.getTimeDate().equals(sampleLocalDateTime));
        assertTrue(appointment.getDateStatus().equals(Appointment.DateStatus.PASSED));

        LocalDateTime futureTime = LocalDateTime.now();

        assertSuccess(new EditAppointmentCommand(appointmentIdA, Optional.empty(),Optional.of(futureTime)),
                model, new CommandResult(String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                        appointmentIdA), Tab.APPOINTMENTS).getFeedbackToUser());

        appointment = model.getShop().getAppointmentList().stream().filter(a -> a.getId() == appointmentIdA)
                .findFirst().get();
        assertTrue(appointment.getDateStatus().equals(Appointment.DateStatus.NOW));

        futureTime = LocalDateTime.now();
        futureTime = futureTime.plusDays(7);
        assertSuccess(new EditAppointmentCommand(appointmentIdA, Optional.empty(),Optional.of(futureTime)),
                model, new CommandResult(String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                        appointmentIdA), Tab.APPOINTMENTS).getFeedbackToUser());

        appointment = model.getShop().getAppointmentList().stream().filter(a -> a.getId() == appointmentIdA)
                .findFirst().get();
        assertTrue(appointment.getDateStatus().equals(Appointment.DateStatus.UPCOMING));

    }


    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a integer set containing the list of integers given.
     */
    public static Set<Integer> getIntegerSet(Integer... integers) {
        return Arrays.stream(integers)
                .collect(Collectors.toSet());
    }

}
