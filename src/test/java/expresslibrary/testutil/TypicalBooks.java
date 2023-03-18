package expresslibrary.testutil;

import expresslibrary.model.book.Author;
import expresslibrary.model.book.Book;
import expresslibrary.model.book.Isbn;
import expresslibrary.model.book.Title;

/**
 * A utility class containing a list of {@code Book} objects to be used in
 * tests.
 */
public class TypicalBooks {
    public static final Book A_GAME_OF_THRONES = new Book(new Title("A Game of Thrones"),
            new Author("George RR Martin"), new Isbn("9780553103540"));
    public static final Book BELOVED = new Book(new Title("Beloved"), new Author("Toni Morrison"),
            new Isbn("9781400033416"));
    public static final Book CRIME_AND_PUNISHMENT = new Book(new Title("Crime and Punishment"),
            new Author("Fyodor Dostoevsky"), new Isbn("9780486415871"));
    public static final Book DUNE = new Book(new Title("Dune"), new Author("Frank Herbert"), new Isbn("9780441172719"));
    public static final Book EMMA = new Book(new Title("Emma"), new Author("Jane Austen"), new Isbn("9780141439587"));
    public static final Book FRANKENSTEIN = new Book(new Title("Frankenstein"), new Author("Mary Shelley"),
            new Isbn("9780486282114"));
    public static final Book GREAT_GATSBY = new Book(new Title("Great Gatsby"),
            new Author("F Scott Fitzgerald"),
            new Isbn("9780743273565"));
    public static final Book HEART_OF_DARKNESS = new Book(new Title("Heart of Darkness"), new Author("Joseph Conrad"),
            new Isbn("9780141441672"));
    public static final Book INVISIBLE_MAN = new Book(new Title("Invisible Man"), new Author("Ralph Ellison"),
            new Isbn("9780679732761"));
    public static final Book JANE_EYRE = new Book(new Title("Jane Eyre"), new Author("Charlotte Bronte"),
            new Isbn("9780141441146"));

    private TypicalBooks() {} // prevents instantiation

    public static Book[] getTypicalBooks() {
        return new Book[] {A_GAME_OF_THRONES, BELOVED, CRIME_AND_PUNISHMENT, DUNE, EMMA, FRANKENSTEIN,
            GREAT_GATSBY, HEART_OF_DARKNESS, INVISIBLE_MAN, JANE_EYRE};
    }
}
