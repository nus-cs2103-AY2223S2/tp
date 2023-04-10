package seedu.fitbook.model.routine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_EXERCISE_SITUP;
import static seedu.fitbook.testutil.Assert.assertThrows;
import static seedu.fitbook.testutil.routine.TypicalRoutines.CARDIO;
import static seedu.fitbook.testutil.routine.TypicalRoutines.STRENGTH;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.model.routines.UniqueRoutineList;
import seedu.fitbook.model.routines.exceptions.DuplicateRoutineException;
import seedu.fitbook.model.routines.exceptions.RoutineNotFoundException;
import seedu.fitbook.testutil.routine.RoutineBuilder;

public class UniqueRoutineListTest {

    private final UniqueRoutineList uniqueRoutineList = new UniqueRoutineList();

    @Test
    public void contains_nullRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.contains(null));
    }

    @Test
    public void contains_routineNotInList_returnsFalse() {
        assertFalse(uniqueRoutineList.contains(CARDIO));
    }

    @Test
    public void contains_routineInList_returnsTrue() {
        uniqueRoutineList.add(CARDIO);
        assertTrue(uniqueRoutineList.contains(CARDIO));
    }

    @Test
    public void contains_routineWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRoutineList.add(CARDIO);
        Routine editedCardio = new RoutineBuilder(CARDIO).withExercises(VALID_EXERCISE_SITUP)
                .build();
        assertTrue(uniqueRoutineList.contains(editedCardio));
    }

    @Test
    public void add_nullRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.add(null));
    }

    @Test
    public void add_duplicateRoutine_throwsDuplicateRoutineException() {
        uniqueRoutineList.add(CARDIO);
        assertThrows(DuplicateRoutineException.class, () -> uniqueRoutineList.add(CARDIO));
    }

    @Test
    public void setRoutine_nullTargetClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.setRoutine(null, CARDIO));
    }

    @Test
    public void setRoutine_nullEditedRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.setRoutine(CARDIO, null));
    }

    @Test
    public void setRoutine_targetRoutineNotInList_throwsRoutineNotFoundException() {
        assertThrows(RoutineNotFoundException.class, () -> uniqueRoutineList.setRoutine(CARDIO, CARDIO));
    }

    @Test
    public void setRoutine_editedRoutineIsSameRoutine_success() {
        uniqueRoutineList.add(CARDIO);
        uniqueRoutineList.setRoutine(CARDIO, CARDIO);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        expectedUniqueRoutineList.add(CARDIO);
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutine_editedClientHasSameIdentity_success() {
        uniqueRoutineList.add(CARDIO);
        Routine editedRoutine = new RoutineBuilder(CARDIO).withExercises(VALID_EXERCISE_SITUP)
                .build();
        uniqueRoutineList.setRoutine(CARDIO, editedRoutine);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        expectedUniqueRoutineList.add(editedRoutine);
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutine_editedRoutineHasDifferentIdentity_success() {
        uniqueRoutineList.add(CARDIO);
        uniqueRoutineList.setRoutine(CARDIO, STRENGTH);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        expectedUniqueRoutineList.add(STRENGTH);
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutine_editedRoutineHasNonUniqueIdentity_throwsDuplicateRoutineException() {
        uniqueRoutineList.add(CARDIO);
        uniqueRoutineList.add(STRENGTH);
        assertThrows(DuplicateRoutineException.class, () -> uniqueRoutineList.setRoutine(CARDIO, STRENGTH));
    }

    @Test
    public void remove_nullRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.remove(null));
    }

    @Test
    public void remove_routineDoesNotExist_throwsRoutineNotFoundException() {
        assertThrows(RoutineNotFoundException.class, () -> uniqueRoutineList.remove(CARDIO));
    }

    @Test
    public void remove_existingRoutine_removesRoutine() {
        uniqueRoutineList.add(CARDIO);
        uniqueRoutineList.remove(CARDIO);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutines_nullUniqueRoutineList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.setRoutines((UniqueRoutineList) null));
    }

    @Test
    public void setRoutines_uniqueRoutineList_replacesOwnListWithProvidedUniqueRoutineList() {
        uniqueRoutineList.add(CARDIO);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        expectedUniqueRoutineList.add(STRENGTH);
        uniqueRoutineList.setRoutines(expectedUniqueRoutineList);
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutines_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.setRoutines((List<Routine>) null));
    }

    @Test
    public void setRoutines_list_replacesOwnListWithProvidedList() {
        uniqueRoutineList.add(CARDIO);
        List<Routine> routineList = Collections.singletonList(STRENGTH);
        uniqueRoutineList.setRoutines(routineList);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        expectedUniqueRoutineList.add(STRENGTH);
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutines_listWithDuplicateRoutines_throwsDuplicateRoutineException() {
        List<Routine> listWithDuplicateRoutines = Arrays.asList(CARDIO, CARDIO);
        assertThrows(DuplicateRoutineException.class, () -> uniqueRoutineList.setRoutines(listWithDuplicateRoutines));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueRoutineList.asUnmodifiableObservableList().remove(0));
    }
}
