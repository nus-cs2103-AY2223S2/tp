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
    private final String startDate;
    private final String premium;
    private final String frequency;

    // What is this for? Similar to Tag but what's the purpose?
    @JsonCreator
    public JsonAdaptedPolicy(String policyName) {
        this.policyName = policyName;
    }

    public JsonAdaptedPolicy(Policy source) {
        policyName = source.getPolicyName();// Need tell Yuze to make this field in Policy to be public, similar to tag
        startDate = source.getStartDate().format(Policy.DATE_FORMAT); //tell yuze make this
        premium = source.getPremium().toString();
        frequency = source.getFrequency().toString();
    }
    /*
     need tell yuze to make all the new classes to support constructor
     that takes in strings because i need convert from json to there
     */


    /* Need to see how this works ... based on tracing tag, this seems to get
    * called only when you update it? that is from object->Json*/
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
