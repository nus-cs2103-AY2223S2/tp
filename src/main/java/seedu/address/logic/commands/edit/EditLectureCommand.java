package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_LECTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
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

    public static final String MESSAGE_SUCCESS = "Edited lecture of module %s: %s";
    public static final String MESSAGE_DUPLICATE_LECTURE = "This lecture already exists in %s.";

    private final ModuleCode moduleCode;
    private final LectureName lectureName;
    private final EditLectureDescriptor editLectureDescriptor;

    /**
     * Creates an {@code EditLectureCommand} to edit the details of a lecture whose name is {@code lectureName} in
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

        if (!model.hasModule(moduleCode)) {
            throw new CommandException(String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
        }

        if (!model.hasLecture(moduleCode, lectureName)) {
            throw new CommandException(String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, moduleCode));
        }

        ReadOnlyModule module = model.getModule(moduleCode);
        ReadOnlyLecture lectureToEdit = module.getLecture(lectureName);
        Lecture editedLecture = createEditedLecture(lectureToEdit);

        if (!lectureToEdit.isSameLecture(editedLecture) && module.hasLecture(editedLecture)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_LECTURE, moduleCode));
        }

        model.setLecture(module, lectureToEdit, editedLecture);
        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleCode, editedLecture),
                new LectureEditInfo(moduleCode, lectureToEdit, editedLecture));
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

    private Lecture createEditedLecture(ReadOnlyLecture lectureToEdit) {
        requireNonNull(lectureToEdit);

        LectureName updatedName = editLectureDescriptor.getName().orElse(lectureToEdit.getName());
        Set<Tag> updatedTags = editLectureDescriptor.getTags().orElse(lectureToEdit.getTags());

        List<Video> videos = lectureToEdit.getVideoList();

        return new Lecture(updatedName, updatedTags, videos);
    }

    /**
     * Stores the details to edit the lecture with.<p>
     * Each non-empty field value will replace the corresponding field value of the lecture.
     */
    public static class EditLectureDescriptor {
        private LectureName name;
        private Set<Tag> tags;

        public EditLectureDescriptor() {}

        /**
         * Copy constructor.
         *
         * @param toCopy The {@code EditLectureDescriptor} to copy.
         */
        public EditLectureDescriptor(EditLectureDescriptor toCopy) {
            setName(toCopy.name);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         *
         * @return True if at least one field is edited. Otherwise, false.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, tags);
        }

        public Optional<LectureName> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(LectureName name) {
            this.name = name;
        }

        /**
         * If {@code tags} is non-null, returns an {@code Optional} containing an immutable tag set, which throws
         * {@code UnsupportedOperationException} if modification is attempted.<p>
         *
         * Else, returns an {@code Optional#empty()}.
         *
         * @return An {@code Optional} containing an immutable tag set if {@code tags} is non-null. Otherwise,
         *         {@code Optional#empty()}.
         */
        public Optional<Set<Tag>> getTags() {
            return tags == null ? Optional.empty() : Optional.of(Collections.unmodifiableSet(tags));
        }

        /**
         * Replace the elements in this object's {@code tags} with the elements in {@code newTags}.
         *
         * @param newTags The new tags.
         */
        public void setTags(Set<Tag> newTags) {
            this.tags = newTags == null ? null : new HashSet<>(newTags);
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

            return getName().equals(descriptor.getName())
                    && getTags().equals(descriptor.getTags());
        }
    }
}
