package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Mathutoring;
import seedu.address.model.Model;


/**
 * Clears the mathutoring.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Student list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.checkStudent(null);
        model.setMathutoring(new Mathutoring());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
