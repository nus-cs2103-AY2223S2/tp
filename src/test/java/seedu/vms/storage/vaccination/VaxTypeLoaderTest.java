package seedu.vms.storage.vaccination;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.testutil.SampleVaxTypeData;
import seedu.vms.model.vaccination.VaxTestingUtil;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.model.vaccination.VaxTypeManager;


public class VaxTypeLoaderTest {
    private static final String TEST_FOLDER_PATH = "src/test/data/JsonVaxTypeTest/";

    private static final String FILE_DEFAULT_VALUES = "DefaultTypeValues.json";
    private static final String DEFAULT_NAME = "UNCHI";
    private static final int UNCHI_MAX_AGE = 100;

    private static final String FILE_MISSING_NAME = "MissingName.json";
    private static final String FILE_EMPTY_REQ = "EmptyReqSet.json";
    private static final String FILE_MISSING_REQ = "MissingReqSet.json";
    private static final String FILE_MISSING_REQ_TYPE = "MissingReqType.json";
    private static final String FILE_NON_STRING_IN_REQ = "NonStringInReqSet.json";
    private static final String FILE_UNKNOWN_REQ_TYPE = "UnknownReqType.json";
    private static final String FILE_NON_JSON_FORMAT = "NonJsonFormat.json";

    private static final String FILE_VALID_MULTIPLE = "ValidMultipleTypes.json";
    private static final String FILE_EMPTY = "ZeroTypes.json";


    @Test
    public void load_resource() throws Exception {
        VaxTypeManager storage = VaxTypeLoader.load();
        VaxType vaxType = storage.get(SampleVaxTypeData.NAME).get();
        VaxTestingUtil.assertVaxType(vaxType,
                SampleVaxTypeData.NAME,
                SampleVaxTypeData.GROUPS,
                SampleVaxTypeData.MIN_AGE,
                SampleVaxTypeData.MAX_AGE,
                SampleVaxTypeData.MIN_SPACING,
                SampleVaxTypeData.HISTORY_REQS,
                SampleVaxTypeData.ALLERGY_REQS);
    }


    @Test
    public void load_fileDefaultValues() throws Exception {
        VaxTypeManager storage = VaxTypeLoader.load(TEST_FOLDER_PATH + FILE_DEFAULT_VALUES);
        VaxType vaxType = storage.get(DEFAULT_NAME).get();
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
        VaxTypeManager storage = VaxTypeLoader.load(TEST_FOLDER_PATH + FILE_VALID_MULTIPLE);
        VaxType vaxType = storage.get(DEFAULT_NAME).get();
        VaxTestingUtil.assertVaxType(vaxType,
                DEFAULT_NAME,
                VaxType.DEFAULT_GROUP_SET,
                VaxType.DEFAULT_MIN_AGE,
                UNCHI_MAX_AGE,
                VaxType.DEFAULT_MIN_SPACING,
                VaxType.DEFAULT_HISTORY_REQS,
                VaxType.DEFAULT_ALLERGY_REQS);
        vaxType = storage.get(SampleVaxTypeData.NAME).get();
        VaxTestingUtil.assertVaxType(vaxType,
                SampleVaxTypeData.NAME,
                SampleVaxTypeData.GROUPS,
                SampleVaxTypeData.MIN_AGE,
                SampleVaxTypeData.MAX_AGE,
                SampleVaxTypeData.MIN_SPACING,
                SampleVaxTypeData.HISTORY_REQS,
                SampleVaxTypeData.ALLERGY_REQS);
    }


    @Test
    public void load_emptyList() throws Exception {
        VaxTypeManager storage = VaxTypeLoader.load(TEST_FOLDER_PATH + FILE_EMPTY);
        assertTrue(storage.asUnmodifiableObservableMap().isEmpty());
    }


    @Test
    void load_fileMissingName_exceptionThrown() throws Exception {
        try {
            VaxTypeLoader.load(TEST_FOLDER_PATH + FILE_MISSING_NAME);
        } catch (IllegalValueException illValEx) {
            // expected exception
            return;
        }
    }


    @Test
    void load_emptyReq_exceptionThrown() throws Exception {
        try {
            VaxTypeLoader.load(TEST_FOLDER_PATH + FILE_EMPTY_REQ);
        } catch (IllegalValueException illValEx) {
            // expected exception
            return;
        }
    }


    @Test
    void load_missingReq_exceptionThrown() throws Exception {
        try {
            VaxTypeLoader.load(TEST_FOLDER_PATH + FILE_MISSING_REQ);
        } catch (IllegalValueException illValEx) {
            // expected exception
            return;
        }
    }


    @Test
    void load_missingReqType_exceptionThrown() throws Exception {
        try {
            VaxTypeLoader.load(TEST_FOLDER_PATH + FILE_MISSING_REQ_TYPE);
        } catch (IllegalValueException illValEx) {
            // expected exception
            return;
        }
    }


    @Test
    void load_nonStringInReqList_exceptionThrown() throws Exception {
        try {
            VaxTypeLoader.load(TEST_FOLDER_PATH + FILE_NON_STRING_IN_REQ);
        } catch (IOException ioEx) {
            // expected exception
            return;
        }
    }


    @Test
    void load_unknownReqType_exceptionThrown() throws Exception {
        try {
            VaxTypeLoader.load(TEST_FOLDER_PATH + FILE_UNKNOWN_REQ_TYPE);
        } catch (IOException ioEx) {
            // expected exception
            return;
        }
    }


    @Test
    void load_nonJsonFormat_exceptionThrown() throws Exception {
        try {
            VaxTypeLoader.load(TEST_FOLDER_PATH + FILE_NON_JSON_FORMAT);
        } catch (IOException ioEx) {
            // expected exception
            return;
        }
    }
}
