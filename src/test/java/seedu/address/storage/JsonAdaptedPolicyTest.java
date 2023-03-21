package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPolicy.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPolicies.HEALTH;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.policy.CustomDate;
import seedu.address.model.client.policy.Frequency;
import seedu.address.model.client.policy.PolicyName;
import seedu.address.model.client.policy.Premium;

class JsonAdaptedPolicyTest {

    private static final String INVALID_POLICY_NAME = "#HealthInsuance";
    private static final String INVALID_DATE = "10-10-2023";
    private static final String INVALID_FREQUENCY = "Biweekly";
    private static final String INVALID_PREMIUM = "Hello";

    private static final String VALID_POLICY_NAME = HEALTH.getPolicyName().toString();
    private static final String VALID_DATE = HEALTH.getStartDate().toString();
    private static final String VALID_PREMIUM = HEALTH.getPremium().toString();
    private static final String VALID_FREQUENCY = HEALTH.getFrequency().toString();

    @Test
    public void toModelType_validPolicy_returnsPolicy() throws IllegalValueException {
        JsonAdaptedPolicy policy = new JsonAdaptedPolicy(HEALTH);
        assertEquals(HEALTH, policy.toModelType());
    }
    @Test
    public void toModelType_invalidPolicyName_throwsIllegalValueException() {
        JsonAdaptedPolicy policy =
                new JsonAdaptedPolicy(INVALID_POLICY_NAME, VALID_DATE, VALID_PREMIUM,
                        VALID_FREQUENCY);
        String expectedMessage = PolicyName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

    @Test
    public void toModelType_nullPolicyName_throwsIllegalValueException() {
        JsonAdaptedPolicy policy =
                new JsonAdaptedPolicy(null, VALID_DATE, VALID_PREMIUM,
                        VALID_FREQUENCY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PolicyName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedPolicy policy =
                new JsonAdaptedPolicy(VALID_POLICY_NAME, INVALID_DATE, VALID_PREMIUM,
                        VALID_FREQUENCY);
        String expectedMessage = CustomDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedPolicy policy =
                new JsonAdaptedPolicy(VALID_POLICY_NAME, null, VALID_PREMIUM, VALID_FREQUENCY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CustomDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

    @Test
    public void toModelType_invalidPremium_throwsIllegalValueException() {
        JsonAdaptedPolicy policy =
                new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_DATE, INVALID_PREMIUM, VALID_FREQUENCY);
        String expectedMessage = Premium.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

    @Test
    public void toModelType_nullPremium_throwsIllegalValueException() {
        JsonAdaptedPolicy policy =
                new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_DATE, null, VALID_FREQUENCY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Premium.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

    @Test
    public void toModelType_invalidFrequency_throwsIllegalValueException() {
        JsonAdaptedPolicy policy =
                new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_DATE, VALID_PREMIUM, INVALID_FREQUENCY);
        String expectedMessage = Frequency.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

    @Test
    public void toModelType_nullFrequency_throwsIllegalValueException() {
        JsonAdaptedPolicy policy =
               new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_DATE, VALID_PREMIUM, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Frequency.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }
}
