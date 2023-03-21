package seedu.patientist.model.ward;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.patientist.testutil.TypicalWards.BLOCK_A_WARD_1;
import static seedu.patientist.testutil.TypicalWards.BLOCK_A_WARD_2;

import seedu.patientist.model.person.Person;
import seedu.patientist.testutil.WardBuilder;
import org.junit.jupiter.api.Test;

public class WardListTest {
    WardList wardList = new WardList();

    @Test
    public void contains_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wardList.contains((Ward) null));
        assertThrows(NullPointerException.class, () -> wardList.contains((Person) null));
    }
}
