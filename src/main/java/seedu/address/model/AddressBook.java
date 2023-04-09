package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.UniqueApplicationList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueApplicationList applications;
    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * NoteList that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        applications = new UniqueApplicationList();
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
     * Replaces the contents of the person list with {@code applications}.
     * {@code applications} must not contain duplicate applications.
     */
    public void setApplications(List<InternshipApplication> applications) {
        this.applications.setApplications(applications);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setApplications(newData.getInternshipList());
    }

    /// application-level operations
    /**
     * Returns true if an internship application with the same identity
     * as {@code internshipApplication} exists in the address book.
     */
    public boolean hasApplication(InternshipApplication application) {
        requireNonNull(application);
        return applications.contains(application);
    }
    //// person-level operations

    /**
     * Adds an internship application to the tracker.
     * The application must not already exist in the tracker.
     */
    public void addApplication(InternshipApplication application) {
        applications.add(application);
    }

    /**
     * Adds internship applications to the tracker.
     * The application must not already exist in the tracker.
     */
    public void addApplications(List<InternshipApplication> application) {
        applications.add(application);
    }


    /**
     * Adds an application to the address book.
     * The application must not already exist in the address book.
     */
    public void addInternshipApplication(InternshipApplication internshipApplication) {
        applications.add(internshipApplication);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedApplication}.
     * {@code target} must exist in the address book.
     * The application identity of {@code editedApplication}
     * must not be the same as another existing application in the address book.
     */
    public void setApplication(InternshipApplication target, InternshipApplication editedApplication) {
        requireNonNull(editedApplication);

        applications.setApplication(target, editedApplication);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeApplication(InternshipApplication key) {
        applications.remove(key);
    }
    //// util methods

    @Override
    public String toString() {
        return applications.asUnmodifiableObservableList().size() + " applications";
    }

    @Override
    public ObservableList<InternshipApplication> getInternshipList() {
        return applications.asUnmodifiableObservableList();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && applications.equals(((AddressBook) other).applications));
    }

    @Override
    public int hashCode() {
        return applications.hashCode();
    }
}
