package taa.logic.commands;

import static taa.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_NAME;
import static taa.logic.parser.CliSyntax.PREFIX_STAT_TYPE;

import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Displays the statistics for the active class list.
 */
public class ClassStatisticsCommand extends Command {

    public static final String COMMAND_WORD = "class_stats";

    public static final String MESSAGE_SUCCESS = "Displayed statistics for the %1$s of the active class";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays statistics for the active class.\n"
            + "Parameters: " + PREFIX_STAT_TYPE + "FIELD [" + PREFIX_ASSIGNMENT_NAME + "ASSIGNMENT_NAME]\n"
            + "where FIELD is either '"
            + ClassStatisticField.ATTENDANCE.toString().toLowerCase() + "' or '"
            + ClassStatisticField.GRADES.toString().toLowerCase() +"'. \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STAT_TYPE + "grades" + PREFIX_ASSIGNMENT_NAME + "Homework 1";
    public static final String MESSAGE_UNKNOWN_FIELD = "The FIELD parameter passed in is not recognised. \n"
            + "Please enter only either 'attendance' or 'grades' for this parameter.";
    private ClassStatisticField field;
    private String assignmentName;

    /**
     * Handles the case where no assignment name is passed in.
     */
    public ClassStatisticsCommand(ClassStatisticField field) {
        this.field = field;
    }

    /**
     * Handles the case where an assignment name is passed in.
     */
    public ClassStatisticsCommand(ClassStatisticField field, String assignmentName) {
        this.field = field;
        this.assignmentName = assignmentName;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireNonNull(this.field);

        if (this.field == ClassStatisticField.ATTENDANCE) {
            // display histogram of attendance over all 12 weeks
        } else if (this.field == ClassStatisticField.GRADES) {
            // display bell curve
        } else {
            throw new CommandException(MESSAGE_UNKNOWN_FIELD);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, this.field.toString()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof ClassStatisticsCommand) { // instanceof handles null values
            ClassStatisticsCommand otherCommand = (ClassStatisticsCommand) other;
            return this.field == otherCommand.field
                    && this.assignmentName.equals(otherCommand.assignmentName);
        }
        return false;
    }
}
