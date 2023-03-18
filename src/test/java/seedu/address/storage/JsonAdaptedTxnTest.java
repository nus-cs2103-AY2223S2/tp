package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTxn.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTransactions.COFFEE_MACHINES_B;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Owner;
import seedu.address.model.transaction.TxnStatus;
import seedu.address.model.transaction.Value;



public class JsonAdaptedTxnTest {
    private static final String INVALID_DESC = "";
    private static final String INVALID_OWNER = "@@@";
    private static final String INVALID_STATUS = "iuuno";
    private static final String INVALID_VALUE = "abd";

    private static final String VALID_DESC = COFFEE_MACHINES_B.getDescription().toString();
    private static final String VALID_OWNER = COFFEE_MACHINES_B.getOwner().toString();
    private static final String VALID_STATUS = COFFEE_MACHINES_B.getStatus().toString();
    private static final String VALID_VALUE = COFFEE_MACHINES_B.getValue().toString();


    @Test
    public void toModelType_validTxnDetails_returnsTxn() throws Exception {
        JsonAdaptedTxn txn = new JsonAdaptedTxn(COFFEE_MACHINES_B);
        assertEquals(COFFEE_MACHINES_B, txn.toModelType());
    }

    @Test
    public void toModelType_invalidDesc_throwsIllegalValueException() {
        JsonAdaptedTxn txn =
                new JsonAdaptedTxn(INVALID_DESC, VALID_OWNER, VALID_STATUS, VALID_VALUE);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, txn::toModelType);
    }

    @Test
    public void toModelType_nullDesc_throwsIllegalValueException() {
        JsonAdaptedTxn txn =
                new JsonAdaptedTxn(null, VALID_OWNER, VALID_STATUS, VALID_VALUE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, txn::toModelType);
    }

    @Test
    public void toModelType_invalidOwner_throwsIllegalValueException() {
        JsonAdaptedTxn txn =
                new JsonAdaptedTxn(VALID_DESC, INVALID_OWNER, VALID_STATUS, VALID_VALUE);
        String expectedMessage = Owner.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, txn::toModelType);
    }

    @Test
    public void toModelType_nullOwner_throwsIllegalValueException() {
        JsonAdaptedTxn txn =
                new JsonAdaptedTxn(VALID_DESC, null, VALID_STATUS, VALID_VALUE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Owner.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, txn::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedTxn txn =
                new JsonAdaptedTxn(VALID_DESC, VALID_OWNER, INVALID_STATUS, VALID_VALUE);
        String expectedMessage = TxnStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, txn::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedTxn txn =
                new JsonAdaptedTxn(VALID_DESC, VALID_OWNER, null, VALID_VALUE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TxnStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, txn::toModelType);
    }

    @Test
    public void toModelType_invalidValue_throwsIllegalValueException() {
        JsonAdaptedTxn txn =
                new JsonAdaptedTxn(VALID_DESC, VALID_OWNER, VALID_STATUS, INVALID_VALUE);
        String expectedMessage = Value.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, txn::toModelType);
    }

    @Test
    public void toModelType_nullValue_throwsIllegalValueException() {
        JsonAdaptedTxn txn =
                new JsonAdaptedTxn(VALID_DESC, VALID_OWNER, VALID_STATUS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Value.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, txn::toModelType);
    }

}
