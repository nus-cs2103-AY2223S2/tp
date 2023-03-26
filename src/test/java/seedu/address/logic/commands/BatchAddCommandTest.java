package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalEmployees.getTypicalExecutiveProDb;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class BatchAddCommandTest {

    private Model model = new ModelManager(getTypicalExecutiveProDb(), new UserPrefs());


    @Test
    void execute_batchAddFileNoExist_throwsCommandException() {
        Path testData = Paths.get("src", "test", "data", "BatchAddTest", "filename.csv");
        BatchAddCommand batchAddCommand = new BatchAddCommand("filename.csv");
        batchAddCommand.setFilePath(testData);
        assertCommandFailure(batchAddCommand, model, "File Not Found");
    }

}
