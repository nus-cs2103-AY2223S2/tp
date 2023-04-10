package fasttrack.ui;

import static fasttrack.testutil.TypicalExpenses.APPLE;
import static fasttrack.testutil.TypicalExpenses.BANANA;
import static fasttrack.testutil.TypicalExpenses.CHERRY;
import static fasttrack.ui.JavaFxTestHelper.initJavaFxHelper;
import static fasttrack.ui.JavaFxTestHelper.setUpHeadlessMode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import fasttrack.model.expense.Expense;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;


class ExpenseListPanelTest {

    private ExpenseListPanel expensePanel;
    private ObservableList<Expense> expenses;

    @BeforeEach
    public void setUp() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        expenses = FXCollections.observableArrayList(APPLE, BANANA, CHERRY);
    }

    @BeforeAll
    static void initJfx() throws InterruptedException {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        setUpHeadlessMode();
        initJavaFxHelper();
    }


    @Test
    public void expenseListView_validExpenses_countEqual() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        expensePanel = new ExpenseListPanel(expenses);
        CompletableFuture<Void> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                // Test that the number of recurring expenses is correct
                ListView<?> expenseListView = (ListView<?>) expensePanel.getRoot().lookup("#expenseListView");
                assertEquals(expenses.size(), expenseListView.getItems().size());
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
    public void expenseListView_validExpenses_countZero() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        expenses = FXCollections.observableArrayList();
        expensePanel = new ExpenseListPanel(expenses);
        CompletableFuture<Void> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                ListView<?> expenseListView = (ListView<?>) expensePanel.getRoot().lookup("#expenseListView");
                assertEquals(0, expenseListView.getItems().size());
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
