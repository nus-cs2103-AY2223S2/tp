package seedu.address.logic.commands.task.todo;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.JobTitle;
import seedu.address.model.todo.ApplicationDeadline;
import seedu.address.model.todo.InternshipTodo;
import seedu.address.model.todo.NoteContent;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TODO;

/**
 * Edits the note content of a todo identified using it's displayed index from the list of todos.
 */
public class EditNoteContentCommand extends Command {

    public static final String COMMAND_WORD = "edit_c";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the deadline of the specified todo from current available list.\n"
            + "Parameters: INDEX (INDEX must be a positive integer) "
            + "[" + PREFIX_NOTE_CONTENT + "NOTE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NOTE_CONTENT + "Change venue\n";

    public static final String MESSAGE_UPDATE_STATUS_SUCCESS = "Note Content updated: %1$s";

    private final Index targetIndex;

    private final NoteContent toUpdate;

    /**
     * @param targetIndex of the to update content
     * @param content to update
     */
    public EditNoteContentCommand(Index targetIndex, NoteContent content) {
        requireNonNull(targetIndex);
        requireNonNull(content);

        this.targetIndex = targetIndex;
        toUpdate = content;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<InternshipTodo> lastShownList = model.getFilteredTodoList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISPLAYED_INDEX);
        }

        InternshipTodo todoToUpdateDeadline = lastShownList.get(targetIndex.getZeroBased());
        InternshipTodo updatedTodo = createdUpdatedTodo(todoToUpdateDeadline, toUpdate);

        model.setTodo(todoToUpdateDeadline, updatedTodo);
        model.updateFilteredTodoList(PREDICATE_SHOW_ALL_TODO);
        return new CommandResult(String.format(MESSAGE_UPDATE_STATUS_SUCCESS, updatedTodo));
    }

    /**
     * Creates and returns a {@code InternshipApplication} with the content of {@code content}
     */
    private static InternshipTodo createdUpdatedTodo(InternshipTodo todo, NoteContent content) {
        assert todo != null;

        CompanyName companyName = todo.getInternshipTitle();
        JobTitle jobTitle = todo.getJobTitle();
        ApplicationDeadline deadline = todo.getDeadline();

        return new InternshipTodo(companyName, jobTitle, deadline, content);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditNoteContentCommand // instanceof handles nulls
                && targetIndex.equals(((EditNoteContentCommand) other).targetIndex)
                && toUpdate.equals(((EditNoteContentCommand) other).toUpdate)); // state check
    }
}
