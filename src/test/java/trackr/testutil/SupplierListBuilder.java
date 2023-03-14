package trackr.testutil;

import trackr.model.SupplierList;
import trackr.model.supplier.Supplier;

/**
 * A utility class to help with building SupplierList objects.
 * Example usage: <br>
 *     {@code SupplierList sl = new SupplierListBuilder().withPerson("John", "Doe").build();}
 */
public class SupplierListBuilder {

    private SupplierList supplierList;

    public SupplierListBuilder() {
        supplierList = new SupplierList();
    }

    public SupplierListBuilder(SupplierList supplierList) {
        this.supplierList = supplierList;
    }

    /**
     * Adds a new {@code Supplier} to the {@code SupplierList} that we are building.
     */
    public SupplierListBuilder withSupplier(Supplier person) {
        supplierList.addSupplier(person);
        return this;
    }

    public SupplierList build() {
        return supplierList;
    }
}
