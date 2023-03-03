package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

public class VaxRecordTest {
    private static final VaxType TYPE_1 = new VaxType("UNCHI",
            VaxType.DEFAULT_GROUP_SET,
            VaxType.DEFAULT_MIN_AGE,
            VaxType.DEFAULT_MAX_AGE,
            VaxType.DEFAULT_MIN_SPACING,
            VaxType.DEFAULT_ALLERGY_REQS,
            VaxType.DEFAULT_HISTORY_REQS);
    private static final VaxType TYPE_2 = new VaxType("ZAKO",
            VaxType.DEFAULT_GROUP_SET,
            VaxType.DEFAULT_MIN_AGE,
            VaxType.DEFAULT_MAX_AGE,
            VaxType.DEFAULT_MIN_SPACING,
            VaxType.DEFAULT_ALLERGY_REQS,
            VaxType.DEFAULT_HISTORY_REQS);

    private static final List<VaxRecord> CORRECT_REC_ORDER = List.of(
            new VaxRecord(TYPE_1, LocalDateTime.MIN),
            new VaxRecord(TYPE_2, LocalDateTime.MIN),
            new VaxRecord(TYPE_1, LocalDateTime.MAX));


    @Test
    public void compareToTest() {
        ArrayList<VaxRecord> sortedRec = new ArrayList<>(CORRECT_REC_ORDER);
        // compareTo will sort in a way that it thinks is correct
        // if what it thinks is wrong, sortedRec will change.
        sortedRec.sort(Comparator.naturalOrder());
        assertEquals(CORRECT_REC_ORDER, sortedRec);
    }
}
