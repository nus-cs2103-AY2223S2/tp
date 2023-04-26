package seedu.careflow.model.readonly;

import javafx.collections.ObservableList;
import seedu.careflow.model.hospital.Hospital;

/**
 * Unmodifiable view of a hospital record
 */
public interface ReadOnlyHospitalRecords {
    ObservableList<Hospital> getHospitalList();
}
