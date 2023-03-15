package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MasterDeck;
import seedu.address.model.card.Card;

/**
 * A utility class containing a list of {@code Card} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Card ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withTags("friends").withDeck("Default").build();
    public static final Card BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withTags("owesMoney", "friends").withDeck("Default").build();
    public static final Card CARL = new PersonBuilder().withName("Carl Kurz").withAddress("wall street").build();
    public static final Card DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withAddress("10th street").withTags("friends").withDeck("Default").build();
    public static final Card ELLE = new PersonBuilder().withName("Elle Meyer").withAddress("michegan ave")
            .withDeck("Default").build();
    public static final Card FIONA = new PersonBuilder().withName("Fiona Kunz").withAddress("little tokyo")
            .withDeck("Default").build();
    public static final Card GEORGE = new PersonBuilder().withName("George Best").withAddress("4th street")
            .withDeck("Default").build();

    // Manually added
    public static final Card HOON = new PersonBuilder().withName("Hoon Meier").withAddress("little india").build();
    public static final Card IDA = new PersonBuilder().withName("Ida Mueller").withAddress("chicago ave").build();

    // Manually added - Card's details found in {@code CommandTestUtil}
    public static final Card AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Card BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code Deck} with all the typical persons.
     */
    public static MasterDeck getTypicalAddressBook() {
        MasterDeck ab = new MasterDeck();
        for (Card card : getTypicalPersons()) {
            ab.addCard(card);
        }
        ab.initDecks();
        return ab;
    }

    public static List<Card> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
