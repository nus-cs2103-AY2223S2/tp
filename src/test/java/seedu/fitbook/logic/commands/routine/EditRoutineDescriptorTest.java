package seedu.fitbook.logic.commands.routine;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.logic.commands.CommandTestUtil.DESC_CARDIO;
import static seedu.fitbook.logic.commands.CommandTestUtil.DESC_STRENGTH;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_EXERCISE_SITUP;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_ROUTINE_NAME_STRENGTH;

import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.EditRoutineCommand.EditRoutineDescriptor;
import seedu.fitbook.testutil.routine.EditRoutineDescriptorBuilder;

public class EditRoutineDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditRoutineDescriptor descriptorWithSameValues = new EditRoutineDescriptor(DESC_CARDIO);
        assertTrue(DESC_CARDIO.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CARDIO.equals(DESC_CARDIO));

        // null -> returns false
        assertFalse(DESC_CARDIO.equals(null));

        // different types -> returns false
        assertFalse(DESC_CARDIO.equals(5));

        // different values -> returns false
        assertFalse(DESC_CARDIO.equals(DESC_STRENGTH));

        // different name -> returns false
        EditRoutineDescriptor editedCardio = new EditRoutineDescriptorBuilder(DESC_CARDIO)
                .withRoutineName(VALID_ROUTINE_NAME_STRENGTH).build();
        assertFalse(DESC_CARDIO.equals(editedCardio));

        // different exercises -> returns false
        editedCardio = new EditRoutineDescriptorBuilder(DESC_CARDIO).withExercise(VALID_EXERCISE_SITUP).build();
        assertFalse(DESC_CARDIO.equals(editedCardio));
    }
}
