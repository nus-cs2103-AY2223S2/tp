package seedu.sudohr.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.sudohr.commons.core.GuiSettings;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.ReadOnlySudoHr;
import seedu.sudohr.model.ReadOnlyUserPrefs;
import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Employee validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Employee validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_EMPLOYEE, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateIdOnly_throwsCommandException() {
        Employee validPerson = new PersonBuilder().build();
        Employee sameIdPerson = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB).withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_FRIEND)
                .build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(sameIdPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_EMPLOYEE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateEmailOnly_throwsCommandException() {
        Employee validPerson = new PersonBuilder().build();
        Employee sameEmail = new PersonBuilder().withName(VALID_NAME_BOB).withId(VALID_ID_BOB)
                .withAddress(VALID_ADDRESS_BOB).withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_FRIEND)
                .build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(sameEmail);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_EMAIL, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicatePhoneNumberOnly_throwsCommandException() {
        Employee validPerson = new PersonBuilder().build();
        Employee samePhone = new PersonBuilder().withName(VALID_NAME_BOB).withId(VALID_ID_BOB)
                .withAddress(VALID_ADDRESS_BOB).withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_FRIEND)
                .build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(samePhone);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PHONE, () -> addCommand.execute(modelStub));
    }

    // duplicate phone should be identified first
    @Test
    public void execute_differentIdOnly_throwsCommandException() {
        Employee validPerson = new PersonBuilder().build();
        Employee diffId = new PersonBuilder().withId(VALID_ID_BOB).build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(diffId);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PHONE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        // note default ID for person builder is "0777"
        Employee alice = new PersonBuilder().withName("Alice").build();
        Employee bob = new PersonBuilder().withName("Bob").build();
        Employee differentBob = new PersonBuilder().withName("BOB").withId(VALID_ID_BOB).build();

        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);
        AddCommand addDifferentBobCommand = new AddCommand(differentBob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // same person id -> returns false
        // Note: strict equality across every field
        assertFalse(addAliceCommand.equals(addBobCommand));

        // different person id -> returns false
        assertFalse(addDifferentBobCommand.equals(addAliceCommand));
    }

    /**
     * A default model stub that have all of its methods failing.
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
        public Path getSudoHrFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSudoHrFilePath(Path sudoHrFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEmployee(Employee employee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSudoHr(ReadOnlySudoHr sudoHr) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlySudoHr getSudoHr() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEmployee(Employee employee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClashingEmail(Employee person)  {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClashingPhoneNumber(Employee person)  {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEmployee(Employee target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEmployee(Employee target, Employee editedEmployee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Employee> getFilteredEmployeeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEmployeeList(Predicate<Employee> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Department getDepartment(DepartmentName name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDepartment(Department department) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDepartment(Department d) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDepartment(Department target, Department editedDepartment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeDepartment(Department key) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEmployeeToDepartment(Employee p, Department d) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeEmployeeFromDepartment(Employee p, Department d) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Department> getFilteredDepartmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDepartmentList(Predicate<Department> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Employee person;

        ModelStubWithPerson(Employee person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasEmployee(Employee employee) {
            requireNonNull(employee);
            return this.person.isSameEmployee(employee);
        }

        @Override
        public boolean hasClashingEmail(Employee person) {
            requireNonNull(person);
            return this.person.emailClashes(person);
        }

        @Override
        public boolean hasClashingPhoneNumber(Employee person) {
            requireNonNull(person);
            return this.person.phoneClashes(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Employee> personsAdded = new ArrayList<>();

        @Override
        public boolean hasEmployee(Employee employee) {
            requireNonNull(employee);
            return personsAdded.stream().anyMatch(employee::isSameEmployee);
        }

        @Override
        public boolean hasClashingEmail(Employee person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::emailClashes);
        }

        @Override
        public boolean hasClashingPhoneNumber(Employee person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::phoneClashes);
        }

        @Override
        public void addEmployee(Employee person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlySudoHr getSudoHr() {
            return new SudoHr();
        }
    }

}
