package seedu.address.storage.json;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileHelper;
import seedu.address.commons.util.JsonHelper;
import seedu.address.model.IdentifiableManager;
import seedu.address.model.ReadOnlyIdentifiableManager;
import seedu.address.model.item.Identifiable;

@ExtendWith(MockitoExtension.class)
public class JsonIdentifiableStorageTest {

    @Mock
    private JsonHelper jsonHelper;

    @Mock
    private FileHelper fileHelper;

    private Path filePath;

    @Mock
    private JsonIdentifiableManagerStub jsonManager;

    @Mock
    private Logger logger;

    private JsonIdentifiableStorageStub storage;

    @BeforeEach
    void setUp() throws DataConversionException, IOException, IllegalValueException {

        filePath = Path.of("test", "path");

        storage = new JsonIdentifiableStorageStub(filePath);

        Mockito.when(jsonManager.toModelType()).thenReturn(new IdentifiableManager<>());

        Mockito.when(jsonHelper.readJsonFile(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.of(jsonManager));
    }

    @Test
    void read_noArgs_shouldCallWithDefaultPath()
            throws DataConversionException, IOException {
        storage.read();
        Mockito.verify(jsonHelper, Mockito.times(1))
                .readJsonFile(filePath, JsonIdentifiableManagerStub.class);
    }

    @Test
    void read_hasPath_shouldCallWithThePathGiven()
            throws DataConversionException, IOException {
        final Path newPath = Path.of("Hello", "World");
        storage.read(newPath);
        Mockito.verify(jsonHelper, Mockito.times(1))
                .readJsonFile(newPath, JsonIdentifiableManagerStub.class);
        Mockito.verify(jsonHelper, Mockito.never())
                .readJsonFile(filePath, JsonIdentifiableManagerStub.class);
    }

    private static class IdentifiableStub implements Identifiable {
        private final String id;

        IdentifiableStub(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }
    }

    private static class JsonAdaptedIdentifiableStub implements JsonAdaptedModel<IdentifiableStub> {
        private final String id;

        JsonAdaptedIdentifiableStub(String id) {
            this.id = id;
        }

        @Override
        public IdentifiableStub toModelType() throws IllegalValueException {
            return new IdentifiableStub(this.id);
        }
    }

    private static class JsonIdentifiableManagerStub extends
            JsonIdentifiableManager<IdentifiableStub, JsonAdaptedIdentifiableStub> {

        @Override
        protected JsonAdaptedIdentifiableStub getJsonAdaptedModel(IdentifiableStub item) {
            return new JsonAdaptedIdentifiableStub(item.getId());
        }
    }

    private class JsonIdentifiableStorageStub extends
            JsonIdentifiableStorage<IdentifiableStub, JsonAdaptedIdentifiableStub, JsonIdentifiableManagerStub> {

        public JsonIdentifiableStorageStub(Path filePath) {
            super(filePath, jsonHelper, fileHelper, logger);
        }

        @Override
        protected Class<JsonIdentifiableManagerStub> getManagerClass() {
            return JsonIdentifiableManagerStub.class;
        }

        @Override
        protected JsonIdentifiableManagerStub createManager(ReadOnlyIdentifiableManager<IdentifiableStub> modelManager) {
            final JsonIdentifiableManagerStub res =
                    new JsonIdentifiableManagerStub();
            res.readFromManager(modelManager);
            return res;
        }
    }
}
