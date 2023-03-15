package seedu.modtrek.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_SEMYEAR;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.modtrek.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.modtrek.commons.util.CollectionUtil;
import seedu.modtrek.logic.commands.exceptions.CommandException;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.Credit;
import seedu.modtrek.model.module.Grade;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.model.tag.Tag;

/**
 * Edits the details of an existing module in the ModTrek.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the module identified. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: MODULE CODE "
            + "[" + PREFIX_CREDIT + "CREDITS] "
            + "[" + PREFIX_SEMYEAR + "SEMESTER-YEAR] "
            + "[" + PREFIX_GRADE + "GRADE] "
            + "[" + PREFIX_TAG + "TAG...]\n"
            + "Example: " + COMMAND_WORD + " CS2106 "
            + PREFIX_CREDIT + " 4 "
            + PREFIX_SEMYEAR + " Y2S2";

    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Edited Module: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the grade book.";
    public static final String MESSAGE_EDIT_MODULE_FAIL = "Module %1$s is not yet added";

    private final Code code;
    private final EditModuleDescriptor editModuleDescriptor;

    /**
     * @param code of the module in the filtered module list to edit
     * @param editModuleDescriptor details to edit the module with
     */
    public EditCommand(Code code, EditModuleDescriptor editModuleDescriptor) {
        requireNonNull(code);
        requireNonNull(editModuleDescriptor);

        this.code = code;
        this.editModuleDescriptor = new EditModuleDescriptor(editModuleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Module> lastShownList = model.getFilteredModuleList();

        //should change this to a better way to retrieve module (possibly HashSet for modules?)
        Module moduleToEdit = new Module(code);
        int index = lastShownList.indexOf(moduleToEdit);
        if (index < 0) {
            throw new CommandException(String.format(MESSAGE_EDIT_MODULE_FAIL, moduleToEdit.getCode()));
        }
        moduleToEdit = lastShownList.get(index);
        Module editedModule = createEditedModule(moduleToEdit, editModuleDescriptor);

        if (!moduleToEdit.isSameModule(editedModule) && model.hasModule(editedModule)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.setModule(moduleToEdit, editedModule);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, editedModule));
    }

    /**
     * Creates and returns a {@code Module} with the details of {@code moduleToEdit}
     * edited with {@code editModuleDescriptor}.
     */
    private static Module createEditedModule(Module moduleToEdit, EditModuleDescriptor editModuleDescriptor) {
        assert moduleToEdit != null;

        Code updatedCode = editModuleDescriptor.getCode().orElse(moduleToEdit.getCode());
        Credit updatedCredit = editModuleDescriptor.getCredit().orElse(moduleToEdit.getCredit());
        SemYear updatedSemYear = editModuleDescriptor.getSemYear().orElse(moduleToEdit.getSemYear());
        Grade updatedGrade = editModuleDescriptor.getGrade().orElse(moduleToEdit.getGrade());
        Set<Tag> updatedTags = editModuleDescriptor.getTags().orElse(moduleToEdit.getTags());

        return new Module(updatedCode, updatedCredit, updatedSemYear, updatedTags, updatedGrade);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return code.equals(e.code)
                && editModuleDescriptor.equals(e.editModuleDescriptor);
    }

    /**
     * Stores the details to edit the module with. Each non-empty field value will replace the
     * corresponding field value of the module.
     */
    public static class EditModuleDescriptor {
        private Code code;
        private Credit credit;
        private SemYear semYear;
        private Grade grade;
        private Set<Tag> tags;

        public EditModuleDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditModuleDescriptor(EditModuleDescriptor toCopy) {
            setCode(toCopy.code);
            setCredit(toCopy.credit);
            setSemYear(toCopy.semYear);
            setGrade(toCopy.grade);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(code, credit, semYear, grade, tags);
        }

        public void setCode(Code code) {
            this.code = code;
        }

        public Optional<Code> getCode() {
            return Optional.ofNullable(code);
        }

        public void setCredit(Credit credit) {
            this.credit = credit;
        }

        public Optional<Credit> getCredit() {
            return Optional.ofNullable(credit);
        }

        public void setSemYear(SemYear semYear) {
            this.semYear = semYear;
        }

        public Optional<SemYear> getSemYear() {
            return Optional.ofNullable(semYear);
        }

        public void setGrade(Grade grade) {
            this.grade = grade;
        }

        public Optional<Grade> getGrade() {
            return Optional.ofNullable(grade);
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
            if (!(other instanceof EditModuleDescriptor)) {
                return false;
            }

            // state check
            EditModuleDescriptor e = (EditModuleDescriptor) other;

            return getCode().equals(e.getCode())
                    && getCredit().equals(e.getCredit())
                    && getSemYear().equals(e.getSemYear())
                    && getGrade().equals(e.getGrade())
                    && getTags().equals(e.getTags());
        }
    }
}
