package trackr.testutil;

import static trackr.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static trackr.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static trackr.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static trackr.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static trackr.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static trackr.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trackr.model.SupplierList;
import trackr.model.person.Supplier;

/**
 * A utility class containing a list of {@code Supplier} objects to be used in tests.
 */
//@@author arkarsg-reused
public class TypicalSuppliers {

    public static final Supplier ALICE = new SupplierBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Supplier BENSON = new SupplierBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Supplier CARL = new SupplierBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Supplier DANIEL = new SupplierBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Supplier ELLE = new SupplierBuilder().withName("Elle Meyer").withPhone("94823244")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Supplier FIONA = new SupplierBuilder().withName("Fiona Kunz").withPhone("94824273")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Supplier GEORGE = new SupplierBuilder().withName("George Best").withPhone("94824423")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Supplier HOON = new SupplierBuilder().withName("Hoon Meier").withPhone("84824243")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Supplier IDA = new SupplierBuilder().withName("Ida Mueller").withPhone("84821313")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Supplier AMY = new SupplierBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Supplier BOB = new SupplierBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalSuppliers() {} // prevents instantiation

    /**
     * Returns an {@code SupplierList} with all the typical suppliers.
     */
    public static SupplierList getTypicalSupplierList() {
        SupplierList sl = new SupplierList();
        for (Supplier person : getTypicalSuppliers()) {
            sl.addItem(person);
        }
        return sl;
    }

    public static List<Supplier> getTypicalSuppliers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
