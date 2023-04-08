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
        assertCommandFailure(batchAddCommand, model, BatchAddCommand.MESSAGE_FILE_NOT_FOUND);
    }

    @Test
    void execute_batchAddFileEmpty_throwsCommandException() {
        Path testData = Paths.get("src", "test", "data", "BatchAddTest", "emptyFile.csv");
        BatchAddCommand batchAddCommand = new BatchAddCommand("emptyFile.csv");
        batchAddCommand.setFilePath(testData);
        assertCommandFailure(batchAddCommand, model, String.format("%s does not have any data", "emptyFile.csv"));
    }

    // @Test
    // void execute_batchAddFileWithDuplicates_throwsCommandException() {
    //     Path testData = Paths.get("src", "test", "data", "BatchAddTest", "duplicates.csv");
    //     BatchAddCommand batchAddCommand = new BatchAddCommand("duplicates.csv");
    //     batchAddCommand.setFilePath(testData);
    //     assertCommandFailure(batchAddCommand, model, "One person in the list is found to be a duplicate. "
    //             + "Call aborted");
    // }


    //    @Test
    //    void execute_batchAdd_success() {
    //        Model emptyDatabase = new ModelManager(new ExecutiveProDb(), new UserPrefs());
    //        Path testData = Paths.get("src", "test", "data", "BatchAddTest", "BatchAddSuccess.csv");
    //        BatchAddCommand batchAddCommand = new BatchAddCommand("BatchAddSuccess.csv");
    //        batchAddCommand.setFilePath(testData);
    //
    //        String expectedMessage = String.format(BatchAddCommand.MESSAGE_WORKS, 2);
    //        ModelManager expectedModel = new ModelManager(new ExecutiveProDb(), new UserPrefs());
    //        EmployeeId.setCount(1);
    //        expectedModel.addEmployee(AMY);
    //        expectedModel.addEmployee(BOB);
    //
    //        assertCommandSuccess(batchAddCommand, emptyDatabase, expectedMessage, expectedModel);
    //    }

}
