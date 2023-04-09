package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAILSTUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMAGESTUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEXNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEWCLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEWINDEXNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEWNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEWPARENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEWPHONEPARENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONESTUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTAGE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Class;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Image;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.Relationship;
import seedu.address.model.person.student.Attendance;
import seedu.address.model.person.student.Cca;
import seedu.address.model.person.student.Homework;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.Test;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class StudentEditCommand extends StudentCommand {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified by their "
            + "index number and class in the displayed student list \n"
            + "Parameters: "
            + PREFIX_INDEXNUMBER + "INDEX NUMBER "
            + "["
            + PREFIX_NEWNAME + "NEW NAME "
            + PREFIX_NEWINDEXNUMBER + "NEW INDEX NUMBER "
            + PREFIX_NEWCLASS + "NEW STUDENT CLASS "
            + PREFIX_SEX + "SEX "
            + PREFIX_STUDENTAGE + "AGE "
            + PREFIX_IMAGESTUDENT + "IMAGE STUDENT "
            + PREFIX_CCA + "CCA "
            + PREFIX_COMMENT + "COMMENT "
            + PREFIX_PHONESTUDENT + "STUDENT NUMBER "
            + PREFIX_EMAILSTUDENT + "STUDENT EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_NEWPARENTNAME + "PARENT NAME "
            + PREFIX_NEWPHONEPARENT + "PARENT PHONE NUMBER "
            + PREFIX_RELATIONSHIP + "RELATIONSHIP "
            + "]\n"
            + "Example: \n" + "student 1A " + COMMAND_WORD + " "
            + PREFIX_INDEXNUMBER + "02 "
            + PREFIX_NEWNAME + "Tan Ah Niu "
            + PREFIX_NEWINDEXNUMBER + "03 "
            + PREFIX_NEWCLASS + "1B "
            + PREFIX_SEX + "M"
            + PREFIX_STUDENTAGE + "10 "
            + PREFIX_IMAGESTUDENT + "XX.png (where XX is your image name) "
            + PREFIX_CCA + "AIKIDO "
            + PREFIX_COMMENT + "Quiet "
            + PREFIX_PHONESTUDENT + "90909090 "
            + PREFIX_EMAILSTUDENT + "tanahcow@gmail.com "
            + PREFIX_ADDRESS + "Blk 245 Ang Mo Kio Avenue 1 #11-800 S(560245) "
            + PREFIX_NEWPARENTNAME + "Tan Ah Seng "
            + PREFIX_NEWPHONEPARENT + "98989898 "
            + PREFIX_RELATIONSHIP + "FATHER";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited student: %1$s; Class: %2$s; Index Number: %3$s;";

    private IndexNumber indexNumber;
    private IndexNumber newIndexNumber;
    private Class studentClass;
    private Class newStudentClass;
    private Age newAge;
    private Image newImage;
    private Cca newCca;
    private Set<Attendance> newAttendance;
    private Comment newComment;
    private Phone newStudentPhoneNumber;
    private Email newEmail;
    private Address newAddress;
    private Set<Tag> newTagList;
    private Set<Test> newTest;
    private Set<Homework> newHomework;
    private Relationship newRelationship;
    private Name newName;
    private Sex newSex;
    private Phone newParentPhoneNumber;
    private Name newParentName;

    /**
         * Creates a StudentEditCommand to edit the specified {@code Student}
     */
    public StudentEditCommand(Name newName, IndexNumber indexNumber, IndexNumber newIndexNumber,
                              Class studentClass, Class newStudentClass, Sex newSex, Phone newParentPhoneNumber,
                              Name newParentName, Relationship newRelationship, Age newAge, Image newImage, Cca newCca,
                              Comment newComment, Phone newStudentPhoneNumber, Email newEmail, Address newAddress) {
        requireNonNull(indexNumber);
        requireNonNull(studentClass);

        this.newName = newName;
        this.newRelationship = newRelationship;
        this.newParentName = newParentName;
        this.indexNumber = indexNumber;
        this.studentClass = studentClass;
        this.newParentPhoneNumber = newParentPhoneNumber;
        this.newAge = newAge;
        this.newImage = newImage;
        this.newCca = newCca;
        this.newComment = newComment;
        this.newStudentPhoneNumber = newStudentPhoneNumber;
        this.newEmail = newEmail;
        this.newAddress = newAddress;
        this.newSex = newSex;
        this.newIndexNumber = newIndexNumber;
        this.newStudentClass = newStudentClass;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        requireNonNull(model);

        //@@author diatbbin-reused
        //Reused from https://github.com/4ndrelim/tp/blob/master/src/main
        // /java/seedu/sudohr/logic/commands/department/DeleteDepartmentCommand.java
        //with modifications
        Student studentToEdit = model.getStudent(indexNumber, studentClass);
        if (studentToEdit == null) {
            throw new CommandException(Messages.MESSAGE_STUDENT_NOT_FOUND);
        }
        //@@author
        Student editedStudent = createEditedStudent(studentToEdit);

        model.setStudent(studentToEdit, setParent(editedStudent, model, studentToEdit));
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent.getName(),
                editedStudent.getStudentClass(), editedStudent.getIndexNumber()));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code student}
     */
    public Student createEditedStudent(Student student) {
        if (Name.isDefaultName(newName.fullName)) {
            this.newName = student.getName();
        }
        if (IndexNumber.isDefaultIndexNumber(newIndexNumber.value)) {
            this.newIndexNumber = this.indexNumber;
        }
        if (Class.isDefaultClass(newStudentClass.getClassName())) {
            this.newStudentClass = this.studentClass;
        }
        if (Phone.isDefaultPhone(newParentPhoneNumber.value)) {
            this.newParentPhoneNumber = student.getParentNumber();
        }
        if (Phone.isDefaultPhone(newStudentPhoneNumber.value)) {
            this.newStudentPhoneNumber = student.getPhone();
        }
        if (Age.isDefaultAge(newAge.value)) {
            this.newAge = student.getAge();
        }
        if (Image.isDefaultImage(newImage.value)) {
            this.newImage = student.getImage();
        }
        if (Cca.isDefaultCca(newCca.value)) {
            this.newCca = student.getCca();
        }
        if (Comment.isDefaultComment(newComment.value)) {
            this.newComment = student.getComment();
        }
        if (Image.isDefaultImage(newImage.value)) {
            this.newImage = student.getImage();
        }
        if (Email.isDefaultEmail(newEmail.value)) {
            this.newEmail = student.getEmail();
        }
        if (Address.isDefaultAddress(newAddress.value)) {
            this.newAddress = student.getAddress();
        }
        if (Sex.isDefaultSex(newSex.value)) {
            this.newSex = student.getSex();
        }
        if (Relationship.isDefaultRelationship(newRelationship.rls)) {
            this.newRelationship = student.getRls();
        }
        if (Name.isDefaultName(newParentName.fullName)) {
            this.newParentName = student.getParentName();
        }

        this.newTagList = student.getTagList();
        this.newTest = student.getTest();
        this.newHomework = student.getHomeworkSet();
        this.newAttendance = student.getAttendance();

        return new Student(newName, this.newStudentClass, this.newIndexNumber, this.newSex,
                this.newParentName, this.newParentPhoneNumber, this.newRelationship, this.newAge, this.newImage,
                this.newEmail, this.newStudentPhoneNumber, this.newCca, this.newAddress, this.newAttendance,
                newHomework, this.newTest, this.newTagList, this.newComment);
    }

    /**
     * A method that binds the Student's Parent / NOK to the Student
     *
     * @param student    Student with edited particulars.
     * @param model      {@code Model} which the command should operate on.
     * @param oldStudent Student with original particulars before being edited.
     * @return Edited Student that is bound to its Parent / NOK.
     */
    public Student setParent(Student student, Model model, Student oldStudent)
            throws ParseException {
        if (student.getParentNumber() == oldStudent.getParentNumber()) { // Parent phone number did not change
            return updateParent(student, model, oldStudent);
        } else { // Parent phone number changed (NEW PARENT)
            return changeParent(student, model, oldStudent);
        }
    }

    /**
     * Helper method for setParent() that updates the Parent/NOK info instead of changing Parent/NOK.
     *
     * @param student    Student with edited particulars.
     * @param model      {@code Model} which the command should operate on.
     * @param oldStudent Student with original particulars before being edited.
     * @return Edited Student that is bound to its Parent / NOK.
     */
    public Student updateParent(Student student, Model model, Student oldStudent) {
        ObservableList<Parent> parents = model.getFilteredParentList();
        if (student.getParentName() != oldStudent.getParentName()) { // Parent changed his/her name
            for (Parent p : parents) {
                if ((p.getPhone().equals(oldStudent.getParentNumber())) && (p.getName().equals(
                        oldStudent.getParentName()))) {
                    Parent newParent = new Parent(student.getParentName(), p.getAge(), p.getImage(), p.getEmail(),
                            p.getPhone(), p.getAddress(), p.getTags());
                    newParent = editParent(p, newParent, model);
                    model.setParent(p, newParent);
                    student.setParent(newParent);
                    return student;
                }
            }
        }
        Phone parentNumber = student.getParentNumber();
        Name parentName = student.getParentName();
        for (Parent p : parents) { // Parent did not change any particulars
            if ((p.getPhone().equals(parentNumber)) && (p.getName().equals(parentName))) {
                student.setParent(p);
                Parent newParent = p;
                newParent.addStudent(student); //bind student to parent
                newParent.removeStudent(oldStudent);
                model.setParent(p, newParent); //update parent in parents
                return student;
            }
        }
        assert false : "The program should not reach this line!";
        return student;
    }

    /**
     * Helper method for setParent() that changes the Parent/NOK for the student.
     *
     * @param student    Student with edited particulars.
     * @param model      {@code Model} which the command should operate on.
     * @param oldStudent Student with original particulars before being edited.
     * @return Edited Student that is bound to its Parent / NOK.
     */
    public Student changeParent(Student student, Model model, Student oldStudent) {
        ObservableList<Parent> parents = model.getFilteredParentList();
        for (Parent p : parents) {
            if ((p.getPhone().equals(oldStudent.getParentNumber())) && (p.getName().equals(
                    oldStudent.getParentName()))) {
                if (p.getStudents().size() == 1) { // Removes parent if this is the only student binded to him/her
                    model.deleteParent(p);
                } else { // Remove student binding to original parent
                    Parent originalParent = p;
                    p.removeStudent(oldStudent);
                    model.setParent(originalParent, p);
                }
                for (Parent np : parents) { // Locating new parent in existing parents
                    if ((np.getPhone().equals(student.getParentNumber())) && (np.getName().equals(
                            student.getParentName()))) {
                        student.setParent(np);
                        Parent newParent = np;
                        np.addStudent(student);
                        model.setParent(newParent, np);
                        return student;
                    }
                }
                Parent newParent = new Parent(student.getParentName(), new Age("Insert parent age here!"),
                        new Image("Insert parent image here!"), new Email("Insert parent email here!"),
                        student.getParentNumber(), new Address("Insert Address here!"), p.getTags());
                // Created new parent since it does not exist in existing parents
                newParent.addStudent(student);
                student.setParent(newParent);
                model.addParent(newParent);
                return student;
            }
        }
        assert false : "The program should not reach this line!";
        return student;
    }

    /**
     * Method that helps transfer and update all students in the original Parent object to the new EDITED Parent object.
     *
     * @param parent    Original Parent object that is to be edited.
     * @param newParent Edited Parent object.
     * @return Edited Parent object with list of students in original Parent object and updates all the students.
     */
    private Parent editParent(Parent parent, Parent newParent, Model model) {
        List<Student> students = null;
        if (parent.hasStudents()) {
            students = parent.getStudents();
        }

        if (students == null) {
            return newParent;
        }

        for (Student student : students) {
            Student originalStudent = student;
            student.setParent(newParent);
            model.setStudent(originalStudent, student);
            newParent.addStudent(student);
        }

        return newParent;
    }
}
