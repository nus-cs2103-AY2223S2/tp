package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Student;

/**
 * Finds and lists all homework in the homework tracker that match the given name and status keywords.
 * Displays a list of homework with the ability to filter by student name and homework status.
 * Keyword matching is case-insensitive.
 */
public class ViewLessonCommand extends Command {

    public static final String COMMAND_WORD = "view-lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all exams filtered by\n"
        + "* name of student (case-insensitive) and/or\n"
        + "* date and/or\n"
        + "* subject and/or\n"
        + "* whether it is done\n"
        + "and displays them as a list with index numbers.\n"
        + "Parameters: [n/STUDENT_NAME] [date/Date] [sub/Subject] [done/COMPLETED_KEYWORD]\n"
        + "Example: " + COMMAND_WORD + " n/John date/2023-03-29 sub/Biology done/done";
    private static final String SEPERATOR = "--------------------------------------------------\n";
    private static final String DOT = ". ";
    private static final String LINE_BREAK = "\n";
    private static final String NAME_LABEL = "%s:\n";
    private static final Predicate<Lesson> SHOW_ALL_LESSONS = lesson -> true;

    private final Predicate<Student> namePredicate;
    private final Predicate<Lesson> lessonDatePredicate;
    private final Predicate<Lesson> subjectPredicate;
    private final Predicate<Lesson> donePredicate;
    private final boolean defaultPredicateFlag;


    /**
     * Overloaded constructor for ViewLessonCommand.
     *
     * @param namePredicate Predicate to filter students by name.
     * @param defaultPredicateFlag Flag to indicate if the default predicate is used.
     */
    public ViewLessonCommand(Predicate<Student> namePredicate, Predicate<Lesson> subjectPredicate,
                             Predicate<Lesson> donePredicate, boolean defaultPredicateFlag) {
        this.namePredicate = namePredicate;
        this.lessonDatePredicate = SHOW_ALL_LESSONS;
        this.defaultPredicateFlag = defaultPredicateFlag;
        this.subjectPredicate = subjectPredicate;
        this.donePredicate = donePredicate;
    }

    /**
     * Overloaded constructor for ViewLessonCommand.
     *
     * @param namePredicate Predicate to filter students by name.
     * @param lessonDatePredicate Predicate to filter lessons by date.
     * @param defaultPredicateFlag Flag to indicate if the default predicate is used.
     */
    public ViewLessonCommand(Predicate<Student> namePredicate, Predicate<Lesson> lessonDatePredicate,
                               Predicate<Lesson> subjectPredicate, Predicate<Lesson> donePredicate,
                             boolean defaultPredicateFlag) {
        this.namePredicate = namePredicate;
        this.lessonDatePredicate = lessonDatePredicate;
        this.defaultPredicateFlag = defaultPredicateFlag;
        this.subjectPredicate = subjectPredicate;
        this.donePredicate = donePredicate;
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
        model.updateFilteredStudentList(namePredicate);

        List<Student> studentList = model.getFilteredStudentList();

        int numberOfStudents = studentList.size();
        int numOfLessons = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(SEPERATOR);

        // Loop through each student and add their lesson to the string builder
        for (Student student : studentList) {
            List<Lesson> lessonList = student.getFilteredLessonsList(lessonDatePredicate, subjectPredicate,
                donePredicate);
            if (!lessonList.isEmpty()) {
                sb.append(String.format(NAME_LABEL, student.getName().fullName)).append(LINE_BREAK);
                numOfLessons += lessonList.size();

                for (int i = 0; i < lessonList.size(); i++) {
                    sb.append(i + 1).append(DOT).append(lessonList.get(i)).append(LINE_BREAK);
                }

                sb.append(SEPERATOR);
            }
        }

        // If no homework is found, throw an exception
        if (numOfLessons == 0) {
            throw new CommandException(Messages.MESSAGE_NO_LESSON_FOUND);
        }

        // If the default predicate is used, display a different message
        if (defaultPredicateFlag) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_ALL_LESSONS_LISTED_OVERVIEW, numOfLessons, sb));
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_LESSONS_LISTED_OVERVIEW, numOfLessons, numberOfStudents, sb));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewLessonCommand // instanceof handles nulls
                && namePredicate.equals(((ViewLessonCommand) other).namePredicate)
                && lessonDatePredicate.equals(((ViewLessonCommand) other).lessonDatePredicate)
                && defaultPredicateFlag == ((ViewLessonCommand) other).defaultPredicateFlag); // state check
    }
}
