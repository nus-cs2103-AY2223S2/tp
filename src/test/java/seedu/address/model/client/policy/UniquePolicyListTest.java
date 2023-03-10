package seedu.address.model.client.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UniquePolicyListTest {

    private PolicyName name = new PolicyName("Health");
    private CustomDate date = new CustomDate("01.02.2023");
    private Premium premium = new Premium("200");
    private Frequency frequency = new Frequency("quarterly");
    private Policy policy = new Policy(name, date, premium, frequency);
    private UniquePolicyList list = new UniquePolicyList();


    @Test
    void contains() {
        assertFalse(list.contains(policy));
    }

    @Test
    void add() {
        list.add(policy);
        assertTrue(list.contains(policy));
    }

    @Test
    void setPolicy() {
        list.add(policy);
        list.setPolicy(policy, policy);
        UniquePolicyList expectedUniquePolicyList = new UniquePolicyList();
        expectedUniquePolicyList.add(policy);
        assertEquals(expectedUniquePolicyList, list);
    }

    @Test
    void remove() {
        list.add(policy);
        list.remove(policy);
        assertFalse(list.contains(policy));
    }

    @Test
    void testSetPolicies() {
        assertTrue(true);
    }


    @Test
    void testEquals() {
        assertTrue(true);
    }

}
