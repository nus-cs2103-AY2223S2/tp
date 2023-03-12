package seedu.careflow.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.drug.UniqueDrugList;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;

/**
 * Wraps all data at the Drug Inventory level
 */
public class DrugInventory implements ReadOnlyDrugInventory {
    private final UniqueDrugList drugs;

    {
        drugs = new UniqueDrugList();
    }

    public DrugInventory() {}

    /**
     * creates a DrugInventory using the Drugs in the {@code toBeCopied}
     */
    public DrugInventory(ReadOnlyDrugInventory toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs.setDrugs(drugs);
    }

    /**
     * Resets the existing data of this drug inventory with {@code newData}.
     */
    public void resetData(ReadOnlyDrugInventory newData) {
        requireNonNull(newData);
        setDrugs(newData.getDrugList());
    }

    /**
     * Returns true if a drug with the same identity as {@code drug} exists in the drug inventory.
     */
    public boolean hasDrug(Drug drug) {
        requireNonNull(drug);
        return drugs.contains(drug);
    }

    /**
     * Adds a drug to the drug inventory.
     * The drug must not already exist in the drug inventory.
     */
    public void addDrug(Drug d) {
        drugs.add(d);
    }

    /**
     * Replaces the given drug {@code target} in the list with {@code editedDrug}.
     * {@code target} must exist in the drug inventory.
     * The drug identity of {@code editedDrug} must not be the same as another existing drug in the drug
     * inventory.
     */
    public void setDrug(Drug target, Drug editedDrug) {
        requireNonNull(target);
        drugs.setDrug(target, editedDrug);
    }

    /**
     * Removes {@code key} from drug inventory.
     * {@code key} must exist in the drug inventory.
     */
    public void removeDrug(Drug key) {
        drugs.remove(key);
    }

    @Override
    public String toString() {
        return drugs.asUnmodifiableObservableList().size() + " drugs";
    }

    @Override
    public ObservableList<Drug> getDrugList() {
        return drugs.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DrugInventory
                && drugs.equals(((DrugInventory) other).drugs));
    }

    @Override
    public int hashCode() {
        return drugs.hashCode();
    }
}
