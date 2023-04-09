package seedu.socket.testutil;

import static seedu.socket.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_GITHUBPROFILE_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_GITHUBPROFILE_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_LANGUAGE_CPLUSPLUS;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_LANGUAGE_PYTHON;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.socket.model.Socket;
import seedu.socket.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withProfile("alice-pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withProfile("benson-meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withLanguages("Java", "JavaScript")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withProfile("carl-kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withAddress("wall street")
            .withLanguages("C").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withLanguages("Python")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withProfile("elle-meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withLanguages("Python")
            .withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withProfile("hoon-meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withAddress("chicago ave")
            .withLanguages("C++", "C#").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withProfile(VALID_GITHUBPROFILE_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withLanguages(VALID_LANGUAGE_PYTHON)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withProfile(VALID_GITHUBPROFILE_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withLanguages(VALID_LANGUAGE_CPLUSPLUS)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();
    public static final Person EMPTY = new PersonBuilder().withName("Empty Contact")
        .withProfile("")
        .withPhone("")
        .withEmail("")
        .withAddress("")
        .build();
    public static final Person EMPTY_TWO = new PersonBuilder().withName("Empty Contact 2")
        .withProfile("")
        .withPhone("")
        .withEmail("")
        .withAddress("")
        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code Socket} with all the typical persons.
     */
    public static Socket getTypicalSocket() {
        Socket socket = new Socket();
        for (Person person : getTypicalPersons()) {
            socket.addPerson(person);
        }
        return socket;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
