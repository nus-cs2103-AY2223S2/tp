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
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.txncommands.AddTxnCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.TransactionBuilder;

public class AddTxnCommandTest {

    @Test
    public void constructor_nullTxn_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTxnCommand(null));
    }

    @Test
    public void execute_txnAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTxnAdded modelStub = new ModelStubAcceptingTxnAdded();
        Transaction validTxn = new TransactionBuilder().build();

        CommandResult commandResult = new AddTxnCommand(validTxn).execute(modelStub);

        assertEquals(String.format(AddTxnCommand.MESSAGE_SUCCESS, validTxn), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTxn), modelStub.txnsAdded);
    }

    @Test
    public void execute_duplicateTxn_throwsCommandException() {
        Transaction validTxn = new TransactionBuilder().build();
        AddTxnCommand addTxnCommand = new AddTxnCommand(validTxn);
        ModelStub modelStub = new ModelStubWithTxn(validTxn);

        assertThrows(CommandException.class, AddTxnCommand.MESSAGE_DUPLICATE_TRANSACTION, (
                ) -> addTxnCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Transaction coffeeMachines = new TransactionBuilder().withDesc("COFFEEMAcHINEEEEE").build();
        Transaction coffeeBeans = new TransactionBuilder().withDesc("CAWFEE BEANZ").build();
        AddTxnCommand addCoffeeMachines = new AddTxnCommand(coffeeMachines);
        AddTxnCommand addCoffeeBeans = new AddTxnCommand(coffeeBeans);

        // same object -> returns true
        assertTrue(addCoffeeMachines.equals(addCoffeeMachines));

        // same values -> returns true
        AddTxnCommand addMachinesCommandCopy = new AddTxnCommand(coffeeMachines);
        assertTrue(addCoffeeMachines.equals(addMachinesCommandCopy));

        // different types -> returns false
        assertFalse(addCoffeeMachines.equals(1));

        // null -> returns false
        assertFalse(addCoffeeMachines.equals(null));

        // different person -> returns false
        assertFalse(addCoffeeMachines.equals(addCoffeeBeans));
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
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
        public void addTransaction(Transaction transaction) {
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
        public boolean hasTransaction(Transaction transaction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void deleteTransaction(Transaction target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTransaction(Transaction target, Transaction editedTransaction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Transaction> getFilteredTransactionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTransactionList(Predicate<Transaction> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithTxn extends ModelStub {
        private final Transaction txn;

        ModelStubWithTxn(Transaction txn) {
            requireNonNull(txn);
            this.txn = txn;
        }

        @Override
        public boolean hasTransaction(Transaction txn) {
            requireNonNull(txn);
            return this.txn.isSameTransaction(txn);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingTxnAdded extends ModelStub {
        final ArrayList<Transaction> txnsAdded = new ArrayList<>();

        @Override
        public boolean hasTransaction(Transaction txn) {
            requireNonNull(txn);
            return txnsAdded.stream().anyMatch(txn::isSameTransaction);
        }

        @Override
        public void addTransaction(Transaction txn) {
            requireNonNull(txn);
            txnsAdded.add(txn);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
