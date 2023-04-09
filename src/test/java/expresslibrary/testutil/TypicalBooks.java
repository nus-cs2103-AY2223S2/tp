package expresslibrary.testutil;

import expresslibrary.model.book.Book;

/**
 * A utility class containing a list of {@code Book} objects to be used in
 * tests.
 */
public class TypicalBooks {
    public static final Book A_GAME_OF_THRONES = new BookBuilder()
            .withTitle("A Game of Thrones")
            .withAuthor("George RR Martin")
            .withIsbn("9780553103540")
            .build();

    public static final Book BELOVED = new BookBuilder()
            .withTitle("Beloved")
            .withAuthor("Toni Morrison")
            .withIsbn("9781400033416")
            .build();

    public static final Book CRIME_AND_PUNISHMENT = new BookBuilder()
            .withTitle("Crime and Punishment")
            .withAuthor("Fyodor Dostoevsky")
            .withIsbn("9780486415871")
            .build();

    public static final Book DUNE = new BookBuilder()
            .withTitle("Dune")
            .withAuthor("Frank Herbert")
            .withIsbn("9780441172719")
            .build();

    public static final Book EMMA = new BookBuilder()
            .withTitle("Emma")
            .withAuthor("Jane Austen")
            .withIsbn("9780141439587")
            .build();

    public static final Book FRANKENSTEIN = new BookBuilder()
            .withTitle("Frankenstein")
            .withAuthor("Mary Shelley")
            .withIsbn("9780486282114")
            .build();

    public static final Book GREAT_GATSBY = new BookBuilder()
            .withTitle("Great Gatsby")
            .withAuthor("F Scott Fitzgerald")
            .withIsbn("9780743273565")
            .build();

    public static final Book HEART_OF_DARKNESS = new BookBuilder()
            .withTitle("Heart of Darkness")
            .withAuthor("Joseph Conrad")
            .withIsbn("9780141441672")
            .build();

    public static final Book INVISIBLE_MAN = new BookBuilder()
            .withTitle("Invisible Man")
            .withAuthor("Ralph Ellison")
            .withIsbn("9780679732761")
            .build();

    public static final Book JANE_EYRE = new BookBuilder()
            .withTitle("Jane Eyre")
            .withAuthor("Charlotte Bronte")
            .withIsbn("9780141441146")
            .build();

    private TypicalBooks() {} // prevents instantiation

    public static Book[] getTypicalBooks() {
        return new Book[] { A_GAME_OF_THRONES, BELOVED, CRIME_AND_PUNISHMENT, DUNE, EMMA, FRANKENSTEIN,
            GREAT_GATSBY, HEART_OF_DARKNESS, INVISIBLE_MAN, JANE_EYRE };
    }
}
