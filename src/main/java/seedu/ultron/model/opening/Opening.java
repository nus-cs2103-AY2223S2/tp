package seedu.ultron.model.opening;

import static seedu.ultron.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents an Opening in the Openings list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Opening {

    // Identity fields
    private final Position position;
    private final Company company;
    private final Email email;

    // Data fields
    private final Status status;
    private final Remark remark;
    private final ArrayList<Date> dates = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Opening(Position position, Company company, Email email, Status status, Remark remark, List<Date> dates) {
        requireAllNonNull(position, company, email, status, dates);
        this.position = position;
        this.company = company;
        this.email = email;
        this.status = status;
        this.remark = remark;
        this.dates.addAll(dates);
        this.dates.sort(null);
    }

    public Position getPosition() {
        return position;
    }

    public Company getCompany() {
        return company;
    }

    public Email getEmail() {
        return email;
    }

    public Status getStatus() {
        return status;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable date set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Date> getDates() {
        return Collections.unmodifiableList(dates);
    }

    /**
     * Returns true if both openings have the same position and company.
     * This defines a weaker notion of equality between two openings.
     */
    public boolean isSameOpening(Opening otherOpening) {
        if (otherOpening == this) {
            return true;
        }

        return otherOpening != null
                && otherOpening.getPosition().equals(getPosition())
                && otherOpening.getCompany().equals(getCompany());
    }

    /**
     * Returns true if both openings have the same identity and data fields.
     * This defines a stronger notion of equality between two openings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Opening)) {
            return false;
        }

        Opening otherOpening = (Opening) other;
        return otherOpening.getPosition().equals(getPosition())
                && otherOpening.getCompany().equals(getCompany())
                && otherOpening.getEmail().equals(getEmail())
                && otherOpening.getStatus().equals(getStatus())
                && otherOpening.getRemark().equals(getRemark())
                && otherOpening.getDates().equals(getDates());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(position, company, email, status, remark, dates);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getPosition())
                .append(" Company: ")
                .append(getCompany())
                .append(" Email: ")
                .append(getEmail())
                .append(" Status: ")
                .append(getStatus());

        if (getRemark() != null) {
            builder.append(" Remark: ")
                    .append(getRemark());
        }

        List<Date> dates = getDates();
        if (!dates.isEmpty()) {
            builder.append("; Dates: ");
            dates.forEach(builder::append);
        }
        return builder.toString();
    }

}
