package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.SharedComparatorsUtil;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Person;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.shop.Shop;
import seedu.address.model.mapping.AppointmentDataMap;
import seedu.address.model.mapping.CustomerVehicleMap;
import seedu.address.model.mapping.ServiceDataMap;
import seedu.address.model.mapping.TechnicianDataMap;
import seedu.address.model.mapping.VehicleDataMap;
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
    private final SortedList<Customer> sortedFilteredCustomers;
    private final FilteredList<Technician> filteredTechnicians;
    private final SortedList<Technician> sortedFilteredTechnicians;
    private final FilteredList<Service> filteredServices;
    private final SortedList<Service> sortedFilteredServices;
    private final FilteredList<Vehicle> filteredVehicles;
    private final SortedList<Vehicle> sortedFilteredVehicles;
    private final FilteredList<Appointment> filteredAppointments;
    private final SortedList<Appointment> sortedFilteredAppointments;
    private final ObservableMap<String, Integer> filteredParts;
    private final Shop shop;

    private Customer selectedCustomer;
    private Vehicle selectedVehicle;
    private Service selectedService;
    private Appointment selectedAppointment;
    private Technician selectedTechnician;

    // Mapped
    private final CustomerVehicleMap customerVehicleMap;
    private final VehicleDataMap vehicleDataMap;
    private final ServiceDataMap serviceDataMap;
    private final AppointmentDataMap appointmentDataMap;
    private final TechnicianDataMap technicianDataMap;

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
        this.sortedFilteredCustomers = new SortedList<>(this.filteredCustomers);

        filteredTechnicians = new FilteredList<>(this.shop.getTechnicianList());
        this.sortedFilteredTechnicians = new SortedList<>(this.filteredTechnicians);

        filteredServices = new FilteredList<>(this.shop.getServiceList());
        this.sortedFilteredServices = new SortedList<>(this.filteredServices);

        filteredVehicles = new FilteredList<>(this.shop.getVehicleList());
        this.sortedFilteredVehicles = new SortedList<>(this.filteredVehicles);

        filteredAppointments = new FilteredList<>(this.shop.getAppointmentList());
        this.sortedFilteredAppointments = new SortedList<>(this.filteredAppointments);
        // default sort for appointments
        this.sortedFilteredAppointments.setComparator(SharedComparatorsUtil.getDefaultAppointmentSort());

        this.filteredParts = this.shop.getPartMap();

        customerVehicleMap = new CustomerVehicleMap(this.shop.getCustomerList(), this.shop.getVehicleList(),
            this.shop.getAppointmentList());
        vehicleDataMap = new VehicleDataMap(this.shop.getVehicleList(), this.shop.getCustomerList(),
            this.shop.getServiceList());
        serviceDataMap = new ServiceDataMap(this.shop.getServiceList(), this.shop.getTechnicianList(),
            this.shop.getVehicleList());
        appointmentDataMap = new AppointmentDataMap(this.shop.getAppointmentList(), this.shop.getTechnicianList(),
            this.shop.getCustomerList());
        technicianDataMap = new TechnicianDataMap(this.shop.getTechnicianList(), this.shop.getServiceList(),
            this.shop.getAppointmentList());

        if (filteredCustomers.size() > 0) {
            selectedCustomer = filteredCustomers.get(0);
        }
        if (filteredVehicles.size() > 0) {
            selectedVehicle = filteredVehicles.get(0);
        }
        if (filteredServices.size() > 0) {
            selectedService = filteredServices.get(0);
        }
        if (filteredAppointments.size() > 0) {
            selectedAppointment = sortedFilteredAppointments.get(0);
        }
        if (filteredTechnicians.size() > 0) {
            selectedTechnician = filteredTechnicians.get(0);
        }
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new Shop());
    }

    @Override
    public void resetMaps() {
        this.customerVehicleMap.reset(this.shop.getCustomerList(), this.shop.getVehicleList(),
            this.shop.getAppointmentList());
        this.vehicleDataMap.reset(this.shop.getVehicleList(), this.shop.getCustomerList(),
            this.shop.getServiceList());
        this.serviceDataMap.reset(this.shop.getServiceList(), this.shop.getTechnicianList(),
            this.shop.getVehicleList());
        this.appointmentDataMap.reset(this.shop.getAppointmentList(), this.shop.getTechnicianList(),
            this.shop.getCustomerList());
        ;
        this.technicianDataMap.reset(this.shop.getTechnicianList(), this.shop.getServiceList(),
            this.shop.getAppointmentList());
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

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return this.filteredPersons;
    }

    // Shop ===================================================================================================

    @Override
    public Shop getShop() {
        return shop;
    }

    @Override
    public void setShop(ReadOnlyShop shop) {
        this.shop.resetData(shop);
    }

    @Override
    public Path getShopFilePath() {
        return userPrefs.getShopFilePath();
    }

    @Override
    public void setShopFilePath(Path shopFilePath) {
        requireNonNull(shopFilePath);
        userPrefs.setShopFilePath(shopFilePath);
    }

    // Filtered Getters =======================================================================================

    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        return sortedFilteredCustomers;
    }

    @Override
    public ObservableList<Vehicle> getFilteredVehicleList() {
        return sortedFilteredVehicles;
    }

    @Override
    public ObservableList<Service> getFilteredServiceList() {
        return sortedFilteredServices;
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return sortedFilteredAppointments;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {

    }

    @Override
    public ObservableList<Technician> getFilteredTechnicianList() {
        return sortedFilteredTechnicians;
    }

    // selecting ===========================================================================================
    @Override
    public void selectCustomer(Customer customer) {
        selectedCustomer = customer;
    }

    @Override
    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    @Override
    public void selectVehicle(Vehicle vehicle) {
        selectedVehicle = vehicle;
    }

    @Override
    public Vehicle getSelectedVehicle() {
        return selectedVehicle;
    }

    @Override
    public void selectService(Service service) {
        selectedService = service;
    }

    @Override
    public Service getSelectedService() {
        return selectedService;
    }

    @Override
    public void selectAppointment(Appointment appointment) {
        selectedAppointment = appointment;
    }

    @Override
    public Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    @Override
    public void selectTechnician(Technician technician) {
        selectedTechnician = technician;
    }

    @Override
    public Technician getSelectedTechnician() {
        return selectedTechnician;
    }

    // Set predicates && comparators =================================================================================

    // Filters
    @Override
    public void updateFilteredCustomerList(Predicate<? super Customer> predicate) {
        requireNonNull(predicate);
        filteredCustomers.setPredicate(predicate);
    }

    @Override
    public void updateFilteredVehicleList(Predicate<? super Vehicle> predicate) {
        requireNonNull(predicate);
        filteredVehicles.setPredicate(predicate);
    }

    @Override
    public void updateFilteredServiceList(Predicate<? super Service> predicate) {
        requireNonNull(predicate);
        filteredServices.setPredicate(predicate);
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<? super Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
    }

    @Override
    public void updateFilteredTechnicianList(Predicate<? super Technician> predicate) {
        requireNonNull(predicate);
        filteredTechnicians.setPredicate(predicate);
    }

    // Sorters
    @Override
    public void updateCustomerComparator(Comparator<? super Customer> comparator) {
        requireNonNull(comparator);
        sortedFilteredCustomers.setComparator(comparator);
    }

    @Override
    public void updateVehicleComparator(Comparator<? super Vehicle> comparator) {
        requireNonNull(comparator);
        sortedFilteredVehicles.setComparator(comparator);
    }

    @Override
    public void updateServiceComparator(Comparator<? super Service> comparator) {
        requireNonNull(comparator);
        sortedFilteredServices.setComparator(comparator);
    }

    @Override
    public void updateAppointmentComparator(Comparator<? super Appointment> comparator) {
        requireNonNull(comparator);
        sortedFilteredAppointments.setComparator(comparator);
    }

    @Override
    public void updateTechnicianComparator(Comparator<? super Technician> comparator) {
        requireNonNull(comparator);
        sortedFilteredTechnicians.setComparator(comparator);
    }

    // Map getters ===========================================================================================

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

    @Override
    public AppointmentDataMap getAppointmentDataMap() {
        return this.appointmentDataMap;
    }

    @Override
    public TechnicianDataMap getTechnicianDataMap() {
        return this.technicianDataMap;
    }
}

