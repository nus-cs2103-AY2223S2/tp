package expresslibrary.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import expresslibrary.commons.core.Messages;
import expresslibrary.commons.core.index.Index;
import expresslibrary.logic.commands.exceptions.CommandException;
import expresslibrary.model.Model;
import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;

/**
 * Deletes a book identified using it's displayed index from the express
 * library.
 */
public class DeleteBookCommand extends Command {

    public static final String COMMAND_WORD = "deleteBook";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the book identified by the index number used in the displayed book list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_BOOK_SUCCESS = "Deleted Book: %1$s";

    private final Index targetIndex;
    private final Boolean deleteOption;

    /**
     * Constructor for DeleteBookCommand.
     */
    public DeleteBookCommand(Index targetIndex, Boolean deleteOption) {
        this.targetIndex = targetIndex;
        this.deleteOption = deleteOption;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownBookList = model.getFilteredBookList();
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownBookList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Book bookToDelete = lastShownBookList.get(targetIndex.getZeroBased());

        if (bookToDelete.getIsBorrowed()) {
            if (!deleteOption) {
                throw new CommandException(Messages.MESSAGE_INVALID_DELETE_BOOK);
            }

            Person borrower = bookToDelete.getBorrower();
            if (!lastShownPersonList.contains(borrower)) {
                throw new CommandException(Messages.MESSAGE_BORROWER_NOT_FOUND);
            }
            Person origPerson = model.getPerson(borrower);
            borrower.returnBook(bookToDelete);
            model.setPerson(origPerson, borrower);
        }

        model.deleteBook(bookToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_BOOK_SUCCESS, bookToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteBookCommand // instanceof handles nulls
                        && targetIndex.equals(((DeleteBookCommand) other).targetIndex)); // state check
    }
}
