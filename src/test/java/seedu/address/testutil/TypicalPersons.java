//package seedu.address.testutil;
//
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import seedu.address.model.AddressBook;
//import seedu.address.model.contact.Contact;
//import seedu.address.model.contact.Email;
//import seedu.address.model.contact.Phone;
//import seedu.address.model.person.Person;
//
///**
// * A utility class containing a list of {@code Person} objects to be used in tests.
// */
//public class TypicalPersons {
//
//    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
//            .withAddress("123, Jurong West Ave 6, #08-111").withEmail(PersonBuilder.VALID_EMAIL_PLACEHOLDER)
//            .withPhone(PersonBuilder.VALID_PHONE_PLACEHOLDER)
//            .withTags("friends").build();
//    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
//            .withAddress("311, Clementi Ave 2, #02-25")
//            .withEmail(PersonBuilder.VALID_EMAIL_PLACEHOLDER).withPhone(PersonBuilder.VALID_PHONE_PLACEHOLDER)
//            .withTags("owesMoney", "friends")
//            .withContact(new Contact(new Phone("98765432"), new Email("johnd@example.com"))).build();
//    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
//            .withPhone(PersonBuilder.VALID_PHONE_PLACEHOLDER)
//            .withEmail(PersonBuilder.VALID_EMAIL_PLACEHOLDER).withAddress("wall street").build();
//    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
//            .withPhone(PersonBuilder.VALID_PHONE_PLACEHOLDER)
//            .withEmail(PersonBuilder.VALID_EMAIL_PLACEHOLDER).withAddress("10th street").withTags("friends").build();
//    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
//            .withPhone(PersonBuilder.VALID_PHONE_PLACEHOLDER)
//            .withEmail(PersonBuilder.VALID_EMAIL_PLACEHOLDER).withAddress("michegan ave").build();
//    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
//            .withPhone(PersonBuilder.VALID_PHONE_PLACEHOLDER)
//            .withEmail(PersonBuilder.VALID_EMAIL_PLACEHOLDER).withAddress("little tokyo").build();
//    public static final Person GEORGE = new PersonBuilder().withName("George Best")
//            .withPhone(PersonBuilder.VALID_PHONE_PLACEHOLDER)
//            .withEmail(PersonBuilder.VALID_EMAIL_PLACEHOLDER)
//            .withAddress("4th street").build();
//
//    // Manually added
//    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
//            .withPhone(PersonBuilder.VALID_PHONE_PLACEHOLDER)
//            .withEmail(PersonBuilder.VALID_EMAIL_PLACEHOLDER).withAddress("little india").build();
//    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
//            .withPhone(PersonBuilder.VALID_PHONE_PLACEHOLDER)
//            .withEmail(PersonBuilder.VALID_EMAIL_PLACEHOLDER).withAddress("chicago ave").build();
//
//    // Manually added - Person's details found in {@code CommandTestUtil}
//    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
//            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
//    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
//            .withPhone(PersonBuilder.VALID_PHONE_PLACEHOLDER)
//            .withEmail(PersonBuilder.VALID_EMAIL_PLACEHOLDER)
//            .withAddress(VALID_ADDRESS_BOB)
//            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
//            .build();
//
//    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER
//
//    private TypicalPersons() {} // prevents instantiation
//
//    /**
//     * Returns an {@code AddressBook} with all the typical persons.
//     */
//    public static AddressBook getTypicalAddressBook() {
//        AddressBook ab = new AddressBook();
//        for (Person person : getTypicalPersons()) {
//            ab.addPerson(person);
//        }
//        return ab;
//    }
//
//    public static List<Person> getTypicalPersons() {
//        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
//    }
//}
