package seedu.address.files;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;




class FilesManagerTest {

    private String path;
    private FilesManager filesManager;
    private Person testPerson;

    @BeforeEach
    void setUp() {
        testPerson = new Person(new Name("sf"), new Phone("12345678"),
                new Email("sf@123.com"), new Address("com 1 St"), new Age("20"), new HashSet<>());
        filesManager = new FilesManager(testPerson);
        path = "reports/" + testPerson.getName().fullName;
    }

    @AfterEach
    void tearDown() {
        filesManager.deleteAll();
    }
    @Test
    void testInitFile() {
        filesManager.initFile();
        File directory = new File(path);
        assertTrue(directory.exists() && directory.isDirectory(), "Directory structure not created correctly");
    }

    @Test
    void testDeleteAllDeletesDirectory() throws IOException {
        String doctorName = "Dr. Test";
        String description = "Test description";
        int days = 5;
        filesManager.generateMc(doctorName, description, days);
        filesManager.deleteAll();
        Path directoryPath = Paths.get(path);
        assertTrue(!Files.exists(directoryPath), "Directory not deleted");
    }
    @Test
    void testGenerateMc() {
        String doctorName = "Dr. Test";
        String description = "Test description";
        int days = 5;
        filesManager.generateMc(doctorName, description, days);
        filesManager.generateMc(doctorName, description, days);

        // Check if two files were created
        File directory = new File(path);
        String[] files = directory.list();
        assertTrue(files.length == 2, "Files not generated correctly");
    }

    /*
    @Test
    void testReadNthFile() {
        String doctorName = "Dr. Test";
        String description = "Test description";
        int days = 5;
        filesManager.generateMc(doctorName, description, days);

        assertDoesNotThrow(() -> filesManager.readNthFile(1));
    }*/

    @Test
    void testDeleteNthFile() {
        // Generate a test file
        String doctorName = "Dr. Test";
        String description = "Test description";
        int days = 5;
        filesManager.generateMc(doctorName, description, days);

        // Get the number of files before deleting
        int initialFileCount = filesManager.getFileNames().size();

        // Delete the generated file
        filesManager.deleteNthFile(1);

        // Print the list of file names after deleting
        System.out.println("Files after deletion: " + filesManager.getFileNames());

        // Get the number of files after deleting
        int finalFileCount = filesManager.getFileNames().size();

        assertTrue(initialFileCount - 1 == finalFileCount, "File not deleted");
    }

}
