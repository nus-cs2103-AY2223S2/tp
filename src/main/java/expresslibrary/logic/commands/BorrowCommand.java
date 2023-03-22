package expresslibrary.logic.commands;

import expresslibrary.commons.core.Messages;
import expresslibrary.commons.core.index.Index;
import expresslibrary.logic.commands.exceptions.CommandException;
import expresslibrary.model.Model;
import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static expresslibrary.commons.util.CollectionUtil.requireAllNonNull;
import static expresslibrary.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class BorrowCommand extends Command {

    public static final String COMMAND_WORD = "borrow";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lends a book identified by the index number used in the book listing to "
            + "the person identified by the index number used in the last person listing. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "b/ [BOOK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "b/ 2";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Book Index: %2$s";

    public static final String MESSAGE_BORROW_SUCCESS = "Person: %1$s borrowed Book: %2$s";

    private final Index personIndex;
    private final Index bookIndex;

    public BorrowCommand(Index personIndex, Index bookIndex) {
        requireAllNonNull(personIndex, bookIndex);

        this.personIndex = personIndex;
        this.bookIndex = bookIndex;
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
        if (bookToBorrow.getIsBorrowed()) {
            //throw new CommandException(Messages.MESSAGE_BOOK_BORROWED);
        }

        Set<Book> borrowedBooks = personToEdit.getBooks();
        borrowedBooks.add(bookToBorrow);

        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), borrowedBooks, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson, bookToBorrow));
    }

    /**
     * Generates a command execution success message based on whether the borrowed book is added to
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit, Book bookToBorrow) {
        //String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
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
