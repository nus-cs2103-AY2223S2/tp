package fasttrack.storage;

import static fasttrack.storage.JsonAdaptedExpense.MISSING_FIELD_MESSAGE_FORMAT;
import static fasttrack.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import fasttrack.commons.exceptions.IllegalValueException;
import fasttrack.model.category.Category;
import fasttrack.model.category.MiscellaneousCategory;
import fasttrack.model.expense.Expense;

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
