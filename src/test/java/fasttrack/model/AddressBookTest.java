package fasttrack.model;

import static fasttrack.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import fasttrack.model.category.Category;
import fasttrack.model.expense.Expense;
import fasttrack.model.expense.RecurringExpenseManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddressBookTest {

    private final ExpenseTracker addressBook = new ExpenseTracker();


    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getExpenseList());
        assertEquals(Collections.emptyList(), addressBook.getCategoryList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        //ExpenseTracker newData = getTypicalExpenseTracker();
        //addressBook.resetData(newData);
        //assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        //Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
        //        .build();
        //List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        //AddressBookStub newData = new AddressBookStub(newPersons);

        //assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        //assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        //assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        //addressBook.addPerson(ALICE);
        //assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        //addressBook.addPerson(ALICE);
        //Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
        //        .build();
        //assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        //assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface
     * constraints.
     */
    private static class AddressBookStub implements ReadOnlyExpenseTracker {
        private final ObservableList<Category> categories = FXCollections.observableArrayList();
        private final ObservableList<Expense> expenses = FXCollections.observableArrayList();
        private final ObservableList<RecurringExpenseManager> expenseGenerators = FXCollections.observableArrayList();
        private final ObjectProperty<Budget> simpleBudget = new SimpleObjectProperty<>(new Budget(0));


        @Override
        public ObservableList<Category> getCategoryList() {
            return categories;
        }

        @Override
        public ObservableList<Expense> getExpenseList() {
            return expenses;
        }

        @Override
        public ObservableList<RecurringExpenseManager> getRecurringExpenseGenerators() {
            return expenseGenerators;
        }

        @Override
        public ObjectProperty<Budget> getBudgetForStats() {
            return this.simpleBudget;
        }

        @Override
        public Budget getBudget() {
            return this.simpleBudget.get();
        }
    }

}
