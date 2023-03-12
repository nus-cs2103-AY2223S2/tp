package seedu.address.model.entity.shop;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.Vehicle;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.exceptions.PersonNotFoundException;
import seedu.address.model.exception.VehicleNotFoundException;
import seedu.address.model.service.Appointment;
import seedu.address.model.service.Part;
import seedu.address.model.service.PartMap;
import seedu.address.model.service.Service;

/**
 * A Shop is an entity that usually buy sells things.
 */
public abstract class Shop extends Entity {
    private final List<Customer> customers;
    private final List<Appointment> appointments;
    private final PartMap partMap;

    public Shop() {
        this.customers = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.partMap = new PartMap();
    }

    /**
     * Adds customer to the shop
     *
     * @param customer Customer to be added
     */
    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    /**
     * @return List of customers registered with the shop
     */
    public List<Customer> getCustomers() {
        return this.customers;
    }

    /**
     * Get part map
     *
     * @return part map
     */
    public PartMap getPartMap() {
        return this.partMap;
    }

    /**
     * Get appointment list
     *
     * @return List of appointments
     */
    public List<Appointment> getAppointments() {
        return this.appointments;
    }

    /**
     * Adds vehicle to the shop
     *
     * @param customerId Id of vehicle's owner
     * @param vehicle    Vehicle to be added
     * @throws PersonNotFoundException Customer not registered with the shop
     */
    public void addVehicle(int customerId, Vehicle vehicle) throws PersonNotFoundException {
        for (var customer : customers) {
            if (customer.getId() == customerId) {
                customer.addVehicle(vehicle);
                return;
            }
        }
        throw new PersonNotFoundException();
    }

    /**
     * Adds service to a vehicle
     *
     * @param customerId Id of vehicle owner
     * @param vehicleId  Id of vehicle
     * @param service    Service to be added to vehicle
     * @throws PersonNotFoundException  If customer not registered with shop
     * @throws VehicleNotFoundException if customer does not have specific vehicle
     */
    public void addService(int customerId, int vehicleId, Service service)
            throws VehicleNotFoundException, PersonNotFoundException {
        for (var customer : this.getCustomers()) {
            if (customer.getId() != customerId) {
                continue;
            }
            for (var vehicle : customer.getVehicles()) {
                if (vehicle.getId() != vehicleId) {
                    continue;
                }
                vehicle.addService(service);
                return;
            }
            throw new VehicleNotFoundException();
        }
        throw new PersonNotFoundException();
    }

    /**
     * Adds part to the part map
     *
     * @param part Part to be added
     */
    public void addPart(Part part) {
        this.partMap.addPart(part.getName(), part);
    }

    /**
     * Adds appointment to the appointment list
     *
     * @param appointment Appointment to be added
     */
    public void addAppointment(Appointment appointment) {
        this.getAppointments().add(appointment);
    }
}
