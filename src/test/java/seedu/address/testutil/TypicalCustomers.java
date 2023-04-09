package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.entity.person.Customer;
//import seedu.address.model.entity.shop.Shop;

/**
 * A utility class containing a list of {@code Customer} objects to be used in tests.
 */
public class TypicalCustomers {
            public static final Customer ALICE = new CustomerBuilder().withName("Alice Pauline")
                    .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                    .withPhone("94351253")
                    .withTags("friends")
                    .withId(1)
                    .withVehicleIds(new HashSet<>(List.of(1)))
                    .withAppointmentIds(new HashSet<>(List.of(1))).build();
            public static final Customer BENSON = new CustomerBuilder().withName("Benson Meier")
                    .withAddress("311, Clementi Ave 2, #02-25")
                    .withEmail("johnd@example.com").withPhone("98765432")
                    .withTags("owesMoney", "friends")
                      .withId(2)
                      .withVehicleIds(new HashSet<>(List.of(2)))
                      .withAppointmentIds(new HashSet<>(List.of(2))).build();
            public static final Customer CARL = new CustomerBuilder().withName("Carl Kurz").withPhone("95352563")
                    .withEmail("heinz@example.com").withAddress("wall street")
                                                        .withId(3)
                                                        .withVehicleIds(new HashSet<>(List.of(3)))
                                                        .withAppointmentIds(new HashSet<>(List.of(3))).build();
            public static final Customer DANIEL = new CustomerBuilder().withName("Daniel Meier").withPhone("87652533")
                    .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").withId(4)
                                                          .withVehicleIds(new HashSet<>(List.of(4)))
                                                          .withAppointmentIds(new HashSet<>(List.of(4))).build();
            public static final Customer ELLE = new CustomerBuilder().withName("Elle Meyer").withPhone("9482224")
                    .withEmail("werner@example.com").withAddress("michegan ave").withId(5)
                                                        .withVehicleIds(new HashSet<>(List.of(5)))
                                                        .withAppointmentIds(new HashSet<>(List.of(5))).build();
            public static final Customer FIONA = new CustomerBuilder().withName("Fiona Kunz").withPhone("9482427")
                    .withEmail("lydia@example.com").withAddress("little tokyo").withId(6)
                                                         .withVehicleIds(new HashSet<>(List.of(6)))
                                                         .withAppointmentIds(new HashSet<>(List.of(6))).build();
            public static final Customer GEORGE = new CustomerBuilder().withName("George Best").withPhone("9482442")
                    .withEmail("anna@example.com").withAddress("4th street").withId(7)
                                                          .withVehicleIds(new HashSet<>(List.of(7)))
                                                          .withAppointmentIds(new HashSet<>(List.of(7))).build();

            private TypicalCustomers() {} // prevents instantiation

//            /**
//             * Returns an {@code Shop} with all the typical persons.
//             */
//            public static Shop getTypicalShop() {
//                Shop ab = new Shop();
//                for (Customer person : getTypicalCustomers()) {
//                    ab.addCustomer(person.getName(), person.getPhone(), person.getEmail(), person.getAddress(), person.getTags());
//                }
//                return ab;
//            }

            public static List<Customer> getTypicalCustomers() {
                return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
            }
}
