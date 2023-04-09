package seedu.address.logic.commands;

import static seedu.address.logic.commands.AutoM8CommandTestUtil.assertFailure;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.person.Address;
import seedu.address.model.entity.person.Email;
import seedu.address.model.entity.person.Name;
import seedu.address.model.entity.person.Phone;
import seedu.address.model.entity.shop.exception.AppointmentNotFoundException;
import seedu.address.model.entity.shop.exception.CustomerNotFoundException;
import seedu.address.model.entity.shop.exception.DuplicateEmailException;
import seedu.address.model.entity.shop.exception.DuplicatePhoneNumberException;
import seedu.address.model.entity.shop.exception.DuplicatePlateNumberException;
import seedu.address.model.entity.shop.exception.EmptyInputException;
import seedu.address.model.entity.shop.exception.NoFieldEditedException;
import seedu.address.model.entity.shop.exception.TechnicianNotFoundException;
import seedu.address.model.entity.shop.exception.VehicleNotFoundException;
import seedu.address.model.service.VehicleType;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalShop;

public class AutoM8EditCommandTest {

    private static final int customerIdA = 1;
    private static final int technicianIdA = 1;
    private static final int appointmentIdA = 1;
    private static final int vehicleIdA = 1;
    private static final Name customerNameA = new Name("Alex Yeoh");
    private static final Name technicianNameA = new Name("James Tan");
    private static final Phone customerPhoneA = new Phone("87438807");
    private static final Phone technicianPhoneA = new Phone("89764362");
    private static final Email customerEmailA = new Email("alexyeoh@example.com");
    private static final Email technicianEmailA = new Email("jamestan@example.com");
    private static final Address customerAddressA = new Address("Blk 30 Geylang Street 29, #06-40");
    private static final Address technicianAddressA = new Address("Blk 586 Bedok Street 23, #08-46");
    private static final Set<Tag> customerTagA = getTagSet("regular");
    private static final Set<Tag> technicianTagA = getTagSet("big boss");
    //    private static final Set<Integer> customerVehicleIdA = getIntegerSet(1, 2);
    //    private static final Set<Integer> customerAppointmentIdA = getIntegerSet(1, 2, 3, 4);
    //    private static final Set<Integer> technicianServiceIdA = getIntegerSet(1, 4, 5, 6);
    //    private static final Set<Integer> technicianAppointmentIdA = getIntegerSet(1, 4);
    private static final LocalDateTime sampleLocalDateTimeA = LocalDateTime.parse("10/12/2022 02:00 PM",
            AutoM8CommandTestUtil.getDtf());
    private static final String vehiclePlateNumberA = "SKA1234A";
    private static final String vehicleColorA = "Red";
    private static final String vehicleBrandA = "Toyota Corolla";
    private static final VehicleType vehicleTypeA = VehicleType.CAR;
    //    private static final Set<Integer> vehicleServiceIdsA = getIntegerSet(1, 5);
    private static final Set<Tag> noTag = new HashSet<>();
    private static final Set<Integer> noValueIntegerSet = new HashSet<>();


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
        assertFailure(new EditAppointmentCommand(-1, Optional.of(customerIdA), Optional.of(sampleLocalDateTimeA)),
                model, new AppointmentNotFoundException(-1));

        assertFailure(new EditAppointmentCommand(99, Optional.of(customerIdA), Optional.of(sampleLocalDateTimeA)),
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
        assertFailure(new EditCustomerCommand(2, Optional.of(customerNameA), Optional.of(new Phone("180078279277")),
                        Optional.of(customerEmailA), Optional.of(customerAddressA), Optional.of(customerTagA)), model,
                new DuplicateEmailException(customerEmailA));

        assertFailure(new EditCustomerCommand(2, Optional.of(customerNameA), Optional.of(customerPhoneA),
                Optional.of(new Email("damith@gmail.com")), Optional.of(customerAddressA),
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

    // @Test
    // private void validVehicle_success() {
    //    Model model = new TypicalShop().getTypicalModel();

    //}

    //    private void foo(Model model) {
    //        Vehicle editedVehicle = new Vehicle(vehicleId,
    //                newOwnerId, newPlateNumber, newColor, newBrand, newType, vehicle.getServiceIdsSet());
    //
    //        editedVehicleCommand....
    //
    //        find vehicle with id... == editedVehicleCommand ? success.
    //    }

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
