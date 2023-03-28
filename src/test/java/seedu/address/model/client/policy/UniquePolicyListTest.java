package seedu.address.model.client.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.exceptions.DuplicatePolicyException;
import seedu.address.model.client.exceptions.PolicyNotFoundException;

class UniquePolicyListTest {

    private PolicyName name = new PolicyName("Health Insurance");
    private PolicyName name1 = new PolicyName("Life Insurance");
    private CustomDate date = new CustomDate("01.02.2023");
    private Premium premium = new Premium("200");
    private Frequency frequency = new Frequency("weekly");
    private Policy policy = new Policy(name, date, premium, frequency);
    private Policy policy1 = new Policy(name1, date, premium, frequency);
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
    void addDuplicate() {
        list.add(policy);
        assertThrows(DuplicatePolicyException.class, () -> list.add(policy));
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
    void setPolicyNotFoundException() {
        assertThrows(PolicyNotFoundException.class, () -> list.setPolicy(policy1, policy1));
    }

    @Test
    void remove() {
        list.add(policy);
        list.remove(policy);
        assertFalse(list.contains(policy));
    }

    @Test
    void removeNotFound() {
        assertThrows(PolicyNotFoundException.class, () -> list.remove(policy));
    }

    @Test
    void testUnique() {
        List<Policy> list1 = new ArrayList<>();
        list1.add(policy);
        list1.add(policy1);
        assertTrue(UniquePolicyList.policiesAreUnique(list1));
        list1.add(policy);
        assertFalse(UniquePolicyList.policiesAreUnique(list1));
    }

    @Test
    void equalsTest() {
        assertTrue(list.equals(list));
        assertFalse(list.equals(null));
        assertFalse(list.equals(4));
    }

    @Test
    void testCloneAddPolicy() {
        UniquePolicyList listAfterCloneOne = list.clone();
        assertFalse(list == listAfterCloneOne);
        assertTrue(list.equals(listAfterCloneOne));

        list.add(policy);
        assertFalse(list.equals(listAfterCloneOne));

        UniquePolicyList listAfterCloneTwo = list.clone();
        assertFalse(list == listAfterCloneTwo);
        assertTrue(list.equals(listAfterCloneTwo));

        listAfterCloneTwo.add(policy1);
        assertFalse(list.equals(listAfterCloneTwo));
    }

    @Test
    void testCloneDeletePolicy() {
        list.add(policy);
        list.add(policy1);
        UniquePolicyList listAfterCloneOne = list.clone();
        assertFalse(list == listAfterCloneOne);
        assertTrue(list.equals(listAfterCloneOne));

        list.remove(policy);
        assertFalse(list.equals(listAfterCloneOne));

        UniquePolicyList listAfterCloneTwo = list.clone();
        assertFalse(list == listAfterCloneTwo);
        assertTrue(list.equals(listAfterCloneTwo));

        listAfterCloneTwo.remove(policy1);
        assertFalse(list.equals(listAfterCloneTwo));
    }

}
