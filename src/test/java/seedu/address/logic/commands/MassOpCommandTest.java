package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalElister;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Tag;

/**
 * Contains integration tests (interaction with the Model) for {@code MassOpCommand}.
 */
public class MassOpCommandTest {
    private final Model model = new ModelManager(getTypicalElister(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalElister(), new UserPrefs());

    @Test
    public void equals() {
        MassOpCommand firstCommand = new MassOpCommand("delete_tag Test1");
        MassOpCommand secondCommand = new MassOpCommand("tag Test1");
        MassOpCommand thirdCommand = new MassOpCommand("tag Test2");

        // same object -> returns true
        assertEquals(firstCommand, firstCommand);
        assertEquals(secondCommand, secondCommand);
        assertEquals(thirdCommand, thirdCommand);

        // same values -> returns true
        MassOpCommand firstCommandCopy = new MassOpCommand("delete_tag Test1");
        assertEquals(firstCommand, firstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, firstCommand);

        // null -> returns false
        assertNotEquals(null, firstCommand);

        // different subcommand type -> returns false
        assertNotEquals(firstCommand, secondCommand);

        // different subcommand arguments -> returns false
        assertNotEquals(secondCommand, thirdCommand);
    }

    @Test
    public void execute_zeroRegexes_allFiltered() {
        Model modelCopy = model.stateDetachedCopy();
        String expectedMessage = String.format(MassOpCommand.MESSAGE_SUCCESS, 7, 7);
        Tag testTag = new Tag("Test");
        for (Person p: expectedModel.getFilteredPersonList()) {
            p.addTag(testTag);
        }
        MassOpCommand command = new MassOpCommand("tag Test");
        assertCommandSuccess(command, modelCopy, expectedMessage, expectedModel);
        Person aliceWithTag = ALICE.deepCopy();
        aliceWithTag.addTag(testTag);
        Person bensonWithTag = BENSON.deepCopy();
        bensonWithTag.addTag(testTag);
        Person carlWithTag = CARL.deepCopy();
        carlWithTag.addTag(testTag);
        Person danielWithTag = DANIEL.deepCopy();
        danielWithTag.addTag(testTag);
        Person elleWithTag = ELLE.deepCopy();
        elleWithTag.addTag(testTag);
        Person fionaWithTag = FIONA.deepCopy();
        fionaWithTag.addTag(testTag);
        Person georgeWithTag = GEORGE.deepCopy();
        georgeWithTag.addTag(testTag);
        assertEquals(Arrays.asList(aliceWithTag, bensonWithTag, carlWithTag, danielWithTag,
                elleWithTag, fionaWithTag, georgeWithTag), modelCopy.getFilteredPersonList());
    }

}
