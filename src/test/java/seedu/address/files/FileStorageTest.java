package seedu.address.files;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class FileStorageTest {

    private String path;

    @BeforeEach
    void setUp() {
        path = "reports/testuser";
        FileStorage.createDrc(path);
    }

    @AfterEach
    void tearDown() throws IOException {
        FileStorage.deleteDrc("testuser");
    }

    @Test
    void testCreateDrc() {
        File dir = new File(path);
        assertTrue(dir.exists(), "Directory does not exist");
        assertTrue(dir.isDirectory(), "Path is not a directory");
    }

    @Test
    void testDeleteDrc() throws IOException {
        //create a test file inside the directory
        File testFile = new File(path + "/testfile.txt");
        testFile.createNewFile();

        //check if the directory and file exists before deleting
        assertTrue(testFile.exists(), "File does not exist");
        assertTrue(testFile.isFile(), "Path is not a file");
        File directory = new File(path);
        assertTrue(directory.exists(), "Directory does not exist");
        assertTrue(directory.isDirectory(), "Path is not a directory");

        //assert that the directory and file does not exist after deleting
        FileStorage.deleteDrc("testuser");
        assertFalse(directory.exists(), "Directory still exists");
        assertFalse(testFile.exists(), "File still exists");
    }

    /*
    @Test
    void testUploadFile() throws IOException {
        File testFile = new File(path + "/testfile.txt");
        testFile.createNewFile();
        assertTrue(testFile.exists(), "File does not exist");

        //call the uploadFile method
        FileStorage file = new FileStorage(path);
        file.uploadFile();

        //assert that the uploaded file exists
        File uploadedFile = new File("reports/testuser/testfile.txt");
        assertTrue(uploadedFile.exists(), "Uploaded file does not exist");

    }
    */

    @Test
    void testCheckDir() {
        String testDir = "test-dir";
        File testFile = new File(testDir);
        assertFalse(testFile.exists());
        FileStorage.checkDir(testFile);
        assertTrue(testFile.exists());
        assertTrue(testFile.isDirectory());
        testFile.delete();
    }

    @Test
    void testIsImageFile() {
        File jpgFile = new File("test.jpg");
        assertTrue(FileStorage.isImageFile(jpgFile), "JPG file should be detected as an image file");

        File pngFile = new File("test.png");
        assertTrue(FileStorage.isImageFile(pngFile), "PNG file should be detected as an image file");

        File bmpFile = new File("test.bmp");
        assertTrue(FileStorage.isImageFile(bmpFile), "BMP file should be detected as an image file");

        File gifFile = new File("test.gif");
        assertTrue(FileStorage.isImageFile(gifFile), "GIF file should be detected as an image file");

        File txtFile = new File("test.txt");
        assertFalse(FileStorage.isImageFile(txtFile), "TXT file should not be detected as an image file");
    }
}