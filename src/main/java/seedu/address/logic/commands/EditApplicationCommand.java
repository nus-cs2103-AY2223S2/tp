package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_COMPANY_EMAIL;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_TAG;
import static seedu.address.model.ApplicationModel.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ApplicationModel;
import seedu.address.model.application.Application;
import seedu.address.model.application.CompanyEmail;
import seedu.address.model.application.CompanyName;
import seedu.address.model.application.Role;
import seedu.address.model.application.Status;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;

/**
 * Edits the details of an existing application in the internship book.
 */
public class EditApplicationCommand extends ApplicationCommand {

    public static final String COMMAND_WORD = "edit-app";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the application identified "
            + "by the index number used in the displayed application list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ROLE + "ROLE] "
            + "[" + PREFIX_COMPANY_NAME + "COMPANY NAME] "
            + "[" + PREFIX_COMPANY_EMAIL + "COMPANY EMAIL] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_COMPANY_EMAIL + "gogglerecruiter@goggletalents.com "
            + PREFIX_STATUS + "ACCEPTED";

    public static final String MESSAGE_EDIT_APPLICATION_SUCCESS = "Edited Application: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPLICATION = "This application "
            + "already exists in the internship book.";


    private final Index index;
    private final EditApplicationDescriptor editApplicationDescriptor;

    /**
     * @param index of the application in the filtered application list to edit
     * @param editApplicationDescriptor details to edit the application with
     */
    public EditApplicationCommand(Index index, EditApplicationDescriptor editApplicationDescriptor) {
        requireNonNull(index);
        requireNonNull(editApplicationDescriptor);

        this.index = index;
        this.editApplicationDescriptor = new EditApplicationDescriptor(editApplicationDescriptor);
    }

    @Override
    public CommandResult execute(ApplicationModel model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getSortedApplicationList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Application applicationToEdit = lastShownList.get(index.getZeroBased());
        Application editedApplication = createEditedApplication(applicationToEdit, editApplicationDescriptor);

        if (!applicationToEdit.equals(editedApplication) && model.hasApplication(editedApplication)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICATION);
        }

        model.setApplication(applicationToEdit, editedApplication);
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication));
    }

    /**
     * Creates and returns a {@code Application} with the details of {@code appToEdit}
     * edited with {@code editApplicationDescriptor}.
     */
    protected static Application createEditedApplication(Application appToEdit,
                                                       EditApplicationDescriptor editApplicationDescriptor) {
        assert appToEdit != null;

        Role updatedRole = editApplicationDescriptor.getRole().orElse(appToEdit.getRole());
        CompanyName updatedCompanyName = editApplicationDescriptor.getCompanyName().orElse(appToEdit.getCompanyName());
        CompanyEmail updatedCompanyEmail = editApplicationDescriptor.getCompanyEmail()
                .orElse(appToEdit.getCompanyEmail());
        Status updatedStatus = editApplicationDescriptor.getStatus().orElse(appToEdit.getStatus());
        Task updatedTask = editApplicationDescriptor.getTask().orElse(appToEdit.getTask());
        Set<Tag> updatedTags = editApplicationDescriptor.getTags().orElse(appToEdit.getTags());

        return new Application(updatedRole, updatedCompanyName, updatedCompanyEmail,
                updatedStatus, updatedTask, updatedTags);
    }

    /**
     * Creates and returns a {@code Application} with the details of {@code appToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    protected static Application createEditedApplication(Application appToEdit,
                                                         EditTaskDescriptor editTaskDescriptor) {
        assert appToEdit != null;
        Deadline updatedDeadline = editTaskDescriptor.getDeadline().orElse(appToEdit.getTask().getDeadline());
        Description updatedDescription = editTaskDescriptor.getDescription()
                .orElse(appToEdit.getTask().getDescription());
        Task updatedTask = new Task(updatedDeadline, updatedDescription);

        return new Application(appToEdit.getRole(), appToEdit.getCompanyName(), appToEdit.getCompanyEmail(),
                appToEdit.getStatus(), updatedTask, appToEdit.getTags());
    }

    /**
     * Creates and returns an {@code Application} with no task.
     */
    protected static Application createEditedApplicationWithoutTask(Application appToEdit) {
        return new Application(appToEdit.getRole(), appToEdit.getCompanyName(), appToEdit.getCompanyEmail(),
                appToEdit.getStatus(), null, appToEdit.getTags());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditApplicationCommand)) {
            return false;
        }

        // state check
        EditApplicationCommand e = (EditApplicationCommand) other;
        return index.equals(e.index)
                && editApplicationDescriptor.equals(e.editApplicationDescriptor);
    }

    /**
     * Stores the details to edit the application with. Each non-empty field value will replace the
     * corresponding field value of the application.
     */
    public static class EditApplicationDescriptor {
        private Role role;
        private CompanyName companyName;
        private CompanyEmail companyEmail;
        private Status status;
        private Set<Tag> tags;

        private Task task;

        public EditApplicationDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditApplicationDescriptor(EditApplicationDescriptor toCopy) {
            setRole(toCopy.role);
            setCompanyName(toCopy.companyName);
            setCompanyEmail(toCopy.companyEmail);
            setStatus(toCopy.status);
            setTask(toCopy.task);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(role, companyName, companyEmail, status, task, tags);
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public Optional<Role> getRole() {
            return Optional.ofNullable(role);
        }

        public void setCompanyName(CompanyName name) {
            this.companyName = name;
        }

        public Optional<CompanyName> getCompanyName() {
            return Optional.ofNullable(companyName);
        }

        public void setCompanyEmail(CompanyEmail companyEmail) {
            this.companyEmail = companyEmail;
        }

        public Optional<CompanyEmail> getCompanyEmail() {
            return Optional.ofNullable(companyEmail);
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        public void setTask(Task task) {
            this.task = task;
        }

        public Optional<Task> getTask() {
            return Optional.ofNullable(task);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditApplicationDescriptor)) {
                return false;
            }

            // state check
            EditApplicationDescriptor e = (EditApplicationDescriptor) other;

            return getRole().equals(e.getRole())
                    && getCompanyName().equals(e.getCompanyName())
                    && getCompanyEmail().equals(e.getCompanyEmail())
                    && getStatus().equals(e.getStatus())
                    && getTask().equals(e.getTask());
        }
    }
}
