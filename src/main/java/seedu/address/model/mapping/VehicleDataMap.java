package seedu.address.model.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.service.Service;
import seedu.address.model.service.ServiceList;
import seedu.address.model.service.Vehicle;

/**
 * Represents a mapping between vehicles and their associated customer and
 * services.
 */
public class VehicleDataMap {
    private final Map<Integer, Vehicle> idToVehicleMap = new HashMap<>();
    private final Map<Integer, Customer> idToCustomerMap = new HashMap<>();
    private final Map<Integer, Service> idToServiceMap = new HashMap<>();
    private final Map<Vehicle, Customer> vehicleToCustomerMap = new HashMap<>();
    private final Map<Vehicle, ServiceList> vehicleToServicesMap = new HashMap<>();


    /**
     * Initialises the id mappings based on the provided lists.
     *
     * @param vehicles The list of vehicles.
     * @param customers The list of customers.
     * @param services  The list of services.
     */
    public void initialiseIdMappings(List<Vehicle> vehicles, List<Customer> customers, List<Service> services) {
        for (Vehicle vehicle : vehicles) {
            idToVehicleMap.put(vehicle.getId(), vehicle);
        }
        for (Customer customer : customers) {
            idToCustomerMap.put(customer.getId(), customer);
        }
        for (Service service : services) {
            idToServiceMap.put(service.getId(), service);
        }
    }

    /**
     * Clears all mappings.
     */
    public void clearAll() {
        idToVehicleMap.clear();
        idToCustomerMap.clear();
        idToServiceMap.clear();
        vehicleToCustomerMap.clear();
        vehicleToServicesMap.clear();
    }

    /**
     * Process the mappings of vehicles to their associated customer and
     * services based on the current id maps
     *
     */
    public void processMappings() {
        for (int key : idToVehicleMap.keySet()) {
            Vehicle vehicle = idToVehicleMap.get(key);
            ServiceList vehicleServiceList = new ServiceList();
            Customer vehicleCustomer = idToCustomerMap.get(vehicle.getOwnerId());

            if (vehicleCustomer != null) {
                this.vehicleToCustomerMap.put(vehicle, vehicleCustomer);
            }

            for (int serviceId : vehicle.getServiceIds()) {
                Service service = idToServiceMap.get(serviceId);
                if (service != null) {
                    vehicleServiceList.add(service);
                }
            }
            this.vehicleToServicesMap.put(vehicle, vehicleServiceList);
        }
    }

    /**
     * Modifies the provided vehicle and updates the mappings.
     *
     * @param vehicle The vehicle to add or update.
     * @param isRemove A flag to indicate whether to remove given vehicle.
     */
    public void modifyVehicle(Vehicle vehicle, boolean isRemove) {
        if (isRemove) {
            idToVehicleMap.remove(vehicle.getId());
        } else {
            idToVehicleMap.put(vehicle.getId(), vehicle);
        }
        processMappings();
    }

    /**
     * Modifies the provided customer and updates the mappings.
     *
     * @param customer The customer to add or update.
     * @param isRemove A flag to indicate whether to remove given customer.
     */
    public void modifyCustomer(Customer customer, boolean isRemove) {
        if (isRemove) {
            idToCustomerMap.remove(customer.getId());
        } else {
            idToCustomerMap.put(customer.getId(), customer);
        }
        processMappings();
    }

    /**
     * Modifies the provided service and updates the mappings.
     *
     * @param service The service to add or update.
     * @param isRemove A flag to indicate whether to remove given service.
     */
    public void modifyService(Service service, boolean isRemove) {
        if (isRemove) {
            idToServiceMap.remove(service.getId());
        } else {
            idToServiceMap.put(service.getId(), service);
        }
        processMappings();
    }

    /**
     * Resets the mappings based on the lists provided and process the vehicle
     * associated mappings again
     *
     * @param vehicles The list of vehicles.
     * @param customers The list of customers.
     * @param services  The list of services.
     */
    public void reset(List<Vehicle> vehicles, List<Customer> customers, List<Service> services) {
        clearAll();
        initialiseIdMappings(vehicles, customers, services);
        processMappings();
    }

    /**
     * Returns an unmodifiable observable list of services associated with the
     * specified vehicle.
     *
     * @param vehicle The vehicle whose services to retrieve.
     * @return An unmodifiable observable list of vehicles associated with the
     *         specified customer.
     */
    public ObservableList<Service> getVehicleServices(Vehicle vehicle) {
        return this.vehicleToServicesMap.get(vehicle).asUnmodifiableObservableList();
    }

    /**
     * Returns the Customer associated with the specified vehicle.
     *
     * @param vehicle The vehicle whose owner to retrieve.
     * @return The Vehicle owner
     */
    public Customer getVehicleCustomer(Vehicle vehicle) {
        return this.vehicleToCustomerMap.get(vehicle);
    }
}
