package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.vms.testutil.SampleVaxTypeData;

public class VaxRecordTest {
    private static final List<VaxRecord> CORRECT_REC_ORDER = List.of(
            new VaxRecord(SampleVaxTypeData.TYPE_REAL, LocalDateTime.MIN),
            new VaxRecord(SampleVaxTypeData.TYPE_1, LocalDateTime.MIN),
            new VaxRecord(SampleVaxTypeData.TYPE_REAL, LocalDateTime.MAX));


    @Test
    public void compareToTest() {
        ArrayList<VaxRecord> sortedRec = new ArrayList<>(CORRECT_REC_ORDER);
        // compareTo will sort in a way that it thinks is correct
        // if what it thinks is wrong, sortedRec will change.
        sortedRec.sort(Comparator.naturalOrder());
        assertEquals(CORRECT_REC_ORDER, sortedRec);
    }
}
