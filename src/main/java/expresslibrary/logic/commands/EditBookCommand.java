package expresslibrary.logic.commands;

import static expresslibrary.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_BORROW_DATE;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_DUE_DATE;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_ISBN;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_TITLE;
import static expresslibrary.model.Model.PREDICATE_SHOW_ALL_BOOKS;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import expresslibrary.commons.core.Messages;
import expresslibrary.commons.core.index.Index;
import expresslibrary.commons.util.CollectionUtil;
import expresslibrary.logic.commands.exceptions.CommandException;
import expresslibrary.model.Model;
import expresslibrary.model.book.Author;
import expresslibrary.model.book.Book;
import expresslibrary.model.book.Isbn;
import expresslibrary.model.book.Title;
import expresslibrary.model.person.Person;

/**
 * Edits the details of an existing book in the express library.
 */
public class EditBookCommand extends Command {

    public static final String COMMAND_WORD = "editBook";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the book identified "
            + "by the index number used in the displayed book list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_AUTHOR + "AUTHOR] "
            + "[" + PREFIX_ISBN + "ISBN] "
            + "[" + PREFIX_BORROW_DATE + "BORROW_DATE] "
            + "[" + PREFIX_DUE_DATE + "DUE_DATE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ISBN + "9876543210000 "
            + PREFIX_BORROW_DATE + "18/04/2022 "
            + PREFIX_DUE_DATE + "25/04/2022";

    public static final String MESSAGE_EDIT_BOOK_SUCCESS = "Edited book: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_BOOK = "This book already exists in the express library.";

    private final Index index;
    private final EditBookDescriptor editBookDescriptor;

    /**
     * @param index              of the book in the filtered book list to edit
     * @param editBookDescriptor details to edit the book with
     */
    public EditBookCommand(Index index, EditBookDescriptor editBookDescriptor) {
        requireNonNull(index);
        requireNonNull(editBookDescriptor);

        this.index = index;
        this.editBookDescriptor = new EditBookDescriptor(editBookDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownBookList = model.getFilteredBookList();
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownBookList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Book bookToEdit = lastShownBookList.get(index.getZeroBased());
        Book editedBook = createEditedBook(bookToEdit, editBookDescriptor);

        Person borrower = editedBook.getBorrower();
        if (borrower != null) {
            if (!lastShownPersonList.contains(borrower)) {
                throw new CommandException(Messages.MESSAGE_BORROWER_NOT_FOUND);
            }
            Person origPerson = model.getPerson(borrower);
            borrower.returnBook(bookToEdit);
            borrower.borrowBook(editedBook);
            model.setPerson(origPerson, borrower);
        }

        if (!bookToEdit.isSameBook(editedBook) && model.hasBook(editedBook)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOK);
        }

        model.setBook(bookToEdit, editedBook);
        model.updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS);
        return new CommandResult(String.format(MESSAGE_EDIT_BOOK_SUCCESS, editedBook));
    }

    /**
     * Creates and returns a {@code Book} with the details of {@code bookToEdit}
     * edited with {@code editBookDescriptor}.
     *
     * @throws CommandException
     */
    private static Book createEditedBook(Book bookToEdit, EditBookDescriptor editBookDescriptor)
            throws CommandException {
        assert bookToEdit != null;

        Title updatedTitle = editBookDescriptor.getTitle().orElse(bookToEdit.getTitle());
        Author updatedAuthor = editBookDescriptor.getAuthor().orElse(bookToEdit.getAuthor());
        Isbn updatedIsbn = editBookDescriptor.getIsbn().orElse(bookToEdit.getIsbn());
        Person updatedBorrower = editBookDescriptor.getBorrower().orElse(bookToEdit.getBorrower());
        LocalDate updatedBorrowDate = editBookDescriptor.getBorrowDate().orElse(bookToEdit.getBorrowDate());
        LocalDate updatedDueDate = editBookDescriptor.getDueDate().orElse(bookToEdit.getDueDate());

        Book editedBook = new Book(updatedTitle, updatedAuthor, updatedIsbn);
        if (updatedBorrower != null) {
            if (updatedBorrowDate.isAfter(updatedDueDate)) {
                throw new CommandException(Messages.MESSAGE_BORROW_DATE_AFTER_DUE_DATE);
            }
            if (updatedBorrowDate.isAfter(LocalDate.now())) {
                throw new CommandException(Messages.MESSAGE_BORROW_DATE_AFTER_CURRENT_DATE);
            }
            editedBook.loanBookTo(updatedBorrower, updatedBorrowDate, updatedDueDate);
        } else {
            // Make sure user can't edit borrow date and due date if book is not borrowed
            if (editBookDescriptor.getBorrowDate().orElse(null) != null) {
                throw new CommandException(Messages.MESSAGE_INVALID_EDIT_BORROW_DATE);
            }
            if (editBookDescriptor.getDueDate().orElse(null) != null) {
                throw new CommandException(Messages.MESSAGE_INVALID_EDIT_BORROW_DATE);
            }
        }

        return editedBook;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditBookCommand)) {
            return false;
        }

        // state check
        EditBookCommand e = (EditBookCommand) other;
        return index.equals(e.index)
                && editBookDescriptor.equals(e.editBookDescriptor);
    }

    /**
     * Stores the details to edit the book with. Each non-empty field value will
     * replace the
     * corresponding field value of the book.
     */
    public static class EditBookDescriptor {
        private Title title;
        private Author author;
        private Isbn isbn;
        private Person borrower;
        private LocalDate borrowDate;
        private LocalDate dueDate;

        public EditBookDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditBookDescriptor(EditBookDescriptor toCopy) {
            setTitle(toCopy.title);
            setAuthor(toCopy.author);
            setIsbn(toCopy.isbn);
            setBorrower(toCopy.borrower);
            setBorrowDate(toCopy.borrowDate);
            setDueDate(toCopy.dueDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, author, isbn, borrowDate, dueDate);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public Optional<Author> getAuthor() {
            return Optional.ofNullable(author);
        }

        public void setIsbn(Isbn isbn) {
            this.isbn = isbn;
        }

        public Optional<Isbn> getIsbn() {
            return Optional.ofNullable(isbn);
        }

        public void setBorrower(Person borrower) {
            this.borrower = borrower;
        }

        public Optional<Person> getBorrower() {
            return Optional.ofNullable(borrower);
        }

        public void setBorrowDate(LocalDate borrowDate) {
            this.borrowDate = borrowDate;
        }

        public Optional<LocalDate> getBorrowDate() {
            return Optional.ofNullable(borrowDate);
        }

        public void setDueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
        }

        public Optional<LocalDate> getDueDate() {
            return Optional.ofNullable(dueDate);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditBookDescriptor)) {
                return false;
            }

            // state check
            EditBookDescriptor e = (EditBookDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getAuthor().equals(e.getAuthor())
                    && getIsbn().equals(e.getIsbn())
                    && getBorrower().equals(e.getBorrower())
                    && getBorrowDate().equals(e.getBorrowDate())
                    && getDueDate().equals(e.getDueDate());
        }
    }
}
