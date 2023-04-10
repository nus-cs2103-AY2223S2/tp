package expresslibrary.logic.commands;

import static expresslibrary.commons.util.CollectionUtil.requireAllNonNull;
import static expresslibrary.model.Model.PREDICATE_SHOW_ALL_BOOKS;
import static expresslibrary.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDate;
import java.util.List;

import expresslibrary.commons.core.Messages;
import expresslibrary.commons.core.index.Index;
import expresslibrary.logic.commands.exceptions.CommandException;
import expresslibrary.model.Model;
import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;

/**
 * Lends a book to a person in the express library.
 */
public class BorrowCommand extends Command {

    public static final String COMMAND_WORD = "borrow";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lends a book identified by the index number used in the book listing to "
            + "the person identified by the index number used in the last person listing. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "b/[BOOK] d/[DATE dd/mm/yyyy]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "b/2 d/22/09/2025";


    public static final String MESSAGE_BORROW_SUCCESS = "Person: %1$s borrowed Book: %2$s";

    private final Index personIndex;
    private final Index bookIndex;
    private final LocalDate dueDate;

    /**
     * @param personIndex of the person in the filtered person list to lend book to
     * @param bookIndex of the book to lend to the specified person
     * @param dueDate of the book stating when it should be returned
     */
    public BorrowCommand(Index personIndex, Index bookIndex, LocalDate dueDate) {
        requireAllNonNull(personIndex, bookIndex, dueDate);

        this.personIndex = personIndex;
        this.bookIndex = bookIndex;
        this.dueDate = dueDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> personList = model.getFilteredPersonList();
        List<Book> bookList = model.getFilteredBookList();

        if (personIndex.getZeroBased() >= personList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        if (bookIndex.getZeroBased() >= bookList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Person personToEdit = personList.get(personIndex.getZeroBased());
        Book bookToBorrow = bookList.get(bookIndex.getZeroBased());

        // Checkout bookToBorrow (due date, borrower, borrowDate)
        assert bookToBorrow != null;

        // check if book has been borrowed by someone else
        if (bookToBorrow.getIsBorrowed() && !bookToBorrow.getBorrower().isSamePerson(personToEdit)) {
            throw new CommandException(Messages.MESSAGE_BOOK_BORROWED);
        }

        Book origBook = model.getBook(bookToBorrow);

        // Create the person copy
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getBooks(), personToEdit.getTags());

        // Update person with borrowed book
        editedPerson.borrowBook(bookToBorrow);

        // Update book's field with borrower
        bookToBorrow.loanBookTo(editedPerson, LocalDate.now(), dueDate);

        model.setPerson(personToEdit, editedPerson);
        model.setBook(origBook, bookToBorrow);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS);

        return new CommandResult(generateSuccessMessage(editedPerson, bookToBorrow));
    }

    /**
     * Generates a command execution success message based on whether the borrowed book is added to
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit, Book bookToBorrow) {
        return String.format(MESSAGE_BORROW_SUCCESS, personToEdit, bookToBorrow);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BorrowCommand)) {
            return false;
        }

        // state check
        BorrowCommand e = (BorrowCommand) other;
        return personIndex.equals(e.personIndex)
                && bookIndex.equals(e.bookIndex);
    }

}
