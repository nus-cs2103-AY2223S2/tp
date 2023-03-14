package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Person;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.person.UniqueCustomerList;
import seedu.address.model.entity.person.UniquePersonList;
import seedu.address.model.entity.person.UniqueTechnicianList;
import seedu.address.model.service.UniqueVehicleList;
import seedu.address.model.service.Vehicle;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    // Yet to migrate from persons to customer and technicians
    private final UniqueCustomerList customers; // UniqueCustomerList customers;
    private final UniqueTechnicianList technicians; // UniqueStaffList staff;
    private final Object parts; // UniquePartsList parts;
    private final Object services; // UniqueServiceList services;
    private final Object appointments; // UniqueAppointmentList appointments;
    //    private final Object garage; // UniqueGarage garage;
    private final UniqueVehicleList vehicles;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        customers = new UniqueCustomerList();
        technicians = new UniqueTechnicianList();
        parts = null;
        services = null;
        appointments = null;
        // garage = null;
        vehicles = new UniqueVehicleList();
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
        return customers.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addCustomer(Customer p) {
        customers.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setCustomer(Customer target, Customer editedPerson) {
        requireNonNull(editedPerson);
        customers.setCustomer(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeCustomer(Customer key) {
        customers.remove(key);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setCustomers(List<Customer> persons) {
        customers.setCustomers(persons);
    }

    @Override
    public ObservableList<Customer> getCustomerList() {
        return customers.asUnmodifiableObservableList();
    }

    // --------------------------------------------------
    //// technician-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasTechnician(Technician person) {
        requireNonNull(person);
        return technicians.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addTechnician(Technician p) {
        technicians.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setTechnician(Technician target, Technician editedPerson) {
        requireNonNull(editedPerson);
        technicians.setTechnician(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTechnician(Technician key) {
        technicians.remove(key);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setTechnicians(List<Technician> persons) {
        technicians.setTechnicians(persons);
    }

    @Override
    public ObservableList<Technician> getTechnicianList() {
        return technicians.asUnmodifiableObservableList();
    }

    // --------------------------------------------------
    //// vehicle-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasVehicle(Vehicle vehicle) {
        requireNonNull(vehicle);
        return vehicles.contains(vehicle);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setVehicle(Vehicle target, Vehicle editedVehicle) {
        requireNonNull(editedVehicle);
        vehicles.setVehicle(target, editedVehicle);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeVehicle(Vehicle key) {
        vehicles.remove(key);
    }

    /**
     * Replaces the contents of the vehicle list with {@code vehicle}.
     * {@code vehicle} must not contain duplicate vehicle.
     */
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles.setVehicles(vehicles);
    }

    @Override
    public ObservableList<Vehicle> getVehicleList() {
        return vehicles.asUnmodifiableObservableList();
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
