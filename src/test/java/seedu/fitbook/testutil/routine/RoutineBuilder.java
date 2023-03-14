package seedu.fitbook.testutil.routine;

import java.util.ArrayList;
import java.util.List;

import seedu.fitbook.model.routines.Exercise;
import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.model.routines.RoutineName;
import seedu.fitbook.model.util.SampleExerciseRoutineDataUtil;


/**
 * A utility class to help with building Routine objects.
 */
public class RoutineBuilder {

    public static final String DEFAULT_ROUTINE_NAME = "Cardio";

    private RoutineName routineName;
    private List<Exercise> exercises;


    /**
     * Creates a {@code RoutineBuilder} with the default details.
     */
    public RoutineBuilder() {
        routineName = new RoutineName(DEFAULT_ROUTINE_NAME);
        exercises = new ArrayList<>();
    }

    /**
     * Initializes the RoutineBuilder with the data of {@code routineToCopy}.
     */
    public RoutineBuilder(Routine routineToCopy) {
        routineName = routineToCopy.getRoutineName();
        exercises = new ArrayList<>(routineToCopy.getExercises());
    }

    /**
     * Sets the {@code RoutineName} of the {@code Routine} that we are building.
     */
    public RoutineBuilder withRoutineName(String routineName) {
        this.routineName = new RoutineName(routineName);
        return this;
    }

    /**
     * Parses the {@code exercises} into a {@code Set<Exercise>}
     * and set it to the {@code Routine} that we are building.
     */
    public RoutineBuilder withExercises(String ... exercises) {
        this.exercises = SampleExerciseRoutineDataUtil.getExerciseList(exercises);
        return this;
    }

    public Routine build() {
        return new Routine(routineName, exercises);
    }

}
