package seedu.techtrack.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.techtrack.model.role.Role;
import seedu.techtrack.model.role.UniqueRoleList;

/**
 * Wraps all data at the role-book level
 * Duplicates are not allowed (by .isSameRole comparison)
 */
public class RoleBook implements ReadOnlyRoleBook {

    private UniqueRoleList roles;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        roles = new UniqueRoleList();
    }

    public RoleBook() {}

    /**
     * Creates an RoleBook using the Roles in the {@code toBeCopied}
     */
    public RoleBook(ReadOnlyRoleBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the role list with {@code roles}.
     * {@code roles} must not contain duplicate roles.
     */
    public void setRoles(List<Role> roles) {
        this.roles.setRoles(roles);
    }

    /**
     * Resets the existing data of this {@code RoleBook} with {@code newData}.
     */
    public void resetData(ReadOnlyRoleBook newData) {
        requireNonNull(newData);

        setRoles(newData.getRoleList());
    }

    //// role-level operations

    /**
     * Returns true if a role with the same identity as {@code role} exists in the role book.
     */
    public boolean hasRole(Role role) {
        requireNonNull(role);
        return roles.contains(role);
    }

    /**
     * Adds a role to the role book.
     * The role must not already exist in the role book.
     */
    public void addRole(Role p) {
        roles.add(p);
    }

    /**
     * Replaces the given role {@code target} in the list with {@code editedRole}.
     * {@code target} must exist in the role book.
     * The role identity of {@code editedRole} must not be the same as another existing role in the role book.
     */
    public void setRole(Role target, Role editedRole) {
        requireNonNull(editedRole);

        roles.setRole(target, editedRole);
    }

    /**
     * Removes {@code key} from this {@code RoleBook}.
     * {@code key} must exist in the role book.
     */
    public void removeRole(Role key) {
        roles.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return roles.asUnmodifiableObservableList().size() + " roles";
        // TODO: refine later
    }

    @Override
    public ObservableList<Role> getRoleList() {
        return roles.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoleBook // instanceof handles nulls
                && roles.equals(((RoleBook) other).roles));
    }

    @Override
    public int hashCode() {
        return roles.hashCode();
    }
}
