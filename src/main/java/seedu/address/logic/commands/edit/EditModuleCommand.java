package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of a module in the tracker.
 */
public class EditModuleCommand extends EditCommand {

    public static final String MESSAGE_SUCCESS = "Edited module: %s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the tracker.";

    private final ModuleCode moduleCode;
    private final EditModuleDescriptor editModuleDescriptor;

    /**
     * Creates an {@code EditModuleCommand} to edit the details of a module in the tracker whose code is
     * {@code moduleCode}.
     *
     * @param moduleCode The code of the module to be edited.
     * @param editModuleDescriptor The details to edit the module with.
     */
    public EditModuleCommand(ModuleCode moduleCode, EditModuleDescriptor editModuleDescriptor) {
        requireAllNonNull(moduleCode, editModuleDescriptor);

        this.moduleCode = moduleCode;
        this.editModuleDescriptor = new EditModuleDescriptor(editModuleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(moduleCode)) {
            throw new CommandException(String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
        }

        ReadOnlyModule moduleToEdit = model.getTracker().getModule(moduleCode);
        Module editedModule = createEditedModule(moduleToEdit);

        if (!moduleToEdit.isSameModule(editedModule) && model.hasModule(editedModule)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.setModule(moduleToEdit, editedModule);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedModule));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditModuleCommand)) {
            return false;
        }

        EditModuleCommand otherCommand = (EditModuleCommand) other;

        return moduleCode.equals(otherCommand.moduleCode)
                && editModuleDescriptor.equals(otherCommand.editModuleDescriptor);
    }

    private Module createEditedModule(ReadOnlyModule moduleToEdit) {
        requireNonNull(moduleToEdit);

        ModuleCode updatedCode = editModuleDescriptor.getCode().orElse(moduleToEdit.getCode());
        ModuleName updateName = editModuleDescriptor.getName().orElse(moduleToEdit.getName());

        Set<Tag> tags = moduleToEdit.getTags();
        List<? extends ReadOnlyLecture> lectures = moduleToEdit.getLectureList();

        return new Module(updatedCode, updateName, tags, lectures);
    }

    /**
     * Stores the details to edit the module with.<p>
     * Each non-empty field value will replace the corresponding field value of the module.
     */
    public static class EditModuleDescriptor {
        private ModuleCode code;
        private ModuleName name;

        public EditModuleDescriptor() {}

        /**
         * Copy constructor.
         *
         * @param toCopy The {@code EditModuleDescriptor} to copy.
         */
        public EditModuleDescriptor(EditModuleDescriptor toCopy) {
            requireNonNull(toCopy);

            setCode(toCopy.code);
            setName(toCopy.name);
        }

        /**
         * Returns true if at least one field is edited.
         *
         * @return True if at least one field is edited. Otherwise, false.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(code, name);
        }

        public Optional<ModuleCode> getCode() {
            return Optional.ofNullable(code);
        }

        public void setCode(ModuleCode code) {
            this.code = code;
        }

        public Optional<ModuleName> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(ModuleName name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditModuleDescriptor)) {
                return false;
            }

            EditModuleDescriptor descriptor = (EditModuleDescriptor) other;

            return getName().equals(descriptor.getName())
                    && getCode().equals(descriptor.getCode());
        }
    }

}
