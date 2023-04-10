package fasttrack.ui;

import static fasttrack.testutil.TypicalCategories.FOOD;
import static fasttrack.testutil.TypicalCategories.TECH;
import static fasttrack.testutil.TypicalExpenses.APPLE;
import static fasttrack.testutil.TypicalExpenses.CHERRY;
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

import fasttrack.model.category.Category;
import fasttrack.model.expense.Expense;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ComponentTest {

    // Ignore this class on Linux
    private Category category;
    private int displayedIndex;
    private int associatedExpenseCount;
    private CategoryListPanel categoryListPanel;
    private ObservableList<Category> categories;
    private ObservableList<Expense> expenses;

    @BeforeEach
    public void setUp() throws InterruptedException {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        category = FOOD;
        displayedIndex = 1;
        associatedExpenseCount = 3;
        categories = FXCollections.observableArrayList(FOOD, TECH);
        expenses = FXCollections.observableArrayList(APPLE, CHERRY);
    }

    @BeforeAll
    static void initJfx() throws InterruptedException {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    @Test
    public void testCategoryCard_validData_success() {
        CategoryCard categoryCard = new CategoryCard(category, displayedIndex, associatedExpenseCount);
        CompletableFuture<Void> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                // Test that the category name label is set correctly
                Label categoryNameLabel = (Label) categoryCard.getRoot().lookup("#categoryName");
                assertEquals("Food", categoryNameLabel.getText());

                // Test that the index label is set correctly
                Label indexLabel = (Label) categoryCard.getRoot().lookup("#id");
                assertEquals("1. ", indexLabel.getText());

                // Test that the expense count label is set correctly
                Label expenseCountLabel = (Label) categoryCard.getRoot().lookup("#expenseCount");
                assertEquals("3", expenseCountLabel.getText());
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
    public void testEquals_validCategoryCard_success() {
        CategoryCard categoryCard1 = new CategoryCard(category, displayedIndex, associatedExpenseCount);
        CategoryCard categoryCard2 = new CategoryCard(category, displayedIndex + 1, associatedExpenseCount - 1);
        CategoryCard categoryCard3 = new CategoryCard(category, displayedIndex + 1, associatedExpenseCount - 1);
        CompletableFuture<Void> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                assertEquals(categoryCard1, categoryCard1);
                assertNotEquals(categoryCard1, categoryCard2);
                assertEquals(categoryCard2, categoryCard3);
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
