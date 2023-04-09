package seedu.address.logic.commands.exam;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Exam;
import seedu.address.model.student.Student;

/**
 * Finds and lists all homework in the homework tracker that match the given name and status keywords.
 * Displays a list of homework with the ability to filter by student name and homework status.
 * Keyword matching is case-insensitive.
 */
public class ViewExamCommand extends Command {

    public static final String COMMAND_WORD = "view-exam";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all exams filtered by\n"
        + "* name of student (case-insensitive) and/or\n"
        + "* date and/or\n"
        + "* exam name and/or\n"
        + "* whether it is done\n"
        + "and displays them as a list with index numbers.\n"
        + "Parameters: "
        + PREFIX_NAME + "STUDENT_NAME "
        + PREFIX_DATE + "DATE "
        + PREFIX_EXAM + "EXAM NAME "
        + PREFIX_DONE + "COMPLETED KEYWORD\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_DATE + "2023-05-21 "
        + PREFIX_EXAM + "Math MYE "
        + PREFIX_DONE + "done(or not done)";
    private static final Predicate<Exam> SHOW_ALL_EXAMS = exam -> true;
    private final Predicate<Student> namePredicate;
    private final Predicate<Exam> examDatePredicate;
    private final Predicate<Exam> examNamePredicate;
    private final Predicate<Exam> donePredicate;
    private final boolean defaultPredicateFlag;
    private final List<String> names;


    /**
     * Overloaded constructor for ViewLessonCommand.
     *
     * @param namePredicate Predicate to filter students by name.
     * @param defaultPredicateFlag Flag to indicate if the default predicate is used.
     */
    public ViewExamCommand(List<String> names, Predicate<Student> namePredicate, Predicate<Exam> examNamePredicate,
                             Predicate<Exam> donePredicate, boolean defaultPredicateFlag) {
        this.namePredicate = namePredicate;
        this.examDatePredicate = SHOW_ALL_EXAMS;
        this.defaultPredicateFlag = defaultPredicateFlag;
        this.examNamePredicate = examNamePredicate;
        this.donePredicate = donePredicate;
        this.names = names;
    }

    /**
     * Overloaded constructor for ViewLessonCommand.
     *
     * @param namePredicate Predicate to filter students by name.
     * @param examDatePredicate Predicate to filter lessons by date.
     * @param defaultPredicateFlag Flag to indicate if the default predicate is used.
     */
    public ViewExamCommand(List<String> names, Predicate<Student> namePredicate, Predicate<Exam> examDatePredicate,
                             Predicate<Exam> examNamePredicate, Predicate<Exam> donePredicate,
                             boolean defaultPredicateFlag) {
        this.namePredicate = namePredicate;
        this.examDatePredicate = examDatePredicate;
        this.defaultPredicateFlag = defaultPredicateFlag;
        this.examNamePredicate = examNamePredicate;
        this.donePredicate = donePredicate;
        this.names = names;
    }

    /**
     * Executes the view-lesson command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException if an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        StringBuilder nonExistNames = new StringBuilder();
        for (String name : names) {
            if (model.noSuchStudent(name)) {
                nonExistNames.append(name).append(", ");
            }
        }
        if (nonExistNames.length() != 0) {
            nonExistNames = new StringBuilder(nonExistNames.substring(0, nonExistNames.length() - 2));
            throw new CommandException(String.format(Messages.MESSAGE_NO_SUCH_STUDENT, nonExistNames));
        }
        StringBuilder dupNames = new StringBuilder();
        for (String name : names) {
            if (model.hasDuplicateName(name)) {
                dupNames.append(name).append(", ");
            }
        }
        if (dupNames.length() != 0) {
            dupNames = new StringBuilder(dupNames.substring(0, dupNames.length() - 2));
            throw new CommandException(String.format(Messages.MESSAGE_HAS_DUPLICATE_NAMES, dupNames));
        }
        model.updateFilteredStudentList(namePredicate);

        List<Student> studentList = model.getFilteredStudentList();

        int numberOfStudents = studentList.size();
        int numOfLessons = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------------\n");

        // Loop through each student and add their lesson to the string builder
        for (Student student : studentList) {
            List<Exam> examList = student.getFilteredExamList(examDatePredicate, examNamePredicate,
                donePredicate);
            if (!examList.isEmpty()) {
                sb.append(student.getName().fullName).append(":\n");

                numOfLessons += examList.size();

                for (int i = 0; i < examList.size(); i++) {
                    sb.append(i + 1).append(". ").append(examList.get(i)).append("\n");
                }

                sb.append("--------------------------------------------------\n");
            }
        }

        // If no homework is found, throw an exception
        if (numOfLessons == 0) {
            throw new CommandException(Messages.MESSAGE_NO_EXAM_FOUND);
        }

        // If the default predicate is used, display a different message
        if (defaultPredicateFlag) {
            return new CommandResult(
                String.format(Messages.MESSAGE_ALL_EXAMS_LISTED_OVERVIEW, numOfLessons, sb));
        } else {
            return new CommandResult(
                String.format(Messages.MESSAGE_EXAMS_LISTED_OVERVIEW, numOfLessons, numberOfStudents, sb));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ViewExamCommand // instanceof handles nulls
            && namePredicate.equals(((ViewExamCommand) other).namePredicate)
            && examDatePredicate.equals(((ViewExamCommand) other).examDatePredicate)
            && defaultPredicateFlag == ((ViewExamCommand) other).defaultPredicateFlag);
    }
}

