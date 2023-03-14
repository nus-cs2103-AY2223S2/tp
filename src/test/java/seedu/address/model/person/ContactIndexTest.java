package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.InvalidContactIndexException;

class ContactIndexTest {

    @Test
    public void create_invalidContactIndexNegative_throwsContactInvalidIndexException() {
        assertThrows(InvalidContactIndexException.class, () -> new ContactIndex(-1));
    }

    @Test
    public void create_invalidContactIndexVeryNegative_throwsContactInvalidIndexException() {
        assertThrows(InvalidContactIndexException.class, () -> new ContactIndex(Integer.MIN_VALUE));
    }

    @Test
    public void create_indexZero_doesNotThrowAnyException() {
        assertDoesNotThrow(() -> new ContactIndex(0));
    }

    @Test
    public void create_indexOne_doesNotThrowAnyException() {
        assertDoesNotThrow(() -> new ContactIndex(1));
    }

    @Test
    public void create_randomIndex_doesNotThrowAnyException() {
        assertDoesNotThrow(() -> new ContactIndex(new Random().nextInt(Integer.MAX_VALUE)));
    }

    @Test
    public void valueTest_validRandomContactIndex_sameNumber() {
        int randomValidIndex = new Random().nextInt(Integer.MAX_VALUE);
        assertEquals(new ContactIndex(randomValidIndex).getContactIndex(), randomValidIndex);
    }

    @Test
    public void compareHash_twoIdenticalContactIndexObject_true() {
        ContactIndex contactIndex = new ContactIndex(new Random().nextInt(Integer.MAX_VALUE));
        assertEquals(contactIndex.hashCode(), contactIndex.hashCode());
    }

    @Test
    public void compareHash_twoDifferentContactIndexObjectsSameValue_true() {
        ContactIndex contactIndex1 = new ContactIndex(1);
        ContactIndex contactIndex2 = new ContactIndex(1);
        assertEquals(contactIndex1.hashCode(), contactIndex2.hashCode());
    }

    @Test
    public void compareEquality_twoIdenticalContactIndexObjects_equal() {
        ContactIndex contactIndex = new ContactIndex(new Random().nextInt(Integer.MAX_VALUE));
        assertEquals(contactIndex, contactIndex);
    }

    @Test
    public void compareEquality_twoSameContactIndexDifferentObjects_equal() {
        int randomValue = new Random().nextInt(Integer.MAX_VALUE);
        ContactIndex contactIndex1 = new ContactIndex(randomValue);
        ContactIndex contactIndex2 = new ContactIndex(randomValue);
        assertEquals(contactIndex1, contactIndex2);
    }

    @Test
    public void compareEquality_twoDifferentContactIndexValues_notEqual() {
        ContactIndex contactIndex1 = new ContactIndex(1);
        ContactIndex contactIndex2 = new ContactIndex(2);
        assertNotEquals(contactIndex1, contactIndex2);
    }

    @Test
    public void compareValue_twoEqualValue_return0() {
        int randomValue = new Random().nextInt(Integer.MAX_VALUE);
        ContactIndex contactIndex1 = new ContactIndex(randomValue);
        ContactIndex contactIndex2 = new ContactIndex(randomValue);
        assertEquals(contactIndex1.compareTo(contactIndex2), 0);
    }

    @Test
    public void compareValue_twoEqualObjects_return0() {
        int randomValue = new Random().nextInt(Integer.MAX_VALUE);
        ContactIndex contactIndex1 = new ContactIndex(randomValue);
        assertEquals(contactIndex1.compareTo(contactIndex1), 0);
    }

    @Test
    public void compareValue_twoDifferentObjects_return0() {
        ContactIndex contactIndex1 = new ContactIndex(1);
        ContactIndex contactIndex2 = new ContactIndex(2);
        assertTrue(contactIndex2.compareTo(contactIndex1) > 0);
        assertTrue(contactIndex1.compareTo(contactIndex2) < 0);
    }

}
