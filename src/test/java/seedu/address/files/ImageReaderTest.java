package seedu.address.files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ImageReaderTest {
    private static final String SAMPLE_IMAGE_PATH = "resources/images/address_book_32.png";

    private ImageReader imageReader;

    @BeforeEach
    void setUp() {
        Path path = Paths.get(SAMPLE_IMAGE_PATH);
        imageReader = new ImageReader(path);
    }

    @Test
    void getPath() {
        assertEquals(Paths.get(SAMPLE_IMAGE_PATH), imageReader.getPath());
    }

    @Test
    void getFileName_validPath() {
        String fileName = imageReader.getFileName(Paths.get(SAMPLE_IMAGE_PATH));
        assertEquals("address_book_32.png", fileName);
    }

    @Test
    void getFileName_nullPath() {
        assertThrows(NullPointerException.class, () -> imageReader.getFileName(null));
    }


    @Test
    void loadFile_invalidPath(@TempDir Path tempDir) {
        Path nonExistentPath = tempDir.resolve("nonexistent.jpg");
        BufferedImage errorImage = imageReader.loadFile(nonExistentPath);

        assertNotNull(errorImage);
        assertEquals(300, errorImage.getWidth());
        assertEquals(200, errorImage.getHeight());
    }

    @Test
    void loadFile_nullPath() {
        assertThrows(NullPointerException.class, () -> imageReader.loadFile(null));
    }

}
