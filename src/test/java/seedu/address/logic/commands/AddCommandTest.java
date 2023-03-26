package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyShop;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Person;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.mapping.CustomerVehicleMap;
import seedu.address.model.mapping.ServiceDataMap;
import seedu.address.model.mapping.VehicleDataMap;
import seedu.address.model.service.PartMap;
import seedu.address.model.service.Service;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.appointment.Appointment;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getShopFilePath() {
            return null;
        }

        @Override
        public void setShopFilePath(Path shopFilePath) {

        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Service> getFilteredServiceList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PartMap getPartMap() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCustomerList(Predicate<Customer> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCustomer(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCustomer(int customerId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCustomer(Customer target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCustomer(Customer target, Customer editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addVehicle(int customerId, Vehicle vehicle) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasVehicle(int vehicleId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteVehicle(Vehicle target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addService(int vehicleId, Service service) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasService(int serviceId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteService(Service service) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPart(String partName, int quantity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPart(String partName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Technician> getFilteredTechnicianList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTechnician(int id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTechnician(Technician technician) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePartsMap() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTechnician(Technician target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public CustomerVehicleMap getCustomerVehicleMap() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTechnicianList(Predicate<Technician> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredVehicleList(Predicate<Vehicle> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredServiceList(Predicate<Service> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        public ObservableList<Customer> getFilteredCustomerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Vehicle> getFilteredVehicleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public VehicleDataMap getVehicleDataMap() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ServiceDataMap getServiceDataMap() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void selectCustomer(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Customer getSelectedCustomer() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void selectVehicle(Vehicle vehicle) {

        }

        @Override
        public Vehicle getSelectedVehicle() {
            return null;
        }

        @Override
        public void selectService(Service service) {

        }

        @Override
        public Service getSelectedService() {
            return null;
        }

        @Override
        public ReadOnlyShop getShop() {
            throw new AssertionError("This method should not be called.");
        }
    }


    // TODO: 3/14/2023 Extend AddCommandTest   ModelStubWithParts ModelStubWithVehicles etc
    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
