package seedu.address.model.application;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Application in the internship book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 * Comment to let merge operation detect file. To be deleted subsequently.
 */
public class Application {
    private final Role role;
    private final CompanyName companyName;
    private final CompanyEmail companyEmail;
    private final Status status;

    /**
     * Every field must be present and not null.
     */
    public Application(Role role, CompanyName companyName, CompanyEmail companyEmail, Status status) {
        requireAllNonNull(role, companyName, companyEmail, status);
        this.role = role;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.status = status;
    }

    public Role getRole() {
        return this.role;
    }

    public CompanyName getCompanyName() {
        return this.companyName;
    }

    public CompanyEmail getCompanyEmail() {
        return this.companyEmail;
    }

    public Status getStatus() {
        return this.status;
    }

    /**
     * Returns true if both applications have the same role, company name and company email fields.
     * Equality of status is not checked since both applications are considered to be equal regardless
     * of whether they have the same status or not.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Application)) {
            return false;
        }

        Application otherApplication = (Application) other;
        return otherApplication.getRole().equals(getRole())
                && otherApplication.getCompanyName().equals(getCompanyName())
                && otherApplication.getCompanyEmail().equals(getCompanyEmail());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(role, companyName, companyEmail, status);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getRole())
                .append("; Company: ")
                .append(getCompanyName())
                .append("; Company's email: ")
                .append(getCompanyEmail())
                .append("; Application status: ")
                .append(getStatus());

        return builder.toString();
    }
}
