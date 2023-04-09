package expresslibrary.testutil;

import java.time.LocalDate;

import expresslibrary.logic.commands.EditBookCommand.EditBookDescriptor;
import expresslibrary.model.book.Author;
import expresslibrary.model.book.Book;
import expresslibrary.model.book.Isbn;
import expresslibrary.model.book.Title;
import expresslibrary.model.person.Person;

/**
 * A utility class to help with building EditBookDescriptor objects.
 */
public class EditBookDescriptorBuilder {

    private EditBookDescriptor descriptor;

    public EditBookDescriptorBuilder() {
        descriptor = new EditBookDescriptor();
    }

    public EditBookDescriptorBuilder(EditBookDescriptor descriptor) {
        this.descriptor = new EditBookDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBookDescriptor} with fields containing
     * {@code book}'s details
     */
    public EditBookDescriptorBuilder(Book book) {
        descriptor = new EditBookDescriptor();
        descriptor.setTitle(book.getTitle());
        descriptor.setAuthor(book.getAuthor());
        descriptor.setIsbn(book.getIsbn());
        descriptor.setBorrower(book.getBorrower());
        descriptor.setBorrowDate(book.getBorrowDate());
        descriptor.setDueDate(book.getDueDate());
    }

    /**
     * Sets the {@code Title} of the {@code EditBookDescriptor} that we are
     * building.
     */
    public EditBookDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Author} of the {@code EditBookDescriptor} that we are
     * building.
     */
    public EditBookDescriptorBuilder withAuthor(String author) {
        descriptor.setAuthor(new Author(author));
        return this;
    }

    /**
     * Sets the {@code Isbn} of the {@code EditBookDescriptor} that we are
     * building.
     */
    public EditBookDescriptorBuilder withIsbn(String isbn) {
        descriptor.setIsbn(new Isbn(isbn));
        return this;
    }

    /**
     * Sets the {@code Borrower} of the {@code EditBookDescriptor} that we are
     * building.
     */
    public EditBookDescriptorBuilder withBorrower(Person borrower) {
        descriptor.setBorrower(borrower);
        return this;
    }

    /**
     * Sets the {@code BorrowDate} of the {@code EditBookDescriptor} that we are
     * building.
     */
    public EditBookDescriptorBuilder withBorrowDate(LocalDate borrowDate) {
        descriptor.setBorrowDate(borrowDate);
        return this;
    }

    /**
     * Sets the {@code DueDate} of the {@code EditBookDescriptor} that we are
     * building.
     */
    public EditBookDescriptorBuilder withDueDate(LocalDate dueDate) {
        descriptor.setDueDate(dueDate);
        return this;
    }

    public EditBookDescriptor build() {
        return descriptor;
    }
}
