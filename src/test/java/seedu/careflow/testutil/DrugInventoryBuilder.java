package seedu.careflow.testutil;

import seedu.careflow.model.DrugInventory;
import seedu.careflow.model.drug.Drug;

/**
 * A utility class to help with building DrugInventory objects.
 * Example usage: <br>
 *     {@code DrugInventory hr = new DrugInventoryBuilder().withHospital("Prozac", "Vicine").build();}
 */
public class DrugInventoryBuilder {
    private DrugInventory drugInventory;

    public DrugInventoryBuilder() {
        drugInventory = new DrugInventory();
    }

    public DrugInventoryBuilder(DrugInventory drugInventory) {
        this.drugInventory = drugInventory;
    }

    /**
     * Adds a new {@code Drug} to the {@code CareFlow} that we are building.
     */
    public DrugInventoryBuilder withDrug(Drug drug) {
        drugInventory.addDrug(drug);
        return this;
    }

    public DrugInventory build() {
        return drugInventory;
    }
}
