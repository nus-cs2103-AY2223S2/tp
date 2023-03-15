package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedExpense.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.category.Category;
import seedu.address.model.category.MiscellaneousCategory;
import seedu.address.model.expense.Expense;

public class JsonAdaptedExpenseTest {
    private static final String VALID_NAME = "Benson";
    private static final double VALID_AMOUNT = 23;
    private static final LocalDate VALID_DATE = LocalDate.now();
    private static final Category VALID_CATEGORY = new MiscellaneousCategory();

    @Test
    public void toModelType_validExpenseDetails_returnsExpense() throws Exception {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(
            new Expense(VALID_NAME, VALID_AMOUNT, VALID_DATE, VALID_CATEGORY));
        assertEquals(VALID_NAME, expense.toModelType().getName());
        assertEquals(VALID_AMOUNT, expense.toModelType().getAmount());
        assertEquals(VALID_DATE, expense.toModelType().getDate());
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(
            new Expense(null, VALID_AMOUNT, VALID_DATE, VALID_CATEGORY));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Name");
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }
}
