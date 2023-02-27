package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;


public class VaccinationRecordTest {
    private static final List<VaccinationRecord> ORDERED_LIST = List.of(
            new VaccinationRecord(Vaccination.MODERNA_DOSE_1, LocalDateTime.of(2023, 3, 5, 4, 55)),
            new VaccinationRecord(Vaccination.MODERNA_DOSE_2, LocalDateTime.of(2023, 3, 5, 4, 55)),
            new VaccinationRecord(Vaccination.MODERNA_DOSE_1, LocalDateTime.of(2023, 3, 5, 4, 56)));


    @Test
    public void compareToTest() {
        ArrayList<VaccinationRecord> sortedList = new ArrayList<>(ORDERED_LIST);
        sortedList.sort(Comparator.naturalOrder());
        // if compareTo is implemented wrong, records will be rearranged
        assertEquals(ORDERED_LIST, sortedList);
    }
}
