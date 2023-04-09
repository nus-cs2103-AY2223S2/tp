package seedu.address.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.NoteList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyNote;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.TodoList;
import seedu.address.model.application.CompanyName;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.InternshipStatus;
import seedu.address.model.application.InterviewDate;
import seedu.address.model.application.JobTitle;
import seedu.address.model.application.Location;
import seedu.address.model.application.ProgrammingLanguage;
import seedu.address.model.application.Qualification;
import seedu.address.model.application.Rating;
import seedu.address.model.application.Reflection;
import seedu.address.model.application.Review;
import seedu.address.model.application.Salary;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.documents.CoverLetterLink;
import seedu.address.model.documents.Documents;
import seedu.address.model.documents.ResumeLink;
import seedu.address.model.tag.TaskType;
import seedu.address.model.task.ApplicationDeadline;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.Note;
import seedu.address.model.task.NoteContent;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static InternshipApplication[] getSampleInternshipApplications() {
        return new InternshipApplication[] {
            new InternshipApplication(new CompanyName("Facebook"), new JobTitle("Software Engineer")),
            new InternshipApplication(new CompanyName("Meta"), new JobTitle("Cloud Engineer")),
            new InternshipApplication(new CompanyName("Google"), new JobTitle("Frontend Developer")),
            new InternshipApplication(new CompanyName("Grab"), new JobTitle("Backend Developer"),
                    getReviewSet("Competitive environment"),
                    new Contact(new Phone("98765432"), new Email("career@grab.com")), InternshipStatus.RECEIVED,
                    false,
                    new Documents(new ResumeLink("https://myowndrive.com/resume_grab"),
                            new CoverLetterLink("https://myowndrive.com/cover_letter_grab"))),
            new InternshipApplication(new CompanyName("Foodpanda"), new JobTitle("Fullstack Developer"),
                    getReviewSet("Interesting people", "Interesting atmosphere"),
                    getProgrammingLanguageSet("Java", "C++"),
                    getQualificationSet("Test Qualification", "AWS Cloud Certificate"),
                    new Location("Holland Village"), new Salary("9999 SGD"),
                    getNoteSet("Great food available nearby"),
                    new Rating("5/5"),
                    getReflectionSet("Can improve on coding speed", "Learn more about the company"),
                    new Contact(new Phone("12345678"), new Email("hr@foodpanda.com")), InternshipStatus.PENDING,
                    false, new InterviewDate("2024-01-03 12:31 PM"),
                    new Documents(new ResumeLink("https://example.com/resume_foodpanda"),
                            new CoverLetterLink("https://example.com/cover_letter_foodpanda")))
        };
    }

    public static InternshipTodo[] getSampleTodo() {
        return new InternshipTodo[] {
            new InternshipTodo(new CompanyName("Google"), new JobTitle("SE"),
                    new ApplicationDeadline(LocalDate.parse("2023-10-01")),
                    new NoteContent("no note"), LocalDate.parse("2023-03-11"), TaskType.TODO),
            new InternshipTodo(new CompanyName("Meta"), new JobTitle("Web Dev"),
                    new ApplicationDeadline(LocalDate.parse("2023-07-10")),
                    new NoteContent("no note"), LocalDate.parse("2023-02-11"), TaskType.TODO),
            new InternshipTodo(new CompanyName("Amazon"), new JobTitle("App Dev"),
                    new ApplicationDeadline(LocalDate.parse("2023-10-02")),
                    new NoteContent("no note"), LocalDate.parse("2023-01-11"), TaskType.TODO)
        };
    }

    public static Note[] getSampleNotes() {
        return new Note[] {new Note(new NoteContent("Vacation starts next week!")),
            new Note(new NoteContent("Project should be finalized by this week!")),
            new Note(new NoteContent("Write letter!"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (InternshipApplication sampleInternshipApplication : getSampleInternshipApplications()) {
            sampleAb.addInternshipApplication(sampleInternshipApplication);
        }
        return sampleAb;
    }

    public static ReadOnlyTodoList getSampleTodoList() {
        TodoList sampleTd = new TodoList();
        for (InternshipTodo sampleTodo : getSampleTodo()) {
            sampleTd.addTodo(sampleTodo);
        }
        return sampleTd;
    }

    public static ReadOnlyNote getSampleNoteList() {
        NoteList sampleNl = new NoteList();
        for (Note sampleNote : getSampleNotes()) {
            sampleNl.addNote(sampleNote);
        }
        return sampleNl;
    }

    /**
     * Returns a review set containing the list of strings given.
     */
    public static Set<Review> getReviewSet(String... strings) {
        return Arrays.stream(strings)
                .map(Review::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a programming language set containing the list of strings given.
     */
    public static Set<ProgrammingLanguage> getProgrammingLanguageSet(String... strings) {
        return Arrays.stream(strings)
                .map(ProgrammingLanguage::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a qualification set containing the list of strings given.
     */
    public static Set<Qualification> getQualificationSet(String... strings) {
        return Arrays.stream(strings)
                .map(Qualification::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a note set containing the list of strings given.
     */
    public static Set<seedu.address.model.application.Note> getNoteSet(String... strings) {
        return Arrays.stream(strings)
                .map(seedu.address.model.application.Note::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a reflection set containing the list of strings given.
     */
    public static Set<Reflection> getReflectionSet(String... strings) {
        return Arrays.stream(strings)
                .map(Reflection::new)
                .collect(Collectors.toSet());
    }
}
