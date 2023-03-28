package seedu.patientist.model.ward;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.patientist.model.person.Person;


public class WardListTest {
    private WardList wardList = new WardList();

    @Test
    public void contains_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wardList.contains((Ward) null));
        assertThrows(NullPointerException.class, () -> wardList.contains((Person) null));
    }
}
