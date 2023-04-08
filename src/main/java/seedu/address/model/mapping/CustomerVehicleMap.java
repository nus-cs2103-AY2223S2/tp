package seedu.address.model.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.service.Service;
import seedu.address.model.service.UniqueVehicleList;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.VehicleType;
import seedu.address.model.service.appointment.Appointment;
import seedu.address.model.service.appointment.UniqueAppointmentList;

/**
 * Represents a mapping between customers with their associated vehicles and appointments.
 */
public class CustomerVehicleMap {
    private final Map<Integer, Customer> idToCustomerMap = new HashMap<>();
    private final Map<Integer, Vehicle> idToVehicleMap = new HashMap<>();
    private final Map<Integer, Appointment> idToAppointmentMap = new HashMap<>();
    private final Map<Customer, UniqueVehicleList> customerToVehiclesMap = new HashMap<>();
    private final Map<Customer, UniqueAppointmentList> customerToAppointmentsMap = new HashMap<>();

    /**
     * Initialises the id mappings based on the provided lists.
     *
     * @param customers The list of customers.
     * @param vehicles The list of vehicles.
     * @param appointments The list of appointments.
     */
    public void initialiseIdMappings(List<Customer> customers, List<Vehicle> vehicles, List<Appointment> appointments) {
        for (Customer customer : customers) {
            idToCustomerMap.put(customer.getId(), customer);
        }
        for (Vehicle vehicle : vehicles) {
            idToVehicleMap.put(vehicle.getId(), vehicle);
        }
        for (Appointment appointment : appointments) {
            idToAppointmentMap.put(appointment.getId(), appointment);
        }
    }

    /**
     * Clears all mappings.
     */
    public void clearAll() {
        idToCustomerMap.clear();
        idToVehicleMap.clear();
        idToAppointmentMap.clear();
        customerToVehiclesMap.clear();
        customerToAppointmentsMap.clear();
    }

    /**
     * Process the mappings of customers to their associated vehicles and appointments based on the current id maps
     *
     */
    public void processMappings() {
        for (int key : idToCustomerMap.keySet()) {
            Customer customer = idToCustomerMap.get(key);
            UniqueVehicleList customerVehicles = new UniqueVehicleList();
            UniqueAppointmentList customerAppointments = new UniqueAppointmentList();
            for (int vehicleId : customer.getVehicleIds()) {
                Vehicle vehicle = idToVehicleMap.get(vehicleId);
                if (vehicle != null) {
                    customerVehicles.add(vehicle);
                }
            }
            for (int appointmentId : customer.getAppointmentIds()) {
                Appointment appointment = idToAppointmentMap.get(appointmentId);
                if (appointment != null) {
                    customerAppointments.add(appointment);
                }
            }
            this.customerToVehiclesMap.put(customer, customerVehicles);
            this.customerToAppointmentsMap.put(customer, customerAppointments);
        }
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
     * Modifies the provided appointment and updates the mappings.
     *
     * @param appointment The appointment to add or update.
     * @param isRemove A flag to indicate whether to remove given appointment.
     */
    public void modifyAppointment(Appointment appointment, boolean isRemove) {
        if (isRemove) {
            idToAppointmentMap.remove(appointment.getId());
        } else {
            idToAppointmentMap.put(appointment.getId(), appointment);
        }
        processMappings();
    }

    /**
     * Resets the mappings based on the lists provided and process the customer
     * associated mappings again
     *
     * @param customers The list of customers.
     * @param vehicles The list of vehicles.
     * @param appointments The list of appointments.
     */
    public void reset(List<Customer> customers, List<Vehicle> vehicles, List<Appointment> appointments) {
        clearAll();
        initialiseIdMappings(customers, vehicles, appointments);
        processMappings();
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

    /**
     * Returns an unmodifiable observable list of appointments associated with the
     * specified customer.
     *
     * @param customer The customer whose appointments to retrieve.
     * @return An unmodifiable observable list of appointments associated with the
     *         specified customer.
     */
    public ObservableList<Appointment> getCustomerAppointments(Customer customer) {
        return this.customerToAppointmentsMap.get(customer).asUnmodifiableObservableList();
    }

    /**
     * Returns the number of vehicles with the type car a customer has
     *
     * @param customer The customer whose vehicles to retrieve.
     * @return the number of vehicles with the type car a customer has.
     */
    public int getCustomerCarsCount(Customer customer) {
        int count = 0;
        List<Vehicle> customerVehicles = this.getCustomerVehicles(customer);
        for (Vehicle vehicle : customerVehicles) {
            if (vehicle.getType() == VehicleType.CAR) {
                count++;
            }
        }

        return count;
    }

    /**
     * Returns the number of vehicles with the type motorbike a customer has
     *
     * @param customer The customer whose vehicles to retrieve.
     * @return the number of vehicles with the type motorbike a customer has.
     */
    public int getCustomerMotorbikesCount(Customer customer) {
        int count = 0;
        List<Vehicle> customerVehicles = this.getCustomerVehicles(customer);
        for (Vehicle vehicle : customerVehicles) {
            if (vehicle.getType() == VehicleType.MOTORBIKE) {
                count++;
            }
        }

        return count;
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
