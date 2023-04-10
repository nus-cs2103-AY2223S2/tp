package seedu.loyaltylift.testutil;

import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.CustomerType;

/**
 * A utility class containing a list of {@code Customer} objects to be used in tests.
 */
public class TypicalCustomers {

    public static final Customer ALICE = new CustomerBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com").withPhone("94351253").build();
    public static final Customer BENSON = new CustomerBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withPoints(0, 0).withMarked(true).build();
    public static final Customer CARL = new CustomerBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Customer DANIEL = new CustomerBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").build();
    public static final Customer ELLE = new CustomerBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Customer FIONA = new CustomerBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Customer GEORGE = new CustomerBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Customer HOON = new CustomerBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Customer IDA = new CustomerBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Customer's details found in {@code CommandTestUtil}
    public static final Customer AMY = new CustomerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).build();
    public static final Customer BOB = new CustomerBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .build();

    // Manually added - Enterprises
    public static final Customer CHOCOLATE_FACTORY = new CustomerBuilder().withName("The Chocolate Factory")
            .withPhone("69920123").withEmail("chocofactory@enterprise.com").withAddress("30 Chocolate Street, #01-02")
            .withCustomerType(CustomerType.ENTERPRISE).build();

    public static final Customer SLY_FOX = new CustomerBuilder().withName("The Sly Fox")
            .withPhone("60012421").withEmail("slyfox@enterprise.com").withAddress("30 Wolf Street, #01-33")
            .withCustomerType(CustomerType.ENTERPRISE).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    public static List<Customer> getTypicalCustomers() {
        return new ArrayList<>(
                Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, CHOCOLATE_FACTORY, SLY_FOX));
    }
}
