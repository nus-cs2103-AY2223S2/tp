package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Homework;
import seedu.address.model.student.Student;

/**
 * Finds and lists all homework in the homework tracker that match the given name and status keywords.
 * Displays a list of homework with the ability to filter by student name and homework status.
 * Keyword matching is case-insensitive.
 */
public class ViewHomeworkCommand extends Command {

    public static final String COMMAND_WORD = "view-homework";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all homework that match the specified "
            + "name and status keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_NAME + "STUDENT_NAME "
            + PREFIX_STATUS + "STATUS\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_STATUS + "pending";
    private static final String SEPERATOR = "--------------------------------------------------\n";
    private static final String DOT = ". ";
    private static final String LINE_BREAK = "\n";
    private static final String NAME_LABEL = "%s:\n";
    private static final Predicate<Homework> SHOW_ALL_HOMEWORK = homework -> true;
    private final Predicate<Student> namePredicate;
    private final Predicate<Homework> homeworkStatusPredicate;
    private final boolean defaultPredicateFlag;


    /**
     * Overloaded constructor for ViewHomeworkCommand.
     *
     * @param namePredicate Predicate to filter students by name.
     * @param defaultPredicateFlag Flag to indicate if the default predicate is used.
     */
    public ViewHomeworkCommand(Predicate<Student> namePredicate, boolean defaultPredicateFlag) {
        this.namePredicate = namePredicate;
        this.homeworkStatusPredicate = SHOW_ALL_HOMEWORK;
        this.defaultPredicateFlag = defaultPredicateFlag;
    }

    /**
     * Overloaded constructor for ViewHomeworkCommand.
     *
     * @param namePredicate Predicate to filter students by name.
     * @param homeworkStatusPredicate Predicate to filter homework by status.
     * @param defaultPredicateFlag Flag to indicate if the default predicate is used.
     */
    public ViewHomeworkCommand(Predicate<Student> namePredicate, Predicate<Homework> homeworkStatusPredicate,
                               boolean defaultPredicateFlag) {
        this.namePredicate = namePredicate;
        this.homeworkStatusPredicate = homeworkStatusPredicate;
        this.defaultPredicateFlag = defaultPredicateFlag;
    }

    /**
     * Executes the view-homework command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException if an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(namePredicate);

        List<Student> studentList = model.getFilteredStudentList();

        int numberOfStudents = studentList.size();
        int numberOfHomework = getNumberOfHomework(studentList);
        String message = formatAllHomework(studentList);


        // If no homework is found, throw an exception
        if (numberOfHomework == 0) {
            throw new CommandException(Messages.MESSAGE_NO_HOMEWORK_FOUND);
        }

        // If the default predicate is used, display a different message
        if (defaultPredicateFlag) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_ALL_HOMEWORK_LISTED_OVERVIEW, numberOfHomework, message));
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_HOMEWORK_LISTED_OVERVIEW,
                            numberOfHomework, numberOfStudents, message));
        }
    }

    /**
     * Formats all homework in the student list into a string.
     *
     * @param studentList List of students.
     * @return String containing all homework in the student list.
     */
    public String formatAllHomework(List<Student> studentList) {
        int numberOfHomework = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(SEPERATOR);

        // Loop through each student and add their homework to the string builder
        for (Student student : studentList) {
            List<Homework> homeworkList = student.getHomeworkList();
            if (!homeworkList.isEmpty()) {
                sb.append(String.format(NAME_LABEL, student.getName().fullName));
                numberOfHomework += homeworkList.size();

                for (int i = 0; i < homeworkList.size(); i++) {
                    sb.append(i + 1).append(DOT).append(homeworkList.get(i)).append(LINE_BREAK);
                }

                sb.append(SEPERATOR);
            }
        }
        return sb.toString();
    }

    /**
     * Returns the number of homework in the student list.
     *
     * @param studentList List of students.
     * @return Number of homework in the student list.
     */
    public int getNumberOfHomework(List<Student> studentList) {
        int numberOfHomework = 0;
        for (Student student : studentList) {
            List<Homework> homeworkList = student.getHomeworkList();
            if (!homeworkList.isEmpty()) {
                numberOfHomework += homeworkList.size();
            }
        }
        return numberOfHomework;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewHomeworkCommand // instanceof handles nulls
                && namePredicate.equals(((ViewHomeworkCommand) other).namePredicate)
                && homeworkStatusPredicate.equals(((ViewHomeworkCommand) other).homeworkStatusPredicate)
                && defaultPredicateFlag == ((ViewHomeworkCommand) other).defaultPredicateFlag); // state check
    }
}
