package seedu.connectus.testutil;

import static seedu.connectus.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_CCA_ICS;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_CCA_NES;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_MAJOR_BBA;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_MAJOR_COMPUTER_SCIENCE;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_MODULE_CS2101;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_MODULE_CS2103T;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_REMARK_FRIEND;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_REMARK_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.connectus.model.ConnectUs;
import seedu.connectus.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withRemarks("friends")
            .withModules("CS2101")
            .withCcas("NES")
            .build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withRemarks("owesMoney", "friends").withBirthday("30/04/2004")
            .withModules("CS2103T").withCcas("ICS")
            .build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withRemarks("friends").withCcas("ICS")
            .build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meier").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withRemarks("colleagues").build();
    public static final Person JASON = new PersonBuilder().withName("Jason Derulo")
            .withEmail("jason@example.com").withAddress("my house").build();
    public static final Person KASEY = new PersonBuilder().withName("Kasey Basey")
            .withPhone("91234567").withAddress("your house").withRemarks("friends").build();
    public static final Person LARRY = new PersonBuilder().withName("Lineup Larry")
            .withPhone("92345678").withEmail("larry@example.com").withRemarks("friends").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withRemarks(VALID_REMARK_FRIEND)
            .withModules(VALID_MODULE_CS2101).withCcas(VALID_CCA_ICS)
            .withMajors(VALID_MAJOR_COMPUTER_SCIENCE).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withRemarks(VALID_REMARK_HUSBAND, VALID_REMARK_FRIEND)
            .withModules(VALID_MODULE_CS2103T).withCcas(VALID_CCA_NES)
            .withMajors(VALID_MAJOR_BBA).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code ConnectUs} with all the typical persons.
     */
    public static ConnectUs getTypicalConnectUs() {
        ConnectUs ab = new ConnectUs();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
