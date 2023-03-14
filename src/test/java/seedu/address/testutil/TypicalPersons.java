package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.Session;

import static seedu.address.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {


    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withPayRate("10")
            .withPhone("94351253").withSession("01-01-2022 12:00", "01-01-2022 13:00")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withPayRate("11").withPhone("98765432").withSession("02-01-2022 14:00", "02-01-2022 15:00")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withPayRate("12").withAddress("wall street").withSession("03-01-2022 16:00", "03-01-2022 17:00")
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withPayRate("13").withAddress("10th street").withSession("01-01-2022 12:00", "01-01-2022 13:00")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withPayRate("14").withAddress("michegan ave").withSession("02-01-2022 14:00", "02-01-2022 15:00")
            .build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withPayRate("15").withAddress("little tokyo").withSession("03-01-2022 16:00", "03-01-2022 17:00")
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withPayRate("16").withAddress("4th street")
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withPayRate("17").withAddress("little india")
            .build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withPayRate("18").withAddress("chicago ave")
            .build();


    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withPayRate(VALID_PAY_RATE_AMY).withAddress(VALID_ADDRESS_AMY).withSession(VALID_SESSION_START_AMY, VALID_SESSION_END_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withPayRate(VALID_PAY_RATE_BOB).withAddress(VALID_ADDRESS_BOB).withSession(VALID_SESSION_START_BOB, VALID_SESSION_END_BOB).withTags(VALID_TAG_HUSBAND,
                VALID_TAG_FRIEND)
            .build();

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
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
