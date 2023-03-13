package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.policy.CustomDate;
import seedu.address.model.client.policy.Frequency;
import seedu.address.model.client.policy.Policy;
import seedu.address.model.client.policy.PolicyName;
import seedu.address.model.client.policy.Premium;

/**
 * Jackson-friendly version of {@Link Policy}
 */
public class JsonAdaptedPolicy {
    private final String name;
    private final String startDate;
    private final String premium;
    private final String frequency;

    /**
     * Constructs a {@code JsonAdaptedPolicy} with the given policy details.
     * @param name The name of the policy.
     * @param startDate The starting date of the policy.
     * @param premium The value of the policy.
     * @param frequency The duration of the policy.
     */
    @JsonCreator
    public JsonAdaptedPolicy(@JsonProperty("name") String name, @JsonProperty("startDate") String startDate,
                             @JsonProperty("premium") String premium, @JsonProperty("frequency") String frequency) {
        this.name = name;
        this.startDate = startDate;
        this.premium = premium;
        this.frequency = frequency;
    }

    /**
     * Coverts a given {@code Policy} into this class for Jackson use.
     * @param source The policy that we are interested in.
     */
    public JsonAdaptedPolicy(Policy source) {
        name = source.getPolicyName().toString(); // does this break Law of Demeter?
        startDate = source.getStartDate().toString();
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
        policy.append(name);
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

    public Policy toModelType() throws IllegalValueException {
        if (!PolicyName.isValidName(name)) {
            throw new IllegalValueException(PolicyName.MESSAGE_CONSTRAINTS);
        }
        if (!CustomDate.isValidDate(startDate)) {
            throw new IllegalValueException(CustomDate.MESSAGE_CONSTRAINTS);
        }
        if (!Premium.isValidPremium(premium)) {
            throw new IllegalValueException(Premium.MESSAGE_CONSTRAINTS);
        }
        if (!Frequency.isValidFrequency(frequency)) {
            throw new IllegalValueException(Frequency.MESSAGE_CONSTRAINTS);
        }
        return new Policy(new PolicyName(name), new CustomDate(startDate),
                new Premium(premium), new Frequency(frequency));
    }

}
