package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.category.Category;
import seedu.address.model.category.UniqueCategoryList;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ExpenseTracker implements ReadOnlyExpenseTracker {

    private final UniquePersonList persons;
    private final UniqueCategoryList categories;
    private final ExpenseList expenses;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication
     * between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other
     * ways to avoid duplication
     * among constructors.
     */
    {
        persons = new UniquePersonList();
        categories = new UniqueCategoryList();
        expenses = new ExpenseList();
    }

    public ExpenseTracker() {
    }

    /**
     * Creates an ExpenseTracker using the Persons in the {@code toBeCopied}
     */
    public ExpenseTracker(ReadOnlyExpenseTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    public void setCategories(List<Category> categories) {
        this.categories.setCategoryList(categories);
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses.setExpenseList(expenses);
    }

    /**
     * Resets the existing data of this {@code ExpenseTracker} with {@code newData}.
     */
    public void resetData(ReadOnlyExpenseTracker newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setExpenses(newData.getExpenseList());
        setCategories(newData.getCategoryList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if the given category exists in the list.
     * @param category The category to check for existence in the list.
     * @return true if the category exists in the list and false otherwise.
     */
    public boolean hasCategory(Category category) {
        requireNonNull(category);
        return categories.contains(category);
    }

    /**
     * Returns true if a category with the given name exists in the list.
     * @param categoryName The name of the category to check for existence in the
     *                     list.
     * @return true if a category with the given name exists in the list and false
     *         otherwise.
     */
    public boolean hasCategory(String categoryName) {
        for (Category c : categories.asUnmodifiableList()) {
            if (Objects.equals(c.getCategoryName(), categoryName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    public void addCategory(Category toAdd) {
        categories.add(toAdd);
    }

    /**
     * Replaces the given person {@code target} in the list with
     * {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Replaces the given category {@code target} in the list with
     * {@code editedCategory}.
     * {@code target} must exist in the address book.
     * The category identity of {@code editedCategory} must not be the same as
     * another existing category in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    public void removeCategory(Category key) {
        categories.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Category> getCategoryList() {
        return categories.asUnmodifiableList();
    }

    @Override
    public ObservableList<Expense> getExpenseList() {
        return expenses.asUnmodifiableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseTracker // instanceof handles nulls
                        && persons.equals(((ExpenseTracker) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

    public Category getCategoryInstance(String categoryName) {
        for (Category c : categories.asUnmodifiableList()) {
            if (Objects.equals(c.getCategoryName(), categoryName)) {
                return c;
            }
        }
        return null;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void removeExpense(Expense expense) {
        expenses.remove(expense);
    }
}
