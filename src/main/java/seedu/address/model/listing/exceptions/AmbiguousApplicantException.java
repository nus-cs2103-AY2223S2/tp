package seedu.address.model.listing.exceptions;

/**
 * Signals that the operation is unsure which applicant to delete.
 */
public class AmbiguousApplicantException extends RuntimeException {
    /**
     * Creates an AmbiguousApplicantException to signify that the exact applicant cannot be determined.
     */
    public AmbiguousApplicantException() {
        super("There are multiple applicants with the same name, specify which "
                + "applicant by adding the 4-digit unique identifier after the name, e.g. a/I am failing#2103");
    }
}
