package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Exam;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateEntryException;

/**
 * Adds an exam to a student.
 */
public class CreateExamCommand extends Command {

    public static final String COMMAND_WORD = "add-exam";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an exam to a student.\n"
            + "Parameters: "
            + "n/STUDENT_NAME "
            + "e/EXAM_DESCRIPTION "
            + "start/START_TIME "
            + "end/END_TIME\n"
            + "Example: " + COMMAND_WORD + " "
            + "n/John Doe "
            + "e/Math MYE Paper 1 "
            + "start/2023-03-21 12:00"
            + " end/2023-03-21 13:00";


    private final String examDescription;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final NameContainsKeywordsPredicate predicate;

    /**
     * Creates a CreateExamCommand to add the specified exam to the specified student.
     */
    public CreateExamCommand(NameContainsKeywordsPredicate predicate, String examDescription, LocalDateTime startTime,
            LocalDateTime endTime) {
        requireNonNull(predicate);
        requireNonNull(examDescription);
        requireNonNull(startTime);

        this.examDescription = examDescription;
        this.startTime = startTime;
        this.endTime = endTime;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);

        List<Student> studentList = model.getFilteredStudentList();

        if (startTime.isBefore(LocalDateTime.now())) {
            throw new CommandException("start time must be in the future.");
        }

        //Todo: currently weightage is 0 for convenience, implement this where possible
        Exam exam = new Exam(examDescription, startTime, endTime, 0d, Exam.ExamStatus.Upcoming, null);

        try {
            for (Student student : studentList) {
                student.addExam(exam);
            }
        } catch (DuplicateEntryException e) {
            throw new CommandException(e.getMessage());
        }

        StringBuilder sb = new StringBuilder();
        for (Student student : studentList) {
            sb.append(student.getName().fullName);
            sb.append("\n");
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_EXAM_ADDED_SUCCESS, exam, sb));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateExamCommand // instanceof handles nulls
                && predicate.equals(((CreateExamCommand) other).predicate)
                && startTime.equals(((CreateExamCommand) other).startTime)
                && endTime.equals(((CreateExamCommand) other).endTime)
                && examDescription.equals(((CreateExamCommand) other).examDescription));
    }
}

