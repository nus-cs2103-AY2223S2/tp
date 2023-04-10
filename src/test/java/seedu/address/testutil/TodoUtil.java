package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;

import java.time.format.DateTimeFormatter;

import seedu.address.model.task.InternshipTodo;

/**
 * A utility class for Todo.
 */
public class TodoUtil {

    /**
     * Returns the part of command string for the given {@code todo}'s details.
     */
    public static String getTodoDetails(InternshipTodo todo) {
        String deadline = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(todo.getDeadline().applicationDeadline);
        return PREFIX_COMPANY_NAME + todo.getInternshipTitle().fullName + " "
                + PREFIX_JOB_TITLE + todo.getJobTitle().fullName + " "
                + PREFIX_DEADLINE + deadline;
    }
}
