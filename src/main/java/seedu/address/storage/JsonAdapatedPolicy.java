package seedu.address.storage;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.policy.CustomDate;
import seedu.address.model.client.policy.Frequency;
import seedu.address.model.client.policy.Policy;
import seedu.address.model.client.policy.PolicyName;
import seedu.address.model.client.policy.Premium;

/**
 * Jackson-friendly version of {@link seedu.address.model.client.policy.Policy}.
 */
class JsonAdapatedPolicy {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Policy's %s field is missing!";


    private final String policyName;
    private final String date;
    private final String premium;
    private final String frequency;

    /**
     * Constructs a {@code JsonAdaptedPolicy} with the given policy details.
     */
    @JsonCreator
    public JsonAdapatedPolicy(@JsonProperty("policyName") String policyName, @JsonProperty("date") String date,
                              @JsonProperty("premium") String premium, @JsonProperty("frequency") String frequency) {
        this.policyName = policyName;
        this.date = date;
        this.premium = premium;
        this.frequency = frequency;
    }
    /**
     * Converts a given {@code Policy} into this class for Jackson use.
     */
    public JsonAdapatedPolicy(Policy source) {
        policyName = source.getPolicyName().policyName;
        date = source.getStartDate().toString();
        premium = source.getPremium().value;
        frequency = source.getFrequency().toString();
    }
    public Policy toModelType() throws IllegalValueException {
        if (policyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PolicyName.class.getSimpleName()));
        }
        if (!PolicyName.isValidName(policyName)) {
            throw new IllegalValueException(PolicyName.MESSAGE_CONSTRAINTS);
        }
        final PolicyName modelPolicyName = new PolicyName(this.policyName);
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CustomDate.class.getSimpleName()));
        }
        if (!CustomDate.isValidDate(date)) {
            throw new IllegalValueException(CustomDate.MESSAGE_CONSTRAINTS);
        }
        final CustomDate modelDate = new CustomDate(this.date);
        if (premium == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Premium.class.getSimpleName()));
        }
        if (!Premium.isValidPremium(premium)) {
            throw new IllegalValueException(Premium.MESSAGE_CONSTRAINTS);
        }
        final Premium modelPremium = new Premium(this.premium);
        if (frequency == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Frequency.class.getSimpleName()));
        }
        if (!Frequency.isValidFrequency(frequency)) {
            throw new IllegalValueException(Frequency.MESSAGE_CONSTRAINTS);
        }
        final Frequency modelFrequency = new Frequency(this.frequency);
        return new Policy(modelPolicyName, modelDate, modelPremium, modelFrequency);
    }
}
