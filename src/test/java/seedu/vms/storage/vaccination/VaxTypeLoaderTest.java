package seedu.vms.storage.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.Age;
import seedu.vms.model.GroupName;
import seedu.vms.model.vaccination.VaxTestingUtil;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.model.vaccination.VaxTypeManager;
import seedu.vms.testutil.SampleVaxTypeData;


public class VaxTypeLoaderTest {
    private static final String TEST_FOLDER_PATH = "src/test/data/JsonVaxTypeTest/";

    private static final String FILE_DEFAULT_VALUES = "DefaultTypeValues.json";
    private static final GroupName DEFAULT_NAME = new GroupName("UNCHI");
    private static final Age UNCHI_MAX_AGE = new Age(100);

    private static final String FILE_MISSING_NAME = "MissingName.json";
    private static final String FILE_EMPTY_REQ = "EmptyReqSet.json";
    private static final String FILE_MISSING_REQ = "MissingReqSet.json";
    private static final String FILE_MISSING_REQ_TYPE = "MissingReqType.json";
    private static final String FILE_NON_STRING_IN_REQ = "NonStringInReqSet.json";
    private static final String FILE_UNKNOWN_REQ_TYPE = "UnknownReqType.json";
    private static final String FILE_NON_JSON_FORMAT = "NonJsonFormat.json";

    private static final String FILE_VALID_MULTIPLE = "ValidMultipleTypes.json";
    private static final String FILE_EMPTY = "ZeroTypes.json";

    private static final String TEST_SAVE_FILE = "VAX_TYPE_TEST_FILE.json";

    @TempDir
    public Path testFolder;


    @Test
    public void load_resource() throws Exception {
        VaxTypeManager storage = VaxTypeLoader.load();
        VaxType vaxType = storage.get(SampleVaxTypeData.NAME_REAL.getName()).get();
        VaxTestingUtil.assertVaxType(vaxType,
                SampleVaxTypeData.NAME_REAL,
                SampleVaxTypeData.GROUPS_REAL,
                SampleVaxTypeData.MIN_AGE_REAL,
                SampleVaxTypeData.MAX_AGE_REAL,
                SampleVaxTypeData.MIN_SPACING_REAL,
                SampleVaxTypeData.HISTORY_REQS_REAL,
                SampleVaxTypeData.ALLERGY_REQS_REAL);
    }


    @Test
    public void load_fileDefaultValues() throws Exception {
        VaxTypeManager storage = VaxTypeLoader.load(Paths.get(TEST_FOLDER_PATH + FILE_DEFAULT_VALUES));
        VaxType vaxType = storage.get(DEFAULT_NAME.getName()).get();
        VaxTestingUtil.assertVaxType(vaxType,
                DEFAULT_NAME,
                VaxType.DEFAULT_GROUP_SET,
                VaxType.DEFAULT_MIN_AGE,
                VaxType.DEFAULT_MAX_AGE,
                VaxType.DEFAULT_MIN_SPACING,
                VaxType.DEFAULT_HISTORY_REQS,
                VaxType.DEFAULT_ALLERGY_REQS);
    }


    @Test
    public void load_multipleTypes() throws Exception {
        VaxTypeManager storage = VaxTypeLoader.load(Paths.get(TEST_FOLDER_PATH + FILE_VALID_MULTIPLE));
        VaxType vaxType = storage.get(DEFAULT_NAME.getName()).get();
        VaxTestingUtil.assertVaxType(vaxType,
                DEFAULT_NAME,
                VaxType.DEFAULT_GROUP_SET,
                VaxType.DEFAULT_MIN_AGE,
                UNCHI_MAX_AGE,
                VaxType.DEFAULT_MIN_SPACING,
                VaxType.DEFAULT_HISTORY_REQS,
                VaxType.DEFAULT_ALLERGY_REQS);
        vaxType = storage.get(SampleVaxTypeData.NAME_REAL.getName()).get();
        VaxTestingUtil.assertVaxType(vaxType,
                SampleVaxTypeData.NAME_REAL,
                SampleVaxTypeData.GROUPS_REAL,
                SampleVaxTypeData.MIN_AGE_REAL,
                SampleVaxTypeData.MAX_AGE_REAL,
                SampleVaxTypeData.MIN_SPACING_REAL,
                SampleVaxTypeData.HISTORY_REQS_REAL,
                SampleVaxTypeData.ALLERGY_REQS_REAL);
    }


    @Test
    public void load_emptyList() throws Exception {
        VaxTypeManager storage = VaxTypeLoader.load(Paths.get(TEST_FOLDER_PATH + FILE_EMPTY));
        assertTrue(storage.asUnmodifiableObservableMap().isEmpty());
    }


    @Test
    void load_fileMissingName_exceptionThrown() throws Exception {
        try {
            VaxTypeLoader.load(Paths.get(TEST_FOLDER_PATH + FILE_MISSING_NAME));
        } catch (IllegalValueException illValEx) {
            // expected exception
            return;
        }
    }


    @Test
    void load_emptyReq_exceptionThrown() throws Exception {
        try {
            VaxTypeLoader.load(Paths.get(TEST_FOLDER_PATH + FILE_EMPTY_REQ));
        } catch (IllegalValueException illValEx) {
            // expected exception
            return;
        }
    }


    @Test
    void load_missingReq_exceptionThrown() throws Exception {
        try {
            VaxTypeLoader.load(Paths.get(TEST_FOLDER_PATH + FILE_MISSING_REQ));
        } catch (IllegalValueException illValEx) {
            // expected exception
            return;
        }
    }


    @Test
    void load_missingReqType_exceptionThrown() throws Exception {
        try {
            VaxTypeLoader.load(Paths.get(TEST_FOLDER_PATH + FILE_MISSING_REQ_TYPE));
        } catch (IllegalValueException illValEx) {
            // expected exception
            return;
        }
    }


    @Test
    void load_nonStringInReqList_exceptionThrown() throws Exception {
        try {
            VaxTypeLoader.load(Paths.get(TEST_FOLDER_PATH + FILE_NON_STRING_IN_REQ));
        } catch (IOException ioEx) {
            // expected exception
            return;
        }
    }


    @Test
    void load_unknownReqType_exceptionThrown() throws Exception {
        try {
            VaxTypeLoader.load(Paths.get(TEST_FOLDER_PATH + FILE_UNKNOWN_REQ_TYPE));
        } catch (IOException ioEx) {
            // expected exception
            return;
        }
    }


    @Test
    void load_nonJsonFormat_exceptionThrown() throws Exception {
        try {
            VaxTypeLoader.load(Paths.get(TEST_FOLDER_PATH + FILE_NON_JSON_FORMAT));
        } catch (IOException ioEx) {
            // expected exception
            return;
        }
    }


    @Test
    public void write() throws Exception {
        // empty manager
        testSaveMethod(new VaxTypeManager());
        // filled manager
        // assuming default loading works
        testSaveMethod(VaxTypeLoader.load());
    }


    private void testSaveMethod(VaxTypeManager manager) throws Exception {
        Path saveFile = testFolder.resolve(TEST_SAVE_FILE);
        VaxTypeLoader.fromModelType(manager).write(saveFile);
        VaxTypeManager loaded = VaxTypeLoader.load(saveFile);
        assertEquals(manager.asUnmodifiableObservableMap(), loaded.asUnmodifiableObservableMap());
    }
}
