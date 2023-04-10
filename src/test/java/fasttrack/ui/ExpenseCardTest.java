package fasttrack.ui;

import static fasttrack.testutil.TypicalExpenses.APPLE;
import static fasttrack.ui.JavaFxTestHelper.initJavaFxHelper;
import static fasttrack.ui.JavaFxTestHelper.setUpHeadlessMode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import fasttrack.model.expense.Expense;
import javafx.application.Platform;
import javafx.scene.control.Label;

class ExpenseCardTest {

    private Expense expense;
    private int displayedIndex;

    @BeforeEach
    public void setUp() {
        expense = APPLE;
        displayedIndex = 1;
    }

    @BeforeAll
    static void initJfx() throws InterruptedException {
        setUpHeadlessMode();
        initJavaFxHelper();
    }

    @Test
    public void testExpenseCard_validData_success() {
        ExpenseCard expenseCard = new ExpenseCard(expense, displayedIndex);
        CompletableFuture<Void> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                // Test that the category name label is set correctly
                Label expenseNameLabel = (Label) expenseCard.getRoot().lookup("#expenseName");
                assertEquals("Apple", expenseNameLabel.getText());

                // Test that the index label is set correctly
                Label indexLabel = (Label) expenseCard.getRoot().lookup("#id");
                assertEquals("1. ", indexLabel.getText());

                // Test that the price label is set correctly
                Label priceLabel = (Label) expenseCard.getRoot().lookup("#price");
                assertEquals("$1.50", priceLabel.getText());

                // Test that the category label is set correctly
                Label categoryLabel = (Label) expenseCard.getRoot().lookup("#category");
                assertEquals("Food", categoryLabel.getText());

                // Test that the date label is set correctly
                Label frequencyLabel = (Label) expenseCard.getRoot().lookup("#date");
                assertEquals("01/03/23", frequencyLabel.getText());
                future.complete(null);
            } catch (AssertionFailedError e) {
                future.completeExceptionally(e);
            }
        });
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            fail("Assertion error thrown in Platform.runLater thread: " + e.getMessage());
        }
    }

    @Test
    public void testEquals_validExpenseCard_success() {
        ExpenseCard expenseCard1 = new ExpenseCard(expense, displayedIndex);
        ExpenseCard expenseCard2 = new ExpenseCard(expense, displayedIndex + 1);
        ExpenseCard expenseCard3 = new ExpenseCard(expense, displayedIndex + 1);
        CompletableFuture<Void> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                assertEquals(expenseCard1, expenseCard1);
                assertNotEquals(expenseCard1, expenseCard2);
                assertEquals(expenseCard2, expenseCard3);
                future.complete(null);
            } catch (AssertionFailedError e) {
                future.completeExceptionally(e);
            }
        });
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            fail("Assertion error thrown in Platform.runLater thread: " + e.getMessage());
        }
    }

}
