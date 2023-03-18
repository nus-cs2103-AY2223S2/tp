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
import static seedu.address.model.Model.*;

/**
 * Edits the deadline of a todo identified using it's displayed index from the list of todos.
 */
public class EditDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "edit_d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the deadline of the specified todo from current available list.\n"
            + "Parameters: INDEX (INDEX must be a positive integer) "
            + "[" + PREFIX_DEADLINE + "DEADLINE] (must be in format dd-mm-yyyy)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DEADLINE + "01-10-2023\n";

    public static final String MESSAGE_UPDATE_STATUS_SUCCESS = "Deadline updated: %1$s";

    private final Index targetIndex;

    private final ApplicationDeadline toUpdate;

    /**
     * @param targetIndex of the to update deadline
     * @param deadline to update
     */
    public EditDeadlineCommand(Index targetIndex, ApplicationDeadline deadline) {
        requireNonNull(targetIndex);
        requireNonNull(deadline);

        this.targetIndex = targetIndex;
        toUpdate = deadline;
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
     * Creates and returns a {@code InternshipApplication} with the deadline of {@code deadline}
     */
    private static InternshipTodo createdUpdatedTodo(InternshipTodo todo, ApplicationDeadline deadline) {
        assert todo != null;

        CompanyName companyName = todo.getInternshipTitle();
        JobTitle jobTitle = todo.getJobTitle();
        NoteContent note = todo.getNote();

        if (note != null) {
            return new InternshipTodo(companyName, jobTitle, deadline, note);
        } else {
            return new InternshipTodo(companyName, jobTitle, deadline);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditDeadlineCommand // instanceof handles nulls
                && targetIndex.equals(((EditDeadlineCommand) other).targetIndex)
                && toUpdate.equals(((EditDeadlineCommand) other).toUpdate)); // state check
    }
}
