package seedu.address.model.readonly;

import javafx.collections.ObservableList;
import seedu.address.model.drug.Drug;

/**
 * Unmodifiable view of a drug inventory
 */
public interface ReadOnlyDrugInventory {
    ObservableList<Drug> getDrugList();
}
