package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENDTIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENDTIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTTIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTTIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TuteeManagingSystem;
import seedu.address.model.tutee.Tutee;

/**
 * A utility class containing a list of {@code Tutee} objects EndTime be used in tests.
 */
public class TypicalPersons {

    public static final Tutee ALICE = new TuteeTestBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withSubject("Math").withSchedule("friday").withRemark("")
            .withStartTime("10:30").withEndTime("12:30").withTags("friends").build();
    public static final Tutee BENSON = new TuteeTestBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withRemark("He can't take beer!")
            .withEmail("johnd@example.com").withPhone("98765432").withSubject("Math").withSchedule("friday")
            .withStartTime("12:30").withEndTime("14:30").withTags("owesMoney", "friends").build();
    public static final Tutee CARL = new TuteeTestBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withSubject("Math").withSchedule("friday")
            .withStartTime("14:30").withEndTime("16:30").build();
    public static final Tutee DANIEL = new TuteeTestBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withStartTime("16:30").withEndTime("18:30").withTags("friends").build();
    public static final Tutee ELLE = new TuteeTestBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withSubject("Math").withSchedule("friday")
            .withStartTime("18:30").withEndTime("20:30").build();
    public static final Tutee FIONA = new TuteeTestBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withSubject("Math").withSchedule("friday")
            .withStartTime("20:30").withEndTime("22:30").build();
    public static final Tutee GEORGE = new TuteeTestBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withSubject("Math").withSchedule("friday")
            .withStartTime("08:30").withEndTime("10:30").build();

    // Manually added
    public static final Tutee HOON = new TuteeTestBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withSubject("Math").withSchedule("friday")
            .withStartTime("15:30").withEndTime("16:30").build();
    public static final Tutee IDA = new TuteeTestBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withSubject("Math").withSchedule("friday")
            .withStartTime("10:00").withEndTime("12:00").build();

    // Manually added - Tutee's details found in {@code CommandTestUtil}
    public static final Tutee AMY = new TuteeTestBuilder()
        .withName(VALID_NAME_AMY)
        .withPhone(VALID_PHONE_AMY)
        .withEmail(VALID_EMAIL_AMY)
        .withAddress(VALID_ADDRESS_AMY)
        .withAddress(VALID_ADDRESS_AMY)
        .withSubject(VALID_SUBJECT_AMY)
        .withSchedule(VALID_SCHEDULE_AMY)
        .withStartTime(VALID_STARTTIME_AMY)
        .withEndTime(VALID_ENDTIME_AMY)
        .withTags(VALID_TAG_FRIEND)
        .build();

    public static final Tutee BOB = new TuteeTestBuilder()
        .withName(VALID_NAME_BOB)
        .withPhone(VALID_PHONE_BOB)
        .withEmail(VALID_EMAIL_BOB)
        .withAddress(VALID_ADDRESS_BOB)
        .withSubject(VALID_SUBJECT_BOB)
        .withSchedule(VALID_SCHEDULE_BOB)
        .withStartTime(VALID_STARTTIME_BOB)
        .withEndTime(VALID_ENDTIME_BOB)
        .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code TuteeManagingSystem} with all the typical persons.
     */
    public static TuteeManagingSystem getTypicalTuteeManagingSystem() {
        TuteeManagingSystem ab = new TuteeManagingSystem();
        for (Tutee tutee : getTypicalPersons()) {
            ab.addPerson(tutee);
        }
        return ab;
    }

    public static List<Tutee> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
