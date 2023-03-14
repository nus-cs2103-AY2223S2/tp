package seedu.address.model.client.policy;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an insurance policy that contains its name, start date, premium, frequency, and tags.
 * A policy object is immutable, and all fields must be present and not null.
 */
public class Policy {

    // fields
    private final PolicyName policyName;
    private final CustomDate startDate;
    private final Premium premium;
    private final Frequency frequency;

    /**
     * Every field must be present and not null.
     */
    public Policy(PolicyName policyName, CustomDate startDate, Premium premium, Frequency frequency) {
        requireAllNonNull(policyName, startDate, premium, frequency);
        this.policyName = policyName;
        this.startDate = startDate;
        this.premium = premium;
        this.frequency = frequency;
    }

    public PolicyName getPolicyName() {
        return policyName;
    }

    public CustomDate getStartDate() {
        return startDate;
    }

    public Premium getPremium() {
        return premium;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    /**
     * Returns true if both policies have the same policy name.
     * This defines a weaker notion of equality between two policies.
     */
    public boolean isSamePolicy(Policy otherPolicy) {
        if (otherPolicy == this) {
            return true;
        }

        return otherPolicy != null
                && otherPolicy.getPolicyName().equals(getPolicyName());
    }

    /**
     * Returns true if both policies have the same identity and data fields.
     * This defines a stronger notion of equality between two policies.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Policy)) {
            return false;
        }

        Policy otherPolicy = (Policy) other;
        return otherPolicy.getPolicyName().equals(getPolicyName())
                && otherPolicy.getStartDate().equals(getStartDate())
                && otherPolicy.getPremium().equals(getPremium())
                && otherPolicy.getFrequency().equals(getFrequency());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(policyName, startDate, premium, frequency);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getPolicyName())
                .append("; Start Date: ")
                .append(getStartDate())
                .append("; Premium: ")
                .append(getPremium())
                .append("; Frequency: ")
                .append(getFrequency())
                .append("; ");
        return builder.toString();
    }
}
