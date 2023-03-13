package taa.logic.commands;

import taa.model.Model;

/**
 * Grades a student submission of an assignment.
 */
public class GradeCommand extends Command {

    public static final String COMMAND_WORD = "grade";
    public static final String MESSAGE_SUCCESS = "Assignment submission graded.";
    private final String assignmentName;
    private final int studentId;
    private final int marks;

    /**
     * @param assignmentName
     * @param studentId
     * @param marks
     */
    public GradeCommand(String assignmentName, int studentId, int marks) {
        this.assignmentName = assignmentName;
        this.studentId = studentId;
        this.marks = marks;
    }

    @Override
    public CommandResult execute(Model model) {
        model.grade(assignmentName, studentId, marks);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
