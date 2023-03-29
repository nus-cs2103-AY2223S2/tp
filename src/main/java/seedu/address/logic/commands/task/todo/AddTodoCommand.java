package seedu.address.logic.commands.task.todo;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.TodoType;
import seedu.address.model.task.InternshipTodo;

/**
 * Adds an application to the internship tracker.
 */
public class AddTodoCommand extends Command {

    public static final String COMMAND_WORD = "add_todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an interested internship todo.\n"
            + "Parameters: "
            + PREFIX_COMPANY_NAME + "COMPANY_NAME "
            + PREFIX_JOB_TITLE + "JOB_TITLE\n"
            + PREFIX_DEADLINE + "DEADLINE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY_NAME + "LinkedIn "
            + PREFIX_JOB_TITLE + "Software Engineer "
            + PREFIX_DEADLINE + "2023-10-01";

    public static final String MESSAGE_SUCCESS = "New TODO added: %1$s";
    public static final String MESSAGE_DUPLICATE_TODO = "This TODO already exists in the todo list";

    private static final TodoType type = TodoType.TODO;

    private final InternshipTodo todo;

    /**
     * Creates an AddCommand to add the specified {@code InternshipApplication}
     */
    public AddTodoCommand(InternshipTodo applicationTodo) {
        requireNonNull(applicationTodo);
        todo = applicationTodo;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTodo(todo)) {
            throw new CommandException(MESSAGE_DUPLICATE_TODO);
        }

        model.addTodo(todo);
        return new CommandResult(String.format(MESSAGE_SUCCESS, todo), type);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTodoCommand // instanceof handles nulls
                && todo.equals(((AddTodoCommand) other).todo));
    }
}
