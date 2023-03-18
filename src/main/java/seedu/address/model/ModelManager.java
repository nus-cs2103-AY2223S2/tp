package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Person;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.shop.Shop;
import seedu.address.model.mapping.CustomerVehicleMap;
import seedu.address.model.mapping.ServiceDataMap;
import seedu.address.model.mapping.VehicleDataMap;
import seedu.address.model.service.PartMap;
import seedu.address.model.service.Service;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.appointment.Appointment;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Customer> filteredCustomers;
    private final FilteredList<Technician> filteredTechnicians;
    private final FilteredList<Service> filteredServices;
    private final FilteredList<Vehicle> filteredVehicles;
    private final FilteredList<Appointment> filteredAppointment;

    private final PartMap partMap;
    private final Shop shop;

    // Mapped
    private final CustomerVehicleMap customerVehicleMap;
    private final VehicleDataMap vehicleDataMap;
    private final ServiceDataMap serviceDataMap;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyShop shop) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.shop = new Shop(shop);


        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredCustomers = new FilteredList<>(this.shop.getCustomerList());
        filteredTechnicians = new FilteredList<>(this.shop.getTechnicianList());
        filteredServices = new FilteredList<>(this.shop.getServiceList());
        filteredVehicles = new FilteredList<>(this.shop.getVehicleList());
        filteredAppointment = new FilteredList<>(this.shop.getAppointmentList());
        partMap = this.shop.getPartMap();
        //        filteredParts = new FilteredList<>(this.shop.getPartList()); // filteredParts

        customerVehicleMap = new CustomerVehicleMap(this.shop.getCustomerList(), this.shop.getVehicleList());
        vehicleDataMap = new VehicleDataMap(this.shop.getVehicleList(), this.shop.getCustomerList(),
                this.shop.getServiceList());
        serviceDataMap = new ServiceDataMap(this.shop.getServiceList(), this.shop.getTechnicianList(),
                this.shop.getVehicleList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new Shop());
    }

    private void resetMaps() {
        this.customerVehicleMap.reset(this.shop.getCustomerList(), this.shop.getVehicleList());
        this.vehicleDataMap.reset(this.shop.getVehicleList(), this.shop.getCustomerList(),
                this.shop.getServiceList());
        this.serviceDataMap.reset(this.shop.getServiceList(), this.shop.getTechnicianList(),
                this.shop.getVehicleList());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    //=========== AddressBook ================================================================================
    @Override
    public ReadOnlyShop getShop() {
        return shop;
    }

    // ==== For persons ===
    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }
    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    // ==== For Customers ==

    /**
     * Adds customer to the shop
     *
     * @param customer Customer to be added
     */
    @Override
    public void addCustomer(Customer customer) {
        this.shop.addCustomer(customer);
        resetMaps();
        updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        //#todo fix 44 allow shops
    }

    /**
     * Checks whether Shop already has customer
     *
     * @param customerId Customer ID to be checked
     */
    @Override
    public boolean hasCustomer(int customerId) {
        return this.shop.hasCustomer(customerId);
    }

    @Override
    public void deleteCustomer(Customer target) {
        this.shop.removeCustomer(target);
    }

    @Override
    public void setCustomer(Customer target, Customer editedPerson) {
        requireAllNonNull(target, editedPerson);
        this.shop.setCustomer(target, editedPerson);
    }

    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        return filteredCustomers;
    }

    @Override
    public void updateFilteredCustomerList(Predicate<Customer> predicate) {
        requireNonNull(predicate);
        filteredCustomers.setPredicate(predicate);
    }

    // ==== For Vehicles ==

    @Override
    public ObservableList<Vehicle> getFilteredVehicleList() {
        return filteredVehicles;
    }

    /**
     * Adds vehicle to the shop
     *
     * @param vehicle Vehicle to be added
     */
    @Override
    public void addVehicle(int customerId, Vehicle vehicle) {
        this.shop.addVehicle(customerId, vehicle);
        resetMaps();
        updateFilteredVehicleList(PREDICATE_SHOW_ALL_VEHICLES);
    }

    /**
     * Checks if shop already has vehicle
     *
     * @param vehicleId Vehicle ID to check against
     */
    @Override
    public boolean hasVehicle(int vehicleId) {
        return this.shop.hasVehicle(vehicleId);
    }

    @Override
    public void deleteVehicle(Vehicle target) {
        this.shop.removeVehicle(target);
    }

    // ==== For Services ==
    @Override
    public ObservableList<Service> getFilteredServiceList() {
        return filteredServices;
    }

    /**
     * Adds service
     *
     * @param service Service to add
     */
    @Override
    public void addService(int vehicleId, Service service) {
        this.shop.addService(vehicleId, service);
        resetMaps();
        updateFilteredServiceList(PREDICATE_SHOW_ALL_SERVICES);
    }

    /**
     * @param serviceId ID of service
     * @return Whether service already in the system
     */
    @Override
    public boolean hasService(int serviceId) {
        return this.shop.hasService(serviceId);
    }

    public void deleteService(Service service) {
        this.shop.removeService(service);
    }

    // ==== For Appointment ==
    /**
     * Adds appointment
     *
     * @param appointment Appointment to add
     */
    @Override
    public void addAppointment(Appointment appointment) {
        this.shop.addAppointment(appointment);
    }

    @Override
    public void deleteAppointment(Appointment target) {
        this.shop.removeAppointment(target);
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointment;
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return this.shop.hasAppointment(appointment);
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);
        this.shop.setAppointment(target, editedAppointment);
    }

    // ==== For Part ==

    /**
     * Adds part
     *
     * @param partName Name of the part to add
     * @param quantity Quantity of the part to add
     */
    @Override
    public void addPart(String partName, int quantity) {
        this.shop.addPart(partName, quantity);
    }

    /**
     * Checks if part already exists
     *
     * @param partName Name of part to check
     */
    @Override
    public boolean hasPart(String partName) {
        return this.shop.hasPart(partName);
    }

    @Override
    public ObservableList<Technician> getFilteredTechnicianList() {
        return filteredTechnicians;
    }

    // ==== For Technician ==
    @Override
    public void addTechnician(Technician technician) {
        this.shop.addTechnician(technician);
    }

    @Override
    public boolean hasTechnician(int technicianId) {
        return this.shop.hasTechnician(technicianId);
    }

    @Override
    public void deleteTechnician(Technician target) {
        this.shop.removeTechnician(target);
    }

    // ==== Mapped ==
    @Override
    public CustomerVehicleMap getCustomerVehicleMap() {
        return this.customerVehicleMap;
    }

    @Override
    public VehicleDataMap getVehicleDataMap() {
        return this.vehicleDataMap;
    }

    @Override
    public ServiceDataMap getServiceDataMap() {
        return this.serviceDataMap;
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public PartMap getPartMap() {
        return partMap;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredTechnicianList(Predicate<Technician> predicate) {
        requireNonNull(predicate);
        filteredTechnicians.setPredicate(predicate);
    }

    @Override
    public void updateFilteredServiceList(Predicate<Service> predicate) {
        requireNonNull(predicate);
        filteredServices.setPredicate(predicate);
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointment.setPredicate(predicate);
    }

    @Override
    public void updateFilteredVehicleList(Predicate<Vehicle> predicate) {
        requireNonNull(predicate);
        filteredVehicles.setPredicate(predicate);
    }

    //    @Override
    //    public void updateFilteredPartList(Predicate<Part> predicate) {
    //        requireNonNull(predicate);
    //        filteredParts.setPredicate(predicate);
    //    }

    //    @Override
    //    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
    //        requireNonNull(predicate);
    //        filteredAppointment.setPredicate(predicate);
    //    }

    @Override
    public void updatePartsMap() {
        //todo: GUI stuff for printing!
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
