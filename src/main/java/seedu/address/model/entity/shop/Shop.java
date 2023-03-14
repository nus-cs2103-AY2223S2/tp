package seedu.address.model.entity.shop;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyShop;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.person.UniqueCustomerList;
import seedu.address.model.entity.person.UniqueTechnicianList;
import seedu.address.model.entity.person.exceptions.PersonNotFoundException;
import seedu.address.model.service.Part;
import seedu.address.model.service.PartMap;
import seedu.address.model.service.Service;
import seedu.address.model.service.ServiceList;
import seedu.address.model.service.UniqueVehicleList;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.appointment.Appointment;
import seedu.address.model.service.exception.PartNotFoundException;
import seedu.address.model.service.exception.VehicleNotFoundException;

/**
 * A Shop is an entity that usually buy sells things.
 */
public abstract class Shop implements ReadOnlyShop {
    private final UniqueCustomerList customers = new UniqueCustomerList();
    private final UniqueVehicleList vehicles = new UniqueVehicleList();
    private final UniqueTechnicianList technicians = new UniqueTechnicianList();
    private final ServiceList services = new ServiceList();

    //TODO: Implement immutable list for appointments
    private final List<Appointment> appointments;
    private final PartMap partMap;


    /**
     * Constructor for class Shop.
     */
    public Shop() {
        this.appointments = new ArrayList<>();
        this.partMap = new PartMap();
    }

    /**
     * Creates a Shop using the data in the {@code toBeCopied}
     */
    public Shop(ReadOnlyShop toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// Get operations

    @Override
    public ObservableList<Customer> getCustomerList() {
        return this.customers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Vehicle> getVehicleList() {
        return this.vehicles.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Technician> getTechnicianList() {
        return this.technicians.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Service> getServiceList() {
        return this.services.asUnmodifiableObservableList();
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

    //// Add operations

    /**
     * Adds customer to the shop
     *
     * @param customer Customer to be added
     */
    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    /**
     * Checks if customer is registered
     *
     * @param customerId Customer ID to check
     */
    public boolean hasCustomer(int customerId) {
        return this.getCustomerList().stream()
                .anyMatch(c -> c.getId() == customerId);
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
                this.getVehicleList().add(vehicle);
                return;
            }
        }
        throw new PersonNotFoundException();
    }

    /**
     * Checks if vehicle is in the shop
     *
     * @param vehicleId Vehicle ID to check
     */
    public boolean hasVehicle(int vehicleId) {
        return this.getVehicleList().stream()
                .anyMatch(v -> v.getId() == vehicleId);
    }

    /**
     * Adds service to a vehicle
     *
     * @param vehicleId Id of vehicle
     * @param service   Service to be added to vehicle
     * @throws VehicleNotFoundException if customer does not have specific vehicle
     */
    public void addService(int vehicleId, Service service) throws VehicleNotFoundException {
        for (var vehicle : this.getVehicleList()) {
            if (vehicle.getId() == vehicleId) {
                vehicle.addService(service);
                this.services.add(service);
            }
        }
        throw new VehicleNotFoundException();
    }

    /**
     * Checks if service already added
     *
     * @param serviceId Service ID to check
     */
    public boolean hasService(int serviceId) {
        return this.getServiceList()
                .stream()
                .anyMatch(s -> s.getId() == serviceId);
    }

    /**
     * Adds part to the part map
     *
     * @param part Part to be added
     */
    public void addPart(Part part) {
        this.getPartMap().addPart(part.getName(), part);
    }

    /**
     * Increases part stock
     *
     * @param partName Name of part
     * @param amt      Amount to increase by
     */
    public void addPartStock(String partName, int amt) throws PartNotFoundException {
        this.partMap.getPart(partName).increaseStock(amt);
    }

    /**
     * Checks if part already in the system
     *
     * @param partName Name of part
     */
    public boolean hasPart(String partName) {
        return this.partMap.contains(partName);
    }

    /**
     * Adds appointment to the appointment list
     *
     * @param appointment Appointment to be added
     */
    public void addAppointment(Appointment appointment) {
        this.getAppointments().add(appointment);
    }

    //// Edit, Update, Overwrite operations

    /**
     * Replaces the contents of the customer list with {@code customers}.
     * {@code customers} must not contain duplicate customers.
     */
    public void setCustomers(List<Customer> customers) {
        this.customers.setCustomers(customers);
    }

    /**
     * Replaces the contents of the vehicle list with {@code vehicles}.
     * {@code vehicles} must not contain duplicate vehicles.
     */
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles.setVehicles(vehicles);
    }

    /**
     * Replaces the contents of the technician list with {@code technicians}.
     * {@code technicians} must not contain duplicate technicians.
     */
    public void setTechnicians(List<Technician> technicians) {
        this.technicians.setTechnicians(technicians);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyShop newData) {
        requireNonNull(newData);

        setCustomers(newData.getCustomerList());
        setVehicles(newData.getVehicleList());
        setTechnicians(newData.getTechnicianList());
    }

    //// Delete operations
}
