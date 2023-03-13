package seedu.internship.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.UniqueInternshipList;

/**
 * Wraps all data at the internship-catalogue level
 * Duplicates are not allowed (by .isSameInternship comparison)
 */
public class InternshipCatalogue implements ReadOnlyInternshipCatalogue {

    private final UniqueInternshipList internships;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        internships = new UniqueInternshipList();
    }

    public InternshipCatalogue() {}

    /**
     * Creates an InternshipCataloue using the internships in the {@code toBeCopied}
     */
    public InternshipCatalogue(ReadOnlyInternshipCatalogue toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the internship list with {@code internships}.
     * {@code internships} must not contain duplicate internships.
     */
    public void setInternships(List<Internship> internships) {
        this.internships.setInternships(internships);
    }

    /**
     * Resets the existing data of this {@code InternshipCatalogue} with {@code newData}.
     */
    public void resetData(ReadOnlyInternshipCatalogue newData) {
        requireNonNull(newData);

        setInternships(newData.getInternshipList());
    }

    //// internship-level operations

    /**
     * Returns true if a internship is the same as another {@code internship} exists in the internship catalogue.
     */
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return this.internships.contains(internship);
    }

    /**
     * Adds a internship to the internship Catalogue.
     * The internship must not already exist in the internship Catalogue.
     */
    public void addInternship(Internship p) {
        internships.add(p);
    }

    /**
     * Replaces the given internship {@code target} in the list with {@code editedInternship}.
     * {@code target} must exist in the internship Catalogue.
     * The  {@code editedInternship} must not be the same as another existing internship in the internship Catalogue.
     */
    public void setInternship(Internship target, Internship editedInternship) {
        requireNonNull(editedInternship);

        internships.setInternship(target, editedInternship);
    }

    /**
     * Removes {@code key} from this {@code internship Catalogue}.
     * {@code key} must exist in the internship Catalogue.
     */
    public void removeInternship(Internship key) {
        this.internships.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return internships.asUnmodifiableObservableList().size() + " internships";
        // TODO: refine later
    }

    @Override
    public ObservableList<Internship> getInternshipList() {
        return internships.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InternshipCatalogue // instanceof handles nulls
                && internships.equals(((InternshipCatalogue) other).internships));
    }

    @Override
    public int hashCode() {
        return internships.hashCode();
    }
}