package expresslibrary.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import expresslibrary.commons.core.Messages;
import expresslibrary.commons.core.index.Index;
import expresslibrary.logic.commands.exceptions.CommandException;
import expresslibrary.model.Model;
import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the express
 * library.
 */
public class DeletePersonCommand extends Command {

    public static final String COMMAND_WORD = "deletePerson";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;
    private final boolean deleteOption;

    /**
     * Constructor for DeletePersonCommand.
     */
    public DeletePersonCommand(Index targetIndex, boolean deleteOption) {
        this.targetIndex = targetIndex;
        this.deleteOption = deleteOption;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Book> lastShownBookList = model.getFilteredBookList();

        if (targetIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownPersonList.get(targetIndex.getZeroBased());
        if (personToDelete.borrowedAtLeastOneBook()) {
            if (!deleteOption) {
                throw new CommandException(Messages.MESSAGE_INVALID_DELETE_PERSON);
            }

            Set<Book> books = personToDelete.getBooks();
            for (Book book : books) {
                if (!lastShownBookList.contains(book)) {
                    throw new CommandException(Messages.MESSAGE_BOOK_BORROWED_NOT_FOUND);
                }
                Book origBook = model.getBook(book);
                book.returnBook();
                model.setBook(origBook, book);
            }
        }
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePersonCommand // instanceof handles nulls
                        && targetIndex.equals(((DeletePersonCommand) other).targetIndex)); // state check
    }
}
