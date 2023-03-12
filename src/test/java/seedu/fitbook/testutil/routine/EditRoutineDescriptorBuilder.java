
package seedu.fitbook.testutil.routine;

import seedu.fitbook.logic.commands.EditRoutineCommand.EditRoutineDescriptor;
import seedu.fitbook.model.routines.Exercise;
import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.model.routines.RoutineName;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * A utility class to help with building EditClientDescriptor objects.
 */
public class EditRoutineDescriptorBuilder {

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
        descriptor.setExercise((Exercise) routine.getExercises());
    }

    /**
     * Sets the {@code RoutineName} of the {@code EditRoutineDescriptor} that we are building.
     */
    public EditRoutineDescriptorBuilder withRoutineName(String routineName) {
        descriptor.setRoutineName(new RoutineName(routineName));
        return this;
    }

    /**
     * Parses the {@code exercises} into a {@code Set<Exercise>} and set it to the {@code EditRoutineDescriptor}
     * that we are building.
     */
    public EditRoutineDescriptorBuilder withExercises(String... exercises) {
        List<Exercise> exerciseList = Stream.of(exercises).map(Exercise::new).collect(Collectors.toList());
        descriptor.setExercise(exerciseList);
        return this;
    }

    public EditRoutineDescriptor build() {
        return descriptor;
    }
}
