package seedu.fitbook.testutil.routine;

import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EXERCISE_NUMBER;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_ROUTINE;

import seedu.fitbook.logic.commands.AddRoutineCommand;
import seedu.fitbook.logic.commands.EditRoutineCommand.EditRoutineDescriptor;
import seedu.fitbook.model.routines.Routine;

/**
 * A utility class for Routine.
 */
public class RoutineUtil {

    /**
     * Returns an add routine command string for adding the {@code routine}.
     */
    public static String getAddRoutineCommand(Routine routine) {
        return AddRoutineCommand.COMMAND_WORD + " " + getRoutineDetails(routine);
    }

    /**
     * Returns the part of command string for the given {@code routine}'s details.
     */
    public static String getRoutineDetails(Routine routine) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ROUTINE + routine.getRoutineName().routineName + " ");
        routine.getExercises().stream().forEach(
                s -> sb.append(PREFIX_EXERCISE + s.exerciseName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditRoutineDescriptor}'s details for exercise change.
     */
    public static String getEditRoutineDescriptorDetailsForExercise(EditRoutineDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getExercise()
                .ifPresent(routineName -> sb.append(PREFIX_EXERCISE).append(routineName.exerciseName).append(" "));
        descriptor.getExerciseIndex()
                .ifPresent(routineName ->
                        sb.append(PREFIX_EXERCISE_NUMBER).append(routineName.getOneBased()));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditRoutineDescriptor}'s details for Routine name change.
     */
    public static String getEditRoutineDescriptorDetailsForRoutineName(EditRoutineDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getRoutineName()
                .ifPresent(routineName -> sb.append(PREFIX_ROUTINE).append(routineName.routineName).append(" "));
        return sb.toString();
    }
}
