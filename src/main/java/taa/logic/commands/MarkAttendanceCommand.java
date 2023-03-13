package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import taa.commons.core.Messages;
import taa.commons.core.index.Index;
import taa.commons.util.CollectionUtil;
import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;
import taa.model.student.Attendance;
import taa.model.student.Name;
import taa.model.student.Student;
import taa.model.tag.Tag;

public class MarkAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "markAtd";
    public static final String SUCCESS_MSG = "Attendance marked successfully!";
    public static final String MESSAGE_DUPLICATE_MARKING = "This student's attendance has already been marked.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the address book.";
    private final Index index;
    private final int week;

    private final EditStudentDescriptor editStudentDescriptor;

    public MarkAttendanceCommand(Index index, EditStudentDescriptor editStudentDescriptor, int week) {
        this.index = index;
        this.editStudentDescriptor = editStudentDescriptor;
        this.week = week;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Attendance studentAtd = studentToEdit.getAtd();
        if (studentAtd.isMarkedWeek(this.week)) {
            return new CommandResult(MESSAGE_DUPLICATE_MARKING);
        }
        studentAtd.markAttendance(this.week);
//        Attendance newAttendance = studentAtd.getCopy();
//        newAttendance.markAttendance(this.week);
//        this.editStudentDescriptor.setAttendance(newAttendance);
//
//        Student editedStudent = createEditedPerson(studentToEdit, editStudentDescriptor);
//
//        if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
//            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
//        }
//
//        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(SUCCESS_MSG, studentToEdit));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedPerson(Student studentToEdit, MarkAttendanceCommand.EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Set<Tag> updatedTags = editStudentDescriptor.getTags().orElse(studentToEdit.getTags());

        return new Student(updatedName, updatedTags);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private Set<Tag> tags;

        private Attendance attendance;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(MarkAttendanceCommand.EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setTags(toCopy.tags);
            setAttendance(toCopy.attendance);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setAttendance(Attendance attendance) {
            this.attendance = attendance;
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

        /**
         * Checks for equality
         * @param other other object to check equality
         * @return true if equal otherwise false
         */
        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCommand.EditStudentDescriptor)) {
                return false;
            }

            // state check
            EditCommand.EditStudentDescriptor e = (EditCommand.EditStudentDescriptor) other;

            return getName().equals(e.getName())
                    && getTags().equals(e.getTags());
        }
    }
}
