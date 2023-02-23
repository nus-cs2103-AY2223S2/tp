package seedu.address.storage.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.IdentifiableManager;
import seedu.address.model.ReadOnlyIdentifiableManager;
import seedu.address.model.item.Identifiable;

@ExtendWith(MockitoExtension.class)
public class JsonIdentifiableManagerTest {

    IdentifiableStub model;

    @Mock
    JsonAdaptedModelStub jsonModel;

    @Mock
    ReadOnlyIdentifiableManagerStub manager;

    JsonIdentifiableManagerStub jsonManager;

    @BeforeEach
    void setUp() {
        model = new IdentifiableStub("test_id");
        jsonManager = new JsonIdentifiableManagerStub();
    }

    @Test
    void readFromManager_validManager_success() throws IllegalValueException {
        // Arrange
        ObservableList<IdentifiableStub> items =
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
        final IdentifiableManager<IdentifiableStub> res = jsonManager.toModelType();
        // Assert
        Assertions.assertNotNull(res);
        Assertions.assertEquals(res, new IdentifiableManager<IdentifiableStub>());
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
    void toModelType_whenDuplicate_shouldThrowIllegalValueException() throws  IllegalValueException {
        // Arrange
        jsonManager.items.add(new JsonAdaptedModelStub("test"));
        jsonManager.items.add(new JsonAdaptedModelStub("test"));
        // Assert
        Assertions.assertThrows(
                IllegalValueException.class, () -> jsonManager.toModelType());
    }


    private static class IdentifiableStub implements Identifiable {
        private final String id;

        public IdentifiableStub(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }
    }

    private static abstract class ReadOnlyIdentifiableManagerStub
            implements ReadOnlyIdentifiableManager<IdentifiableStub> {

    }

    private static class JsonAdaptedModelStub implements
            JsonAdaptedModel<IdentifiableStub> {
        private final String id;

        public JsonAdaptedModelStub(String id) {
            this.id = id;
        }

        @Override
        public IdentifiableStub toModelType() throws IllegalValueException {
            return new IdentifiableStub(id);
        }
    }

    private static class JsonIdentifiableManagerStub extends
            JsonIdentifiableManager<IdentifiableStub, JsonAdaptedModelStub> {
        public JsonIdentifiableManagerStub() {
            super();
        }

        @Override
        protected JsonAdaptedModelStub getJsonAdaptedModel(IdentifiableStub item) {
            return new JsonAdaptedModelStub(item.getId());
        }
    }
}
