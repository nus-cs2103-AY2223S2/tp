package seedu.sudohr.logic.commands;

import seedu.sudohr.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class ShowAllCommand extends Command {

    public static final String COMMAND_WORD = "sa";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Shows all leave, employee and departments.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Showing all leaves, employees and departments.";


    @Override
    public CommandResult execute(Model model) {
        model.updateFilteredDepartmentList(Model.PREDICATE_SHOW_ALL_DEPARTMENTS);
        model.updateFilteredEmployeeList(Model.PREDICATE_SHOW_ALL_EMPLOYEES);
        model.updateFilteredLeaveList(Model.PREDICATE_SHOW_ALL_NON_EMPTY_LEAVES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
