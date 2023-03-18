package expresslibrary.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import expresslibrary.model.ExpressLibrary;
import expresslibrary.model.ReadOnlyExpressLibrary;
import expresslibrary.model.book.Author;
import expresslibrary.model.book.Book;
import expresslibrary.model.book.Isbn;
import expresslibrary.model.book.Title;
import expresslibrary.model.person.Address;
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
    private static final Book[] BOOKS = {
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

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getBooksSet(BOOKS[0], BOOKS[1]), getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getBooksSet(BOOKS[3], BOOKS[2], BOOKS[4]),
                    getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getBooksSet(BOOKS[7], BOOKS[6], BOOKS[5]),
                    getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getBooksSet(BOOKS[9], BOOKS[4], BOOKS[5]),
                    getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getBooksSet(BOOKS[8]),
                    getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getBooksSet(),
                    getTagSet("colleagues"))
        };
    }

    public static ReadOnlyExpressLibrary getSampleExpressLibrary() {
        ExpressLibrary sampleAb = new ExpressLibrary();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
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
     * Returns a books set containing the list of books given.
     */
    public static Set<Book> getBooksSet(Book... books) {
        return Arrays.stream(books)
                .collect(Collectors.toSet());
    }
}
