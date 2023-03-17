package seedu.address.model.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.service.UniqueVehicleList;
import seedu.address.model.service.Vehicle;

/**
 * Represents a mapping between customers and their associated vehicles.
 */
public class CustomerVehicleMap {
    private final Map<Customer, UniqueVehicleList> customerToVehiclesMap = new HashMap<>();

    /**
     * Constructs a CustomerVehicleMap object.
     *
     * @param customers The list of customers.
     * @param vehicles  The list of vehicles.
     */
    public CustomerVehicleMap(FilteredList<Customer> customers, List<Vehicle> vehicles) {

        Map<Integer, Vehicle> idToVehicleMap = new HashMap<>();
        for (Vehicle vehicle : vehicles) {
            idToVehicleMap.put(vehicle.getId(), vehicle);
        }

        for (Customer customer : customers) {
            UniqueVehicleList customerVehicles = new UniqueVehicleList();
            for (int vehicleId : customer.getVehicleIds()) {
                Vehicle vehicle = idToVehicleMap.get(vehicleId);
                if (vehicle != null) {
                    customerVehicles.add(vehicle);
                }
            }
            this.customerToVehiclesMap.put(customer, customerVehicles);
        }
    }

    /**
     * Returns an unmodifiable observable list of vehicles associated with the
     * specified customer.
     *
     * @param customer The customer whose vehicles to retrieve.
     * @return An unmodifiable observable list of vehicles associated with the
     *         specified customer.
     */
    public ObservableList<Vehicle> getCustomerVehicles(Customer customer) {
        return this.customerToVehiclesMap.get(customer).asUnmodifiableObservableList();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (Map.Entry<Customer, UniqueVehicleList> e : this.customerToVehiclesMap.entrySet()) {
            builder.append(e.getKey().getId() + ", ");
        }
        return builder.toString();
    }
}
