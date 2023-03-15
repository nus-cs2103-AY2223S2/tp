package seedu.address.model.mapping;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.shared.Id;

public class PersonTaskTest {
    private Id id1 = new Id();
    private Id id2 = new Id();
    private Id id3 = new Id();
    private PersonTask mapping1 = new PersonTask(id1, id2);
    private PersonTask mapping2 = new PersonTask(id1, id3);
    private PersonTask mapping3 = new PersonTask(id2, id3);

    @Test
    public void isSameMapping() {
        assertTrue(mapping1.isSame(mapping1));

        assertFalse(mapping1.isSame(mapping2));
        assertFalse(mapping2.isSame(mapping3));
    }
}
