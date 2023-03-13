package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.application.Application;
import seedu.address.model.application.UniqueApplicationList;

/**
 * Wraps all data at the internship-book level
 * Duplicates are not allowed
 * Comment to let merge operation detect file. To be deleted subsequently.
 */
public class InternshipBook implements ReadOnlyInternshipBook {

    private final UniqueApplicationList applications;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        applications = new UniqueApplicationList();
    }

    public InternshipBook() {}

    /**
     * Creates an InternshipBook using the Applications in the {@code toBeCopied}
     */
    public InternshipBook(ReadOnlyInternshipBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the application list with {@code applications}.
     * {@code applications} must not contain duplicate Applications.
     */
    public void setApplications(List<Application> applications) {
        this.applications.setApplications(applications);
    }

    /**
     * Resets the existing data of this {@code InternshipBook} with {@code newData}.
     */
    public void resetData(ReadOnlyInternshipBook newData) {
        requireNonNull(newData);

        setApplications(newData.getApplicationList());
    }

    //// application-level operations

    /**
     * Returns true if a duplicate Application exists in the internship book.
     */
    public boolean hasApplication(Application application) {
        requireNonNull(application);
        return applications.contains(application);
    }

    /**
     * Adds an Application to the internship book.
     * The Application must not already exist in the internship book.
     */
    public void addApplication(Application a) {
        applications.add(a);
    }

    /**
     * Replaces the given application {@code target} in the list with {@code editedApplication}.
     * {@code target} must exist in the internship book.
     * {@code editedApplication} must not be the same as another existing Application in the internship book.
     */
    public void setApplication(Application target, Application editedApplication) {
        requireNonNull(editedApplication);

        applications.setApplication(target, editedApplication);
    }

    /**
     * Removes {@code key} from this {@code InternshipBook}.
     * {@code key} must exist in the internship book.
     */
    public void removeApplication(Application key) {
        applications.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return applications.asUnmodifiableObservableList().size() + " applications";
        // TODO: refine later
    }

    @Override
    public ObservableList<Application> getApplicationList() {
        return applications.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InternshipBook // instanceof handles nulls
                && applications.equals(((InternshipBook) other).applications));
    }

    @Override
    public int hashCode() {
        return applications.hashCode();
    }
}
