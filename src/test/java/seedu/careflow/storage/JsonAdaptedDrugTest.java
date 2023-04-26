package seedu.careflow.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.careflow.storage.JsonAdaptedDrug.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.careflow.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.careflow.commons.exceptions.IllegalValueException;
import seedu.careflow.model.drug.ActiveIngredient;
import seedu.careflow.model.drug.Direction;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.drug.Purpose;
import seedu.careflow.model.drug.SideEffect;
import seedu.careflow.model.drug.StorageCount;
import seedu.careflow.model.drug.TradeName;
import seedu.careflow.model.util.SampleDataUtil;


public class JsonAdaptedDrugTest {
    private static final String INVALID_TRADE_NAME = "$$";
    private static final String INVALID_ACTIVE_INGREDIENT = "-";
    private static final String INVALID_DIRECTION = "%_%";
    private static final String INVALID_PURPOSE = ";";
    private static final String INVALID_SIDE_EFFECT = "[]";
    private static final String INVALID_STORAGE_COUNT = "-10";

    private static final Drug SAMPLE_DRUG = SampleDataUtil.getSampleDrugs()[0];
    private static final String VALID_TRADE_NAME = SAMPLE_DRUG.getTradeName().tradeName;
    private static final String VALID_ACTIVE_INGREDIENT = SAMPLE_DRUG.getActiveIngredient().value;
    private static final String VALID_DIRECTION = SAMPLE_DRUG.getDirection().value;
    private static final String VALID_PURPOSE = SAMPLE_DRUG.getPurposes().purpose;
    private static final String VALID_SIDE_EFFECT = SAMPLE_DRUG.getSideEffects().sideEffect;
    private static final String VALID_STORAGE_COUNT = SAMPLE_DRUG.getStorageCount().toString();

    @Test
    public void toModelType_validDrugDetails_returnsDrug() throws Exception {
        JsonAdaptedDrug drug = new JsonAdaptedDrug(SAMPLE_DRUG);
        assertEquals(SAMPLE_DRUG, drug.toModelType());
    }

    @Test
    public void toModelType_invalidTradeName_throwsIllegalValueException() {
        JsonAdaptedDrug drug = new JsonAdaptedDrug(INVALID_TRADE_NAME, VALID_ACTIVE_INGREDIENT, VALID_DIRECTION,
                VALID_PURPOSE, VALID_SIDE_EFFECT, VALID_STORAGE_COUNT);
        String expectedMessage = TradeName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, drug::toModelType);
    }

    @Test
    public void toModelType_nullTradeName_throwsIllegalValueException() {
        JsonAdaptedDrug drug = new JsonAdaptedDrug(null, VALID_ACTIVE_INGREDIENT, VALID_DIRECTION,
                VALID_PURPOSE, VALID_SIDE_EFFECT, VALID_STORAGE_COUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TradeName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, drug::toModelType);
    }

    @Test
    public void toModelType_invalidActiveIngredient_throwsIllegalValueException() {
        JsonAdaptedDrug drug = new JsonAdaptedDrug(VALID_TRADE_NAME, INVALID_ACTIVE_INGREDIENT, VALID_DIRECTION,
                VALID_PURPOSE, VALID_SIDE_EFFECT, VALID_STORAGE_COUNT);
        String expectedMessage = ActiveIngredient.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, drug::toModelType);
    }

    @Test
    public void toModelType_nullActiveIngredient_throwsIllegalValueException() {
        JsonAdaptedDrug drug = new JsonAdaptedDrug(VALID_TRADE_NAME, null, VALID_DIRECTION,
                VALID_PURPOSE, VALID_SIDE_EFFECT, VALID_STORAGE_COUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ActiveIngredient.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, drug::toModelType);
    }

    @Test
    public void toModelType_invalidDirection_throwsIllegalValueException() {
        JsonAdaptedDrug drug = new JsonAdaptedDrug(VALID_TRADE_NAME, VALID_ACTIVE_INGREDIENT, INVALID_DIRECTION,
                VALID_PURPOSE, VALID_SIDE_EFFECT, VALID_STORAGE_COUNT);
        String expectedMessage = Direction.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, drug::toModelType);
    }

    @Test
    public void toModelType_nullDirection_throwsIllegalValueException() {
        JsonAdaptedDrug drug = new JsonAdaptedDrug(VALID_TRADE_NAME, VALID_ACTIVE_INGREDIENT, null,
                VALID_PURPOSE, VALID_SIDE_EFFECT, VALID_STORAGE_COUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Direction.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, drug::toModelType);
    }

    @Test
    public void toModelType_invalidPurpose_throwsIllegalValueException() {
        JsonAdaptedDrug drug = new JsonAdaptedDrug(VALID_TRADE_NAME, VALID_ACTIVE_INGREDIENT, VALID_DIRECTION,
                INVALID_PURPOSE, VALID_SIDE_EFFECT, VALID_STORAGE_COUNT);
        String expectedMessage = Purpose.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, drug::toModelType);
    }

    @Test
    public void toModelType_nullPurpose_throwsIllegalValueException() {
        JsonAdaptedDrug drug = new JsonAdaptedDrug(VALID_TRADE_NAME, VALID_ACTIVE_INGREDIENT, VALID_DIRECTION,
                null, VALID_SIDE_EFFECT, VALID_STORAGE_COUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Purpose.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, drug::toModelType);
    }

    @Test
    public void toModelType_invalidSideEffect_throwsIllegalValueException() {
        JsonAdaptedDrug drug = new JsonAdaptedDrug(VALID_TRADE_NAME, VALID_ACTIVE_INGREDIENT, VALID_DIRECTION,
                VALID_PURPOSE, INVALID_SIDE_EFFECT, VALID_STORAGE_COUNT);
        String expectedMessage = SideEffect.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, drug::toModelType);
    }

    @Test
    public void toModelType_nullSideEffect_throwsIllegalValueException() {
        JsonAdaptedDrug drug = new JsonAdaptedDrug(VALID_TRADE_NAME, VALID_ACTIVE_INGREDIENT, VALID_DIRECTION,
                VALID_PURPOSE, null, VALID_STORAGE_COUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, SideEffect.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, drug::toModelType);
    }

    @Test
    public void toModelType_invalidStorageCount_throwsIllegalValueException() {
        JsonAdaptedDrug drug = new JsonAdaptedDrug(VALID_TRADE_NAME, VALID_ACTIVE_INGREDIENT, VALID_DIRECTION,
                VALID_PURPOSE, VALID_SIDE_EFFECT, INVALID_STORAGE_COUNT);
        String expectedMessage = StorageCount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, drug::toModelType);
    }

    @Test
    public void toModelType_nullStorageCount_throwsIllegalValueException() {
        JsonAdaptedDrug drug = new JsonAdaptedDrug(VALID_TRADE_NAME, VALID_ACTIVE_INGREDIENT, VALID_DIRECTION,
                VALID_PURPOSE, VALID_SIDE_EFFECT, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StorageCount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, drug::toModelType);
    }

}
