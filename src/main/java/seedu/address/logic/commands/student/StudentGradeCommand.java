package seedu.address.logic.commands.student;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORKDONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEXNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;
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
 * A class for "student 'Class Name' 'Index number' grade" command"
 */
public class StudentGradeCommand extends StudentCommand {
    public static final String COMMAND_WORD = "grade";

    public static final String MESSAGE_USAGE = "student CLASS_NAME " + COMMAND_WORD
            + ": Adds a grade to a student in the address book. \n"
            + "Parameters: "
            + PREFIX_INDEXNUMBER + "INDEX NUMBER "
            + PREFIX_TEST + "TEST NAME OR "
            + PREFIX_HOMEWORK + "HOMEWORK NAME "
            + "["
            + PREFIX_SCORE + "SCORE "
            + PREFIX_DEADLINE + "DEADLINE (DD/MM/YYYY) "
            + PREFIX_WEIGHTAGE + "WEIGHTAGE "
            + PREFIX_HOMEWORKDONE + "HOMEWORK DONE? "
            + "]\n"
            + "Example: " + "student 1A " + COMMAND_WORD + " "
            + PREFIX_INDEXNUMBER + "13 "
            + PREFIX_TEST + "CA1 "
            + PREFIX_SCORE + "75 "
            + PREFIX_DEADLINE + "15/05/2023 "
            + PREFIX_WEIGHTAGE + "20\n"
            + "student 1A " + COMMAND_WORD + " "
            + PREFIX_INDEXNUMBER + "13 "
            + PREFIX_HOMEWORK + "Chapter 1 "
            + PREFIX_SCORE + "90 "
            + PREFIX_DEADLINE + "20/03/2023 "
            + PREFIX_WEIGHTAGE + "10";

    public static final String MESSAGE_SUCCESSTEST = "New test added:";
    public static final String MESSAGE_SUCCESSHOMEWORK = "New homework added:";

    private IndexNumber indexNumber;
    private Test test;
    private Homework homework;
    private Class sc;

    /**
     * Creates an add grade command for test
     *
     * @param indexNumber student to be edited
     * @param test test to be added
     */
    public StudentGradeCommand(Class sc, IndexNumber indexNumber, Test test) {
        this.sc = sc;
        this.indexNumber = indexNumber;
        this.test = test;
    }

    /**
     * Creates an add grade command for homework
     * @param sc
     * @param indexNumber
     * @param homework
     */
    public StudentGradeCommand(Class sc, IndexNumber indexNumber, Homework homework) {
        this.sc = sc;
        this.indexNumber = indexNumber;
        this.homework = homework;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = getClassList(model);
        if (lastShownList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        Student studentToEdit = getStudentToEdit(lastShownList);
        if (this.homework != null) {
            Set<Homework> homeworkSet = studentToEdit.getHomework();
            Set<Homework> homeworkSetReplaced = generateHomeWorkSet(homeworkSet);
            int weightage = getHomeWorkWeightage(homeworkSetReplaced);
            checkHomeworkWeightage(weightage, homework);
            homeworkSetReplaced.add(homework);
            Student editedStudent = generateHomeworkNewStudent(studentToEdit, homeworkSetReplaced);
            model.setStudent(studentToEdit, editedStudent);
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
            return new CommandResult(MESSAGE_SUCCESSHOMEWORK);
        } else {
            Set<Test> testSet = studentToEdit.getTest();
            Set<Test> testSetReplaced = generateTestSet(testSet);
            int weightage = getTestWeightage(testSetReplaced);
            checkTestWeightage(weightage, test);
            testSetReplaced.add(test);
            Student editedStudent = generateTestNewStudent(studentToEdit, testSetReplaced);
            model.setStudent(studentToEdit, editedStudent);
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
            return new CommandResult(MESSAGE_SUCCESSTEST);
        }
    }

    /**
     * Check if test weightage is more than 100
     * @param currWeightage
     * @param test
     * @throws CommandException
     */
    public void checkTestWeightage(int currWeightage, Test test) throws CommandException {
        if (currWeightage + test.getWeightage() > 100) {
            throw new CommandException(Messages.MESSAGE_INVALID_WEIGHTAGE);
        }
    }
    /**
     * Check if homework weightage is more than 100
     * @param currWeightage
     * @param homework
     * @throws CommandException
     */
    public void checkHomeworkWeightage(int currWeightage, Homework homework) throws CommandException {
        if (currWeightage + homework.getWeightage() > 100) {
            throw new CommandException(Messages.MESSAGE_INVALID_WEIGHTAGE);
        }
    }
    /**
     * Gets the student to be edited
     * @param studentToEdit
     * @param testSetReplaced
     * @return editedStudent
     * @throws CommandException
     */
    public Student generateTestNewStudent(Student studentToEdit, Set<Test> testSetReplaced) throws CommandException {
        return new Student(studentToEdit.getName(), studentToEdit.getStudentClass(),
                studentToEdit.getIndexNumber(), studentToEdit.getSex(), studentToEdit.getParentName(),
                studentToEdit.getParentNumber(), studentToEdit.getRls(), studentToEdit.getAge(),
                studentToEdit.getImage(), studentToEdit.getEmail(), studentToEdit.getPhone(),
                studentToEdit.getCca(), studentToEdit.getAddress(), studentToEdit.getAttendance(),
                studentToEdit.getHomework(), testSetReplaced, studentToEdit.getTags(), studentToEdit.getComment());
    }

    /**
     * Gets test weightage
     * @param testSetReplaced
     * @return weightage of all tests
     */
    public int getTestWeightage(Set<Test> testSetReplaced) {
        int weightage = 0;
        for (Test t: testSetReplaced) {
            if (t.getWeightage() == -100) {
                continue;
            }
            weightage += t.getWeightage();
        }
        return weightage;
    }
    /**
     * Generates a new test set without the default homework
     * @param testSet
     * @return Set of test
     */
    public Set<Test> generateTestSet(Set<Test> testSet) {
        Set<Test> testSetReplaced = new HashSet<>();
        testSetReplaced.addAll(testSet);
        for (Test t: testSetReplaced) {
            if (t.getName().equals("Insert student test here!")) {
                testSetReplaced.remove(t);
                break;
            }
        }
        return testSetReplaced;
    }
    /**
     * Generates a new student with the updated homework set
     * @param studentToEdit
     * @param homeworkSetReplaced
     * @return New student with updated homework
     */
    public Student generateHomeworkNewStudent(Student studentToEdit, Set<Homework> homeworkSetReplaced) {
        return new Student(studentToEdit.getName(), studentToEdit.getStudentClass(),
                studentToEdit.getIndexNumber(), studentToEdit.getSex(), studentToEdit.getParentName(),
                studentToEdit.getParentNumber(), studentToEdit.getRls(), studentToEdit.getAge(),
                studentToEdit.getImage(), studentToEdit.getEmail(), studentToEdit.getPhone(),
                studentToEdit.getCca(), studentToEdit.getAddress(), studentToEdit.getAttendance(),
                homeworkSetReplaced, studentToEdit.getTest(), studentToEdit.getTags(), studentToEdit.getComment());
    }

    /**
     *  Generates a homework set without the default homework
     * @param homeworkSet
     * @return a homework set to be replaced with the new student
     */
    public Set<Homework> generateHomeWorkSet(Set<Homework> homeworkSet) {
        Set<Homework> homeworkSetReplaced = new HashSet<>();
        homeworkSetReplaced.addAll(homeworkSet);
        for (Homework hw:homeworkSetReplaced) {
            if (hw.getName().equals("Insert student homework here!")) {
                homeworkSetReplaced.remove(hw);
                break;
            }
        }
        return homeworkSetReplaced;
    }
    /**
     * Returns the homework weightage
     * @param homeworkSetReplaced
     * @return homework weightage total
     */
    public int getHomeWorkWeightage(Set<Homework> homeworkSetReplaced) {
        int weightage = 0;
        for (Homework hw:homeworkSetReplaced) {
            if (hw.getWeightage() == -100) {
                continue;
            }
            weightage += hw.getWeightage();
        }
        return weightage;
    }
    /**
     * Returns the class list of the model
     * @param model
     * @return class list of model
     */
    public List<Student> getClassList(Model model) {
        for (int i = 0; i < model.getPcClass().getClassList().size(); i++) {
            if (model.getPcClass().getClassList().get(i).getClassName().equals(sc.getClassName())) {
                return model.getPcClass().getClassList().get(i).getStudents().asUnmodifiableObservableList();
            }
        }
        return model.getPcClass().getClassList().get(0).getStudents().asUnmodifiableObservableList();
    }
    public Student getStudentToEdit(List<Student> lastShownList) throws CommandException {
        for (int i = 0; i < lastShownList.size(); i++) {
            Student curr = lastShownList.get(i);
            if (i == lastShownList.size() - 1 && !curr.getIndexNumber().equals(indexNumber)) {

                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
            if (curr.getIndexNumber().equals(indexNumber) && curr.getStudentClass().equals(sc)) {
                Set<Homework> homeworkSet = curr.getHomework();
                for (Homework hw : homeworkSet) {
                    if (hw.equals(homework)) {
                        throw new CommandException(Messages.MESSAGE_DUPLICATE_HOMEWORK);
                    }
                }
                Set<Test> testSet = curr.getTest();
                for (Test test : testSet) {
                    if (test.equals(this.test)) {
                        throw new CommandException(Messages.MESSAGE_DUPLICATE_TEST);
                    }
                }
                return curr;
            }

        }
        return null;
    }

}
