package expresslibrary.model.person;

import static expresslibrary.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import expresslibrary.model.book.Book;
import expresslibrary.model.tag.Tag;

/**
 * Represents a Person in the express library.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Person {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Set<Book> books = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Set<Book> books, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.books.addAll(books);
        this.tags.addAll(tags);
    }


    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public boolean borrowedAtLeastOneBook() {
        return !books.isEmpty();
    }

    public void borrowBook(Book book) {
        books.add(book);
    }

    public void returnBook(Book book) {
        books.remove(book);
    }

    public boolean hasBorrowedBook(Book book) {
        return books.contains(book);
    }

    /**
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && ( otherPerson.getEmail().equals(getEmail())
                || otherPerson.getPhone().equals(getPhone()) );
    }

    /**
     * Returns true if a given string is a valid person.
     */
    public static boolean isValidPerson(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getBooks().equals(getBooks())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, books, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail());

        Set<Book> books = getBooks();
        if (!books.isEmpty()) {
            builder.append("; Books: ");
            books.forEach(builder::append);
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
