package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Person;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.person.UniquePersonList;
import seedu.address.model.entity.person.exceptions.PersonNotFoundException;
import seedu.address.model.entity.shop.Shop;
import seedu.address.model.service.Vehicle;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final Shop shop;
    // Merge below into Shop
    //    private final UniqueCustomerList customers; // UniqueCustomerList customers;
    //    private final UniqueTechnicianList technicians; // UniqueStaffList staff;
    //    private final Object services; // UniqueServiceList services;
    //    private final Object appointments; // UniqueAppointmentList appointments;
    //    private final UniqueVehicleList vehicles;
    private final Object parts; // UniquePartsList parts;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        //        customers = new UniqueCustomerList();
        //        technicians = new UniqueTechnicianList();
        //        services = null;
        //        appointments = null;
        //        vehicles = new UniqueVehicleList();
        parts = null;
        shop = new Shop();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setVehicles(newData.getVehicleList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    // --------------------------------------------------
    //// customer-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasCustomer(Customer person) {
        requireNonNull(person);
        return shop.hasCustomer(person.getId());
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addCustomer(Customer p) {
        shop.addCustomer(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setCustomer(Customer target, Customer editedPerson) {
        shop.setCustomer(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeCustomer(Customer key) {
        shop.removeCustomer(key);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setCustomers(List<Customer> persons) {
        shop.setCustomers(persons);
    }

    @Override
    public ObservableList<Customer> getCustomerList() {
        return shop.getCustomerList();
    }

    // --------------------------------------------------
    //// technician-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasTechnician(Technician person) {
        requireNonNull(person);
        return shop.hasTechnician(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addTechnician(Technician p) {
        shop.addTechnician(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setTechnician(Technician target, Technician editedPerson) {
        shop.setTechnician(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTechnician(Technician key) {

        shop.removeTechnician(key);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setTechnicians(List<Technician> persons) {
        shop.setTechnicians(persons);
    }

    @Override
    public ObservableList<Technician> getTechnicianList() {
        return shop.getTechnicianList();
    }

    // --------------------------------------------------
    //// vehicle-level operations

    /**
     * Checks if vehicle is in the shop
     *
     * @param vehicleId Vehicle ID to check
     */
    public boolean hasVehicle(int vehicleId) {
        //        requireNonNull(vehicle);
        //        return vehicles.contains(vehicle);
        return shop.hasVehicle(vehicleId);
    }

    /**
     * Adds vehicle to the shop
     *
     * @param customerId Id of vehicle's owner
     * @param vehicle    Vehicle to be added
     * @throws PersonNotFoundException Customer not registered with the shop
     */
    public void addVehicle(int customerId, Vehicle vehicle) throws PersonNotFoundException {
        shop.addVehicle(customerId, vehicle);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setVehicle(Vehicle target, Vehicle editedVehicle) {
        shop.setVehicle(target, editedVehicle);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeVehicle(Vehicle key) {
        shop.removeVehicle(key);
    }

    /**
     * Replaces the contents of the vehicle list with {@code vehicle}.
     * {@code vehicle} must not contain duplicate vehicle.
     */
    public void setVehicles(List<Vehicle> vehicles) {
        shop.setVehicles(vehicles);
    }

    @Override
    public ObservableList<Vehicle> getVehicleList() {
        return shop.getVehicleList();
    }

    // --------------------------------------------------
    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
        // TODO: modify this
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
