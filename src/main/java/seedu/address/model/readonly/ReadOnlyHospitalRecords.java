package seedu.address.model.readonly;

import javafx.collections.ObservableList;
import seedu.address.model.hospital.Hospital;

/**
 * Unmodifiable view of a hospital record
 */
public interface ReadOnlyHospitalRecords {
    ObservableList<Hospital> getHospitalList();
}
