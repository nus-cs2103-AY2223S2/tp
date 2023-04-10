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
                new Isbn("9780747532743")),
        new Book(new Title("To the Lighthouse"), new Author("Virginia Woolf"), new Isbn("9780156907392")),
        new Book(new Title("The Sun Also Rises"), new Author("Ernest Hemingway"), new Isbn("9780684800714")),
        new Book(new Title("The Picture of Dorian Gray"), new Author("Oscar Wilde"), new Isbn("9781593080259")),
        new Book(new Title("The Road"), new Author("Cormac McCarthy"), new Isbn("9780307387899")),
        new Book(new Title("The Hitchhikers Guide to the Galaxy"), new Author("Douglas Adams"),
                new Isbn("9780345391803")),
        new Book(new Title("Beloved"), new Author("Toni Morrison"), new Isbn("9781400033416")),
        new Book(new Title("The Brothers Karamazov"), new Author("Fyodor Dostoevsky"), new Isbn("9780140449242")),
        new Book(new Title("The Trial"), new Author("Franz Kafka"), new Isbn("9780805209990")),
        new Book(new Title("The Handmaids Tale"), new Author("Margaret Atwood"), new Isbn("9780385490818")),
        new Book(new Title("The Bell Jar"), new Author("Sylvia Plath"), new Isbn("9780061148514"))

    };

    private static final Person[] samplePersons = {
        new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new HashSet<>(), getTagSet("researcher", "engineering")),
        new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new HashSet<>(), getTagSet("manager", "finance")),
        new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new HashSet<>(), getTagSet("intern", "marketing")),
        new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new HashSet<>(), getTagSet("executive", "strategy")),
        new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new HashSet<>(), getTagSet("researcher", "data")),
        new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new HashSet<>(), getTagSet("manager", "operations")),
        new Person(new Name("Evelyn Tan"), new Phone("93218901"), new Email("evelyntan@example.com"),
                new HashSet<>(), getTagSet("executive", "marketing")),
        new Person(new Name("Francis Lim"), new Phone("91827654"), new Email("francislim@example.com"),
                new HashSet<>(), getTagSet("engineer", "technology")),
        new Person(new Name("Geraldine Ng"), new Phone("98236547"), new Email("geraldineng@example.com"),
                new HashSet<>(), getTagSet("manager", "hr")),
        new Person(new Name("Henry Tan"), new Phone("93456789"), new Email("henrytan@example.com"),
                new HashSet<>(), getTagSet("researcher", "innovation")),
        new Person(new Name("Isabella Chew"), new Phone("98452678"), new Email("isabellachew@example.com"),
                new HashSet<>(), getTagSet("analyst", "finance")),
        new Person(new Name("Jason Wong"), new Phone("91019876"), new Email("jasonwong@example.com"),
                new HashSet<>(), getTagSet("engineer", "software")),
        new Person(new Name("Karen Lim"), new Phone("90123456"), new Email("karenlim@example.com"),
                new HashSet<>(), getTagSet("manager", "operations")),
        new Person(new Name("Leonard Teo"), new Phone("98876543"), new Email("leonardteo@example.com"),
                new HashSet<>(), getTagSet("researcher", "biotechnology")),
        new Person(new Name("Maggie Lee"), new Phone("92234567"), new Email("maggielee@example.com"),
                new HashSet<>(), getTagSet("analyst", "data")),
        new Person(new Name("Nigel Tan"), new Phone("98675432"), new Email("nigeltan@example.com"),
                new HashSet<>(), getTagSet("engineer", "mechanical")),
        new Person(new Name("Hannah Lee"), new Phone("92637271"), new Email("hannahlee@example.com"),
            new HashSet<>(), getTagSet("developer", "engineering")),
        new Person(new Name("Ivy Tan"), new Phone("91028372"), new Email("ivytan@example.com"),
            new HashSet<>(), getTagSet("designer", "marketing")),
        new Person(new Name("Jeremy Lim"), new Phone("92183749"), new Email("jeremylim@example.com"),
            new HashSet<>(), getTagSet("manager", "operations")),
        new Person(new Name("Karen Ng"), new Phone("93284018"), new Email("karenng@example.com"),
            new HashSet<>(), getTagSet("researcher", "engineering"))
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
        final LocalDate today = LocalDate.now();
        borrowBooks(today.minusDays(12), today.plusDays(2), 0, 0);
        borrowBooks(today.minusDays(18), today.minusDays(4), 0, 1);
        borrowBooks(today.minusDays(20), today.minusDays(6), 1, 2, 3);
        borrowBooks(today.minusDays(11), today.plusDays(3), 3, 4);
        borrowBooks(today.minusDays(2), today.plusDays(12), 2, 6, 7);
        borrowBooks(today, today.plusDays(14), 3, 5);
        borrowBooks(today.minusDays(5), today.plusDays(9), 4, 8);
        borrowBooks(today.minusDays(7), today.plusDays(7), 19, 17, 18, 19);
    }

    /**
     * Helper method to borrow books for sample purposes.
     */
    public static void borrowBooks(LocalDate borrowDate, LocalDate dueDate,
        Integer personIndex, Integer... bookIndexes) {
        for (Integer bookIndex : bookIndexes) {
            sampleBooks[bookIndex].loanBookTo(samplePersons[personIndex], borrowDate, dueDate);
            samplePersons[personIndex].borrowBook(sampleBooks[bookIndex]);
        }
    }
}
