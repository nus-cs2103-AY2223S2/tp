package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOCTOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOCTOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRUG_ALLERGY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRUG_ALLERGY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIABETIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_OSTEOPOROTIC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withNric("S1234567A")
            .withName("Alice Pauline")
            .withDate("11/01/1979")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withDrugAllergy("NKDA")
            .withGender("female")
            .withDoctor("Ben")
            .withTags("Diabetic").build();
    public static final Person BENSON = new PersonBuilder()
            .withNric("S2345678B")
            .withName("Benson Meier")
            .withDate("11/01/1957")
            .withPhone("98765432")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com")
            .withDrugAllergy("NKDA")
            .withTags("Osteoporotic", "Diabetic")
            .withGender("male")
            .withDoctor("Alex")
            .withMedicines("Paracetamol").build();
    public static final Person CARL = new PersonBuilder()
            .withNric("S3456789C")
            .withName("Carl Kurz")
            .withDate("11/01/1958")
            .withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withGender("male")
            .withTags("Asthmatic")
            .withDoctor("Shannon")
            .withDrugAllergy("Penicilin").build();
    public static final Person DANIEL = new PersonBuilder()
            .withNric("S0123456D")
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withDrugAllergy("Aspirin Panadol")
            .withTags("Epileptic")
            .withGender("male")
            .withDoctor("None").build();
    public static final Person ELLE = new PersonBuilder()
            .withNric("S0000000E")
            .withName("Elle Meyer")
            .withDate("11/01/1999")
            .withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withGender("female")
            .withDoctor("Ben")
            .withTags("Arthritic")
            .withDrugAllergy("NKDA").build();
    public static final Person FIONA = new PersonBuilder()
            .withNric("S1111111F")
            .withName("Fiona Kunz")
            .withDate("11/01/2002")
            .withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withGender("female")
            .withDoctor("Shannon")
            .withTags("Diabetic")
            .withDrugAllergy("Aspirin Panadol")
            .withMedicines("Aspirin")
            .build();
    public static final Person GEORGE = new PersonBuilder()
            .withNric("S2222222G")
            .withName("George Best")
            .withDate("11/12/2004")
            .withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withGender("male")
            .withDoctor("Shafiq")
            .withTags("Diabetic")
            .withDrugAllergy("Lidocaine").build();


    // Manually added
    public static final Person HOON = new PersonBuilder()
            .withNric("S3333333H")
            .withName("Hoon Meier")
            .withDate("14/01/1998")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withAddress("little india")
            .withGender("male").withDoctor("Alex")
            .withDrugAllergy("Phenytoin Panadol").build();
    public static final Person IDA = new PersonBuilder()
            .withNric("T4444444I")
            .withName("Ida Mueller")
            .withDate("09/04/1998")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withAddress("chicago ave")
            .withGender("female").withDoctor("Benjamin")
            .withDrugAllergy("Carbamazepine").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withNric(VALID_NRIC_AMY).withName(VALID_NAME_AMY)
        .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
        .withDrugAllergy(VALID_DRUG_ALLERGY_AMY).withTags(VALID_TAG_DIABETIC).withGender(VALID_GENDER_AMY)
            .withDoctor(VALID_DOCTOR_AMY).build();
    public static final Person BOB = new PersonBuilder().withNric(VALID_NRIC_BOB).withName(VALID_NAME_BOB)
        .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
        .withDrugAllergy(VALID_DRUG_ALLERGY_BOB).withTags(VALID_TAG_DIABETIC, VALID_TAG_OSTEOPOROTIC)
            .withDoctor(VALID_DOCTOR_BOB).withGender(VALID_GENDER_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

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
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
