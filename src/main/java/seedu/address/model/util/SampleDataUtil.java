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
import seedu.address.model.person.Address;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.Email;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TaskType;
import seedu.address.model.task.ApplicationDeadline;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.Note;
import seedu.address.model.task.NoteContent;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
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
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
