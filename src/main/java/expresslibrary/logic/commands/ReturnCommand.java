package expresslibrary.logic.commands;

import static expresslibrary.commons.util.CollectionUtil.requireAllNonNull;
import static expresslibrary.model.Model.PREDICATE_SHOW_ALL_BOOKS;
import static expresslibrary.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
public class ReturnCommand extends Command {

    public static final String COMMAND_WORD = "return";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Returns a book identified by the index number used in the book listing from "
            + "the person identified by the index number used in the person listing. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "b/[BOOK] \n"
            + "Example: " + COMMAND_WORD + " 3 "
            + "b/2";

    public static final String MESSAGE_RETURN_SUCCESS = "Person: %1$s returned Book: %2$s";

    private final Index personIndex;
    private final Index bookIndex;

    /**
     * @param personIndex of the person in the filtered person list to return a book
     * @param bookIndex of the book to return
     */
    public ReturnCommand(Index personIndex, Index bookIndex) {
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
        Book bookToReturn = bookList.get(bookIndex.getZeroBased());

        assert bookToReturn != null;
        // Check if book ISN'T borrowed
        if (!bookToReturn.getIsBorrowed()) {
            throw new CommandException(Messages.MESSAGE_BOOK_NOT_BORROWED);
        }

        // Check if the book's borrower matches the person specified
        if (!bookToReturn.getBorrower().isSamePerson(personToEdit)) {
            throw new CommandException(Messages.MESSAGE_BOOK_INVALID_BORROWER);
        }

        Book origBook = model.getBook(bookToReturn);

        // Create the person copy
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getBooks(), personToEdit.getTags());

        editedPerson.returnBook(bookToReturn);
        bookToReturn.returnBook();

        model.setPerson(personToEdit, editedPerson);
        model.setBook(origBook, bookToReturn);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS);

        return new CommandResult(generateSuccessMessage(personToEdit, bookToReturn));
    }

    /**
     * Generates a command execution success message based on whether the borrowed book is added to
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit, Book bookToBorrow) {
        return String.format(MESSAGE_RETURN_SUCCESS, personToEdit, bookToBorrow);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReturnCommand)) {
            return false;
        }

        // state check
        ReturnCommand e = (ReturnCommand) other;
        return personIndex.equals(e.personIndex)
                && bookIndex.equals(e.bookIndex);
    }

}
