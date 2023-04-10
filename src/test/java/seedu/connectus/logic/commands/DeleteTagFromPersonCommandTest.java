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
import seedu.connectus.model.tag.Cca;
import seedu.connectus.model.tag.Major;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Remark;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTagFromPersonCommand}.
 */
public class DeleteTagFromPersonCommandTest {

    private Model model = new ModelManager(getTypicalConnectUs(), new UserPrefs());

    @Test
    public void execute_validModuleIndexUnfilteredList_success() {
        Person personToFiddle = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Module> modifiedModules = convertSetToList(personToFiddle.getModules());
        modifiedModules.remove(0);
        Person expectedPerson = new Person(personToFiddle, personToFiddle.getRemarks(), new HashSet<>(modifiedModules),
            personToFiddle.getCcas(), personToFiddle.getMajors());
        DeleteTagFromPersonCommand command = new DeleteTagFromPersonCommand(INDEX_FIRST_PERSON, Index.fromOneBased(1),
            null, null, null
        );

        String expectedMessage = String.format(DeleteTagFromPersonCommand.MESSAGE_DELETE_TAG_SUCCESS, expectedPerson);

        ModelManager expectedModel = new ModelManager(model.getConnectUs(), new UserPrefs());
        expectedModel.setPerson(personToFiddle, expectedPerson);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMajorIndexUnfilteredList_success() {
        Person personToFiddle = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Major> modifiedMajors = convertSetToList(personToFiddle.getMajors());
        modifiedMajors.remove(0);
        Person expectedPerson = new Person(personToFiddle, personToFiddle.getRemarks(), personToFiddle.getModules(),
                personToFiddle.getCcas(), new HashSet<>(modifiedMajors));
        DeleteTagFromPersonCommand command = new DeleteTagFromPersonCommand(INDEX_FIRST_PERSON, null,
                null, Index.fromOneBased(1), null
        );

        String expectedMessage = String.format(DeleteTagFromPersonCommand.MESSAGE_DELETE_TAG_SUCCESS, expectedPerson);

        ModelManager expectedModel = new ModelManager(model.getConnectUs(), new UserPrefs());
        expectedModel.setPerson(personToFiddle, expectedPerson);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validRemarkIndexUnfilteredList_success() {
        Person personToFiddle = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Remark> modifiedRemarks = convertSetToList(personToFiddle.getRemarks());
        modifiedRemarks.remove(0);
        Person expectedPerson = new Person(personToFiddle, new HashSet<>(modifiedRemarks), personToFiddle.getModules(),
                personToFiddle.getCcas(), personToFiddle.getMajors());
        DeleteTagFromPersonCommand command = new DeleteTagFromPersonCommand(INDEX_FIRST_PERSON, null,
                null, null, Index.fromOneBased(1));

        String expectedMessage = String.format(DeleteTagFromPersonCommand.MESSAGE_DELETE_TAG_SUCCESS, expectedPerson);

        ModelManager expectedModel = new ModelManager(model.getConnectUs(), new UserPrefs());
        expectedModel.setPerson(personToFiddle, expectedPerson);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validCcaIndexUnfilteredList_success() {
        Person personToFiddle = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Cca> modifiedCcas = convertSetToList(personToFiddle.getCcas());
        modifiedCcas.remove(0);
        Person expectedPerson = new Person(personToFiddle, personToFiddle.getRemarks(), personToFiddle.getModules(),
                new HashSet<>(modifiedCcas), personToFiddle.getMajors());
        DeleteTagFromPersonCommand command = new DeleteTagFromPersonCommand(INDEX_FIRST_PERSON, null,
                Index.fromOneBased(1), null, null);

        String expectedMessage = String.format(DeleteTagFromPersonCommand.MESSAGE_DELETE_TAG_SUCCESS, expectedPerson);

        ModelManager expectedModel = new ModelManager(model.getConnectUs(), new UserPrefs());
        expectedModel.setPerson(personToFiddle, expectedPerson);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteTagFromPersonCommand command = new DeleteTagFromPersonCommand(outOfBoundIndex, null,
                null, null, null
        );

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidModuleIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        DeleteTagFromPersonCommand command = new DeleteTagFromPersonCommand(INDEX_FIRST_PERSON, outOfBoundIndex,
                null, null, null);

        assertCommandFailure(command, model, String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX, "module"));
    }

    @Test
    public void execute_invalidCcaIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        DeleteTagFromPersonCommand command = new DeleteTagFromPersonCommand(INDEX_FIRST_PERSON, null,
                outOfBoundIndex, null, null);

        assertCommandFailure(command, model, String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX, "CCA"));
    }

    @Test
    public void execute_invalidMajorIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        DeleteTagFromPersonCommand command = new DeleteTagFromPersonCommand(INDEX_FIRST_PERSON, null,
                null, outOfBoundIndex, null);

        assertCommandFailure(command, model, String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX, "major"));
    }

    @Test
    public void execute_invalidRemarkIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        DeleteTagFromPersonCommand command = new DeleteTagFromPersonCommand(INDEX_FIRST_PERSON, null,
                null, null, outOfBoundIndex);

        assertCommandFailure(command, model, String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX, "remark"));
    }
}
