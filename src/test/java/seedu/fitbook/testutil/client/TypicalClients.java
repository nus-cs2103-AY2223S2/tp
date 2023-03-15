package seedu.fitbook.testutil.client;

import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_ONE;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_TWO;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_CALORIE_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_CALORIE_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_GOAL_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_GOAL_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_WEIGHT_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.fitbook.model.FitBook;
import seedu.fitbook.model.client.Client;


/**
 * A utility class containing a list of {@code Client} objects to be used in tests.
 */
public class TypicalClients {

    public static final Client ALICE = new ClientBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withAppointments("13-02-1999").withWeight("50.0").withGender("M")
            .withGoal("lose-weight").withTags("friends").withCalorie("2310").build();
    public static final Client BENSON = new ClientBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withWeight("50.0").withGender("M")
            .withGoal("lose-weight").withTags("owesMoney", "friends").withAppointments("13-02-2020", "14-01-2021")
            .withCalorie("2424").build();
    public static final Client CARL = new ClientBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withWeight("50.0").withGender("M").withGoal("lose-weight").withCalorie("2111").build();
    public static final Client DANIEL = new ClientBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withWeight("50.0").withGender("M").withAppointments("12-12-2020")
            .withGoal("lose-weight").withTags("friends").withCalorie("2103").build();

    public static final Client ELLE = new ClientBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withWeight("50.0").withGender("M").withGoal("lose-weight").withCalorie("0000").build();
    public static final Client FIONA = new ClientBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withWeight("50.0").withGender("M").withGoal("lose-weight").withCalorie("2154").build();
    public static final Client GEORGE = new ClientBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withWeight("50.0").withGender("M").withGoal("lose-weight").withCalorie("3432").build();

    // Manually added
    public static final Client HOON = new ClientBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withWeight("20.0")
            .withGender("M").withGoal("lose-weight").withCalorie("2134").build();
    public static final Client IDA = new ClientBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withWeight("20.0").withGender("M").withGoal("lose-weight").withCalorie("2657").build();

    // Manually added - Client's details found in {@code CommandTestUtil}
    public static final Client AMY = new ClientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withWeight(VALID_WEIGHT_AMY).withGender(VALID_GENDER_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).withAppointments(VALID_APPOINTMENT_DATE_ONE)
            .withGoal(VALID_GOAL_AMY).withCalorie(VALID_CALORIE_AMY).build();
    public static final Client BOB = new ClientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withWeight(VALID_WEIGHT_BOB).withGender(VALID_GENDER_BOB)
            .withAppointments(VALID_APPOINTMENT_DATE_ONE, VALID_APPOINTMENT_DATE_TWO).withGoal(VALID_GOAL_BOB)
            .withTags(VALID_TAG_FRIEND).withCalorie(VALID_CALORIE_BOB).build();
    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalClients() {} // prevents instantiation

    /**
     * Returns an {@code FitBook} with all the typical clients.
     */
    public static FitBook getTypicalFitBook() {
        FitBook ab = new FitBook();
        for (Client client : getTypicalClients()) {
            ab.addClient(client);
        }
        return ab;
    }

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
