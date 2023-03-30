package wingman.storage.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wingman.commons.exceptions.IllegalValueException;
import wingman.model.ItemManager;
import wingman.model.ReadOnlyItemManager;
import wingman.storage.stubs.ItemStub;
import wingman.storage.stubs.JsonAdaptedIdentifiableStub;
import wingman.storage.stubs.JsonItemManagerStub;

@ExtendWith(MockitoExtension.class)
public class JsonItemManagerTest {

    private ItemStub model;

    @Mock
    private JsonAdaptedIdentifiableStub jsonModel;

    @Mock
    private ReadOnlyItemManager<ItemStub> manager;

    private JsonItemManagerStub jsonManager;

    @BeforeEach
    void setUp() {
        model = new ItemStub("test_id");
        jsonManager = new JsonItemManagerStub();
    }

    @Test
    void readFromManager_validManager_success() throws IllegalValueException {
        // Arrange
        ObservableList<ItemStub> items =
                FXCollections.observableArrayList(model);
        Mockito.when(manager.getItemList()).thenReturn(items);

        // Act
        jsonManager.readFromManager(manager);

        // Assert
        Assertions.assertEquals(1, jsonManager.items.size());
    }

    @Test
    void toModelType_emptyItems_equalsEmptyManager() throws IllegalValueException {
        // Act
        final ItemManager<ItemStub> res = jsonManager.toModelType();
        // Assert
        Assertions.assertNotNull(res);
        Assertions.assertEquals(res, new ItemManager<ItemStub>());
    }

    @Test
    void toModelType_modelThrows_shouldThrowIllegalValueException() throws IllegalValueException {
        // Arrange
        final IllegalValueException exception = new IllegalValueException(
                "Test Message");
        Mockito.when(jsonModel.toModelType()).thenThrow(exception);
        jsonManager.items.add(jsonModel);
        // Assert
        Assertions.assertThrows(
                IllegalValueException.class, () -> jsonManager.toModelType());
    }

    @Test
    void toModelType_whenDuplicate_shouldThrowIllegalValueException() throws IllegalValueException {
        // Arrange
        jsonManager.items.add(new JsonAdaptedIdentifiableStub("test"));
        jsonManager.items.add(new JsonAdaptedIdentifiableStub("test"));
        // Assert
        Assertions.assertThrows(
                IllegalValueException.class, () -> jsonManager.toModelType());
    }

}
