package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LAST_FED_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LAST_FED_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.fish.Fish;

/**
 * A utility class containing a list of {@code Fish} objects to be used in tests.
 */
public class TypicalFishes {

    public static final Fish ALICE = new FishBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withSpecies("alice@example.com")
            .withLastFedDate("06/06/2003")
            .withTags("friends").build();
    public static final Fish BENSON = new FishBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withSpecies("johnd@example.com").withLastFedDate("06/07/2003")
            .withTags("owesMoney", "friends").build();
    public static final Fish CARL = new FishBuilder().withName("Carl Kurz").withLastFedDate("08/06/2003")
            .withSpecies("heinz@example.com").withAddress("wall street").build();
    public static final Fish DANIEL = new FishBuilder().withName("Daniel Meier").withLastFedDate("09/06/2003")
            .withSpecies("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Fish ELLE = new FishBuilder().withName("Elle Meyer").withLastFedDate("10/06/2003")
            .withSpecies("werner@example.com").withAddress("michegan ave").build();
    public static final Fish FIONA = new FishBuilder().withName("Fiona Kunz").withLastFedDate("11/06/2003")
            .withSpecies("lydia@example.com").withAddress("little tokyo").build();
    public static final Fish GEORGE = new FishBuilder().withName("George Best").withLastFedDate("12/06/2003")
            .withSpecies("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Fish HOON = new FishBuilder().withName("Hoon Meier").withLastFedDate("01/01/2000")
            .withSpecies("stefan@example.com").withAddress("little india").build();
    public static final Fish IDA = new FishBuilder().withName("Ida Mueller").withLastFedDate("01/01/2000")
            .withSpecies("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Fish's details found in {@code CommandTestUtil}
    public static final Fish AMY = new FishBuilder().withName(VALID_NAME_AMY).withLastFedDate(VALID_LAST_FED_DATE_AMY)
            .withSpecies(VALID_SPECIES_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Fish BOB = new FishBuilder().withName(VALID_NAME_BOB).withLastFedDate(VALID_LAST_FED_DATE_BOB)
            .withSpecies(VALID_SPECIES_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalFishes() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Fishes.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Fish fish : getTypicalFishes()) {
            ab.addFish(fish);
        }
        return ab;
    }

    public static List<Fish> getTypicalFishes() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
