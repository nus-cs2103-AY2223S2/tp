package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_LECTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.LectureEditInfo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.Video;

/**
 * Edits the details of a lecture in a module.
 */
public class EditLectureCommand extends EditCommand {

    /** The message for when a {@code Lecture} is successfully edited. */
    public static final String MESSAGE_SUCCESS = "Edited lecture of module %s: %s";

    /** The error message for when a duplicate {@code Lecture} is detected. */
    public static final String MESSAGE_DUPLICATE_LECTURE = "This lecture already exists in %s.";

    private final ModuleCode moduleCode;
    private final LectureName lectureName;
    private final EditLectureDescriptor editLectureDescriptor;

    /**
     * Constructs an {@code EditLectureCommand} to edit the details of a lecture whose name is {@code lectureName} in
     * the module whose code is {@code moduleCode}.
     *
     * @param moduleCode The code of the module containing the lecture.
     * @param lectureName The name of the lecture to be edited.
     * @param editLectureDescriptor The details to edit the lecture with.
     */
    public EditLectureCommand(ModuleCode moduleCode, LectureName lectureName,
            EditLectureDescriptor editLectureDescriptor) {

        requireAllNonNull(moduleCode, lectureName, editLectureDescriptor);

        this.moduleCode = moduleCode;
        this.lectureName = lectureName;
        this.editLectureDescriptor = editLectureDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ReadOnlyModule module = getModuleContainingLecture(model);
        ReadOnlyLecture lectureToEdit = getLectureToEdit(module);
        Lecture editedLecture = createEditedLecture(lectureToEdit);

        validateLectureIsNotDuplicate(module, lectureToEdit, editedLecture);

        model.setLecture(module, lectureToEdit, editedLecture);

        return createSuccessResult(lectureToEdit, editedLecture);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditLectureCommand)) {
            return false;
        }

        EditLectureCommand otherCommand = (EditLectureCommand) other;

        return moduleCode.equals(otherCommand.moduleCode)
                && lectureName.equals(otherCommand.lectureName)
                && editLectureDescriptor.equals(otherCommand.editLectureDescriptor);
    }

    private ReadOnlyModule getModuleContainingLecture(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(moduleCode)) {
            throw new CommandException(String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
        }

        return model.getModule(moduleCode);
    }

    private ReadOnlyLecture getLectureToEdit(ReadOnlyModule module) throws CommandException {
        requireNonNull(module);

        if (!module.hasLecture(lectureName)) {
            throw new CommandException(String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, moduleCode));
        }

        return module.getLecture(lectureName);
    }

    private Lecture createEditedLecture(ReadOnlyLecture lectureToEdit) {
        requireNonNull(lectureToEdit);

        LectureName updatedName = editLectureDescriptor.getName().orElse(lectureToEdit.getName());
        Set<Tag> updatedTags = editLectureDescriptor.getTags().orElse(lectureToEdit.getTags());

        List<Video> videos = lectureToEdit.getVideoList();

        return new Lecture(updatedName, updatedTags, videos);
    }

    private void validateLectureIsNotDuplicate(ReadOnlyModule module, ReadOnlyLecture lectureToEdit,
            ReadOnlyLecture editedLecture) throws CommandException {

        requireAllNonNull(module, lectureToEdit, editedLecture);

        if (!lectureToEdit.isSameLecture(editedLecture)
                && module.hasLecture(editedLecture)) {

            throw new CommandException(String.format(MESSAGE_DUPLICATE_LECTURE, moduleCode));
        }
    }

    private CommandResult createSuccessResult(ReadOnlyLecture lectureToEdit, ReadOnlyLecture editedLecture) {
        requireAllNonNull(lectureToEdit, editedLecture);

        String message = String.format(MESSAGE_SUCCESS, moduleCode, editedLecture);
        LectureEditInfo editInfo = new LectureEditInfo(moduleCode, lectureToEdit, editedLecture);

        return new CommandResult(message, editInfo);
    }

    /**
     * Stores the details to edit the lecture with.<p>
     * Each non-empty field value will replace the corresponding field value of the lecture.
     */
    public static class EditLectureDescriptor extends EditEntityDescriptor {
        private LectureName name;

        /**
         * Constructs an {@code EditLectureDescriptor}.
         */
        public EditLectureDescriptor() {
            super();
        }

        /**
         * Copy constructor.
         *
         * @param toCopy The {@code EditLectureDescriptor} to copy.
         */
        public EditLectureDescriptor(EditLectureDescriptor toCopy) {
            super(toCopy);

            requireNonNull(toCopy);

            setName(toCopy.name);
        }

        public Optional<LectureName> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(LectureName name) {
            this.name = name;
        }

        @Override
        public boolean isAnyFieldEdited() {
            return super.isAnyFieldEdited()
                    || CollectionUtil.isAnyNonNull(name);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditLectureDescriptor)) {
                return false;
            }

            EditLectureDescriptor descriptor = (EditLectureDescriptor) other;

            return super.equals(other)
                    && getName().equals(descriptor.getName());
        }
    }
}
