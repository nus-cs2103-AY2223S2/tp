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
 * Jackson-friendly version of {@Link Policy}
 */
public class JsonAdaptedPolicy {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Policy's %s field is missing!";
    private final String policyName;
    private final String startDate;
    private final String premium;
    private final String frequency;

    /**
     * Constructs a {@code JsonAdaptedPolicy} with the given policy details.
     * @param policyName The name of the policy.
     * @param startDate The starting date of the policy.
     * @param premium The value of the policy.
     * @param frequency The duration of the policy.
     */
    @JsonCreator
    public JsonAdaptedPolicy(@JsonProperty("policyName") String policyName, @JsonProperty("startDate") String startDate,
                             @JsonProperty("premium") String premium, @JsonProperty("frequency") String frequency) {
        this.policyName = policyName;
        this.startDate = startDate;
        this.premium = premium;
        this.frequency = frequency;
    }

    /**
     * Coverts a given {@code Policy} into this class for Jackson use.
     * @param source The policy that we are interested in.
     */
    public JsonAdaptedPolicy(Policy source) {
        policyName = source.getPolicyName().toString(); // does this break Law of Demeter?
        startDate = source.getStartDate().toString();
        premium = source.getPremium().toString();
        frequency = source.getFrequency().toString();
    }
    /*
     need tell yuze to make all the new classes to support constructor
     that takes in strings because i need convert from json to there
     */


    /* Need to see how this works ... based on tracing tag, this seems to get
    * called only when you update it? that is from object->Json
    */
    /*
    @JsonValue
    public String getPolicy() {
        String policy = policyName + ", "
        StringBuilder policy = new StringBuilder("[");
        policy.append(policyName);
        policy.append(", ");
        policy.append(startDate);
        policy.append(", ");
        policy.append(premium);
        policy.append(", ");
        policy.append(frequency);
        /* how to better add tags here ?"
        policy.append("]");
        return policy.toString();
    } */

    /**
     * Converts this Jackson-friendly adapted policy object into the model's {@code Policy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted policy.
     */
    public Policy toModelType() throws IllegalValueException {
        if (policyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PolicyName.class.getSimpleName()));
        }
        if (!PolicyName.isValidName(policyName)) {
            throw new IllegalValueException(PolicyName.MESSAGE_CONSTRAINTS);
        }
        final PolicyName modelPolicyName = new PolicyName(policyName);

        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CustomDate.class.getSimpleName()));
        }

        if (!CustomDate.isValidDate(startDate)) {
            throw new IllegalValueException(CustomDate.MESSAGE_CONSTRAINTS);
        }
        final CustomDate modelStartDate = new CustomDate(startDate);

        if (premium == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Premium.class.getSimpleName()));
        }
        if (!Premium.isValidPremium(premium)) {
            throw new IllegalValueException(Premium.MESSAGE_CONSTRAINTS);
        }
        final Premium modelPremium = new Premium(premium);

        if (frequency == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Frequency.class.getSimpleName()));
        }
        if (!Frequency.isValidFrequency(frequency)) {
            throw new IllegalValueException(Frequency.MESSAGE_CONSTRAINTS);
        }
        final Frequency modelFrequency = new Frequency(frequency);

        return new Policy(modelPolicyName, modelStartDate, modelPremium, modelFrequency);
    }

}
