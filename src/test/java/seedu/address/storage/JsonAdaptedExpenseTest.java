package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedExpense.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.category.Category;
import seedu.address.model.category.MiscellaneousCategory;
import seedu.address.model.category.UserDefinedCategory;
import seedu.address.model.expense.Expense;

public class JsonAdaptedExpenseTest {
    private static final String INVALID_NAME = "R@chel";
    private static final Category INVALID_CATEGORY = new UserDefinedCategory("$#@", " ");

    private static final String VALID_NAME = "Benson";
    private static final double VALID_AMOUNT = 23;
    private static final Date VALID_DATE = new Date(2020, 1, 1, 0, 0, 0);
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
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(
                new Expense(INVALID_NAME, VALID_AMOUNT, VALID_DATE, VALID_CATEGORY));
        String expectedMessage = "Name should only contain alphanumeric"
                + "characters and spaces, and it should not be blank";
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(
                new Expense(null, VALID_AMOUNT, VALID_DATE, VALID_CATEGORY));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Name");
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(
                new Expense(VALID_NAME, VALID_AMOUNT, null, VALID_CATEGORY));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date");
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidCategory_throwsIllegalValueException() {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(
                new Expense(VALID_NAME, VALID_AMOUNT, VALID_DATE, INVALID_CATEGORY));
        String expectedMessage = "Category should only contain alphanumeric"
                + "characters and spaces, and it should not be blank";
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

}
