package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module ALICE = new ModuleBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withTimeSlot("alice@example.com")
            .withType("94351253")
            .withTags("friends").build();
    public static final Module BENSON = new ModuleBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withTimeSlot("johnd@example.com").withType("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Module CARL = new ModuleBuilder().withName("Carl Kurz").withType("95352563")
            .withTimeSlot("heinz@example.com").withAddress("wall street").build();
    public static final Module DANIEL = new ModuleBuilder().withName("Daniel Meier").withType("87652533")
            .withTimeSlot("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Module ELLE = new ModuleBuilder().withName("Elle Meyer").withType("9482224")
            .withTimeSlot("werner@example.com").withAddress("michegan ave").build();
    public static final Module FIONA = new ModuleBuilder().withName("Fiona Kunz").withType("9482427")
            .withTimeSlot("lydia@example.com").withAddress("little tokyo").build();
    public static final Module GEORGE = new ModuleBuilder().withName("George Best").withType("9482442")
            .withTimeSlot("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Module HOON = new ModuleBuilder().withName("Hoon Meier").withType("8482424")
            .withTimeSlot("stefan@example.com").withAddress("little india").build();
    public static final Module IDA = new ModuleBuilder().withName("Ida Mueller").withType("8482131")
            .withTimeSlot("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Module's details found in {@code CommandTestUtil}
    public static final Module AMY = new ModuleBuilder().withName(VALID_NAME_AMY).withType(VALID_TYPE_AMY)
            .withTimeSlot(VALID_TIMESLOT_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Module BOB = new ModuleBuilder().withName(VALID_NAME_BOB).withType(VALID_TYPE_BOB)
            .withTimeSlot(VALID_TIMESLOT_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalModules() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical modules.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
