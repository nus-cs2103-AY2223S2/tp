package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.vms.model.GroupName;

public class VaxRecordKeyTest {
    private static final GroupName SAMPLE_NAME = new GroupName("UNCHI");
    private static final LocalDateTime SAMPLE_TIME = LocalDateTime.now();
    private static final VaxRecordKey SAMPLE_RECORD = new VaxRecordKey(SAMPLE_NAME, SAMPLE_TIME);


    @Test
    public void constructor_nullParam_exceptionThrown() {
        assertThrows(NullPointerException.class,
                () -> new VaxRecordKey(null, LocalDateTime.now()));
        assertThrows(NullPointerException.class,
                () -> new VaxRecordKey(SAMPLE_NAME, null));
    }


    @Test
    public void getVaccination() {
        assertEquals(SAMPLE_NAME.toString(), SAMPLE_RECORD.getVaxTypeKey());
    }


    @Test
    public void getTimeTaken() {
        assertEquals(SAMPLE_TIME, SAMPLE_RECORD.getTimeTaken());
    }


    @Test
    public void equals() {
        Object eqs = new VaxRecordKey(SAMPLE_NAME, SAMPLE_TIME);
        Object diff1 = new VaxRecordKey(SAMPLE_NAME, LocalDateTime.MIN);
        Object diff2 = new VaxRecordKey(new GroupName("BANANA"), SAMPLE_TIME);
        Object unrelated = Integer.valueOf(445);

        assertTrue(SAMPLE_RECORD.equals(SAMPLE_RECORD));
        assertTrue(SAMPLE_RECORD.equals(eqs));
        assertFalse(SAMPLE_RECORD.equals(diff1));
        assertFalse(SAMPLE_RECORD.equals(diff2));
        assertFalse(SAMPLE_RECORD.equals(unrelated));
    }


    @Test
    public void hashingTest() {
        VaxRecordKey rec1 = SAMPLE_RECORD;
        VaxRecordKey recEq = new VaxRecordKey(SAMPLE_NAME, SAMPLE_TIME);
        VaxRecordKey recDiff = new VaxRecordKey(new GroupName("BANANA"), SAMPLE_TIME);

        HashSet<VaxRecordKey> recSet = new HashSet<>();
        recSet.add(rec1);

        assertTrue(recSet.contains(recEq));
        assertFalse(recSet.contains(recDiff));
    }
}
