package seedu.address.model.client.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PolicyTest {

    private PolicyName name = new PolicyName("Health");
    private CustomDate date = new CustomDate("01.02.2023");
    private Premium premium = new Premium("200");
    private Frequency frequency = new Frequency("weekly");
    private Policy policy = new Policy(name, date, premium, frequency);

    @Test
    void getPolicyName() {
        PolicyName name = policy.getPolicyName();
        assertEquals(name.toString(), "Health");
    }

    @Test
    void getStartDate() {
        CustomDate date = policy.getStartDate();
        assertNotEquals(date.toString(), "01/02/2023");
    }

    @Test
    void getPremium() {
        Premium premium = policy.getPremium();
        assertEquals(premium.toString(), "200");
    }

    @Test
    void getFrequency() {
        Frequency frequency = policy.getFrequency();
        assertEquals(frequency.toString(), "weekly");
    }

    @Test
    void isSamePolicy() {
        PolicyName name1 = new PolicyName("Health");
        PolicyName name2 = new PolicyName("life");
        CustomDate date1 = new CustomDate("01.02.2023");
        Premium premium1 = new Premium("200");
        Premium premium2 = new Premium("150");
        Frequency frequency1 = new Frequency("weekly");
        Frequency frequency2 = new Frequency("yearly");
        Policy policy1 = new Policy(name1, date1, premium1, frequency1);
        Policy policy2 = new Policy(name1, date1, premium2, frequency2);
        Policy policy3 = new Policy(name1, date1, premium2, frequency2);
        Policy policy4 = new Policy(name2, date1, premium1, frequency1);

        assertTrue(policy.isSamePolicy(policy1));
        assertFalse(policy1.isSamePolicy(policy4));
        assertTrue(policy.equals(policy1));
        assertFalse(policy.equals(policy3));
        assertTrue(policy2.equals(policy3));

    }

    @Test
    void isSamePolicyString() {
        PolicyName name1 = new PolicyName("Health");
        CustomDate date1 = new CustomDate("01.02.2023");
        Premium premium1 = new Premium("200");
        Frequency frequency1 = new Frequency("weekly");
        Policy policy1 = new Policy(name1, date1, premium1, frequency1);

        assertEquals(policy.toString(), policy1.toString());
    }
}

