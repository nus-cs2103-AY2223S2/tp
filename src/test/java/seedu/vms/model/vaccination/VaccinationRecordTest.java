package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;


public class VaccinationRecordTest {
    private static final VaxType TYPE_1 = VaxType.of("TYPE_1", 5, 6, 7, List.of());
    private static final VaxType TYPE_2 = VaxType.of("TYPE_2", 5, 6, 7, List.of());

    private static final List<VaxRecord> ORDERED_LIST = List.of(
            new VaxRecord(TYPE_1, LocalDateTime.of(2023, 3, 5, 4, 55)),
            new VaxRecord(TYPE_2, LocalDateTime.of(2023, 3, 5, 4, 55)),
            new VaxRecord(TYPE_1, LocalDateTime.of(2023, 3, 5, 4, 56)));


    @Test
    public void compareToTest() {
        ArrayList<VaxRecord> sortedList = new ArrayList<>(ORDERED_LIST);
        sortedList.sort(Comparator.naturalOrder());
        // if compareTo is implemented wrong, records will be rearranged
        assertEquals(ORDERED_LIST, sortedList);
    }
}
