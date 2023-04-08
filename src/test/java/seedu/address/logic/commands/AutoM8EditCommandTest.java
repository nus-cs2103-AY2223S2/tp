//package seedu.address.logic.commands;
//
//import static seedu.address.logic.commands.AutoM8CommandTestUtil.assertFailure;
//
//import java.util.Arrays;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.junit.jupiter.api.Test;
//import seedu.address.model.Model;
//import seedu.address.model.entity.person.Address;
//import seedu.address.model.entity.person.Customer;
//import seedu.address.model.entity.person.Email;
//import seedu.address.model.entity.person.Name;
//import seedu.address.model.entity.person.Phone;
//import seedu.address.model.entity.shop.exception.AppointmentNotFoundException;
//import seedu.address.model.entity.shop.exception.CustomerNotFoundException;
//import seedu.address.model.entity.shop.exception.PartNotFoundException;
//import seedu.address.model.entity.shop.exception.TechnicianNotFoundException;
//import seedu.address.model.entity.shop.exception.VehicleNotFoundException;
//import seedu.address.model.tag.Tag;
//import seedu.address.testutil.TypicalShop;
//
//public class AutoM8EditCommandTest {
//
//    static Name customerNameA = new Name("Alex Yeoh");
//    static Phone customerPhoneA = new Phone("87438807");
//    static Email customerEmailA = new Email("alexyeoh@example.com");
//
//    static Customer typicalExistingCustomer = new Customer(1, customerNameA, customerPhoneA,
//            customerEmailA,
//            new Address("Blk 30 Geylang Street 29, #06-40"),
//            getTagSet("regular"), getIntegerSet(1, 2), getIntegerSet(1, 2, 3, 4));
//
//    static Customer typicalDuplicateCustomerId = new Customer(1, new Name("WOOX"), new Phone("87438807"),
//            new Email("alexyeoh@example.com"),
//            new Address("Blk 30 Geylang Street 29, #06-40"),
//            getTagSet("regular"), getIntegerSet(1, 2), getIntegerSet(1, 2, 3, 4));
//
//    static Customer typicalDuplicateCustomerId = new Customer(1, new Name("WOOX"), new Phone("87438807"),
//            new Email("alexyeoh@example.com"),
//            new Address("Blk 30 Geylang Street 29, #06-40"),
//            getTagSet("regular"), getIntegerSet(1, 2), getIntegerSet(1, 2, 3, 4));
//
//    /**
//     * Attempt to edit based on non-existing values in a typical shop
//     */
//    @Test
//    public void invalidValues_failure() {
//
//        Model model = new TypicalShop().getTypicalModel();
//
//        assertFailure(new EditCustomerCommand(-1, "WOOX", "2277", "WOOXWOOX,"), model, new CustomerNotFoundException(-1));
//        assertFailure(new DeleteCustomerCommand(9), model, new CustomerNotFoundException(9));
//
//        assertFailure(new DeleteTechnicianCommand(-1), model, new TechnicianNotFoundException(-1));
//        assertFailure(new DeleteTechnicianCommand(9), model, new TechnicianNotFoundException(9));
//
//        assertFailure(new DeleteAppointmentCommand(-1), model, new AppointmentNotFoundException(-1));
//        assertFailure(new DeleteAppointmentCommand(9), model, new AppointmentNotFoundException(9));
//
//        assertFailure(new DeleteVehicleCommand(-1), model, new VehicleNotFoundException(-1));
//        assertFailure(new DeleteVehicleCommand(9), model, new VehicleNotFoundException(9));
//
//        assertFailure(new DeletePartCommand("Woox Walk DCB"), model, new PartNotFoundException("Woox Walk DCB"));
//    }
//
//    /**
//     * Returns a tag set containing the list of strings given.
//     */
//    public static Set<Tag> getTagSet(String... strings) {
//        return Arrays.stream(strings)
//                .map(Tag::new)
//                .collect(Collectors.toSet());
//    }
//
//    /**
//     * Returns a integer set containing the list of integers given.
//     */
//    public static Set<Integer> getIntegerSet(Integer... integers) {
//        return Arrays.stream(integers)
//                .collect(Collectors.toSet());
//    }
//
//}
