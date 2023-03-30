package seedu.connectus.logic.commands;

import static seedu.connectus.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.connectus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.connectus.logic.commands.CommandUtil.convertSetToList;
import static seedu.connectus.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.connectus.testutil.TypicalPersons.getTypicalConnectUs;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.connectus.commons.core.Messages;
import seedu.connectus.commons.core.index.Index;
import seedu.connectus.model.Model;
import seedu.connectus.model.ModelManager;
import seedu.connectus.model.UserPrefs;
import seedu.connectus.model.person.Person;
import seedu.connectus.model.tag.Module;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTagFromPersonCommand}.
 */
public class DeleteTagFromPersonCommandTest {

    private Model model = new ModelManager(getTypicalConnectUs(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToFiddle = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Module> modifiedModules = convertSetToList(personToFiddle.getModules());
        modifiedModules.remove(0);
        Person expectedPerson = new Person(personToFiddle, personToFiddle.getRemarks(), new HashSet<>(modifiedModules),
            personToFiddle.getCcas(), personToFiddle.getCcaPositions());
        DeleteTagFromPersonCommand command = new DeleteTagFromPersonCommand(INDEX_FIRST_PERSON, Index.fromOneBased(1),
            null, null, null
        );

        String expectedMessage = String.format(DeleteTagFromPersonCommand.MESSAGE_DELETE_TAG_SUCCESS, expectedPerson);

        ModelManager expectedModel = new ModelManager(model.getConnectUs(), new UserPrefs());
        expectedModel.setPerson(personToFiddle, expectedPerson);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteTagFromPersonCommand cmd = new DeleteTagFromPersonCommand(outOfBoundIndex, null, null, null, null
        );

        assertCommandFailure(cmd, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
