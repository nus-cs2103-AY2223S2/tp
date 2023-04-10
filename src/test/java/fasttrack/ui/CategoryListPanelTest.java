package fasttrack.ui;

import static fasttrack.testutil.TypicalCategories.FOOD;
import static fasttrack.testutil.TypicalCategories.TECH;
import static fasttrack.testutil.TypicalExpenses.APPLE;
import static fasttrack.testutil.TypicalExpenses.CHERRY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import fasttrack.model.category.Category;
import fasttrack.model.expense.Expense;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ListView;


public class CategoryListPanelTest {

    private CategoryListPanel categoryListPanel;
    private ObservableList<Category> categories;
    private ObservableList<Expense> expenses;

    @BeforeEach
    public void setUp() {
        categories = FXCollections.observableArrayList(FOOD, TECH);
        expenses = FXCollections.observableArrayList(APPLE, CHERRY);
        // Initialise fake JavaFX environment
        new JFXPanel();
    }

    @Test
    public void categoryListView_validCategories_countEqual() {
        CompletableFuture<Void> future = new CompletableFuture<>();
        categoryListPanel = new CategoryListPanel(categories, expenses);
        Platform.runLater(() -> {
            try {
                // Test that the number of categories is correct
                ListView<?> categoryListView = (ListView<?>) categoryListPanel.getRoot().lookup("#categoryListView");
                assertEquals(categories.size(), categoryListView.getItems().size());
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
    public void categoryListView_emptyList_countZero() {
        categories = FXCollections.observableArrayList();
        expenses = FXCollections.observableArrayList();
        CompletableFuture<Void> future = new CompletableFuture<>();
        categoryListPanel = new CategoryListPanel(categories, expenses);
        Platform.runLater(() -> {
            try {
                ListView<?> categoryListView = (ListView<?>) categoryListPanel.getRoot().lookup("#categoryListView");
                assertEquals(0, categoryListView.getItems().size());
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
