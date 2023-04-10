package fasttrack.ui;

import static fasttrack.testutil.TypicalRecurringExpenses.GYM_MEMBERSHIP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import fasttrack.model.expense.RecurringExpenseManager;
import javafx.application.Platform;
import javafx.scene.control.Label;

class RecurringExpenseCardTest {

    private RecurringExpenseManager recurringExpenseManager;
    private int displayedIndex;
    private static CountDownLatch latch;

    @BeforeEach
    public void setUp() {
        recurringExpenseManager = GYM_MEMBERSHIP;
        displayedIndex = 1;
    }

    @BeforeAll
    public static void initJFX() throws InterruptedException {
        latch = new CountDownLatch(1);
        Platform.startup(() -> {
            latch.countDown();
        });
        latch.await();
    }

    @Test
    public void testRecurringExpenseCard_validData_success() {
        RecurringExpenseCard recurringExpenseCard = new RecurringExpenseCard(
                recurringExpenseManager, displayedIndex);
        CompletableFuture<Void> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                // Test that the category name label is set correctly
                Label expenseNameLabel = (Label) recurringExpenseCard.getRoot().lookup("#expenseName");
                assertEquals("Gym Membership", expenseNameLabel.getText());

                // Test that the index label is set correctly
                Label indexLabel = (Label) recurringExpenseCard.getRoot().lookup("#id");
                assertEquals("1. ", indexLabel.getText());

                // Test that the price label is set correctly
                Label priceLabel = (Label) recurringExpenseCard.getRoot().lookup("#price");
                assertEquals("$50.00", priceLabel.getText());

                // Test that the category label is set correctly
                Label categoryLabel = (Label) recurringExpenseCard.getRoot().lookup("#category");
                assertEquals("Fitness", categoryLabel.getText());

                // Test that the frequency label is set correctly
                Label frequencyLabel = (Label) recurringExpenseCard.getRoot().lookup("#frequency");
                assertEquals("Monthly", frequencyLabel.getText());
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
    public void testEquals_validRecurringExpenseCard_success() {
        RecurringExpenseCard recurringExpenseCard1 = new RecurringExpenseCard(
                recurringExpenseManager, displayedIndex);
        RecurringExpenseCard recurringExpenseCard2 = new RecurringExpenseCard(
                recurringExpenseManager, displayedIndex + 1);
        RecurringExpenseCard recurringExpenseCard3 = new RecurringExpenseCard(
                recurringExpenseManager, displayedIndex + 1);
        CompletableFuture<Void> future = new CompletableFuture<>();

        Platform.runLater(() -> {
            try {
                assertEquals(recurringExpenseCard1, recurringExpenseCard1);
                assertNotEquals(recurringExpenseCard1, recurringExpenseCard2);
                assertEquals(recurringExpenseCard2, recurringExpenseCard3);
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
