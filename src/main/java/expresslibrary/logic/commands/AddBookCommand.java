package expresslibrary.logic.commands;

import static expresslibrary.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_ISBN;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_TITLE;
import static java.util.Objects.requireNonNull;

import expresslibrary.logic.commands.exceptions.CommandException;
import expresslibrary.model.Model;
import expresslibrary.model.book.Book;

/**
 * Adds a book to the express library.
 */
public class AddBookCommand extends Command {

    public static final String COMMAND_WORD = "addBook";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a book to the express library. \n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_AUTHOR + "AUTHOR "
            + PREFIX_ISBN + "ISBN \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Diary of a Wimpy Kid "
            + PREFIX_AUTHOR + "Jeff Kinney "
            + PREFIX_ISBN + "9780810993136 ";

    public static final String MESSAGE_SUCCESS = "New book added: %1$s";
    public static final String MESSAGE_DUPLICATE_BOOK = "This book already exists in the express library.";

    private final Book toAdd;

    /**
     * Creates an AddBookCommand to add the specified {@code Book}
     */
    public AddBookCommand(Book book) {
        requireNonNull(book);
        toAdd = book;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBook(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOK);
        }

        model.addBook(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddBookCommand // instanceof handles nulls
                && toAdd.equals(((AddBookCommand) other).toAdd));
    }
}
