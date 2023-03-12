package seedu.address.model.readonly;

import javafx.collections.ObservableList;
import seedu.address.model.hospital.Hospital;

public interface ReadOnlyHospitalRecords {
    ObservableList<Hospital> getHospitalList();
}
