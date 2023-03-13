package seedu.sudohr.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;

import java.util.Optional;

import seedu.sudohr.commons.util.CollectionUtil;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;

/**
 * Edits the details of an existing department inside SudoHR.
 */
public class EditDepartmentCommand extends Command {
    public static final String COMMAND_WORD = "edep";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the department identified "
            + "by the department name."
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DEPARTMENT_NAME + "NAME] "
            + "Example: " + COMMAND_WORD + " Engineering "
            + PREFIX_DEPARTMENT_NAME + "Software Engineering";

    public static final String MESSAGE_EDIT_DEPARTMENT_SUCCESS = "Edited Department: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_DEPARTMENT = "This department already exists in SudoHR.";
    public static final String MESSAGE_DEPARTMENT_NOT_FOUND = "The department to edit does not exist in SudoHR.";

    private final DepartmentName name;
    private final EditDepartmentCommand.EditDepartmentDescriptor editDepartmentDescriptor;

    /**
     * @param name of the department in the filtered department list to edit
     * @param editDepartmentDescriptor details to edit the department with
     */

    public EditDepartmentCommand(DepartmentName name,
             EditDepartmentCommand.EditDepartmentDescriptor editDepartmentDescriptor) {
        requireNonNull(name);
        requireNonNull(editDepartmentDescriptor);

        this.name = name;
        this.editDepartmentDescriptor = new EditDepartmentCommand.EditDepartmentDescriptor(editDepartmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Department departmentToEdit = model.getDepartment(name);

        if (departmentToEdit == null) {
            throw new CommandException(MESSAGE_DEPARTMENT_NOT_FOUND);
        }

        Department editedDepartment = createEditedDepartment(departmentToEdit, editDepartmentDescriptor);

        if (!departmentToEdit.equals(editedDepartment) && model.hasDepartment(editedDepartment)) {
            throw new CommandException(MESSAGE_DUPLICATE_DEPARTMENT);
        }

        model.setDepartment(departmentToEdit, editedDepartment);
        // MISSING STEP: Update Department view?
        return new CommandResult(String.format(MESSAGE_EDIT_DEPARTMENT_SUCCESS, editedDepartment));
    }

    /**
     * Creates and returns a {@code Department} with the details of {@code departmentToEdit}
     * edited with {@code editDepartmentDescriptor}.
     */
    private static Department createEditedDepartment(Department departmentToEdit,
             EditDepartmentCommand.EditDepartmentDescriptor editDepartmentDescriptor) {
        assert departmentToEdit != null;

        DepartmentName updatedDepartmentName = editDepartmentDescriptor.getName().orElse(departmentToEdit.getName());

        return new Department(updatedDepartmentName, departmentToEdit.getEmployees());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditDepartmentCommand)) {
            return false;
        }

        // state check
        EditDepartmentCommand e = (EditDepartmentCommand) other;
        return name.equals(e.name)
                && editDepartmentDescriptor.equals(e.editDepartmentDescriptor);
    }

    /**
     * Stores the details to edit the department with. Each non-empty field value will replace the
     * corresponding field value of the department.
     */
    public static class EditDepartmentDescriptor {
        private DepartmentName name;

        public EditDepartmentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditDepartmentDescriptor(EditDepartmentCommand.EditDepartmentDescriptor toCopy) {
            setName(toCopy.name);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name);
        }

        public void setName(DepartmentName name) {
            this.name = name;
        }

        public Optional<DepartmentName> getName() {
            return Optional.ofNullable(name);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditDepartmentCommand.EditDepartmentDescriptor)) {
                return false;
            }

            // state check
            EditDepartmentCommand.EditDepartmentDescriptor e = (EditDepartmentCommand.EditDepartmentDescriptor) other;

            return getName().equals(e.getName());
        }
    }
}
