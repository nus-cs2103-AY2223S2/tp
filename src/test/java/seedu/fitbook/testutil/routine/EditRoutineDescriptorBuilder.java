package seedu.fitbook.testutil.routine;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.EditRoutineCommand.EditRoutineDescriptor;
import seedu.fitbook.model.routines.Exercise;
import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.model.routines.RoutineName;

/**
 * A utility class to help with building EditRoutineDescriptor objects.
 */
public class EditRoutineDescriptorBuilder {

    private static final String DEFAULT_EXERCISE = "Plank";
    private static final int DEFAULT_EXERCISE_INDEX = 1;

    private EditRoutineDescriptor descriptor;

    public EditRoutineDescriptorBuilder() {
        descriptor = new EditRoutineDescriptor();
    }

    public EditRoutineDescriptorBuilder(EditRoutineDescriptor descriptor) {
        this.descriptor = new EditRoutineDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditRoutineDescriptor} with fields containing {@code routine}'s details
     */
    public EditRoutineDescriptorBuilder(Routine routine) {
        descriptor = new EditRoutineDescriptor();
        descriptor.setRoutineName(routine.getRoutineName());
        descriptor.setExerciseIndex(Index.fromOneBased(DEFAULT_EXERCISE_INDEX));
        descriptor.setExercise(new Exercise(DEFAULT_EXERCISE));
    }

    /**
     * Sets the {@code RoutineName} of the {@code EditRoutineDescriptor} that we are building.
     */
    public EditRoutineDescriptorBuilder withRoutineName(String routineName) {
        descriptor.setRoutineName(new RoutineName(routineName));
        return this;
    }

    /**
     * Sets the {@code Index} of the {@code EditRoutineDescriptor} that we are building.
     */
    public EditRoutineDescriptorBuilder withExercisesIndex(String index) {
        Index currIndex = Index.fromOneBased(Integer.parseInt(index));
        descriptor.setExerciseIndex(currIndex);
        return this;
    }

    /**
     * Sets the {@code Exercise} of the {@code EditRoutineDescriptor} that we are building.
     */
    public EditRoutineDescriptorBuilder withExercise(String exercise) {
        Exercise newExercise = new Exercise(exercise);
        descriptor.setExercise(newExercise);
        return this;
    }

    public EditRoutineDescriptor build() {
        return descriptor;
    }
}
