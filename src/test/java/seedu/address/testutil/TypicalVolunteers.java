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
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REGION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REGION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SINGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_STRONG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.person.Volunteer;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalVolunteers {

    public static final Volunteer ALICE = new VolunteerBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withTags("friends")
            .withBirthDate("1990-01-01").withRegion("CENTRAL").withNric("S9673908G")
            .withMedicalTags("CPR,BASIC")
            .withAvailableDates("2023-05-01", "2023-05-12")
            .withAvailableDates("2023-05-02", "2023-05-14")
            .withAvailableDates("2023-05-12", "2023-05-15").build();
    public static final Volunteer BENSON = new VolunteerBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withBirthDate("1991-02-01")
            .withRegion("WEST").withNric("S6878241D")
            .withAvailableDates("2023-05-01", "2023-05-12")
            .withAvailableDates("2023-05-02", "2023-05-10")
            .build();
    public static final Volunteer CARL = new VolunteerBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withBirthDate("1992-03-01").withRegion("EAST")
            .withMedicalTags("BLS,INTERMEDIATE", "AED,BASIC").withNric("S3634466J").build();
    public static final Volunteer DANIEL = new VolunteerBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withTags("friends").withBirthDate("1993-04-01").withRegion("NORTH")
            .withNric("S0203151E").build();
    public static final Volunteer ELLE = new VolunteerBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withBirthDate("1994-05-01")
            .withAvailableDates("2023-05-11", "2023-05-12").withRegion("NORTH")
            .withNric("S7238791J").build();
    public static final Volunteer FIONA = new VolunteerBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withBirthDate("1995-06-01").withRegion("EAST")
            .withNric("S3576311B").withMedicalTags("AED,ADVANCED")
            .withAvailableDates("2023-02-01", "2023-03-01").build();

    public static final Volunteer GEORGE = new VolunteerBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withBirthDate("1996-07-01").withRegion("CENTRAL")
            .withNric("S1262951F").build();

    // Manually added
    public static final Volunteer HOON = new VolunteerBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .build();

    public static final Volunteer IDA = new VolunteerBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withBirthDate("1997-08-01").withRegion("EAST")
            .withNric("S8866775G").withMedicalTags("CPR,ADVANCED", "BLS,BASIC")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Volunteer AMY = new VolunteerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_SINGLE)
            .withNric(VALID_NRIC_AMY).withBirthDate(VALID_BIRTH_DATE_AMY).withRegion(VALID_REGION_AMY).build();
    public static final Volunteer BOB = new VolunteerBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_SINGLE, VALID_TAG_STRONG)
            .withNric(VALID_NRIC_BOB).withBirthDate(VALID_BIRTH_DATE_BOB).withRegion(VALID_REGION_BOB).build();

    private TypicalVolunteers() {} // prevents instantiation

    public static List<Volunteer> getTypicalVolunteers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
