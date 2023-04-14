package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ExecutiveProDb;
import seedu.address.testutil.TypicalEmployees;

public class JsonSerializableExecutiveProDbTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableExecutiveProDbTest");
    private static final Path TYPICAL_EMPLOYEES_FILE =
            TEST_DATA_FOLDER.resolve("typicalEmployeesExecutiveProDb.json");
    private static final Path INVALID_EMPLOYEES_FILE =
            TEST_DATA_FOLDER.resolve("invalidEmployeeExecutiveProDb.json");
    private static final Path DUPLICATE_EMPLOYEE_FILE =
            TEST_DATA_FOLDER.resolve("duplicateEmployeeExecutiveProDb.json");

    @Test
    public void toModelType_typicalEmployeesFile_success() throws Exception {
        JsonSerializableExecutiveProDb dataFromFile = JsonUtil.readJsonFile(TYPICAL_EMPLOYEES_FILE,
                JsonSerializableExecutiveProDb.class).get();
        ExecutiveProDb executiveProDbFromFile = dataFromFile.toModelType();
        ExecutiveProDb typicalPersonsExecutiveProDb = TypicalEmployees.getTypicalExecutiveProDb();
        assertEquals(executiveProDbFromFile, typicalPersonsExecutiveProDb);
    }

    @Test
    public void toModelType_invalidEmployeeFile_throwsIllegalValueException() throws Exception {
        JsonSerializableExecutiveProDb dataFromFile = JsonUtil.readJsonFile(INVALID_EMPLOYEES_FILE,
                JsonSerializableExecutiveProDb.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEmployees_throwsIllegalValueException() throws Exception {
        JsonSerializableExecutiveProDb dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EMPLOYEE_FILE,
                JsonSerializableExecutiveProDb.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableExecutiveProDb.MESSAGE_DUPLICATE_EMPLOYEE,
                dataFromFile::toModelType);
    }

}
