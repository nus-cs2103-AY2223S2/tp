package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Class;
import seedu.address.model.person.Name;
import seedu.address.model.person.Sex;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Age;
import seedu.address.model.person.Image;
import seedu.address.model.person.Email;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.Address;
import seedu.address.model.person.parent.Relationship;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.Comment;
import seedu.address.model.person.student.Cca;
import seedu.address.model.person.student.Attendance;
import seedu.address.model.person.student.Homework;
import seedu.address.model.person.student.Test;

import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class StudentEditCommand extends StudentCommand {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final IndexNumber indexNumber;
    private final Class studentClass;
    private final IndexNumber newIndexNumber;
    private final Class newStudentClass;
    private final Phone newParentPhoneNumber;
    private final Age newAge;
    private final Image newImage;
    private final Cca newCca;
    private final Attendance newAttendance;
    private final Comment newComment;
    private final Phone newStudentPhoneNumber;
    private final Email newEmail;
    private final Address newAddress;

    /**
     * @param indexNumber of the person in the filtered person list to edit
     */
    public StudentEditCommand(IndexNumber indexNumber, IndexNumber newIndexNumber, Class studentClass, Class newStudentClass,
                              Phone newParentPhoneNumber, Age newAge, Image newImage, Cca newCca, Attendance newAttendance,
                              Comment newComment, Phone newStudentPhoneNumber, Email newEmail, Address newAddress) {
        requireNonNull(indexNumber);
        requireNonNull(studentClass);

        this.indexNumber = indexNumber;
        this.studentClass = studentClass;
        this.newIndexNumber = newIndexNumber;
        this.newStudentClass = newStudentClass;
        this.newParentPhoneNumber = newParentPhoneNumber;
        this.newAge = newAge;
        this.newImage = newImage;
        this.newCca = newCca;
        this.newAttendance = newAttendance;
        this.newComment = newComment;
        this.newStudentPhoneNumber = newStudentPhoneNumber;
        this.newEmail = newEmail;
        this.newAddress = newAddress;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> students = model.getPcClass().getClassList().get(0).getStudents().asUnmodifiableObservableList();
        for (int i = 0; i < model.getPcClass().getClassList().size(); i++) {
            if (model.getPcClass().getClassList().get(i).getClassName().equals(studentClass.getClassName())) {
                students = model.getPcClass().getClassList().get(i).getStudents().asUnmodifiableObservableList();
                break;
            }
        }

        for (Student student : students) {
            Class sc = student.getStudentClass();
            if (student.getIndexNumber().equals(indexNumber)
                    && sc.equals(studentClass)) {

                model.deleteStudent(student);
                model.addStudent(editedStudent, sc);
                model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
                return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedStudent));
            }
        }
        throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Class updatedStudentClass = editStudentDescriptor.getStudentClass().orElse(studentToEdit.getStudentClass());
        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        IndexNumber updatedIndexNumber = editStudentDescriptor.getIndexNumber().orElse(studentToEdit.getIndexNumber());
        Sex sex = studentToEdit.getSex();
        Name parentName = studentToEdit.getParentName();
        Phone updatedParentNumber = editStudentDescriptor.getParentNumber().orElse(studentToEdit.getParentNumber());
        Relationship rls = studentToEdit.getRls();
        Age updatedAge = editStudentDescriptor.getAge().orElse(studentToEdit.getAge());
        Image updatedImage = editStudentDescriptor.getImage().orElse(studentToEdit.getImage());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Phone updatedStudentNumber = editStudentDescriptor.getStudentNumber().orElse(studentToEdit.getStudentNumber());
        Cca updatedCca = editStudentDescriptor.getCca().orElse(studentToEdit.getCca());
        Address updatedAddress = editStudentDescriptor.getAddress().orElse(studentToEdit.getAddress());
        Attendance updatedAttendance = editStudentDescriptor.getAttendance().orElse(studentToEdit.getAttendance());
        Comment updatedComment = editStudentDescriptor.getComment().orElse(studentToEdit.getComment());
        Set<Tag> updatedTagList = editStudentDescriptor.getTagList().orElse(studentToEdit.getTagList());
        Set<Homework> updatedHomeworkSet = editStudentDescriptor.getHomeworkSet().orElse(studentToEdit.getHomeworkSet());
        Set<Test> updatedTestSet = editStudentDescriptor.getTestSet().orElse(studentToEdit.getTest());

        return new Student(updatedName, updatedStudentClass, updatedIndexNumber, sex, parentName, updatedParentNumber,
                rls, updatedAge, updatedImage, updatedEmail, updatedStudentNumber, updatedCca, updatedAddress,
                updatedAttendance, updatedHomeworkSet, updatedTestSet, updatedTagList, updatedComment);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentEditCommand)) {
            return false;
        }

        // state check
        StudentEditCommand e = (StudentEditCommand) other;
        return indexNumber.equals(e.indexNumber)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditStudentDescriptor {
        private Class sc;
        private Name name;
        private IndexNumber indexNumber;
        private Phone parentNumber;
        private Age age;
        private Image image;
        private Email email;
        private Phone studentNumber;
        private Cca cca;
        private Address address;
        private Attendance attendance;
        private Comment comment;
        private Set<Tag> tagList;
        private Set<Homework> homeworkSet;
        private Set<Test> testSet;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setClass(toCopy.sc);
            setName(toCopy.name);
            setIndexNumber(toCopy.indexNumber);
            setParentNumber(toCopy.parentNumber);
            setAge(toCopy.age);
            setImage(toCopy.image);
            setCca(toCopy.cca);
            setAttendance(toCopy.attendance);
            setComment(toCopy.comment);
            setTagList(toCopy.tagList);
            setHomeworkSet(toCopy.homeworkSet);
            setTestSet(toCopy.testSet);
            setStudentNumber(toCopy.studentNumber);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
        }


        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(sc, name, indexNumber, parentNumber, age, image, cca, attendance,
                    comment, tagList, homeworkSet, testSet, studentNumber, email, address);
        }

        public void setClass(Class sc) {
            this.sc = sc;
        }

        public Optional<Class> getStudentClass() {
            return Optional.ofNullable(sc);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setParentNumber(Phone parentNumber) {
            this.parentNumber = parentNumber;
        }

        public Optional<Phone> getParentNumber() {
            return Optional.ofNullable(parentNumber);
        }

        public void setStudentNumber(Phone studentNumber) {
            this.studentNumber = studentNumber;
        }

        public Optional<Phone> getStudentNumber() {
            return Optional.ofNullable(studentNumber);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }


        public void setIndexNumber(IndexNumber indexNumber) {
            this.indexNumber = indexNumber;
        }

        public Optional<IndexNumber> getIndexNumber() {
            return Optional.ofNullable(indexNumber);
        }

        public void setAge(Age age) {
            this.age = age;
        }

        public Optional<Age> getAge() {
            return Optional.ofNullable(age);
        }

        public void setImage(Image image) {
            this.image = image;
        }

        public Optional<Image> getImage() {
            return Optional.ofNullable(image);
        }

        public void setCca(Cca cca) {
            this.cca = cca;
        }

        public Optional<Cca> getCca() {
            return Optional.ofNullable(cca);
        }

        public void setAttendance(Attendance attendance) {
            this.attendance = attendance;
        }

        public Optional<Attendance> getAttendance() {
            return Optional.ofNullable(attendance);
        }

        public void setComment(Comment comment) {
            this.comment = comment;
        }

        public Optional<Comment> getComment() {
            return Optional.ofNullable(comment);
        }

        public void setTagList(Set<Tag> tagList) {
            this.tagList = tagList;
        }

        public Optional<Set<Tag>> getTagList() {
            return Optional.ofNullable(tagList);
        }

        public void setHomeworkSet(Set<Homework> homeworkSet) {
            this.homeworkSet = homeworkSet;
        }

        public Optional<Set<Homework>> getHomeworkSet() {
            return Optional.ofNullable(homeworkSet);
        }

        public void setTestSet(Set<Test> testSet) {
            this.testSet = testSet;
        }

        public Optional<Set<Test>> getTestSet() {
            return Optional.ofNullable(testSet);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            // state check
            EditStudentDescriptor e = (EditStudentDescriptor) other;

            return getStudentClass().equals(e.getStudentClass())
                    && getName().equals(e.getName())
                    && getIndexNumber().equals(e.getIndexNumber())
                    && getParentNumber().equals(e.getParentNumber())
                    && getAge().equals(e.getAge())
                    && getImage().equals(e.getImage())
                    && getEmail().equals(e.getEmail())
                    && getStudentNumber().equals(e.getStudentNumber())
                    && getCca().equals(e.getCca())
                    && getAddress().equals(e.getAddress())
                    && getAttendance().equals(e.getAttendance())
                    && getComment().equals(e.getComment())
                    && getTagList().equals(e.getTagList())
                    && getHomeworkSet().equals(e.getHomeworkSet())
                    && getTestSet().equals(e.getTestSet());
        }
    }
}
