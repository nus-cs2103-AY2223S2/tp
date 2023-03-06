package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@Link Policy}
 */
public class JsonAdaptedPolicy {
    private final String policyName;
    private final LocalDate startDate;
    private final Premium premium;
    private final Frequency frequency;
    private final Set<Tag> tags;

    // What is this for? Similar to Tag but what's the purpose?
    @JsonCreator
    public JsonAdaptedPolicy(String policyName) {
        this.policyName = policyName;
    }

    public JsonAdaptedPolicy(Policy source) {
        policyName = source.getPolicyName();// Need tell Yuze to make this field in Policy to be public, similar to tag
        startDate = source.getStartDate();
        premium = source.getPremium();
        frequency = source.getFrequency();
        tags = source.getTags();
    }

    @JsonValue
    public String getPolicy() {
        StringBuilder policy = new StringBuilder("[");
        policy.append(policyName);
        policy.append(", ");
        policy.append(startDate);
        policy.append(", ");
        policy.append(premium);
        policy.append(", ");
        policy.append(frequency);
        policy.append();
        /* how to better add tags here ?" */
        policy.append("]");
        return policy.toString();
    }

    public Policy toModelPolicy() throws IllegalValueException {
        /* Just need to check constraints for all of the values... */
        if (!Policy.isValidPolicyName(policyName)) {
            throw new IllegalValueException(Policy.MESSAGE_CONSTRAINTS); // Does policy have message constraints?
        }
        return new Policy(policyName);
    }

}
