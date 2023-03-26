package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_STUDENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
public class SortStudentCommand extends Command {
    public static final String COMMAND_WORD = "sort-student";

    public static final String MESSAGE_SUCCESS = "Listed all persons in sorted order";

    public static String group;
    public static String metric;
    public static boolean increasingOrder;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all address book students.\n "
            + "Parameters: The group of students you wish to sort (either all, lab, tutorial or consultation), "
            + "the metric to be sorted (either address, email, name, perfomance or remark), "
            + "and the desired order (either reverse or nonreverse)\n"
            + "For example: 'sort-student all name reverse' command will order all students in reverse-alphabetical "
            + "ordering of their names";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Sort command not implemented yet";

    public SortStudentCommand(String group, String metric, boolean increasingOrder) {
        this.group = group;
        this.metric = metric;
        this.increasingOrder = increasingOrder;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        /*
        switch (this.group) {
        case "all":
            model.updateSortAllPersonList(this.metric, this.increasingOrder);
            break;
        case "lab":
            model.updateSortLabPersonList(this.metric, this.increasingOrder);
            break;
        case "tutorial":
            model.updateSortTutorialPersonList(this.metric, this.increasingOrder);
            break;
        case "consultation":
            model.updateSortConsultationPersonList(this.metric, this.increasingOrder);
            break;
        }

         */
        model.updateSortAllPersonList(this.metric, this.increasingOrder);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
