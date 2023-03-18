package seedu.address.model.person.parent;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * A Parents class
 */
public class Parents implements ReadOnlyParents {
    private final UniqueParentList parents;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        parents = new UniqueParentList();
    }

    public Parents() {}

    /**
     * Creates a Parents using the Parents in the {@code toBeCopied}
     */
    public Parents(ReadOnlyParents toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the parent list with {@code parents}.
     * {@code parents} must not contain duplicate parents.
     */
    public void setParents(List<Parent> parents) {
        this.parents.setParents(parents);
    }

    /**
     * Resets the existing data of this {@code Parents} with {@code newData}.
     */
    public void resetData(ReadOnlyParents newData) {
        requireNonNull(newData);
        setParents(newData.getParentList());
    }

    //// parent-level operations

    /**
     * Returns true if a parent with the same identity as {@code parent} exists in tea pet.
     */
    public boolean hasParent(Parent parent) {
        requireNonNull(parent);
        return parents.contains(parent);
    }

    /**
     * Returns true if a parent with the same identity as {@code parent} exists in tea pet.
     */
    public boolean hasParentName(String parent) {
        requireNonNull(parent);
        boolean contains = false;
        for (Parent stu : parents) {
            if (stu.getName().fullName.equals(parent)) {
                contains = true;
            }
        }
        return contains;
    }

    /**
     * Returns true if a parent with the same identity as {@code parent} exists in tea pet.
     */
    public boolean hasParentNameNonCaseSensitive(String parent) {
        requireNonNull(parent);
        boolean contains = false;
        for (Parent stu : parents) {
            if (stu.getName().fullName.toLowerCase().equals(parent.toLowerCase())) {
                contains = true;
            }
        }
        return contains;
    }

    /**
     * Adds a parent to tea pet.
     * The parent must not already exist in tea pet.
     */
    public void addParent(Parent p) {
        parents.add(p);
    }

    /**
     * Replaces the given parent {@code target} in the list with {@code editedParent}.
     * {@code target} must exist in tea pet.
     * The parent identity of {@code editedParent} must not be the same as another existing parent in
     * tea pet.
     */
    public void setParent(Parent target, Parent editedParent) {
        requireNonNull(editedParent);
        parents.setParent(target, editedParent);
    }

    /**
     * Removes {@code key} from this {@code Parents}.
     * {@code key} must exist in tea pet.
     */
    public void removeParent(Parent key) {
        parents.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return parents.asUnmodifiableObservableList().size() + " parents";
    }

    @Override
    public ObservableList<Parent> getParentList() {
        return parents.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Parents // instanceof handles nulls
                && parents.equals(((Parents) other).parents));
    }

    @Override
    public int hashCode() {
        return parents.hashCode();
    }
}
