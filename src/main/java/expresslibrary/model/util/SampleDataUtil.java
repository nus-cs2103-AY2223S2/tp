package expresslibrary.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import expresslibrary.model.ExpressLibrary;
import expresslibrary.model.ReadOnlyExpressLibrary;
import expresslibrary.model.book.Author;
import expresslibrary.model.book.Book;
import expresslibrary.model.book.Isbn;
import expresslibrary.model.book.Title;
import expresslibrary.model.person.Email;
import expresslibrary.model.person.Name;
import expresslibrary.model.person.Person;
import expresslibrary.model.person.Phone;
import expresslibrary.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ExpressLibrary} with sample
 * data.
 */
public class SampleDataUtil {
    private static final Book[] sampleBooks = {
        new Book(new Title("To Kill a Mockingbird"), new Author("Harper Lee"), new Isbn("9780446310789")),
        new Book(new Title("1984"), new Author("George Orwell"), new Isbn("9780451524935")),
        new Book(new Title("The Great Gatsby"), new Author("F Scott Fitzgerald"), new Isbn("9780743273565")),
        new Book(new Title("Pride and Prejudice"), new Author("Jane Austen"), new Isbn("9780141439518")),
        new Book(new Title("The Catcher in the Rye"), new Author("JD Salinger"), new Isbn("9780316769488")),
        new Book(new Title("Brave New World"), new Author("Aldous Huxley"), new Isbn("9780060850524")),
        new Book(new Title("One Hundred Years of Solitude"), new Author("Gabriel Garcia Marquez"),
                new Isbn("9780060883287")),
        new Book(new Title("The Hobbit"), new Author("JRR Tolkien"), new Isbn("9780547928227")),
        new Book(new Title("The Lord of the Rings"), new Author("JRR Tolkien"), new Isbn("9780544003415")),
        new Book(new Title("Harry Potter and the Philosophers Stone"), new Author("JK Rowling"),
                new Isbn("9780747532743"))
    };

    private static final Person[] samplePersons = {
        new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
            new HashSet<>(), getTagSet("friends")),
        new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
            new HashSet<>(), getTagSet("colleagues", "friends")),
        new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
            new HashSet<>(), getTagSet("neighbours")),
        new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
            new HashSet<>(), getTagSet("family")),
        new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
            new HashSet<>(), getTagSet("classmates")),
        new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
            new HashSet<>(), getTagSet("colleagues"))
    };

    public static ReadOnlyExpressLibrary getSampleExpressLibrary() {
        ExpressLibrary sampleEl = new ExpressLibrary();
        randomSampleBorrow();
        for (Person samplePerson : samplePersons) {
            sampleEl.addPerson(samplePerson);
        }
        for (Book sampleBook : sampleBooks) {
            sampleEl.addBook(sampleBook);
        }
        return sampleEl;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Randomly borrows books for each person for sample purposes.
     */
    public static void randomSampleBorrow() {
        borrowBooks(0, 0, 1);
        borrowBooks(1, 2, 3, 4);
        borrowBooks(2, 6, 7);
        borrowBooks(3, 5);
        borrowBooks(4, 8);
    }

    /**
     * Helper method to borrow books for sample purposes.
     */
    public static void borrowBooks(Integer personIndex, Integer... bookIndexes) {
        LocalDate sampleBorrowDate = LocalDate.now();
        LocalDate sampleReturnDate = sampleBorrowDate.plusDays(14);
        for (Integer bookIndex : bookIndexes) {
            sampleBooks[bookIndex].loanBookTo(samplePersons[personIndex], sampleBorrowDate, sampleReturnDate);
            samplePersons[personIndex].borrowBook(sampleBooks[bookIndex]);
        }
    }
}
