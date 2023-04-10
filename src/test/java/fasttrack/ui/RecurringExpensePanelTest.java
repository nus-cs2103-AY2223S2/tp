package fasttrack.ui;

import static fasttrack.testutil.TypicalRecurringExpenses.GYM_MEMBERSHIP;
import static fasttrack.testutil.TypicalRecurringExpenses.NETFLIX_SUBSCRIPTION;
import static fasttrack.testutil.TypicalRecurringExpenses.RENT;
import static fasttrack.ui.JavaFxTestHelper.initJavaFxHelper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import fasttrack.model.expense.RecurringExpenseManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;


class RecurringExpensePanelTest {

    private RecurringExpensePanel recurringExpensePanel;
    private ObservableList<RecurringExpenseManager> recurringExpenses;

    @BeforeEach
    public void setUp() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        recurringExpenses = FXCollections.observableArrayList(GYM_MEMBERSHIP, NETFLIX_SUBSCRIPTION, RENT);
    }

    @BeforeAll
    static void initJfx() throws InterruptedException {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        initJavaFxHelper();
    }

    @Test
    public void recurringExpenseListView_validExpenses_countEqual() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        recurringExpensePanel = new RecurringExpensePanel(recurringExpenses);
        CompletableFuture<Void> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                // Test that the number of recurring expenses is correct
                ListView<?> recurringExpenseListView = (ListView<?>) recurringExpensePanel
                        .getRoot().lookup("#recurringExpenseListView");
                assertEquals(recurringExpenses.size(), recurringExpenseListView.getItems().size());
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
    public void recurringExpenseListView_validExpenses_countZero() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        recurringExpenses = FXCollections.observableArrayList();
        RecurringExpensePanel recurringExpensePanel = new RecurringExpensePanel(recurringExpenses);
        CompletableFuture<Void> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                ListView<?> recurringExpenseListView = (ListView<?>) recurringExpensePanel
                        .getRoot().lookup("#recurringExpenseListView");
                assertEquals(0, recurringExpenseListView.getItems().size());
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
