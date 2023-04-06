package seedu.address.model.documents;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import seedu.address.model.application.InternshipApplicationAttribute;

/**
 * Represents a link to a resume in the internship application.
 * Guarantees: immutable; is valid as declared in {@link #isValidResumeLink(String)}
 */
public class ResumeLink extends InternshipApplicationAttribute {

    public static final String MESSAGE_CONSTRAINTS = "Link to resume should be in the form http://domain/path "
            + "or https://domain/path\n"
            + "The domain part can be an IP address, otherwise it is a domain name that must:\n"
            + "    - cannot be localhost\n"
            + "    - end with a domain label of 2 to 7 characters long\n"
            + "    - have each domain label start and end with alphanumeric characters\n"
            + "    - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.";
    private static final String DOMAIN_REGEX = "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String IP_ADDRESS_REGEX =
            "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
                    + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    public final String value;

    /**
     * Constructs an {@code Resume}.
     *
     * @param resumeLink A valid link to a resume.
     */
    public ResumeLink(String resumeLink) {
        requireNonNull(resumeLink);
        checkArgument(isValidResumeLink(resumeLink), MESSAGE_CONSTRAINTS);
        value = resumeLink;
    }

    /**
     * Returns if a given string is a valid link to a resume.
     */
    public static boolean isValidResumeLink(String test) {
        requireNonNull(test);
        try {
            URI uri = new URL(test).toURI();
            String domain = uri.getHost();
            if (isNull(domain)) {
                return false;
            }
            return test.matches("^(https?)://.*$") && (domain.matches(DOMAIN_REGEX)
                    || domain.matches(IP_ADDRESS_REGEX));
        } catch (URISyntaxException | MalformedURLException exception) {
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
                || (other instanceof ResumeLink // instanceof handles nulls
                && value.equals(((ResumeLink) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
