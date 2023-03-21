package seedu.address.testutil;

import seedu.address.model.client.policy.CustomDate;
import seedu.address.model.client.policy.Frequency;
import seedu.address.model.client.policy.Policy;
import seedu.address.model.client.policy.PolicyName;
import seedu.address.model.client.policy.Premium;

/**
 * A utility class to help with building Policy objects.
 */
public class PolicyBuilder {

    public static final String DEFAULT_POLICY_NAME = "Health";
    public static final String DEFAULT_DATE = "01.01.2023";
    public static final String DEFAULT_PREMIUM = "80.69";
    public static final String DEFAULT_FREQUENCY = "monthly";

    private PolicyName name;
    private CustomDate date;
    private Premium premium;
    private Frequency frequency;

    /**
     * Creates a {@code PolicyBuilder} with the default details.
     */
    public PolicyBuilder() {
        name = new PolicyName(DEFAULT_POLICY_NAME);
        date = new CustomDate(DEFAULT_DATE);
        premium = new Premium(DEFAULT_PREMIUM);
        frequency = new Frequency(DEFAULT_FREQUENCY);
    }

    /**
     * Initializes the PolicyBuilder with the data of {@code policyToCopy}.
     */
    public PolicyBuilder(Policy policyToCopy) {
        name = policyToCopy.getPolicyName();
        date = policyToCopy.getStartDate();
        premium = policyToCopy.getPremium();
        frequency = policyToCopy.getFrequency();
    }

    /**
     * Sets the {@code Name} of the {@code Policy} that we are building.
     */
    public PolicyBuilder withPolicyName(String name) {
        this.name = new PolicyName(name);
        return this;
    }


    /**
     * Sets the {@code CustomDate} of the {@code Policy} that we are building.
     */
    public PolicyBuilder withStartDate(String date) {
        this.date = new CustomDate(date);
        return this;
    }

    /**
     * Sets the {@code Premium} of the {@code Policy} that we are building.
     */
    public PolicyBuilder withPremium(String premium) {
        this.premium = new Premium(premium);
        return this;
    }

    /**
     * Sets the {@code Frequency} of the {@code Policy} that we are building.
     */
    public PolicyBuilder withFrequency(String frequency) {
        this.frequency = new Frequency(frequency);
        return this;
    }

    public Policy build() {
        return new Policy(name, date, premium, frequency);
    }

}
