package fasttrack.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fasttrack.model.util.AnalyticsType;
import fasttrack.testutil.TypicalExpenses;
import javafx.beans.property.DoubleProperty;


class AnalyticModelManagerTest {

    private LocalDate testDate;
    private AnalyticModel analyticModel;

    @BeforeEach
    public void setUp() {
        ReadOnlyExpenseTracker expenseTracker = TypicalExpenses.getTypicalExpenseTracker();
        testDate = LocalDate.of(2023, 3, 3);
        analyticModel = new AnalyticModelManager(expenseTracker, testDate);
    }

    @Test
    public void getMonthlySpent_monthContainsExpenses_isValidCalculation() {
        assertEquals(27.7, analyticModel.getMonthlySpent().get());
    }

    @Test
    public void getMonthlyRemaining_monthContainsExpenses_isValidCalculation() {
        assertEquals((1000 - 27.7), analyticModel.getMonthlyRemaining().get());
    }

    @Test
    public void getWeeklySpent_weekContainsExpenses_isValidCalculation() {
        assertEquals(2.7, analyticModel.getWeeklySpent().get());
    }

    @Test
    public void getWeeklyRemaining_weekContainsExpenses_isValidCalculation() {
        assertEquals(((1000 / 4) - 2.7), analyticModel.getWeeklyRemaining().get());
    }

    @Test
    public void getWeeklyChange_weekContainsExpenses_isValidCalculation() {
        // previous week has no expenses
        assertEquals(0, analyticModel.getWeeklyChange().get());
    }

    @Test
    public void getMonthlyChange_monthContainsExpenses_isValidCalculation() {
        assertEquals(((27.7 - 1000) / 1000) * 100, analyticModel.getMonthlyChange().get());
    }

    @Test
    public void getTotalSpent_containsExpenses_isValidCalculation() {
        assertEquals(1031.7, analyticModel.getTotalSpent().get());
    }

    @Test
    public void getBudgetPercentage_containsExpenses_isValidCalculation() {
        assertEquals(2.77, analyticModel.getBudgetPercentage().get());
    }

    @Test
    public void testGetAnalyticsData_allAnalyticsTypes_givesValidCalculations() {
        try {
            for (AnalyticsType type : AnalyticsType.values()) {
                DoubleProperty result = analyticModel.getAnalyticsData(type);
                switch (type) {
                case MONTHLY_SPENT:
                    assertEquals(27.7, result.get());
                    break;
                case MONTHLY_REMAINING:
                    assertEquals((1000 - 27.7), result.get());
                    break;
                case WEEKLY_SPENT:
                    assertEquals(2.7, result.get());
                    break;
                case WEEKLY_REMAINING:
                    assertEquals(((1000 / 4) - 2.7), result.get());
                    break;
                case WEEKLY_CHANGE:
                    assertEquals(0, result.get());
                    break;
                case MONTHLY_CHANGE:
                    assertEquals(((27.7 - 1000) / 1000) * 100, result.get());
                    break;
                case TOTAL_SPENT:
                    assertEquals(1031.7, result.get());
                    break;
                case BUDGET_PERCENTAGE:
                    assertEquals(2.77, result.get());
                    break;
                default:
                    fail("Unexpected analytics type was provided");
                }
            }
        } catch (IllegalArgumentException e) {
            fail("Unexpected exception occurred");
        }
    }

    @Test
    public void equals() {
        ExpenseTracker expenseTracker = TypicalExpenses.getTypicalExpenseTracker();
        ExpenseTracker differentExpenseTracker = new ExpenseTracker();
        AnalyticModelManager analyticModelManager = new AnalyticModelManager(expenseTracker, testDate);
        AnalyticModelManager analyticModelManagerCopy = new AnalyticModelManager(expenseTracker, testDate);
        // same values -> returns true
        assertEquals(analyticModelManager, analyticModelManagerCopy);
        // same object -> returns true
        assertEquals(analyticModelManager, analyticModelManager);
        // null -> returns false
        assertNotEquals(null, analyticModelManager);
        // different types -> returns false
        assertNotEquals(5, analyticModelManager);
        // different expense tracker -> returns false
        assertNotEquals(analyticModelManager, new AnalyticModelManager(differentExpenseTracker));
    }
}
