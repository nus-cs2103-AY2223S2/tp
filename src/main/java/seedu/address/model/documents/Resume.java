package seedu.address.model.documents;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a link to a resume in the internship application.
 * Guarantees: immutable; is valid as declared in {@link #isValidResumeLink(String)}
 */
public class Resume {

    public static final String MESSAGE_CONSTRAINTS = "Link to resume should be in the form http://domain/path "
            + "or https://domain/path";

    public final String value;

    /**
     * Constructs an {@code Resume}.
     *
     * @param resumeLink A valid link to a resume.
     */
    public Resume(String resumeLink) {
        requireNonNull(resumeLink);
        checkArgument(isValidResumeLink(resumeLink), MESSAGE_CONSTRAINTS);
        value = resumeLink;
    }

    /**
     * Returns if a given string is a valid link to a resume.
     */
    public static boolean isValidResumeLink(String test) {
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
                || (other instanceof Resume // instanceof handles nulls
                && value.equals(((Resume) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
