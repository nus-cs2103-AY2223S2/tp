package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ReindexCommand.MESSAGE_REINDEX_ACKNOWLEDGEMENT;
import static seedu.address.testutil.TypicalPersons.ALEX;
import static seedu.address.testutil.TypicalPersons.BEN;
import static seedu.address.testutil.TypicalPersons.CLARK;
import static seedu.address.testutil.TypicalPersons.DAKOTA;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.IndexHandler;
import seedu.address.model.ModelManager;
import seedu.address.model.person.ContactIndex;

public class ReindexCommandTest {

    private static final ReindexCommand REINDEX_COMMAND = new ReindexCommand();

    @Test
    public void execute_deleteIndexOneThenReindex_success() {
        ModelManager model = new ModelManager();
        model.addPerson(ALEX);
        model.addPerson(BEN);

        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(BEN);

        model.deletePerson(ALEX);

        IndexHandler indexHandler = new IndexHandler(model);
        assertTrue(indexHandler.getPersonByIndex(new ContactIndex(1)).isEmpty());

        assertCommandSuccess(REINDEX_COMMAND, model, MESSAGE_REINDEX_ACKNOWLEDGEMENT, expectedModel);
    }

    @Test
    public void execute_deleteIndexTwoThenReindex_success() {
        ModelManager model = new ModelManager();
        model.addPerson(ALEX);
        model.addPerson(BEN);
        model.addPerson(CLARK);

        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(ALEX);
        expectedModel.addPerson(CLARK);

        model.deletePerson(BEN);

        IndexHandler indexHandler = new IndexHandler(model);
        assertTrue(indexHandler.getPersonByIndex(new ContactIndex(2)).isEmpty());

        assertCommandSuccess(REINDEX_COMMAND, model, MESSAGE_REINDEX_ACKNOWLEDGEMENT, expectedModel);
    }

    @Test
    public void execute_deleteIndexMultipleThenReindex_success() {
        ModelManager model = new ModelManager();
        model.addPerson(ALEX);
        model.addPerson(BEN);
        model.addPerson(CLARK);
        model.addPerson(DAKOTA);

        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(BEN);
        expectedModel.addPerson(DAKOTA);

        model.deletePerson(ALEX);
        model.deletePerson(CLARK);

        IndexHandler indexHandler = new IndexHandler(model);
        assertTrue(indexHandler.getPersonByIndex(new ContactIndex(1)).isEmpty());
        assertTrue(indexHandler.getPersonByIndex(new ContactIndex(3)).isEmpty());

        assertCommandSuccess(REINDEX_COMMAND, model, MESSAGE_REINDEX_ACKNOWLEDGEMENT, expectedModel);
    }

}
