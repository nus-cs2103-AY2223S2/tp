package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.NoteList;
import seedu.address.model.TodoList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalInternships {
    public static final InternshipApplication ALICE = new InternshipBuilder().withCompanyName("Alice Wonder")
    .withJobTitle("Software Engineer").build();
    public static final InternshipApplication BENSON = new InternshipBuilder().withCompanyName("Benson Meier")
    .withJobTitle("Software Engineer").build();
    public static final InternshipApplication CARL = new InternshipBuilder().withCompanyName("Carl Kurz")
    .withJobTitle("Software Engineer").build();
    public static final InternshipApplication DANIEL = new InternshipBuilder().withCompanyName("Daniel Meier")
    .withJobTitle("Software Engineer").build();
    public static final InternshipApplication ELLE = new InternshipBuilder().withCompanyName("Elle Meyer")
    .withJobTitle("Software Engineer").build();
    public static final InternshipApplication FIONA = new InternshipBuilder().withCompanyName("Fiona Kunz")
    .withJobTitle("Software Engineer").build();
    public static final InternshipApplication GEORGE = new InternshipBuilder().withCompanyName("George Best")
    .withJobTitle("Software Engineer").build();


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
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static NoteList getTypicalNoteList() {
        return new NoteList();
    }

    public static TodoList getTypicalTodoList() {
        return new TodoList();
    }
}
