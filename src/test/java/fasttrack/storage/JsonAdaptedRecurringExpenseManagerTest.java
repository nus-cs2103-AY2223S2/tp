package fasttrack.storage;

import static fasttrack.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import fasttrack.commons.exceptions.IllegalValueException;
import fasttrack.model.category.Category;
import fasttrack.model.category.UserDefinedCategory;
import fasttrack.model.expense.Price;
import fasttrack.model.expense.RecurringExpenseManager;
import fasttrack.model.expense.RecurringExpenseType;

public class JsonAdaptedRecurringExpenseManagerTest {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Recurring Expense Manager's field is missing!";
    private static final String VALID_EXPENSENAME = "sampleExpenseName";
    private static final double VALID_AMOUNT = 23;
    private static final LocalDate VALID_STARTDATE = LocalDate.now();
    private static final Category VALID_CATEGORY = new UserDefinedCategory("sampleCat", "sampleSum");
    private static final RecurringExpenseType VALID_FREQUENCY = RecurringExpenseType.WEEKLY;

    @Test
    public void toModelType_validRecurringExpenseManager() throws IllegalValueException {
        JsonAdaptedRecurringExpenseManager recurringExpenseManager = new JsonAdaptedRecurringExpenseManager(
                new RecurringExpenseManager(VALID_EXPENSENAME, new Price(VALID_AMOUNT), VALID_CATEGORY,
                        VALID_STARTDATE, VALID_FREQUENCY));
        assertEquals(VALID_EXPENSENAME, recurringExpenseManager.toModelType().getExpenseName());
        assertEquals(VALID_AMOUNT, recurringExpenseManager.toModelType().getAmount());
        assertEquals(VALID_STARTDATE, recurringExpenseManager.toModelType().getExpenseStartDate());
        assertEquals(VALID_FREQUENCY, recurringExpenseManager.toModelType().getRecurringExpenseType());
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() throws IllegalValueException {
        JsonAdaptedRecurringExpenseManager recurringExpenseManager = new JsonAdaptedRecurringExpenseManager(
                new RecurringExpenseManager(null, new Price(VALID_AMOUNT), VALID_CATEGORY, VALID_STARTDATE,
                        VALID_FREQUENCY));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT);
        assertThrows(IllegalValueException.class, expectedMessage, recurringExpenseManager::toModelType);
    }
}
