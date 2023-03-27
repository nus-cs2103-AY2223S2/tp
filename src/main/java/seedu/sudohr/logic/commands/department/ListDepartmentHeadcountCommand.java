package seedu.sudohr.logic.commands.department;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;

import java.time.LocalDate;

import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.model.leave.LeaveDate;


/**
 * Lists headcount for the department on a specified date.
 */
public class ListDepartmentHeadcountCommand extends Command {

    public static final String COMMAND_WORD = "ldhc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Lists the headcount for "
            + "the department on a given date. "
            + "Parameters: "
            + PREFIX_DATE + "DATE"
            + PREFIX_DEPARTMENT_NAME + "DEPARTMENT_NAME"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DEPARTMENT_NAME + "Human Resources"
            + PREFIX_DATE + "2023-03-04";

    public static final String MESSAGE_DEPARTMENT_NOT_EXIST = "The given department does not exist.";

    public static final String MESSAGE_INVALID_DATE_FOR_HEADCOUNT = "The given date to get headcount is invalid.";

    public static final String MESSAGE_SUCCESS = "%1$d employees are present on the given date in %2$s department.";

    private LocalDate date;
    private LocalDate currentDate;
    private DepartmentName departmentName;

    /**
     * Creates a ListDepartmentHeadcountCommand to list all the employees present in a department on a given date.
     * @param date
     */
    public ListDepartmentHeadcountCommand(LocalDate currentDate, LocalDate date, DepartmentName departmentName) {
        requireAllNonNull(date, departmentName);
        this.currentDate = currentDate;
        this.date = date;
        this.departmentName = departmentName;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Department department = model.getDepartment(departmentName);

        if (department == null) {
            throw new CommandException(MESSAGE_DEPARTMENT_NOT_EXIST);
        }

        if (!isValidDate(date, currentDate)) {
            throw new CommandException(MESSAGE_INVALID_DATE_FOR_HEADCOUNT);
        }

        Leave leaveOnGivenDate = model.getLeave(new LeaveDate(date));

        model.updateFilteredEmployeeList(e -> !leaveOnGivenDate.hasEmployee(e));

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredEmployeeList().size(), departmentName));

    }

    private boolean isValidDate(LocalDate date, LocalDate currentDate) {
        LocalDate oneYearAfter = currentDate.plusYears(1);
        boolean isNotBeforeCurrentDate = !date.isBefore(currentDate);
        boolean isWithinOneYearFromCurrentDate = date.isBefore(oneYearAfter);

        return isNotBeforeCurrentDate && isWithinOneYearFromCurrentDate;
    }
}
