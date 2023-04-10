package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REGION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REGION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RISK_LEVEL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RISK_LEVEL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SINGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_STRONG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.person.Elderly;

/**
 * A utility class containing a list of {@code Elderly} objects to be used in tests.
 * (to be finished)
 */
public class TypicalElderly {

    public static final Elderly ALICE = new ElderlyBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withTags("friends")
            .withBirthDate("1990-01-01").withNric("S9186983G").withRegion("NORTH").withRiskLevel("low").build();
    public static final Elderly BENSON = new ElderlyBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withTags("owesMoney", "friends")
            .withRegion("NORTH").withBirthDate("1951-02-01").withNric("S0116088E").withRiskLevel("medium")
            .withAvailableDates("2023-05-01", "2023-05-12").build();
    public static final Elderly CARL = new ElderlyBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withBirthDate("1952-03-01").withNric("S1604578J").withRegion("CENTRAL").withRiskLevel("high").build();
    public static final Elderly DANIEL = new ElderlyBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withTags("friends").withBirthDate("1953-04-01")
            .withRegion("WEST").withNric("S6414118Z").withRiskLevel("LOW").build();
    public static final Elderly ELLE = new ElderlyBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withBirthDate("1954-05-01").withRegion("EAST")
            .withNric("S3271607E").withRiskLevel("HIGH").withAvailableDates("2023-04-01", "2023-04-12").build();
    public static final Elderly FIONA = new ElderlyBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withBirthDate("1955-06-01")
            .withNric("S4862780C").withRegion("CENTRAL").withRiskLevel("MEDIUM").build();
    public static final Elderly GEORGE = new ElderlyBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withBirthDate("1956-07-01")
            .withAvailableDates("2023-05-01", "2023-05-12")
            .withNric("S2241030Z").withRegion("NORTH").withRiskLevel("LoW").build();

    // Manually added
    public static final Elderly HOON = new ElderlyBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .build();
    public static final Elderly IDA = new ElderlyBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Elderly AMY = new ElderlyBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_SINGLE)
            .withNric(VALID_NRIC_AMY).withBirthDate(VALID_BIRTH_DATE_AMY).withRegion(VALID_REGION_AMY)
            .withRiskLevel(VALID_RISK_LEVEL_AMY).build();

    public static final Elderly BOB = new ElderlyBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_SINGLE, VALID_TAG_STRONG)
            .withNric(VALID_NRIC_BOB).withBirthDate(VALID_BIRTH_DATE_BOB).withRegion(VALID_REGION_BOB)
            .withRiskLevel(VALID_RISK_LEVEL_BOB).build();

    public static final Elderly CHARLIE = new ElderlyBuilder().withName("Charlie Kurz")
            .withPhone("95352563").withEmail("heinz@example.com").withAddress("wall street")
            .withBirthDate("1950-01-01").withNric(VALID_NRIC_CHARLIE).withRegion("NORTHEAST").build();

    private TypicalElderly() {} // prevents instantiation

    public static List<Elderly> getTypicalElderly() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
