package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONSULTATIONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_LABS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORIALS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sorts students accordingly.
 */
public class SortStudentCommand extends Command {
    public static final String COMMAND_WORD = "sort-student";

    public static final String MESSAGE_SUCCESS = "Listed all persons in sorted order";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all address book students.\n "
            + "Parameters: The group of students you wish to sort (either all, lab, tutorial or consultation), "
            + "the metric to be sorted (either address, email, name, performance or remark), "
            + "and the desired order (either reverse or nonreverse)\n"
            + "For example: 'sort-student all name reverse' command will order all students in reverse-alphabetical "
            + "ordering of their names";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Sort command not implemented yet";
    private static String group;
    private static String metric;
    private static boolean isIncreasing;

    /**
     * Constructor for SortStudentCommand
     * @param group The group of students to be sorted
     * @param metric The metric to be used for the sorting
     * @param isIncreasing The desired order to sort the students
     */
    public SortStudentCommand(String group, String metric, boolean isIncreasing) {
        this.group = group;
        this.metric = metric;
        this.isIncreasing = isIncreasing;
    }

    /**
     * Getter method for the group
     * @return the group input by the user
     */
    private String getGroup() {
        return this.group;
    }

    /**
     * Getter method for the metric
     * @return the metric input by the user
     */
    private String getMetric() {
        return this.metric;
    }

    /**
     * Getter method for whether the order is increasing
     * @return true if the order is increasing else false
     */
    private boolean getIsIncreasing() {
        return this.isIncreasing;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch (this.group) {
        case "all":
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            model.updateSortAllPersonList(this.metric, this.isIncreasing);
            break;
        case "lab":
            model.updateFilteredLabList(PREDICATE_SHOW_ALL_LABS);
            model.updateSortLabPersonList(this.metric, this.isIncreasing);
            break;
        case "tutorial":
            model.updateFilteredTutorialList(PREDICATE_SHOW_ALL_TUTORIALS);
            model.updateSortTutorialPersonList(this.metric, this.isIncreasing);
            break;
        case "consultation":
            model.updateFilteredConsultationList(PREDICATE_SHOW_ALL_CONSULTATIONS);
            model.updateSortConsultationPersonList(this.metric, this.isIncreasing);
            break;
        default:
            break;
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortStudentCommand) // instanceof handles nulls
                && this.metric == (((SortStudentCommand) other).getMetric()) // state check
                && this.group == ((SortStudentCommand) other).getGroup()
                && this.isIncreasing == ((SortStudentCommand) other).getIsIncreasing();
    }
}
