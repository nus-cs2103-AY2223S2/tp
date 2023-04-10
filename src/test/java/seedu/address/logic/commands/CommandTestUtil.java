package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyShop;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.NameContainsKeywordsPredicate;
import seedu.address.model.entity.person.Person;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.shop.Shop;
import seedu.address.model.service.Service;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.appointment.Appointment;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    //    /**
    //     * Executes the given {@code command}, confirms that <br>
    //     * - a {@code CommandException} is thrown <br>
    //     * - the CommandException message matches {@code expectedMessage} <br>
    //     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
    //     */
    //    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
    //        // we are unable to defensively copy the model for comparison later, so we can
    //        // only do so by copying its components.
    //        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
    //        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());
    //
    //        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
    //        assertEquals(expectedAddressBook, actualModel.getAddressBook());
    //        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    //    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    // ==== For Customers ====================================================


    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered customer list and selected customer in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ReadOnlyShop expectedAddressBook = new Shop(actualModel.getShop());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCustomerList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getShop());
        assertEquals(expectedFilteredList, actualModel.getFilteredCustomerList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the customer at the given {@code targetIndex} in the
     * {@code model}'s shop.
     */
    public static void showCustomerAtId(Model model, int targetId) {
        assertTrue(model.getShop().hasCustomer(targetId));

        model.updateFilteredCustomerList(c -> c.getId() == targetId);
        Customer customer = model.getFilteredCustomerList().get(0);
        assertNotNull(customer);

        final String[] splitName = customer.getName().fullName.split("\\s+");
        model.updateFilteredCustomerList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredCustomerList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the vehicle at the given {@code targetIndex} in the
     * {@code model}'s shop.
     */
    public static void showVehicleAtId(Model model, int targetId) {
        assertTrue(model.getShop().hasVehicle(targetId));

        model.updateFilteredVehicleList(v -> v.getId() == targetId);
        Vehicle vehicle = model.getFilteredVehicleList().get(0);
        assertNotNull(vehicle);

        model.updateFilteredVehicleList(v -> v.getPlateNumber() == vehicle.getPlateNumber());
        assertEquals(1, model.getFilteredVehicleList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the service at the given {@code targetIndex} in the
     * {@code model}'s shop.
     */
    public static void showServiceAtId(Model model, int targetId) {
        assertTrue(model.getShop().hasService(targetId));

        model.updateFilteredServiceList(s -> s.getId() == targetId);
        Service service = model.getFilteredServiceList().get(0);
        assertNotNull(service);

        model.updateFilteredServiceList(s -> s.getId() == service.getId()
                && s.getDescription().equalsIgnoreCase(service.getDescription()));
        assertEquals(1, model.getFilteredServiceList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the appointment at the given {@code targetIndex} in the
     * {@code model}'s shop.
     */
    public static void showAppointmentAtId(Model model, int targetId) {
        assertTrue(model.getShop().hasAppointment(targetId));

        model.updateFilteredAppointmentList(a -> a.getId() == targetId);
        Appointment appointment = model.getFilteredAppointmentList().get(0);
        assertNotNull(appointment);

        model.updateFilteredAppointmentList(a -> a.getId() == appointment.getId()
                && a.getCustomerId() == appointment.getCustomerId());
        assertEquals(1, model.getFilteredAppointmentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the technician at the given {@code targetIndex} in the
     * {@code model}'s shop.
     */
    public static void showTechnicianAtId(Model model, int targetId) {
        assertTrue(model.getShop().hasTechnician(targetId));

        model.updateFilteredTechnicianList(t -> t.getId() == targetId);
        Technician technician = model.getFilteredTechnicianList().get(0);
        assertNotNull(technician);

        final String[] splitName = technician.getName().fullName.split("\\s+");
        model.updateFilteredTechnicianList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
        assertEquals(1, model.getFilteredTechnicianList().size());
    }

}
