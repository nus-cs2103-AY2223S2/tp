package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ContactContainsTagPredicate;
import seedu.address.model.tag.Tag;



/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private Set<Tag> emptyTagSetStub = new HashSet<>();


    private ContactContainsTagPredicate emptyTagsPredicate =
            new ContactContainsTagPredicate(emptyTagSetStub);

    private ContactContainsTagPredicate multipleTagsPredicate =
            new ContactContainsTagPredicate(prepareMultilpleTags());

    /**
     * Test for no contact found if tag keyword given is empty
     */
    @Test
    void executeFilter_noContactFound_emptyTags() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FilterCommand command = new FilterCommand(emptyTagsPredicate);
        expectedModel.updateFilteredPersonList(this.emptyTagsPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    /**
     * Test if there are multiple contacts found when given multiple tags
     */
    @Test
    void executeFilter_multipleContactsFound_multipleTags() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FilterCommand command = new FilterCommand(multipleTagsPredicate);
        expectedModel.updateFilteredPersonList(this.multipleTagsPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /**
     * Sets up a stub tag hashSet
     * @return a set of tags
     */
    private Set<Tag> prepareMultilpleTags() {
        Set<Tag> multipleTagsSet = new HashSet<>();
        Tag fakeTag1 = new Tag("family");
        Tag fakeTag2 = new Tag("friends");
        multipleTagsSet.add(fakeTag1);
        multipleTagsSet.add(fakeTag2);
        return multipleTagsSet;
    }
}
