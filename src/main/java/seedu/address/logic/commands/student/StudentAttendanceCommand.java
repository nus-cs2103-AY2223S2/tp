package seedu.address.logic.commands.student;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEXNUMBER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Class;
import seedu.address.model.person.student.Attendance;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.Student;


/**
 * Adds attendance to a student
 */
public class StudentAttendanceCommand extends StudentCommand {
    public static final String COMMAND_WORD = "attendance";
    public static final String MESSAGE_SUCCESS = "Attendance marked for student";

    public static final String MESSAGE_USAGE = "student CLASS_NAME " + COMMAND_WORD
            + ": Marks student as present. \n"
            + "Parameters: "
            + PREFIX_INDEXNUMBER + "INDEX NUMBER "
            + "["
            + PREFIX_ATTENDANCE + "DATE "
            + "]\n"
            + "Example: " + "student 1A " + COMMAND_WORD + " "
            + PREFIX_INDEXNUMBER + "13 "
            + PREFIX_ATTENDANCE + "15/05/2023";

    private Class sc;
    private IndexNumber indexNumber;

    private Attendance attendance;

    /**
     * Creates an add attendance command for test
     *
     * @param indexNumber student to be edited
     * @param attendance Date to be added
     */
    public StudentAttendanceCommand(Class sc, IndexNumber indexNumber, Attendance attendance) {
        this.sc = sc;
        this.indexNumber = indexNumber;
        this.attendance = attendance;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        List<Student> lastShownList =
                model.getPcClass().getClassList().get(0).getStudents().asUnmodifiableObservableList();
        for (int i = 0; i < model.getPcClass().getClassList().size(); i++) {
            if (model.getPcClass().getClassList().get(i).getClassName().equals(sc.getClassName())) {
                lastShownList = model.getPcClass().getClassList().get(i).getStudents().asUnmodifiableObservableList();
                break;
            }
        }
        Student studentToEdit = null;
        if (lastShownList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        for (int i = 0; i < lastShownList.size(); i++) {
            Student curr = lastShownList.get(i);
            if (i == lastShownList.size() - 1 && !curr.getIndexNumber().equals(indexNumber)) {

                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
            if (curr.getIndexNumber().equals(indexNumber) && curr.getStudentClass().equals(sc)) {
                Set<Attendance> attendanceSet = curr.getAttendance();
                if (attendanceSet.contains(attendance)) {
                    throw new CommandException(Messages.MESSAGE_DUPLICATE_ATTENDANCE);
                }

                studentToEdit = curr;
                break;
            }
        }
        if (studentToEdit == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        Set<Attendance> attendanceSet = studentToEdit.getAttendance();
        Set<Attendance> attendanceSetReplaced = new HashSet<>();
        if (attendance.isAbsent()) {
            LocalDate today = LocalDate.now();
            for (Attendance att : attendanceSet) {
                if (!att.isPresent(today)) {
                    attendanceSetReplaced.add(att);
                }
            }
        } else {
            attendanceSetReplaced.addAll(attendanceSet);
            attendanceSetReplaced.add(attendance);
        }
        Student editedStudent = new Student(studentToEdit.getName(), studentToEdit.getStudentClass(),
                studentToEdit.getIndexNumber(), studentToEdit.getSex(), studentToEdit.getParentName(),
                studentToEdit.getParentNumber(), studentToEdit.getRls(), studentToEdit.getAge(),
                studentToEdit.getImage(), studentToEdit.getEmail(), studentToEdit.getPhone(),
                studentToEdit.getCca(), studentToEdit.getAddress(), attendanceSetReplaced,
                studentToEdit.getHomework(), studentToEdit.getTest(),
                studentToEdit.getTags(), studentToEdit.getComment());
        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedStudent));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
