package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.EmployeeId;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the employee identified by the EMPLOYEE_ID used in the displayed employee list.\n"
            + "Parameters: EMPLOYEE_ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Employee: %1$s";


    private final EmployeeId employeeId;

    public DeleteCommand(EmployeeId employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToDelete;

        for (Person person : lastShownList) {
            if (person.getEmployeeId().equals(employeeId)) {
                model.deletePerson(person);
                personToDelete = person;
                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
            }
        }
        throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }



    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && employeeId.equals(((DeleteCommand) other).employeeId)); // state check
    }
}
