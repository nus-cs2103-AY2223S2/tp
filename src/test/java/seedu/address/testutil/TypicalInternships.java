package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.NoteList;
import seedu.address.model.TodoList;
import seedu.address.model.documents.CoverLetterLink;
import seedu.address.model.documents.Documents;
import seedu.address.model.documents.ResumeLink;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.InternshipStatus;

/**
 * A utility class containing a list of {@code InternshipApplication} objects to be used in tests.
 */
public class TypicalInternships {
    public static final InternshipApplication ALICE = new InternshipBuilder().withCompanyName("Alice Wonder")
        .withJobTitle("Software Engineer").build();
    public static final InternshipApplication BENSON = new InternshipBuilder().withCompanyName("Benson Meier")
        .withJobTitle("Software Engineer").build();
    public static final InternshipApplication CARL = new InternshipBuilder().withCompanyName("Carl Kurz")
        .withJobTitle("Web Developer").withStatus(InternshipStatus.PENDING).build();
    public static final InternshipApplication GOOGLE = new InternshipBuilder()
            .withCompanyName("Google").withJobTitle("Product Manager").build();
    public static final InternshipApplication NETFLIX = new InternshipBuilder()
            .withCompanyName("Netflix")
            .withJobTitle("Network Engineer")
            .withDocuments(new Documents(new ResumeLink("https://drive.example.com/resume_netflix"),
                    new CoverLetterLink("https://drive.example.com/coverletter_netflix"))).build();
    public static final InternshipApplication DANIEL = new InternshipBuilder().withCompanyName("Daniel Meier")
        .withJobTitle("Software Engineer").build();
    public static final InternshipApplication ELLE = new InternshipBuilder().withCompanyName("Elle Meyer")
        .withJobTitle("Web Developer").withStatus(InternshipStatus.PENDING).build();
    public static final InternshipApplication FIONA = new InternshipBuilder().withCompanyName("Fiona Kunz")
        .withJobTitle("Software Engineer").build();
    public static final InternshipApplication GEORGE = new InternshipBuilder().withCompanyName("George Best")
        .withJobTitle("Software Engineer").withStatus(InternshipStatus.PENDING).build();
    public static final InternshipApplication ORACLE = new InternshipBuilder()
            .withCompanyName("Oracle")
            .withJobTitle("Data Engineer")
            .withDocuments(new Documents(new ResumeLink("https://drive.example.com/resume_oracle"),
                    new CoverLetterLink("https://drive.example.com/coverletter_oracle"))).build();
    public static final InternshipApplication HARRY = new InternshipBuilder().withCompanyName("Harry Better")
            .withJobTitle("Web Developer").withStatus(InternshipStatus.PENDING).build();
    public static final InternshipApplication IAN = new InternshipBuilder().withCompanyName("Ian Hande")
            .withJobTitle("Web Developer").withStatus(InternshipStatus.REJECTED).build();
    public static final InternshipApplication JAMES = new InternshipBuilder().withCompanyName("Fiona K")
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
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, GOOGLE, NETFLIX, DANIEL, ELLE, FIONA, GEORGE,
                HARRY, IAN, JAMES, ORACLE));
    }

    public static NoteList getTypicalNoteList() {
        return new NoteList();
    }

    public static TodoList getTypicalTodoList() {
        return new TodoList();
    }
}
