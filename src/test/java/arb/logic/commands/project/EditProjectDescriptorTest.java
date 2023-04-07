package arb.logic.commands.project;

import static arb.logic.commands.CommandTestUtil.DESC_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.DESC_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_DEADLINE_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_PRICE_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_TAG_POTTERY;
import static arb.logic.commands.CommandTestUtil.VALID_TITLE_SKY_PAINTING;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.logic.commands.project.EditProjectCommand.EditProjectDescriptor;
import arb.testutil.EditProjectDescriptorBuilder;
import arb.testutil.PredicateUtil;

public class EditProjectDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditProjectDescriptor descriptorWithSameValues = new EditProjectDescriptor(DESC_OIL_PAINTING);
        assertTrue(DESC_OIL_PAINTING.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_OIL_PAINTING.equals(DESC_OIL_PAINTING));

        // null -> returns false
        assertFalse(DESC_OIL_PAINTING.equals(null));

        // different types -> returns false
        assertFalse(DESC_OIL_PAINTING.equals(5));

        // different values -> returns false
        assertFalse(DESC_OIL_PAINTING.equals(DESC_SKY_PAINTING));

        // different title -> returns false
        EditProjectDescriptor editedOilPainting = new EditProjectDescriptorBuilder(DESC_OIL_PAINTING)
                .withTitle(VALID_TITLE_SKY_PAINTING).build();
        assertFalse(DESC_OIL_PAINTING.equals(editedOilPainting));

        // different deadline -> returns false
        editedOilPainting = new EditProjectDescriptorBuilder(DESC_OIL_PAINTING)
                .withDeadline(VALID_DEADLINE_SKY_PAINTING).build();
        assertFalse(DESC_OIL_PAINTING.equals(editedOilPainting));

        // different price -> returns false
        editedOilPainting = new EditProjectDescriptorBuilder(DESC_OIL_PAINTING)
                .withPrice(VALID_PRICE_SKY_PAINTING).build();
        assertFalse(DESC_OIL_PAINTING.equals(editedOilPainting));

        // different tags -> returns false
        editedOilPainting = new EditProjectDescriptorBuilder(DESC_OIL_PAINTING)
                .withTags(VALID_TAG_POTTERY).build();
        assertFalse(DESC_OIL_PAINTING.equals(editedOilPainting));

        // different client name predicate -> returns false
        editedOilPainting = new EditProjectDescriptorBuilder(DESC_OIL_PAINTING)
                .withClientNamePredicate(PredicateUtil.getNameContainsKeywordsPredicate("not"))
                .build();
        assertFalse(DESC_OIL_PAINTING.equals(editedOilPainting));
    }
}
