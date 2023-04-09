package trackr.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import trackr.commons.core.GuiSettings;
import trackr.commons.core.index.Index;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ReadOnlyMenu;
import trackr.model.ReadOnlyOrderList;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.ReadOnlyUserPrefs;
import trackr.model.item.Item;
import trackr.model.item.ReadOnlyItemList;
import trackr.model.menu.ItemProfit;
import trackr.model.menu.ItemSellingPrice;
import trackr.model.menu.MenuItem;
import trackr.model.order.Order;
import trackr.model.person.Supplier;
import trackr.model.task.Task;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Appends {@code fileName} to the sandbox folder path and returns the resulting path.
     * Creates the sandbox folder if it doesn't exist.
     */
    public static Path getFilePathInSandboxFolder(String fileName) {
        try {
            Files.createDirectories(SANDBOX_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER.resolve(fileName);
    }

    /**
     * Returns the middle index of the person in the {@code model}'s person list.
     */
    public static Index getMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredSupplierList().size() / 2);
    }

    /**
     * Returns the last index of the person in the {@code model}'s person list.
     */
    public static Index getLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredSupplierList().size());
    }

    /**
     * Returns the person in the {@code model}'s person list at {@code index}.
     */
    public static Supplier getPerson(Model model, Index index) {
        return model.getFilteredSupplierList().get(index.getZeroBased());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    public static class ModelStub implements Model {

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTrackrFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTrackrFilePath(Path trackrFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public <T extends Item> void setItemList(ModelEnum modelEnum) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyItemList<? extends Item> getItemList(ModelEnum modelEnum) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public <T extends Item> boolean hasItem(T item, ModelEnum modelEnum) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public <T extends Item> void deleteItem(T item, ModelEnum modelEnum) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public <T extends Item> void addItem(T item, ModelEnum modelEnum) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public <T extends Item> void setItem(T item, T itemEdited, ModelEnum modelEnum) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<? extends Item> getFilteredItemList(ModelEnum modelEnum) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredItemList(Predicate<Item> predicate, ModelEnum modelEnum) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlySupplierList getSupplierList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Supplier> getFilteredSupplierList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskList getTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredTaskList(Comparator<Task> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMenu getMenu() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<MenuItem> getFilteredMenu() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyOrderList getOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getFilteredOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredOrderList(Comparator<Order> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ItemProfit getTotalProfits() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ItemSellingPrice getTotalSales() {
            throw new AssertionError("This method should not be called.");
        }
    }
}
