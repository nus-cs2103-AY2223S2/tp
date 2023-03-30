package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.NoteList;
import seedu.address.model.TodoList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.InternshipStatus;

/**
 * A utility class containing a list of {@code InternshipApplication} objects to be used in tests.
 */
public class TypicalInternships {
    public static final InternshipApplication META = new InternshipBuilder().withCompanyName("Meta")
            .withJobTitle("Software Tester")
            .withContact(new Contact(new Phone("33333333"), new Email("example@meta.com"))).build();
    public static final InternshipApplication BANK_OF_AMERICA = new InternshipBuilder()
            .withCompanyName("Bank of America").withJobTitle("Software Engineer").build();
    public static final InternshipApplication ALICE = new InternshipBuilder().withCompanyName("Alice Wonder")
            .withJobTitle("Software Engineer").build();
    public static final InternshipApplication BENSON = new InternshipBuilder().withCompanyName("Benson Meier")
            .withJobTitle("Software Engineer").build();
    public static final InternshipApplication CARL = new InternshipBuilder().withCompanyName("Carl Kurz")
        .withJobTitle("Web Developer").withStatus(InternshipStatus.PENDING).build();
    public static final InternshipApplication DANIEL = new InternshipBuilder().withCompanyName("Daniel Meier")
        .withJobTitle("Software Engineer").build();
    public static final InternshipApplication ELLE = new InternshipBuilder().withCompanyName("Elle Meyer")
        .withJobTitle("Web Developer").withStatus(InternshipStatus.PENDING).build();
    public static final InternshipApplication FIONA = new InternshipBuilder().withCompanyName("Fiona Kunz")
        .withJobTitle("Software Engineer").build();
    public static final InternshipApplication GEORGE = new InternshipBuilder().withCompanyName("George Best")
            .withJobTitle("Software Engineer").withStatus(InternshipStatus.PENDING).build();
    public static final InternshipApplication HARRY = new InternshipBuilder().withCompanyName("Harry Better")
            .withJobTitle("Web Developer").withStatus(InternshipStatus.PENDING).build();
    public static final InternshipApplication IAN = new InternshipBuilder().withCompanyName("Ian Hande")
            .withJobTitle("Web Developer").withStatus(InternshipStatus.REJECTED).build();
    public static final InternshipApplication JAMES = new InternshipBuilder().withCompanyName("Fiona K")
            .withJobTitle("Software Engineer").build();
    public static final InternshipApplication AMAZON = new InternshipBuilder().withCompanyName("Amazon")
            .withJobTitle("Cloud Engineer")
            .withContact(new Contact(new Phone("66666666"), new Email("example@amazon.com"))).build();

    private TypicalInternships() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical internships.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (InternshipApplication internship : getTypicalInternships()) {
            ab.addApplication(internship);
        }
        return ab;
    }

    public static List<InternshipApplication> getTypicalInternships() {
        return new ArrayList<>(Arrays.asList(META, BANK_OF_AMERICA, ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE,
                HARRY, IAN, JAMES, AMAZON));
    }

    public static NoteList getTypicalNoteList() {
        return new NoteList();
    }

    public static TodoList getTypicalTodoList() {
        return new TodoList();
    }
}
