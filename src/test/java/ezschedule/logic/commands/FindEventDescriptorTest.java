package ezschedule.logic.commands;

import static ezschedule.logic.commands.CommandTestUtil.FIND_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.FIND_DESC_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_NAME_B;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ezschedule.logic.commands.FindCommand.FindEventDescriptor;
import ezschedule.testutil.FindEventDescriptorBuilder;

public class FindEventDescriptorTest {

    @Test
    public void constructor_copyDescriptor_success() {
        FindEventDescriptor toCopyDescriptor = new FindEventDescriptor(FIND_DESC_B);
        FindEventDescriptor descriptor = new FindEventDescriptor(toCopyDescriptor);
        assertTrue(descriptor.getName().equals(FIND_DESC_B.getName()));
        assertTrue(descriptor.getDate().equals(FIND_DESC_B.getDate()));
    }

    @Test
    public void equals() {
        // same values -> returns true
        FindEventDescriptor descriptorWithSameValues = new FindEventDescriptor(FIND_DESC_A);
        assertTrue(FIND_DESC_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(FIND_DESC_A.equals(FIND_DESC_A));

        // null -> returns false
        assertFalse(FIND_DESC_A.equals(null));

        // different types -> returns false
        assertFalse(FIND_DESC_A.equals(5));

        // different values -> returns false
        assertFalse(FIND_DESC_A.equals(FIND_DESC_B));

        // different name -> returns false
        FindEventDescriptor editedA = new FindEventDescriptorBuilder(FIND_DESC_A).withName(VALID_NAME_B).build();
        assertFalse(FIND_DESC_A.equals(editedA));

        // different date -> returns false
        editedA = new FindEventDescriptorBuilder(FIND_DESC_A).withDate(VALID_DATE_B).build();
        assertFalse(FIND_DESC_A.equals(editedA));
    }
}
