package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.SharedComparatorsUtil;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Person;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.shop.Shop;
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
    private final FilteredList<Map.Entry<String, Integer>> filteredParts;
    private final Shop shop;

    private Customer selectedCustomer;
    private Vehicle selectedVehicle;
    private Service selectedService;
    private Appointment selectedAppointment;
    private Technician selectedTechnician;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyShop shop) {
        requireAllNonNull(addressBook, userPrefs, shop);

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

        this.filteredParts = new FilteredList<>(this.shop.getPartMap());

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

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        this.filteredPersons.setPredicate(predicate);
    }


    // Shop ===================================================================================================

    @Override
    public Shop getShop() {
        return shop;
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
    public ObservableList<Technician> getFilteredTechnicianList() {
        return sortedFilteredTechnicians;
    }

    @Override
    public ObservableList<Map.Entry<String, Integer>> getFilteredPartMap() {
        return filteredParts;
    }

    // selecting ===========================================================================================
    @Override
    public void selectCustomer(Function<? super List<? extends Customer>, ? extends Customer> selector) {
        selectedCustomer = selector.apply(this.sortedFilteredCustomers);
    }

    @Override
    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    @Override
    public void selectVehicle(Function<? super List<? extends Vehicle>, ? extends Vehicle> selector) {
        selectedVehicle = selector.apply(this.sortedFilteredVehicles);
    }

    @Override
    public Vehicle getSelectedVehicle() {
        return selectedVehicle;
    }

    @Override
    public void selectService(Function<? super List<? extends Service>, ? extends Service> selector) {
        selectedService = selector.apply(this.sortedFilteredServices);
    }

    @Override
    public Service getSelectedService() {
        return selectedService;
    }

    @Override
    public void selectAppointment(Function<? super List<? extends Appointment>, ? extends Appointment> selector) {
        selectedAppointment = selector.apply(this.sortedFilteredAppointments);
    }

    @Override
    public Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    @Override
    public void selectTechnician(Function<? super List<? extends Technician>, ? extends Technician> selector) {
        selectedTechnician = selector.apply(this.sortedFilteredTechnicians);
    }

    @Override
    public Technician getSelectedTechnician() {
        return selectedTechnician;
    }

    @Override
    public void resetSelected() {
        this.selectCustomer(lst -> lst.isEmpty() ? null : lst.get(0));
        this.selectVehicle(lst -> lst.isEmpty() ? null : lst.get(0));
        this.selectService(lst -> lst.isEmpty() ? null : lst.get(0));
        this.selectAppointment(lst -> lst.isEmpty() ? null : lst.get(0));
        this.selectTechnician(lst -> lst.isEmpty() ? null : lst.get(0));
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

    @Override
    public void updateFilteredPartMap(Predicate<? super Map.Entry<String, Integer>> predicate) {
        requireNonNull(predicate);
        filteredParts.setPredicate(predicate);
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
}

