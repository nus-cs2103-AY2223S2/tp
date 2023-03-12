package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class CommentCommand extends Command {

    public static final String COMMAND_WORD = "comment";
    public static final String MESSAGE_ADD_COMMENT_SUCCESS = "Added comment to Person: %1$s";
    public static final String MESSAGE_DELETE_COMMENT_SUCCESS = "Removed comment from Person: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a comment to a person in the address book. "
            + "Parameters: "
            + "INDEX "
            + PREFIX_COMMENT + "Owes Money";

    private final Index index;
    private final Comment comment;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     *
     * @param index of the person in the filtered person list to edit
     * @param comment of the person to be updated to
     */
    public CommentCommand(Index index, Comment comment) {
        requireAllNonNull(index, comment);

        this.index = index;
        this.comment = comment;
    }

    /**
     * Implements the execution of the command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return  the CommandResult of the command execution
     * @throws CommandException If invalid person index is given
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), comment);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }
    /**
     * Generates a command execution success message based on whether
     * the comment is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !comment.value.isEmpty() ? MESSAGE_ADD_COMMENT_SUCCESS : MESSAGE_DELETE_COMMENT_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CommentCommand)) {
            return false;
        }
        CommentCommand e = (CommentCommand) other;
        return index.equals(e.index)
                && comment.equals((e.comment));
    }
}
