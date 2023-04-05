package seedu.address.model.entity.shop;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.idgen.IdGenerator;
import seedu.address.model.ReadOnlyShop;
import seedu.address.model.entity.person.Address;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Email;
import seedu.address.model.entity.person.Name;
import seedu.address.model.entity.person.Phone;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.person.UniqueCustomerList;
import seedu.address.model.entity.person.UniqueTechnicianList;
import seedu.address.model.entity.person.exceptions.PersonNotFoundException;
import seedu.address.model.entity.shop.exception.AppointmentNotFoundException;
import seedu.address.model.entity.shop.exception.CustomerNotFoundException;
import seedu.address.model.entity.shop.exception.EmptyInputException;
import seedu.address.model.entity.shop.exception.InsufficientPartException;
import seedu.address.model.entity.shop.exception.InvalidQuantityException;
import seedu.address.model.entity.shop.exception.ServiceNotFoundException;
import seedu.address.model.entity.shop.exception.TechnicianNotFoundException;
import seedu.address.model.entity.shop.exception.VehicleNotFoundException;
import seedu.address.model.entity.shop.exception.PartNotFoundException;
import seedu.address.model.service.PartMap;
import seedu.address.model.service.Service;
import seedu.address.model.service.ServiceList;
import seedu.address.model.service.ServiceStatus;
import seedu.address.model.service.UniqueVehicleList;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.VehicleType;
import seedu.address.model.service.appointment.Appointment;
import seedu.address.model.service.appointment.UniqueAppointmentList;
import seedu.address.model.service.exception.PartLessThanZeroException;
import seedu.address.model.tag.Tag;

/**
 * A Shop is an entity that usually buy sells things.
 */
public class Shop implements ReadOnlyShop {
    public static final String MSG_RUNTIME_ERROR =
        "Relationships in shop broken, bug in one of the modification methods";
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();
    private final ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
    private final ObservableList<Technician> technicians = FXCollections.observableArrayList();
    private final ObservableList<Service> services = FXCollections.observableArrayList();
    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    private final ObservableMap<String, Integer> parts = FXCollections.observableHashMap();
    private final Logger logger = LogsCenter.getLogger(this.getClass());

    /**
     * Constructor for class Shop.
     */
    public Shop() {
    }

    /**
     * Creates a Shop using the data in the {@code toBeCopied}
     */
    public Shop(ReadOnlyShop toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    // Getters ---------------------------------------------------------------------

    // Whole list
    public ObservableList<Customer> getCustomerList() {
        return FXCollections.unmodifiableObservableList(this.customers);
    }

    public ObservableList<Vehicle> getVehicleList() {
        return FXCollections.unmodifiableObservableList(this.vehicles);
    }

    public ObservableList<Service> getServiceList() {
        return FXCollections.unmodifiableObservableList(this.services);
    }

    public ObservableList<Technician> getTechnicianList() {
        return FXCollections.unmodifiableObservableList(this.technicians);
    }

    public ObservableList<Appointment> getAppointmentList() {
        return FXCollections.unmodifiableObservableList(this.appointments);
    }

    public ObservableMap<String, Integer> getPartMap() {
        return FXCollections.unmodifiableObservableMap(this.parts);
    }

    // Individual item

    private Customer getCustomer(int id) throws CustomerNotFoundException {
        return this.customers.stream()
            .filter(c -> c.getId() == id)
            .findFirst()
            .orElseThrow(() -> new CustomerNotFoundException(id));
    }
    private Vehicle getVehicle(int id) throws VehicleNotFoundException {
        return this.vehicles.stream()
            .filter(v -> v.getId() == id)
            .findFirst()
            .orElseThrow(() -> new VehicleNotFoundException(id));
    }
   private Service getService(int id) throws ServiceNotFoundException {
        return this.services.stream()
            .filter(c -> c.getId() == id)
            .findFirst()
            .orElseThrow(() -> new ServiceNotFoundException(id));
    }
    private Technician getTechnician(int id) throws TechnicianNotFoundException {
        return this.technicians.stream()
            .filter(c -> c.getId() == id)
            .findFirst()
            .orElseThrow(() -> new TechnicianNotFoundException(id));
    }
    private Appointment getAppointment(int id) throws AppointmentNotFoundException {
        return this.appointments.stream()
            .filter(c -> c.getId() == id)
            .findFirst()
            .orElseThrow(() -> new AppointmentNotFoundException(id));
    }
    public int getPartQty(String partName) throws EmptyInputException, PartNotFoundException {
        if (partName.isBlank()) {
            throw new EmptyInputException("PartName cannot be blank");
        }
        String normalizedName = partName.toUpperCase();
        if (!this.parts.containsKey(normalizedName)) {
            throw new PartNotFoundException(normalizedName);
        }
        return this.parts.get(normalizedName);
    }

    // Checkers -----------------------------------------------------------------------------------

    private boolean hasCustomer(int id) {
        return this.customers.stream().anyMatch(c -> c.getId() == id);
    }

    private boolean hasVehicle(int id) {
        return this.vehicles.stream().anyMatch(v -> v.getId() == id);
    }

    private boolean hasService(int id) {
        return this.services.stream().anyMatch(s -> s.getId() == id);
    }

    private boolean hasTechnician(int id) {
        return this.technicians.stream().anyMatch(t -> t.getId() == id);
    }

    private boolean hasAppointment(int id) {
        return this.appointments.stream().anyMatch(a -> a.getId() == id);
    }

    private boolean hasPart(String partName) {
        return this.parts.containsKey(partName.toUpperCase());
    }

    // Add ------------------------------------------------------------------------------------------------------

    public void addCustomer(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        Customer toAdd = new Customer(IdGenerator.generateCustomerId(), name, phone, email, address, tags);
        this.customers.add(toAdd);
        logger.info(toAdd + " added");
    }

    public void addVehicle(int ownerId, String plateNumber, String color, String brand, VehicleType type)
            throws CustomerNotFoundException, EmptyInputException {
        if (plateNumber.isBlank()) {
            logger.info("Empty input for vehicle plate number");
            throw new EmptyInputException("Plate number should not be blank");
        }
        if (color.isBlank()) {
            logger.info("Empty input for vehicle color");
            throw new EmptyInputException("Color should not be blank");
        }
        if (brand.isBlank()) {
            logger.info("Empty input for vehicle brand");
            throw new EmptyInputException("Brand should not be blank");
        }
        Vehicle toAdd = new Vehicle(IdGenerator.generateVehicleId(), ownerId, plateNumber, color, brand, type);
        this.getCustomer(ownerId).addVehicle(toAdd.getId());
        this.vehicles.add(toAdd);
        logger.info(toAdd + " added");
    }

    public void addService(int vehicleId, Optional<LocalDate> maybeEntryDate, String description,
                           Optional<LocalDate> maybeEstimatedFinishDate, Optional<ServiceStatus> maybeServiceStatus)
                throws VehicleNotFoundException, EmptyInputException {
        if (description.isBlank()) {
            logger.info("Empty input for service description");
            throw new EmptyInputException("Description should not be blank");
        }
        LocalDate entryDate = maybeEntryDate.orElseGet(LocalDate::now);
        LocalDate estimatedFinishDate = maybeEstimatedFinishDate.orElseGet(() -> entryDate.plusDays(7));
        ServiceStatus serviceStatus = maybeServiceStatus.orElse(ServiceStatus.TO_REPAIR);
        Service toAdd = new Service(IdGenerator.generateServiceId(), vehicleId,entryDate, new PartMap() ,
            description, estimatedFinishDate, serviceStatus);
        this.getVehicle(vehicleId).addService(toAdd);
        this.services.add(toAdd);
        logger.info(toAdd + " added");
    }

    public void addTechnician(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        Technician toAdd = new Technician(IdGenerator.generateStaffId(), name, phone, email, address, tags);
        this.technicians.add(toAdd);
        logger.info(toAdd + " added");
    }

    public void addTechnicianToService(int techId, int serviceId)
            throws TechnicianNotFoundException, ServiceNotFoundException {
        if (!this.hasTechnician(techId)) {
            throw new TechnicianNotFoundException(techId);
        }
        Service service = this.getService(serviceId);
        service.assignTechnician(techId);
    }

    public void addTechnicianToAppointment(int techId, int appointmentId)
            throws TechnicianNotFoundException, AppointmentNotFoundException {
        if (!this.hasTechnician(techId)) {
            throw new TechnicianNotFoundException(techId);
        }
        Appointment appointment = this.getAppointment(appointmentId);
        appointment.addTechnician(techId);
    }

    public void addAppointment(int customerId, LocalDateTime timeDate) throws CustomerNotFoundException {
        if (!this.hasCustomer(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        Appointment toAdd = new Appointment(IdGenerator.generateAppointmentId(), customerId, timeDate);
        this.getCustomer(customerId).addAppointment(toAdd);
        this.appointments.add(toAdd);
        logger.info(toAdd + " added");
    }

    public void addPart(String part, int qty)
            throws PartNotFoundException, InvalidQuantityException, EmptyInputException {
        if (part.isBlank()) {
            throw new EmptyInputException("PartName cannot be blank");
        }
        String normalizedPartName = part.toUpperCase();
        if (qty <= 0) {
            throw new InvalidQuantityException(qty);
        }
        if (!this.hasPart(normalizedPartName)) {
            throw new PartNotFoundException(normalizedPartName);
        }
        this.parts.put(normalizedPartName, this.parts.get(normalizedPartName) + qty);
        logger.info(String.format("%s, %d added", normalizedPartName, qty));
    }

    public void addPartToService(int serviceId, String partName, int qty)
            throws PartNotFoundException, InsufficientPartException, ServiceNotFoundException,
                    InvalidQuantityException, EmptyInputException {
        if (partName.isBlank()) {
            throw new EmptyInputException("PartName cannot be blank");
        }
        String normalizedPartName = partName.toUpperCase();
        if (qty <= 0) {
            throw new InvalidQuantityException(qty);
        }
        if (!this.hasPart(normalizedPartName)) {
            throw new PartNotFoundException(normalizedPartName);
        }
        if (this.getPartQty(normalizedPartName) < qty) {
            throw new InsufficientPartException(normalizedPartName, this.getPartQty(normalizedPartName));
        }
        Service service = this.getService(serviceId);
        this.parts.put(normalizedPartName, this.parts.get(normalizedPartName) - qty);
        service.addPart(normalizedPartName, qty);
    }

    // Delete -----------------------------------------------------------------------------------------------

    public void removeCustomer(int customerId) throws CustomerNotFoundException {
        Customer toRemove = this.getCustomer(customerId);
        try {
            for (int i : toRemove.getVehicleIds()) {
                this.removeVehicle(i);
            }
            for (int i : toRemove.getAppointmentIds()) {
                this.removeAppointment(i);
            }
            this.customers.removeIf(c -> c.getId() == customerId);
            logger.info(String.format("Customer %d removed", customerId));
            IdGenerator.setCustomerIdUnused(customerId);
        } catch (VehicleNotFoundException | AppointmentNotFoundException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(MSG_RUNTIME_ERROR);
        }
    }

    public void removeVehicle(int vehicleId) throws VehicleNotFoundException {
        Vehicle toRemove = this.getVehicle(vehicleId);
        try {
            for (int i : toRemove.getServiceIds()) {
                this.removeService(i);
            }
            this.getCustomer(toRemove.getOwnerId()).removeVehicle(toRemove);
            this.vehicles.removeIf(v -> v.getId() == vehicleId);
            logger.info(String.format("Vehicle %d removed", vehicleId));
            IdGenerator.setVehicleIdUnused(vehicleId);
        } catch (ServiceNotFoundException | CustomerNotFoundException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(MSG_RUNTIME_ERROR);
        }
    }

    public void removeAppointment(int appointmentId) throws AppointmentNotFoundException {
        Appointment toRemove = this.getAppointment(appointmentId);
        try {
            this.getCustomer(toRemove.getCustomerId()).removeAppointment(toRemove);
            for (int i : toRemove.getStaffIds()) {
                this.getTechnician(i).removeAppointmentIds(x -> x == i);
            }
            this.appointments.removeIf(a -> a.getId() == appointmentId);
            logger.info(String.format("Appointment %d removed", appointmentId));
            IdGenerator.setAppointmentIdUnused(appointmentId);
        } catch (CustomerNotFoundException | TechnicianNotFoundException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(MSG_RUNTIME_ERROR);
        }
    }

    public void removeService(int serviceId) throws ServiceNotFoundException {
        Service toRemove = this.getService(serviceId);
        try {
            for (var entry : toRemove.getRequiredParts().entrySet()) {
                String partName = entry.getKey();
                int qty = entry.getValue();
                if (this.parts.containsKey(partName)) {
                    this.parts.put(partName, this.parts.get(partName) + qty);
                } else {
                    this.parts.put(partName, qty);
                }
            }
            this.getVehicle(toRemove.getId()).removeService(toRemove);
            for (int i : toRemove.getAssignedToIds()) {
                this.getTechnician(i).removeServiceIds(x -> x == serviceId);
            }
            this.services.removeIf(s -> s.getId() == serviceId);
            logger.info(String.format("Service %d removed", serviceId));
            IdGenerator.setServiceIdUnused(serviceId);
        } catch (VehicleNotFoundException | TechnicianNotFoundException ex) {
            logger.severe(ex.getMessage());
            throw new RuntimeException(MSG_RUNTIME_ERROR);
        }
    }

    public void removeTechnician(int techId) throws TechnicianNotFoundException {
        Technician toRemove = this.getTechnician(techId);
        try {
            for (int i : toRemove.getServiceIds()) {
                this.getService(i).removeTechnician(toRemove);
            }
            for (int i : toRemove.getAppointmentIds()) {
                this.getAppointment(i).removeTechnician(toRemove.getId());
            }
            this.technicians.removeIf(t -> t.getId() == techId);
            logger.info("Technician %d removed");
            IdGenerator.setStaffIdUnused(techId);
        } catch (ServiceNotFoundException | AppointmentNotFoundException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(MSG_RUNTIME_ERROR);
        }
    }

    public void removePart(String name, int quantity)
            throws PartNotFoundException, InsufficientPartException, InvalidQuantityException, EmptyInputException {
        if (name.isBlank()) {
            throw new EmptyInputException("PartName cannot be blank");
        }
        String normalizedPartName = name.toUpperCase();
        if (quantity <= 0) {
            throw new InvalidQuantityException(quantity);
        }
        if (this.getPartQty(normalizedPartName) < quantity) {
            throw new InsufficientPartException(normalizedPartName, quantity);
        }
        int newQty = this.getPartQty(normalizedPartName) - quantity;
        this.parts.put(normalizedPartName, newQty);
        logger.info(String.format("%s x %d added", normalizedPartName, quantity));
    }

    public void removePartFromService(String partName, int serviceId)
            throws PartNotFoundException, ServiceNotFoundException, EmptyInputException {
        if (partName.isBlank()) {
            throw new EmptyInputException("PartName cannot be blank");
        }
        String normalizedPartName = partName.toUpperCase();
        Service service = this.getService(serviceId);
        if (!service.getRequiredParts().containsKey(normalizedPartName)) {
            throw new PartNotFoundException(normalizedPartName);
        }
        int qty = service.getRequiredParts().get(normalizedPartName);
        if (this.parts.containsKey(normalizedPartName)) {
            this.parts.put(normalizedPartName, this.getPartQty(normalizedPartName) + qty);
        } else {
            this.parts.put(normalizedPartName, qty);
        }
        service.getRequiredParts().remove(normalizedPartName);
        logger.info(String.format("%s x % d transferred back to shop", normalizedPartName, qty);
    }

    public void removeTechnicianFromService(int techId, int serviceId)
            throws TechnicianNotFoundException, ServiceNotFoundException {
        Service service = this.getService(serviceId);
        Technician technician = this.getTechnician(techId);
        service.removeTechnician(technician);
        logger.info(String.format("Technician %d unassigned from service %d", techId, serviceId));
    }

    public void removeTechnicianFromAppointment(int techId, int appointmentId)
            throws TechnicianNotFoundException, AppointmentNotFoundException {
        Appointment appointment = this.getAppointment(appointmentId);
        Technician technician = this.getTechnician(techId);
        appointment.removeTechnician(techId);
        logger.info(String.format("Technician %d unassigned from appointment %d", techId, appointmentId));
    }

    // Edit --------------------------------------------------------------------------------------------------

//    // --------------------------------------------------
//    //// Service-level operations
//
//    /**
//     * Adds service to the system
//     *
//     * @param service Service to be added to the system
//     * @throws VehicleNotFoundException If vehicle not in the system
//     */
//    public void addService(Service service) throws VehicleNotFoundException {
//        this.addService(service.getVehicleId(), service);
//    }
//
//    /**
//     * Adds service to a vehicle
//     *
//     * @param vehicleId Id of vehicle
//     * @param service   Service to be added to vehicle
//     * @throws VehicleNotFoundException If vehicle not in the system
//     */
//    public void addService(int vehicleId, Service service) throws VehicleNotFoundException {
//        for (var vehicle : this.getVehicleList()) {
//            if (vehicle.getId() == vehicleId) {
//                vehicle.addService(service);
//                this.services.add(service);
//                return;
//            }
//        }
//        throw new VehicleNotFoundException();
//    }
//
//    /**
//     * Checks if service already added
//     *
//     * @param serviceId Service ID to check
//     */
//    public boolean hasService(int serviceId) {
//        return this.getServiceList()
//            .stream()
//            .anyMatch(s -> s.getId() == serviceId);
//    }
//
//    /**
//     * Wrapper function to also check if service already added
//     * but using Service param
//     *
//     * @param service Service to check
//     */
//    public boolean hasService(Service service) {
//        return this.hasService(service.getId());
//    }
//
//    @Override
//    public ObservableList<Service> getServiceList() {
//        return this.services.asUnmodifiableObservableList();
//    }
//
//    /**
//     * Replaces the contents of the service list with {@code services}.
//     */
//    public void setServices(List<Service> services) {
//        this.services.setServices(services);
//    }
//
//    public void setService(Service services, Service edit) {
//
//        this.services.setService(services, edit);
//    }
//
//    /**
//     * Removes {@code key} from this {@code AddressBook}.
//     * {@code key} must exist in the address book.
//     */
//    public void removeService(Service key) {
//        services.remove(key);
//    }
//
//    // --------------------------------------------------
//    //// Appointment-level operations
//
//    /**
//     * Adds appointment to the appointment list
//     *
//     * @param appointment Appointment to be added
//     */
//    public void addAppointment(Appointment appointment) throws PersonNotFoundException {
//        for (var customer : customers) {
//            if (customer.getId() == appointment.getCustomerId()) {
//                customer.addAppointment(appointment);
//                this.appointments.add(appointment);
//                return;
//            }
//        }
//        throw new PersonNotFoundException();
//    }
//
//    @Override
//    public ObservableList<Appointment> getAppointmentList() {
//        return this.appointments.asUnmodifiableObservableList();
//    }
//
//    public void removeAppointment(Appointment key) {
//        appointments.remove(key);
//    }
//
//    public void setAppointment(Appointment target, Appointment editedAppointment) {
//        requireNonNull(editedAppointment);
//        appointments.setAppointment(target, editedAppointment);
//    }
//
//    /**
//     * Wrapper function to also check if appointment already added
//     * but using appointment param
//     *
//     * @param appointment Appointment to check
//     */
//    public boolean hasAppointment(Appointment appointment) {
//        return appointments.contains(appointment);
//    }
//
//    /**
//     * Checks if appointment in the system
//     *
//     * @param appointmentId ID of appointment
//     */
//    public boolean hasAppointment(int appointmentId) {
//        return this.getAppointmentList().stream().anyMatch(a -> a.getId() == appointmentId);
//    }
//
//    /**
//     * Replaces the contents of the appointment list with {@code appointments}.
//     * {@code appointments} must not contain appointment customers.
//     */
//    public void setAppointments(List<Appointment> appointments) {
//        this.appointments.setAppointments(appointments);
//    }
//
//    // --------------------------------------------------
//    //// part-level operations
//    @Override
//    public PartMap getPartMap() {
//        return this.partMap;
//    }
//
//    /**
//     * Adds part
//     *
//     * @param partName Name of the part to add
//     * @param quantity Quantity of the part to add
//     */
//    public void addPart(String partName, int quantity) {
//        this.getPartMap().addPart(partName, quantity);
//    }
//
//    /**
//     * Adds part to service
//     *
//     * @param serviceId ID of service
//     * @param partName  Name of part
//     * @param quantity  Quantity of part
//     * @throws NoSuchElementException    If service not in system
//     * @throws PartNotFoundException     If part not registered
//     * @throws PartLessThanZeroException If part insufficient
//     */
//    public void addPartToService(int serviceId, String partName, int quantity)
//            throws NoSuchElementException, PartNotFoundException, PartLessThanZeroException {
//        Optional<Service> serviceOption = this.getServiceList()
//            .stream()
//            .filter(s -> s.getId() == serviceId)
//            .findFirst();
//        Service service = serviceOption.orElseThrow();
//        this.getPartMap().decreasePartQuantity(partName, quantity);
//        service.addPart(partName, quantity);
//    }
//
//    /**
//     * Assigns existing technician to existing service
//     *
//     * @param serviceId    ID of service
//     * @param technicianId ID of technician
//     * @throws NoSuchElementException If service or technician doesn't exist
//     */
//    public void addTechnicianToService(int serviceId, int technicianId) throws NoSuchElementException {
//        if (!this.hasTechnician(technicianId)) {
//            throw new NoSuchElementException();
//        }
//        Service service = this.getServiceList().stream()
//            .filter(s -> s.getId() == serviceId)
//            .findFirst()
//            .orElseThrow();
//        service.assignTechnician(technicianId);
//    }
//
//    /**
//     * Assigns existing technician to existing appointment
//     *
//     * @param technicianId ID of technician
//     * @param appointmentId ID of appointment
//     * @throws NoSuchElementException if technician ID or appointment ID does not exist
//     */
//    public void addTechnicianToAppointment(int technicianId, int appointmentId) throws NoSuchElementException {
//        if (!this.hasTechnician(technicianId)) {
//            throw new NoSuchElementException();
//        }
//        Appointment appointment = this.getAppointmentList().stream()
//            .filter(a -> a.getId() == appointmentId)
//            .findFirst()
//            .orElseThrow();
//        appointment.addTechnician(technicianId);
//    }
//
//    /**
//     * Increases part stock by a specified quantity
//     *
//     * @param partName Name of part
//     * @param quantity Quanity to increase by
//     */
//    public void addPartStock(String partName, int quantity) throws PartNotFoundException {
//        this.partMap.increasePartQuantity(partName, quantity);
//    }
//
//    /**
//     * Checks if part already in the system
//     *
//     * @param partName Name of part to check
//     */
//    public boolean hasPart(String partName) {
//        return this.partMap.contains(partName);
//    }
//
//    /**
//     * Replaces the contents of the part map with {@code parts}.
//     */
//    public void setParts(PartMap parts) {
//        this.partMap.replace(parts);
//    }
//
//    // --------------------------------------------------
//    //// customer-level operations
//
//    /**
//     * Adds customer to the shop
//     *
//     * @param customer Customer to be added
//     */
//    public void addCustomer(Customer customer) {
//        this.customers.add(customer);
//    }
//
//    /**
//     * Adds vehicle to the shop and to customer's vehicle ids
//     *
//     * @param customerId Customer ID to check
//     */
//    public boolean hasCustomer(int customerId) {
//        return this.getCustomerList().stream()
//            .anyMatch(c -> c.getId() == customerId);
//    }
//
//    /**
//     * Returns true if a customer with the same id or identity as {@code customer}
//     * exists in the autom8 system.
//     */
//    public boolean hasCustomer(Customer customer) {
//        requireNonNull(customer);
//        return customers.contains(customer);
//    }
//
//
//    /**
//     * Replaces the contents of the customer list with {@code customers}.
//     * {@code customers} must not contain duplicate customers.
//     */
//    public void setCustomers(List<Customer> customers) {
//        this.customers.setCustomers(customers);
//    }
//
//    /**
//     * Replaces the given person {@code target} in the list with {@code editedPerson}.
//     * {@code target} must exist in the address book.
//     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
//     */
//    public void setCustomer(Customer target, Customer editedPerson) {
//        requireNonNull(editedPerson);
//        customers.setCustomer(target, editedPerson);
//    }
//
//    /**
//     * Removes {@code key} from this {@code AddressBook}.
//     * {@code key} must exist in the address book.
//     */
//    public void removeCustomer(Customer key) {
//        key.getAppointmentIds().stream()
//            .flatMap(i -> this.getAppointment(i).stream())
//            .forEach(this::removeAppointment);
//        key.getVehicleIds().stream()
//            .flatMap(i -> this.getVehicle(i).stream())
//            .forEach(this::removeVehicle);
//        customers.remove(key);
//    }
//
//    @Override
//    public ObservableList<Customer> getCustomerList() {
//        return this.customers.asUnmodifiableObservableList();
//    }
//
//    // --------------------------------------------------
//    //// technician-level operations
//
//    /**
//     * Returns true if a person with the same identity as {@code person} exists in the address book.
//     */
//    public boolean hasTechnician(Technician person) {
//        requireNonNull(person);
//        return technicians.contains(person);
//    }
//
//    /**
//     * Checks if technician already in the system
//     *
//     * @param technicianId ID of technician to check against
//     */
//    public boolean hasTechnician(int technicianId) {
//        return this.getTechnicianList().stream()
//            .anyMatch(p -> p.getId() == technicianId);
//    }
//
//    /**
//     * Adds a person to the address book.
//     * The person must not already exist in the address book.
//     */
//    public void addTechnician(Technician p) {
//        technicians.add(p);
//    }
//
//    /**
//     * Replaces the given person {@code target} in the list with {@code editedPerson}.
//     * {@code target} must exist in the address book.
//     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
//     */
//    public void setTechnician(Technician target, Technician editedPerson) {
//        requireNonNull(editedPerson);
//        technicians.setTechnician(target, editedPerson);
//    }
//
//    /**
//     * Removes {@code key} from this {@code AddressBook}.
//     * {@code key} must exist in the address book.
//     */
//    public void removeTechnician(Technician key) {
//        technicians.remove(key);
//    }
//
//    /**
//     * Replaces the contents of the technicians list with {@code technicians}.
//     * {@code technicians} must not contain duplicate technicians.
//     */
//    public void setTechnicians(List<Technician> technicians) {
//        this.technicians.setTechnicians(technicians);
//    }
//
//    @Override
//    public ObservableList<Technician> getTechnicianList() {
//        return this.technicians.asUnmodifiableObservableList();
//    }
//
//    // --------------------------------------------------
//    //// Vehicle-level operations
//
//    /**
//     * Adds vehicle to the shop
//     *
//     * @param customerId Id of vehicle's owner
//     * @param vehicle    Vehicle to be added
//     * @throws PersonNotFoundException Customer not registered with the shop
//     */
//    public void addVehicle(int customerId, Vehicle vehicle) throws PersonNotFoundException {
//        for (var customer : customers) {
//            if (customer.getId() == customerId) {
//                customer.addVehicle(vehicle);
//                this.vehicles.add(vehicle);
//                return;
//            }
//        }
//        throw new PersonNotFoundException();
//    }
//
//    /**
//     * Adds vehicle to the shop
//     *
//     * @param vehicle Vehicle to be added
//     */
//    public void addVehicle(Vehicle vehicle) {
//        this.vehicles.add(vehicle);
//    }
//
//    /**
//     * Checks if vehicle is in the shop
//     *
//     * @param vehicleId Vehicle ID to check
//     */
//    public boolean hasVehicle(int vehicleId) {
//        return this.getVehicleList().stream()
//            .anyMatch(v -> v.getId() == vehicleId);
//    }
//
//    /**
//     * Returns true if a vehicle with the same plate number as {@code vehicle}
//     * exists in the autom8 system.
//     */
//    public boolean hasVehicle(Vehicle vehicle) {
//        requireNonNull(vehicle);
//        return vehicles.contains(vehicle);
//    }
//
//    /**
//     * Replaces the contents of the vehicle list with {@code vehicles}.
//     * {@code vehicles} must not contain duplicate vehicles.
//     */
//    public void setVehicles(List<Vehicle> vehicles) {
//        this.vehicles.setVehicles(vehicles);
//    }
//
//    /**
//     * Replaces the given person {@code target} in the list with {@code editedPerson}.
//     * {@code target} must exist in the address book.
//     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
//     */
//    public void setVehicle(Vehicle target, Vehicle editedVehicle) {
//        requireNonNull(editedVehicle);
//        vehicles.setVehicle(target, editedVehicle);
//    }
//
//    /**
//     * Removes {@code key} from this {@code AddressBook}.
//     * {@code key} must exist in the address book.
//     */
//    public void removeVehicle(Vehicle key) {
//        key.getServiceIds().stream()
//            .flatMap(i -> getService(i).stream())
//            .forEach(this::removeService);
//        vehicles.remove(key);
//    }
//
//    @Override
//    public ObservableList<Vehicle> getVehicleList() {
//        return this.vehicles.asUnmodifiableObservableList();
//    }


    // --------------------------------------------------

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyShop newData) {
        requireNonNull(newData);

        setCustomers(newData.getCustomerList());
        setVehicles(newData.getVehicleList());
        setParts(newData.getPartMap());
        setServices(newData.getServiceList());
        setTechnicians(newData.getTechnicianList());
        setAppointments(newData.getAppointmentList());
        setTechnicians(newData.getTechnicianList());
    }

    // Private getters to help in cascading removal

//    private Optional<Customer> getCustomer(int customerId) {
//        return this.getCustomerList().stream()
//            .filter(c -> c.getId() == customerId)
//            .findFirst();
//    }

//    private Optional<Vehicle> getVehicle(int vehicleId) {
//        return this.getVehicleList().stream()
//            .filter(v -> v.getId() == vehicleId)
//            .findFirst();
//    }
//
//    private Optional<Service> getService(int serviceId) {
//        return this.getServiceList().stream()
//            .filter(v -> v.getId() == serviceId)
//            .findFirst();
//    }
//
//    private Optional<Appointment> getAppointment(int appointmentId) {
//        return this.getAppointmentList().stream()
//            .filter(a -> a.getId() == appointmentId)
//            .findFirst();
//    }

    //// Delete operations

    // --------------------------------------------------
    //// util methods

    //    @Override
    //    public String toString() {
    //        return persons.asUnmodifiableObservableList().size() + " persons";
    //        // TODO: refine later
    //        // TODO: modify this
    //    }
    //
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Shop // instanceof handles nulls
                && customers.equals(((Shop) other).customers));
    }

    @Override
    public int hashCode() {
        return customers.hashCode();
    }

    //// Others
}
