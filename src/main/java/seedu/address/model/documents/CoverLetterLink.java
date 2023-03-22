package seedu.address.model.documents;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a link to a cover letter in the internship application.
 * Guarantees: immutable; is valid as declared in {@link #isValidCoverLetterLink(String)}
 */
public class CoverLetterLink {

    public static final String MESSAGE_CONSTRAINTS = "Link to cover letter should be in the form http://domain/path "
            + "or https://domain/path";

    public final String value;

    /**
     * Constructs an {@code CoverLetter}.
     *
     * @param coverLetterLink A valid link to a cover letter.
     */
    public CoverLetterLink(String coverLetterLink) {
        requireNonNull(coverLetterLink);
        checkArgument(isValidCoverLetterLink(coverLetterLink), MESSAGE_CONSTRAINTS);
        value = coverLetterLink;
    }

    /**
     * Returns if a given string is a valid link to a cover letter.
     */
    public static boolean isValidCoverLetterLink(String test) {
        try {
            new URL(test).toURI();
            return true;
        }
        catch (URISyntaxException | MalformedURLException exception) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CoverLetterLink // instanceof handles nulls
                && value.equals(((CoverLetterLink) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
