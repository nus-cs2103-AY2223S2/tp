package taa.logic.commands;

import static java.util.Objects.requireNonNull;
import static taa.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_NAME;
import static taa.logic.parser.CliSyntax.PREFIX_STAT_TYPE;

import taa.logic.commands.enums.ChartType;
import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;
import taa.model.assignment.exceptions.AssignmentNotFoundException;
import taa.model.assignment.exceptions.NoGradeVarianceException;
import taa.model.assignment.exceptions.NoSubmissionsFoundException;

/**
 * Displays the statistics for the active class list.
 */
public class ClassStatisticsCommand extends Command {

    public static final String COMMAND_WORD = "class_stats";
    public static final String EXAMPLE_USAGE = "Examples: \n"
            + "- " + COMMAND_WORD + " " + PREFIX_STAT_TYPE + "attendance\n"
            + "- " + COMMAND_WORD + " " + PREFIX_STAT_TYPE + "grades " + PREFIX_ASSIGNMENT_NAME + "Homework 1\n";
    public static final String SAVE_IMAGE_HINT = "Tip: Want to save the chart? \n"
            + "Right click on it, select 'Save As' and click on 'PNG'!";

    public static final String MESSAGE_SUCCESS = "Displayed statistics for the %1$s of the active class %2$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays statistics for the active class.\n\n"
            + "Parameters: " + PREFIX_STAT_TYPE + "FIELD [" + PREFIX_ASSIGNMENT_NAME + "ASSIGNMENT_NAME]\n"
            + "where FIELD is either '"
            + ChartType.CLASS_ATTENDANCE.toString().toLowerCase() + "' or '"
            + ChartType.CLASS_GRADES.toString().toLowerCase() + "'. \n\n"
            + EXAMPLE_USAGE;
    public static final String MESSAGE_EMPTY_CLASSLIST = "The class list cannot be empty!";
    public static final String MESSAGE_UNKNOWN_FIELD = "The FIELD parameter passed in is not recognised. \n"
            + "Please enter only either 'attendance' or 'grades' for this parameter.\n"
            + EXAMPLE_USAGE;
    public static final String MESSAGE_MISSING_ASSIGNMENT_NAME = "For grade statistics, the parameter ASSIGNMENT_NAME"
            + " is compulsory. \n"
            + "Please include the ASSIGNMENT_NAME of the assignment you wish to analyse.\n"
            + EXAMPLE_USAGE;
    public static final String MESSAGE_ASSIGNMENT_NOT_FOUND = "The assignment name you have entered does not exist.\n"
            + "Please check that the assignment with the specified name exists for this active class list.\n"
            + EXAMPLE_USAGE;
    private ChartType field;
    private String assignmentName;

    /**
     * Handles the case where no assignment name is passed in.
     */
    public ClassStatisticsCommand(ChartType field) {
        this.field = field;
    }

    /**
     * Handles the case where an assignment name is passed in.
     */
    public ClassStatisticsCommand(ChartType field, String assignmentName) {
        this.field = field;
        this.assignmentName = assignmentName;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireNonNull(this.field);

        if (model.getClassListSize() == 0) {
            throw new CommandException(MESSAGE_EMPTY_CLASSLIST);
        }

        if (this.field == ChartType.CLASS_GRADES
                && !model.hasAssignment(this.assignmentName)) {
            throw new CommandException(MESSAGE_ASSIGNMENT_NOT_FOUND);
        }

        if (this.field == ChartType.CLASS_ATTENDANCE) {
            return displayAttendanceChart(model);
        } else if (this.field == ChartType.CLASS_GRADES) {
            return displayGradeChart(model);
        } else {
            throw new CommandException(MESSAGE_UNKNOWN_FIELD);
        }
    }

    private CommandResult displayAttendanceChart(Model model)
            throws CommandException {
        try {
            model.displayChart(this.field);
        } catch (AssignmentNotFoundException | NoSubmissionsFoundException e) {
            throw new CommandException("Could not display attendance distribution: \n"
                    + e.getMessage());
        } catch (NoGradeVarianceException e) {
            // should not ever reach this, attendance distribution will not require grade variance
            assert false;
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, "attendance", "")
                + "\n\n" + SAVE_IMAGE_HINT);
    }

    private CommandResult displayGradeChart(Model model)
            throws CommandException {
        requireNonNull(model);
        requireNonNull(assignmentName);

        try {
            model.displayChart(this.field, this.assignmentName);
        } catch (AssignmentNotFoundException | NoSubmissionsFoundException | NoGradeVarianceException e) {
            throw new CommandException("Could not display grade distribution: \n"
                    + e.getMessage());
        }

        return new CommandResult(String.format(
                MESSAGE_SUCCESS,
                "grades",
                "(" + this.assignmentName + ")")
                + "\n\n" + SAVE_IMAGE_HINT);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof ClassStatisticsCommand) { // instanceof handles null values
            ClassStatisticsCommand otherCommand = (ClassStatisticsCommand) other;
            return this.field == otherCommand.field
                    && (this.assignmentName == otherCommand.assignmentName
                || this.assignmentName.equals(otherCommand.assignmentName));
        }
        return false;
    }

}
