package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CONTEXT_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESTAMP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNWATCH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WATCH;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of a module, lecture, or video in the tracker.
 */
public abstract class EditCommand extends Command {

    /** The command word for the edit command. */
    public static final String COMMAND_WORD = "edit";

    /** A message describing how the edit command can be used. */
    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD + ":\n"
            + "(1) Edits the details of a module in the tracker.\n"
            + "Parameters: "
            + "{module_code} "
            + "[" + PREFIX_CODE + " {updated_code}] "
            + "[" + PREFIX_NAME + " {updated_name}] "
            + "[" + PREFIX_TAG + " {tag_1}[, {tag_2}[, ...]]]\n"
            + "Example: " + COMMAND_WORD + " CS2040S "
            + PREFIX_CODE + " CS2040 "
            + PREFIX_NAME + " Data Structures and Algorithms "
            + PREFIX_TAG + " Heavy, Math, Analysis\n\n"
            + "(2) Edits the details of a lecture in a module.\n"
            + "Parameters: "
            + "{lecture_name} "
            + PREFIX_MODULE + " {module_code} "
            + "[" + PREFIX_NAME + " {updated_name}] "
            + "[" + PREFIX_TAG + " {tag_1}[, {tag_2}[, ...]]]\n"
            + "Example: " + COMMAND_WORD + " Week 1 "
            + PREFIX_MODULE + " CS2040S "
            + PREFIX_NAME + " Week 01 Introduction "
            + PREFIX_TAG + " Intro, Important\n\n"
            + "(3) Edits the details of a video in a lecture.\n"
            + "Parameters: "
            + "{video_name} "
            + PREFIX_MODULE + " {module_code} "
            + PREFIX_LECTURE + " {lecture_name} "
            + "[" + PREFIX_NAME + " {updated_name}] "
            + "[" + PREFIX_TIMESTAMP + " {updated_timestamp}] "
            + "[" + PREFIX_WATCH + "] "
            + "[" + PREFIX_UNWATCH + "] "
            + "[" + PREFIX_TAG + " {tag_1}[, {tag_2}[, ...]]]\n"
            + "Example: " + COMMAND_WORD + " Video 1 "
            + PREFIX_MODULE + " CS2040S "
            + PREFIX_LECTURE + " Week 1 "
            + PREFIX_NAME + " Video 01 Grade Breakdown "
            + PREFIX_WATCH + " "
            + PREFIX_TAG + " Intro, Short\n\n"
            + MESSAGE_CONTEXT_USAGE;

    /** The error message for when no fields to edit is provided. */
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    /**
     * Stores the details to edit the entity (module, lecture, or video) with.<p>
     * Each non-empty field value will replace the corresponding field value of the entity.
     */
    public abstract static class EditEntityDescriptor {
        private Set<Tag> tags;

        /**
         * Constructs an {@code EditEntityDescriptor}.
         */
        public EditEntityDescriptor() {}

        /**
         * Copy constructor.
         *
         * @param toCopy The {@code EditEntityDescriptor} to copy.
         */
        public EditEntityDescriptor(EditEntityDescriptor toCopy) {
            requireNonNull(toCopy);

            setTags(toCopy.tags);
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

        /**
         * Returns true if at least one field is edited.
         *
         * @return True if at least one field is edited. Otherwise, false.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(tags);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditEntityDescriptor)) {
                return false;
            }

            EditEntityDescriptor descriptor = (EditEntityDescriptor) other;

            return getTags().equals(descriptor.getTags());
        }
    }

}
