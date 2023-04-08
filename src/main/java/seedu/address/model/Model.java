package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Person;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.shop.Shop;
import seedu.address.model.service.Service;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.appointment.Appointment;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    Predicate<Customer> PREDICATE_SHOW_ALL_CUSTOMERS = unused -> true;
    Predicate<Technician> PREDICATE_SHOW_ALL_TECHNICIANS = unused -> true;
    Predicate<Map.Entry<String, Integer>> PREDICATE_SHOW_ALL_PARTS = unused -> true;
    Predicate<Service> PREDICATE_SHOW_ALL_SERVICES = unused -> true;
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;
    Predicate<Vehicle> PREDICATE_SHOW_ALL_VEHICLES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' shop file path.
     */
    Path getShopFilePath();

    /**
     * Sets the user prefs' shop file path.
     */
    void setShopFilePath(Path shopFilePath);

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Exposes Shop api caller
     */
    Shop getShop();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    ObservableList<Service> getFilteredServiceList();

    ObservableList<Appointment> getFilteredAppointmentList();


    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    void updateFilteredAppointmentList(Predicate<? super Appointment> predicate);

    // ==== For Customers ==

    /**
     * Returns an unmodifiable view of the filtered customer list
     */
    ObservableList<Customer> getFilteredCustomerList();

    void resetSelected();

    /**
     * Updates the filter of the filtered customer list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCustomerList(Predicate<? super Customer> predicate);

    // ==== For Vehicles ==

    /**
     * Returns an unmodifiable view of the filtered vehicle list
     */
    ObservableList<Vehicle> getFilteredVehicleList();

    ObservableList<Technician> getFilteredTechnicianList();


    void updateFilteredTechnicianList(Predicate<? super Technician> predicate);

    void updateFilteredVehicleList(Predicate<? super Vehicle> predicate);

    void updateFilteredServiceList(Predicate<? super Service> predicate);

    ObservableList<Map.Entry<String, Integer>> getFilteredPartMap();

    /**
     * Sets currently selected customer
     */
    void selectCustomer(Function<? super List<? extends Customer>, ? extends Customer> selector);

    /**
     * Returns currently selected customer
     */
    Customer getSelectedCustomer();

    /**
     * Sets currently selected vehicle
     */
    void selectVehicle(Function<? super List<? extends Vehicle>, ? extends Vehicle> selector);

    /**
     * Returns currently selected vehicle
     */
    Vehicle getSelectedVehicle();

    /**
     * Sets currently selected service
     */
    void selectService(Function<? super List<? extends Service>, ? extends Service> selector);

    /**
     * Returns currently selected service
     */
    Appointment getSelectedAppointment();

    /**
     * Sets currently selected appointment
     */
    void selectAppointment(Function<? super List<? extends Appointment>, ? extends Appointment> selector);

    /**
     * Returns currently selected service
     */
    Service getSelectedService();

    /**
     * Returns currently selected technician
     */
    Technician getSelectedTechnician();

    /**
     * Sets currently selected technician
     */
    void selectTechnician(Function<? super List<? extends Technician>, ? extends Technician> selector);


    // Sort helper functions

    void updateFilteredPartMap(Predicate<? super Map.Entry<String, Integer>> predicate);

    /**
     * Updates the comparator used to sort customers
     *
     * @param cmp Customer comparator
     */
    void updateCustomerComparator(Comparator<? super Customer> cmp);

    /**
     * Updates the comparator used to sort vehicles
     *
     * @param cmp Vehicle comparator
     */
    void updateVehicleComparator(Comparator<? super Vehicle> cmp);

    /**
     * Updates the comparator used to sort services
     *
     * @param cmp Service comparator
     */
    void updateServiceComparator(Comparator<? super Service> cmp);

    /**
     * Updates the comparator used to sort appointments
     *
     * @param cmp Appointment comparator
     */
    void updateAppointmentComparator(Comparator<? super Appointment> cmp);

    /**
     * Updates the comparator used to sort technicians
     *
     * @param cmp Technician comparator
     */
    void updateTechnicianComparator(Comparator<? super Technician> cmp);
}
