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
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FieldsMatchRegexPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) for {@code MassOpCommand}.
 */
public class MassOpCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FieldsMatchRegexPredicate firstPredicate =
                new FieldsMatchRegexPredicate(Collections.singletonList("name"),
                        new ArrayList<>(),
                        Collections.singletonList("email"),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        Collections.singletonList("tag")
                );
        FieldsMatchRegexPredicate secondPredicate =
                new FieldsMatchRegexPredicate(new ArrayList<>(),
                        Collections.singletonList("phone"),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        Arrays.asList("tag1", "tag2")
                );
        Tag firstTag = new Tag("Test1");
        Tag secondTag = new Tag("Test2");
        boolean isDelete1 = true;
        boolean isDelete2 = false;
        MassOpCommand firstCommand = new MassOpCommand(firstPredicate, firstTag, isDelete1);
        MassOpCommand secondCommand = new MassOpCommand(secondPredicate, secondTag, isDelete2);

        // same object -> returns true
        assertEquals(firstCommand, firstCommand);

        // same values -> returns true
        MassOpCommand firstCommandCopy = new MassOpCommand(firstPredicate, firstTag, isDelete1);
        assertEquals(firstCommand, firstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, firstCommand);

        // null -> returns false
        assertNotEquals(null, firstCommand);

        // different person -> returns false
        assertNotEquals(firstCommand, secondCommand);
    }

    @Test
    public void execute_zeroRegexes_allFiltered() {
        Model modelCopy = model.stateDetachedCopy();
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                7);
        FieldsMatchRegexPredicate predicate =
                new FieldsMatchRegexPredicate(new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>());
        Tag testTag = new Tag("Test");
        MassOpCommand command = new MassOpCommand(predicate, testTag, false);
        expectedModel.updateFilteredPersonList(predicate);
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
                elleWithTag, fionaWithTag, georgeWithTag), model.getFilteredPersonList());
    }

}
