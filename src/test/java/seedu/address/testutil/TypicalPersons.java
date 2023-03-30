package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Patient ALICE = new PersonBuilder()
            .withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withNric("S1234567A")
            .withPrescriptions("Paracetamol", "10")
            .withTags("friends")
            .withRole("Patient")
            .buildPatient();
    public static final Patient BENSON = new PersonBuilder()
            .withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withNric("S1234567B")
            .withPrescriptions("Cough Syrup", "0.01")
            .withTags("owesMoney", "friends")
            .withRole("Patient")
            .buildPatient();
    public static final Patient CARL = new PersonBuilder()
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withNric("T1234567C")
            .withEmail("heinz@example.com")
            .withPrescriptions("20 Paracetamol", "0.01", "20 Oprhenadrine", "10")
            .withAddress("wall street")
            .withRole("Patient")
            .buildPatient();
    public static final Patient DANIEL = new PersonBuilder()
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withNric("T1234567W")
            .withAddress("10th street")
            .withPrescriptions("1 Cough Syrup", "10000")
            .withTags("friends")
            .withRole("Patient")
            .buildPatient();
    public static final Patient ELLE = new PersonBuilder()
            .withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withNric("S1234569L")
            .withPrescriptions("1 Physiotherapy", "1234")
            .withAddress("michegan ave")
            .withRole("Patient")
            .buildPatient();
    public static final Patient FIONA = new PersonBuilder()
            .withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withNric("M1234067P")
            .withPrescriptions("Drug A", "1000", "Drug B", "200", "Drug C", "30", "Drug D", "4")
            .withAddress("little tokyo")
            .withRole("Patient")
            .buildPatient();
    public static final Patient GEORGE = new PersonBuilder()
            .withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withPrescriptions("1 annual checkup", "4.95")
            .withNric("S9874567A")
            .withAddress("4th street")
            .withRole("Patient")
            .buildPatient();
    public static final Doctor SARAH = new PersonBuilder()
            .withName("Sarah Tan")
            .withPhone("9482427")
            .withEmail("sarah@example.com")
            .withNric("S1234567S")
            .withAddress("Sarah Street")
            .withRole("Doctor")
            .buildDoctor();


    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withNric("S0934581L").withRole("Patient")
            .build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withNric("S0134381S").withRole("Patient")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withNric(VALID_NRIC_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).withRole("Patient")
            .build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withNric(VALID_NRIC_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withRole("Patient")
            .build();

    public static final Person JOHN1 = new PersonBuilder().withName("John Doe").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withNric("T2458659P").withRole("Patient").build();

    public static final Person JOHN2 = new PersonBuilder().withName("John Tay").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withNric("M3438159L").withRole("Patient").build();

    public static final Person JOHN3 = new PersonBuilder().withName("John Sena").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withNric("S3458659P").withRole("Patient").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, SARAH,
                JOHN1, JOHN2, JOHN3));
    }
}
