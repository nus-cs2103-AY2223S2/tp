package wingman.storage.json;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import wingman.commons.exceptions.DataConversionException;
import wingman.commons.exceptions.IllegalValueException;
import wingman.commons.util.FileHelper;
import wingman.commons.util.JsonHelper;
import wingman.model.ItemManager;
import wingman.storage.stubs.JsonItemManagerStub;
import wingman.storage.stubs.JsonItemStorageStub;

@ExtendWith(MockitoExtension.class)
public class JsonItemStorageTest {

    @Mock
    private JsonHelper jsonHelper;

    @Mock
    private FileHelper fileHelper;

    private Path filePath;

    @Mock
    private JsonItemManagerStub jsonManager;

    @Mock
    private Logger logger;

    private JsonItemStorageStub storage;

    @BeforeEach
    void setUp() throws DataConversionException, IOException, IllegalValueException {

        filePath = Path.of("test", "path");

        storage = new JsonItemStorageStub(
                filePath, jsonHelper, fileHelper, logger);

        Mockito.lenient()
                .when(jsonManager.toModelType())
                .thenReturn(new ItemManager<>());

        Mockito.lenient()
                .when(jsonHelper.readJsonFile(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.of(jsonManager));
    }

    @Test
    void read_noArgs_shouldCallWithDefaultPath()
            throws DataConversionException, IOException {
        storage.read();
        Mockito.verify(jsonHelper, Mockito.times(1))
                .readJsonFile(filePath, JsonItemManagerStub.class);
    }

    @Test
    void read_hasPath_shouldCallWithThePathGiven()
            throws DataConversionException, IOException {
        final Path newPath = Path.of("Hello", "World");
        storage.read(newPath);
        Mockito.verify(jsonHelper, Mockito.times(1))
                .readJsonFile(newPath, JsonItemManagerStub.class);
        Mockito.verify(jsonHelper, Mockito.never())
                .readJsonFile(filePath, JsonItemManagerStub.class);
    }

    @Test
    void read_fileEmpty_shouldReturnOptionalEmpty()
            throws DataConversionException, IOException {
        Mockito.when(jsonHelper.readJsonFile(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.empty());
        assert storage.read().isEmpty();
    }

    @Test
    void read_modelCannotConvert_throwDataConversionException()
            throws DataConversionException, IOException, IllegalValueException {
        Mockito.when(jsonManager.toModelType()).thenThrow(new IllegalValueException("Test"));
        Assertions.assertThrows(DataConversionException.class, () -> storage.read());
    }

    @Test
    void read_modelCannotConvert_shouldWriteWarningToLogger()
            throws DataConversionException, IOException, IllegalValueException {
        Mockito.when(jsonManager.toModelType()).thenThrow(new IllegalValueException("Test"));
        try {
            storage.read();
        } catch (DataConversionException e) {
            Mockito.verify(logger, Mockito.times(1))
                    .warning(Mockito.anyString());
        }
    }

    @Test
    void save_noPath_shouldCallWithDefaultPath() throws IOException {
        storage.save(new ItemManager<>());
        Mockito.verify(jsonHelper, Mockito.times(1))
                .saveJsonFile(Mockito.any(), Mockito.eq(filePath));
        Mockito.verify(fileHelper, Mockito.times(1))
                .createIfMissing(filePath);
    }

    @Test
    void save_withPath_shouldCallWithPathProvided() throws IOException {
        final Path newPath = Path.of("Hello", "World");
        storage.save(new ItemManager<>(), newPath);
        Mockito.verify(jsonHelper, Mockito.times(1))
                .saveJsonFile(Mockito.any(), Mockito.eq(newPath));
        Mockito.verify(fileHelper, Mockito.times(1))
                .createIfMissing(newPath);
        Mockito.verify(jsonHelper, Mockito.never())
                .saveJsonFile(Mockito.any(), Mockito.eq(filePath));
        Mockito.verify(fileHelper, Mockito.never())
                .createIfMissing(filePath);
    }

}
