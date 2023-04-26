package seedu.medinfo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_DISCHARGE;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_WARD;

import java.util.Comparator;

import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.Model;
import seedu.medinfo.model.patient.Patient;



/**
 * Sort all patients in MedInfo based on the field and the order.
 */

// Solution below adapted from
// https://github.com/AY2223S1-CS2103T-T09-4/tp/blob/master/src/main/java/seedu/address/logic/commands/SortCommand.java
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_UNKNOWN_ORDER_KEYWORD =
            "The order of Sort Command should be 'ASC' or 'DESC'.";
    public static final String MESSAGE_UNKNOWN_TYPE_KEYWORD = "You may only sort by 'name', 'status', 'discharge date'"
            + "or 'ward name' "
            + "followed by 'asc' or 'desc' order.\n"
            + "Example: sort name/asc";

    public static final String MESSAGE_SUCCESS = "Sorted all patients by the given order\n";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + PREFIX_NAME
            + ": Sorts the list of all patients by name. \n"
            + "Parameters: asc/desc\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "asc\n"
            + COMMAND_WORD + " " + PREFIX_STATUS
            + ": Sorts all patients by status. \n"
            + "Parameters: asc/desc\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STATUS + "asc\n"
            + COMMAND_WORD + " " + PREFIX_DISCHARGE
            + ": Sorts all patients by discharge date. \n"
            + "Parameters: asc/desc\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_DISCHARGE + "asc\n"
            + COMMAND_WORD + " " + PREFIX_WARD
            + ": Sorts all patients by ward name. \n"
            + "Parameters: asc/desc\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_WARD + "asc\n";

    /**
     * {@code FIELD} specifies what possible types {@code SortCommand} can accept.
     */
    public static enum Field {
        NAME,
        STATUS,
        DISCHARGE,
        WARD
    };

    /**
     * {@code ORDER} specifies what possible order {@code SortCommand} can accept.
     */
    public static enum Order {
        ASC,
        DESC
    };

    private final Comparator<Patient> comparator;

    /**
     * Constructs a {@code SortCommand} to sort {@code Patients} by the given field in the given order.
     * @param field The {@code Patient} field to be sorted by.
     * @param order The order for {@code Patients} to be displayed.
     */
    public SortCommand(Field field, Order order) {
        this.comparator = generateComparator(field, order);
    }

    /**
     * Generates a Comparator for {@code Patients} based on parameters.
     *
     * @param field field of attribute to be compared
     * @param order order of sorting
     */
    public static Comparator<Patient> generateComparator(Field field, Order order) {
        switch (field) {
        case NAME:
            if (order.equals(Order.ASC)) {
                return Patient::compareToByNameAsc;
            } else {
                return Patient::compareToByNameDesc;
            }
        case STATUS:
            if (order.equals(Order.ASC)) {
                return Patient::compareToByStatusAsc;
            } else {
                return Patient::compareToByStatusDesc;
            }
        case DISCHARGE:
            if (order.equals(Order.ASC)) {
                return Patient::compareToByDischargeAsc;
            } else {
                return Patient::compareToByDischargeDesc;
            }
        case WARD:
            if (order.equals(Order.ASC)) {
                return Patient::compareToByWardAsc;
            } else {
                return Patient::compareToByWardDesc;
            }
        default:
            // default sorting order is by Name Asc
            return Patient::compareToByNameAsc;
        }
    }

    /**
     * Executes the {@code SortCommand} on the given model.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which is the result of the operation.
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPatients(this.comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return this == other // short circuit if same object
                || (other instanceof SortCommand // instanceof handles null
                && this.comparator.equals(((SortCommand) other).comparator)); // state check
    }
}
