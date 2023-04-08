package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.ModuleEditInfo;
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

    /** The message for when a {@code Module} is successfully edited. */
    public static final String MESSAGE_SUCCESS = "Edited module: %s";

    /** The error message for when a duplicate {@code Module} is detected. */
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the tracker.";

    private final ModuleCode moduleCode;
    private final EditModuleDescriptor editModuleDescriptor;

    /**
     * Contructs an {@code EditModuleCommand} to edit the details of a module in the tracker whose code is
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

        ReadOnlyModule moduleToEdit = getModuleToEdit(model);
        Module editedModule = createEditedModule(moduleToEdit);

        validateModuleIsNotDuplicate(model, moduleToEdit, editedModule);

        model.setModule(moduleToEdit, editedModule);

        return createSuccessResult(moduleToEdit, editedModule);
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

    private ReadOnlyModule getModuleToEdit(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(moduleCode)) {
            throw new CommandException(String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
        }

        return model.getModule(moduleCode);
    }

    private Module createEditedModule(ReadOnlyModule moduleToEdit) {
        requireNonNull(moduleToEdit);

        ModuleCode updatedCode = editModuleDescriptor.getCode().orElse(moduleToEdit.getCode());
        ModuleName updateName = editModuleDescriptor.getName().orElse(moduleToEdit.getName());
        Set<Tag> updatedTags = editModuleDescriptor.getTags().orElse(moduleToEdit.getTags());

        List<? extends ReadOnlyLecture> lectures = moduleToEdit.getLectureList();

        return new Module(updatedCode, updateName, updatedTags, lectures);
    }

    private void validateModuleIsNotDuplicate(Model model, ReadOnlyModule moduleToEdit, ReadOnlyModule editedModule)
            throws CommandException {

        requireAllNonNull(model, moduleToEdit, editedModule);

        if (!moduleToEdit.isSameModule(editedModule)
                && model.hasModule(editedModule)) {

            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }
    }

    private CommandResult createSuccessResult(ReadOnlyModule moduleToEdit, ReadOnlyModule editedModule) {
        requireAllNonNull(moduleToEdit, editedModule);

        String message = String.format(MESSAGE_SUCCESS, editedModule);
        ModuleEditInfo editInfo = new ModuleEditInfo(moduleToEdit, editedModule);

        return new CommandResult(message, editInfo);
    }

    /**
     * Stores the details to edit the module with.<p>
     * Each non-empty field value will replace the corresponding field value of the module.
     */
    public static class EditModuleDescriptor extends EditEntityDescriptor {
        private ModuleCode code;
        private ModuleName name;

        /**
         * Constructs an {@code EditModuleDescriptor}.
         */
        public EditModuleDescriptor() {
            super();
        }

        /**
         * Copy constructor.
         *
         * @param toCopy The {@code EditModuleDescriptor} to copy.
         */
        public EditModuleDescriptor(EditModuleDescriptor toCopy) {
            super(toCopy);

            requireNonNull(toCopy);

            setCode(toCopy.code);
            setName(toCopy.name);
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
        public boolean isAnyFieldEdited() {
            return super.isAnyFieldEdited()
                    || CollectionUtil.isAnyNonNull(code, name);
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

            return super.equals(other)
                    && getName().equals(descriptor.getName())
                    && getCode().equals(descriptor.getCode());
        }
    }

}
