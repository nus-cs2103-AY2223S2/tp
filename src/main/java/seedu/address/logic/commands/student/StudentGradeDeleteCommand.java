package seedu.address.logic.commands.student;

import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEXNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEST;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Class;
import seedu.address.model.person.student.Homework;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.Test;
/**
 * A class for "student 'Class Name' 'Index number' grade delete" command"
 */
public class StudentGradeDeleteCommand extends StudentCommand {
    public static final String COMMAND_WORD = "gradedelete";

    public static final String MESSAGE_USAGE = "student CLASS_NAME " + COMMAND_WORD
            + ": Adds a grade to the student in the address book. \n"
            + "Parameters: "
            + PREFIX_INDEXNUMBER + "INDEX NUMBER "
            + "["
            + PREFIX_TEST + "TEST NAME OR "
            + PREFIX_HOMEWORK + "HOMEWORK NAME "
            + "]\n"
            + "Example: " + "student 1A " + COMMAND_WORD + " "
            + PREFIX_INDEXNUMBER + "13 "
            + PREFIX_TEST + "CA1\n"
            + "student 1A" + COMMAND_WORD + " "
            + PREFIX_INDEXNUMBER + "13 "
            + PREFIX_HOMEWORK + "Chapter 1";

    public static final String MESSAGE_SUCCESSTEST = "Test deleted successfully";
    public static final String MESSAGE_SUCCESSHOMEWORK = "Homework deleted successfully";

    private IndexNumber indexNumber;
    private Test test;
    private Homework homework;
    private Class sc;

    /**
     * Creates a delete command for test
     *
     * @param indexNumber student to be edited
     * @param test test to be added
     */
    public StudentGradeDeleteCommand(Class sc, IndexNumber indexNumber, Test test) {
        this.sc = sc;
        this.indexNumber = indexNumber;
        this.test = test;
    }

    /**
     * Creates a delete command for homework
     * @param sc
     * @param indexNumber
     * @param homework
     */
    public StudentGradeDeleteCommand(Class sc, IndexNumber indexNumber, Homework homework) {
        this.sc = sc;
        this.indexNumber = indexNumber;
        this.homework = homework;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
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
                studentToEdit = curr;
                break;
            }

        }
        if (this.homework != null) {
            Set<Homework> homeworkSet = studentToEdit.getHomework();
            Set<Homework> homeworkSetReplaced = new HashSet<>();
            homeworkSetReplaced.addAll(homeworkSet);
            for (Homework hw:homeworkSetReplaced) {
                if (hw.getName().equals(this.homework.getName())) {
                    homeworkSetReplaced.remove(hw);
                    break;
                }
            }
            Student editedStudent = new Student(studentToEdit.getName(), studentToEdit.getStudentClass(),
                    studentToEdit.getIndexNumber(), studentToEdit.getSex(), studentToEdit.getParentName(),
                    studentToEdit.getParentNumber(), studentToEdit.getRls(), studentToEdit.getAge(),
                    studentToEdit.getImage(), studentToEdit.getEmail(), studentToEdit.getPhone(),
                    studentToEdit.getCca(), studentToEdit.getAddress(), studentToEdit.getAttendance(),
                    homeworkSetReplaced, studentToEdit.getTest(), studentToEdit.getTags(), studentToEdit.getComment());

            model.setStudent(studentToEdit, editedStudent);
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

            return new CommandResult(MESSAGE_SUCCESSHOMEWORK);
        } else {
            Set<Test> testSet = studentToEdit.getTest();
            Set<Test> testSetReplaced = new HashSet<>();
            testSetReplaced.addAll(testSet);
            for (Test t: testSetReplaced) {
                if (t.getName().equals(this.test.getName())) {
                    testSetReplaced.remove(t);
                    break;
                }
            }
            Student editedStudent = new Student(studentToEdit.getName(), studentToEdit.getStudentClass(),
                    studentToEdit.getIndexNumber(), studentToEdit.getSex(), studentToEdit.getParentName(),
                    studentToEdit.getParentNumber(), studentToEdit.getRls(), studentToEdit.getAge(),
                    studentToEdit.getImage(), studentToEdit.getEmail(), studentToEdit.getPhone(),
                    studentToEdit.getCca(), studentToEdit.getAddress(), studentToEdit.getAttendance(),
                    studentToEdit.getHomework(), testSetReplaced, studentToEdit.getTags(), studentToEdit.getComment());

            model.setStudent(studentToEdit, editedStudent);
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

            return new CommandResult(MESSAGE_SUCCESSTEST);
        }
    }
}
