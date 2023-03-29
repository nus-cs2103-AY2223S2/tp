package seedu.address.logic.commands.fish;

import static seedu.address.testutil.Assert.assertThrows;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.model.fish.Fish;

public class FishSortCommandTest {
    @Test
    public void constructor_nullComparator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FishSortCommand(null, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Comparator<Fish> validComp = (o1, o2) -> 0;
        assertThrows(NullPointerException.class, () -> new FishSortCommand(validComp, null).execute(null));
    }
}
