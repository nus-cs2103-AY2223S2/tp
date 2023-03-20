package expresslibrary.logic.commands;

import static expresslibrary.model.Model.PREDICATE_SHOW_ALL_BOOKS;
import static java.util.Objects.requireNonNull;

import expresslibrary.model.Model;

/**
 * Lists all books in the express library to the user.
 */
public class ListBookCommand extends Command {

    public static final String COMMAND_WORD = "listBook";

    public static final String MESSAGE_SUCCESS = "Listed all books";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
