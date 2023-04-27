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
import seedu.wife.model.ReadOnlyWife;
import seedu.wife.model.Wife;
import seedu.wife.model.tag.Tag;
import seedu.wife.testutil.TagBuilder;

/**
 * A class to test the CreateTagCommand.
 */
public class CreateTagCommandTest {
    private static final String EXPECTED_ERROR_DUPLICATE = "The tag(s) you are trying to create has been "
            + "created before.";
    private static final String EXPECTED_SUCCESS_MESSAGE = "Tag(s) successfully created:\n%s";

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateTagCommand((Tag) null));
    }

    @Test
    public void execute_tagAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTagAdded modelStub = new ModelStubAcceptingTagAdded();
        Tag validTag = new TagBuilder().build();

        CommandResult commandResult = new CreateTagCommand(validTag).execute(modelStub);
        String expectedOutput = String.format(EXPECTED_SUCCESS_MESSAGE, validTag.getTagName());
        assertEquals(expectedOutput, commandResult.getFeedbackToUser());
        assertEquals(List.of(validTag), modelStub.tagsAdded);
    }

    @Test
    public void execute_duplicateItem_throwsCommandException() {
        Tag validTag = new TagBuilder().build();
        CreateTagCommand newTagCommand = new CreateTagCommand(validTag);
        ModelStub modelStub = new ModelStubWithTag(validTag);

        assertThrows(CommandException.class,
                EXPECTED_ERROR_DUPLICATE, () -> newTagCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Tag dairyTag = new TagBuilder().withTagName("dairy").build();
        Tag dairyTagDifferentCase = new TagBuilder().withTagName("daIRy").build();
        Tag vegetableTag = new TagBuilder().withTagName("vegetable").build();

        CreateTagCommand addDairyTag = new CreateTagCommand(dairyTag);
        CreateTagCommand addDairyTagDifferentCase = new CreateTagCommand(dairyTagDifferentCase);
        CreateTagCommand addVegetableTag = new CreateTagCommand(vegetableTag);

        // same tag name values -> return true
        assertEquals(addDairyTag, addDairyTag);
        assertEquals(addDairyTag, addDairyTagDifferentCase);

        // same tag name but different command object -> returns true
        CreateTagCommand addDairyTagCopy = new CreateTagCommand(dairyTag);
        assertEquals(addDairyTag, addDairyTagCopy);

        // different types -> returns false
        assertNotEquals("dairy", addDairyTag);

        // null -> returns false
        assertNotEquals(null, addDairyTag);

        // different item -> returns false
        assertNotEquals(addDairyTag, addVegetableTag);
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
        public void createTag(Tag tag) {
            requireNonNull(tag);
            tagsAdded.add(tag);
        }

        @Override
        public ReadOnlyWife getWife() {
            return new Wife();
        }
    }
}
