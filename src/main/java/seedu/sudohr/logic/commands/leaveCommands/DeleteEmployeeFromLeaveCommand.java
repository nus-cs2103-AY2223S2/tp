package seedu.sudohr.logic.commands.leavecommands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.sudohr.commons.core.index.Index;
import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.leave.Date;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.model.leave.LeaveContainsEmployeePredicate;
import seedu.sudohr.model.person.Person;

/**
 * Deletes a person from a specific leave in sudohr book.
 */
public class DeleteEmployeeFromLeaveCommand extends Command {
    public static final String COMMAND_WORD = "deleteEmployeeLeave";

    public static final String MESSAGE_PERSON_DOES_NOT_EXIST = "This user does not exists in the leave in sudohr book";

    public static final String MESSAGE_SUCCESS = "person %1$s is deleted from %2$s";

    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index is invalid";

    public static final String MESSAGE_INVALID_DATE = "The employee has not taken a leave";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a employee from leave in the sudohr book. ";

    private final Date dateToDelete;
    private final Index personToDeleteIndex;

    /**
     * Creates an DeleteEmployeeFromLeaveCommand to delete the person at specified
     * {@code personIndex} from the leave at the specified {@code Index}
     */
    public DeleteEmployeeFromLeaveCommand(Index personIndex, Date dateToDelete) {
        requireNonNull(personIndex);
        requireNonNull(dateToDelete);
        this.dateToDelete = dateToDelete;
        personToDeleteIndex = personIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (personToDeleteIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownPersonList.get(personToDeleteIndex.getZeroBased());

        Leave leaveToDelete = new Leave(dateToDelete);

        if (!model.hasEmployeeOnLeave(dateToDelete, personToDelete)) {
            throw new CommandException(MESSAGE_INVALID_DATE);
        }

        leaveToDelete = model.getInternalLeaveIfExist(leaveToDelete);
        model.deleteEmployeeFromLeave(leaveToDelete, personToDelete);

        Set<Person> personsToList = leaveToDelete.getAttendees();
        LeaveContainsEmployeePredicate predicate = new LeaveContainsEmployeePredicate(personsToList);

        model.updateFilteredPersonList(predicate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToDelete, leaveToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteEmployeeFromLeaveCommand // instanceof handles nulls
                        && dateToDelete.equals(((DeleteEmployeeFromLeaveCommand) other).dateToDelete)
                        && personToDeleteIndex
                                .equals(((DeleteEmployeeFromLeaveCommand) other).personToDeleteIndex));
    }

}
