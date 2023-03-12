package seedu.careflow.model.readonly;

import javafx.collections.ObservableList;
import seedu.careflow.model.drug.Drug;

/**
 * Unmodifiable view of a drug inventory
 */
public interface ReadOnlyDrugInventory {
    ObservableList<Drug> getDrugList();
}
