package seedu.wife.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.wife.logic.commands.foodcommands.AddCommandTest.ModelStub;
import static seedu.wife.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.wife.logic.commands.CommandResult;
import seedu.wife.logic.commands.exceptions.CommandException;
import seedu.wife.model.Wife;
import seedu.wife.model.ReadOnlyWife;
import seedu.wife.model.tag.Tag;
import seedu.wife.testutil.TagBuilder;

/**
 * A class to test the NewTagCommand.
 */
public class AddTagCommandTest {
    private static final String EXPECTED_ERROR_DUPLICATE = "The tag you try to add is already in the tag list.";
    private static final String EXPECTED_SUCCESS_MESSAGE = "Tag successfully added: %s";

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null));
    }

    @Test
    public void execute_tagAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTagAdded modelStub = new ModelStubAcceptingTagAdded();
        Tag validTag = new TagBuilder().build();

        CommandResult commandResult = new AddTagCommand(validTag).execute(modelStub);
        String expectedOutput = String.format(EXPECTED_SUCCESS_MESSAGE, validTag.getTagName());
        assertEquals(expectedOutput, commandResult.getFeedbackToUser());
        assertEquals(List.of(validTag), modelStub.tagsAdded);
    }

    @Test
    public void execute_duplicateItem_throwsCommandException() {
        Tag validTag = new TagBuilder().build();
        AddTagCommand newTagCommand = new AddTagCommand(validTag);
        ModelStub modelStub = new ModelStubWithTag(validTag);

        assertThrows(CommandException.class,
                EXPECTED_ERROR_DUPLICATE, () -> newTagCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Tag dairyTag = new TagBuilder().withTagName("dairy").build();
        Tag dairyTag_differentCase = new TagBuilder().withTagName("daIRy").build();
        Tag vegetableTag = new TagBuilder().withTagName("vegetable").build();

        AddTagCommand addFruitsTag = new AddTagCommand(dairyTag);
        AddTagCommand addFruitsTag_differentCase = new AddTagCommand(dairyTag_differentCase);
        AddTagCommand addVegetableTag = new AddTagCommand(vegetableTag);

        // same object -> returns true
        assertEquals(addFruitsTag, addFruitsTag);
        assertEquals(addFruitsTag, addFruitsTag_differentCase);

        // same values -> returns true
        AddTagCommand addFruitsTagCopy = new AddTagCommand(dairyTag);
        assertEquals(addFruitsTag, addFruitsTagCopy);

        // different types -> returns false
        assertNotEquals(1, addFruitsTag);

        // null -> returns false
        assertNotEquals(null, addFruitsTag);

        // different item -> returns false
        assertNotEquals(addFruitsTag, addVegetableTag);
    }

    /**
     * A Model stub that contains a single item.
     */
    private static class ModelStubWithTag extends ModelStub {
        private final Tag tag;

        ModelStubWithTag(Tag tag) {
            requireNonNull(tag);
            this.tag = tag;
        }

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return this.tag.equals(tag);
        }
    }

    /**
     * A Model stub that always accept the item being added.
     */
    private static class ModelStubAcceptingTagAdded extends ModelStub {
        final ArrayList<Tag> tagsAdded = new ArrayList<>();

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return tagsAdded.stream().anyMatch(tag::equals);
        }

        @Override
        public void addTag(Tag tag) {
            requireNonNull(tag);
            tagsAdded.add(tag);
        }

        @Override
        public ReadOnlyWife getWife() {
            return new Wife();
        }
    }
}