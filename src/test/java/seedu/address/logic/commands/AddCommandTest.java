package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
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
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setShopFilePath(Path shopFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook addressBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Shop getShop() {
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
        public void addPerson(Person person) {
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
        public ObservableList<Service> getFilteredServiceList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<? super Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Customer> getFilteredCustomerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetSelected() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCustomerList(Predicate<? super Customer> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Vehicle> getFilteredVehicleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Technician> getFilteredTechnicianList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTechnicianList(Predicate<? super Technician> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredVehicleList(Predicate<? super Vehicle> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredServiceList(Predicate<? super Service> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ObservableList<Map.Entry<String, Integer>> getFilteredPartMap() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void selectCustomer(Function<? super List<? extends Customer>, ? extends Customer> selector) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Customer getSelectedCustomer() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void selectVehicle(Function<? super List<? extends Vehicle>, ? extends Vehicle> selector) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Vehicle getSelectedVehicle() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void selectService(Function<? super List<? extends Service>, ? extends Service> selector) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Appointment getSelectedAppointment() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void selectAppointment(Function<? super List<? extends Appointment>, ? extends Appointment> selector) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Service getSelectedService() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Technician getSelectedTechnician() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void selectTechnician(Function<? super List<? extends Technician>, ? extends Technician> selector) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPartMap(Predicate<? super Map.Entry<String, Integer>> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateCustomerComparator(Comparator<? super Customer> cmp) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateVehicleComparator(Comparator<? super Vehicle> cmp) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateServiceComparator(Comparator<? super Service> cmp) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateAppointmentComparator(Comparator<? super Appointment> cmp) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateTechnicianComparator(Comparator<? super Technician> cmp) {
            throw new AssertionError("This method should not be called.");
        }
    }
}
