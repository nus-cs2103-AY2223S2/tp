package seedu.address.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import seedu.address.model.person.Person;



/**
 * The type Files manager.
 */
public class FilesManager {

    private Person person;
    private Path reportsDir = Paths.get("reports");
    private FileStorage store;
    private FileGenerator create;
    private String path;

    /**
     * Instantiates a new Files manager.
     *
     * @param person the person
     */
    public FilesManager(Person person) {
        this.person = person;
        store = new FileStorage(person.getName().fullName);
        create = new FileGenerator(person,
                "Handsome", "description", 20);
        path = "reports/" + person.getName().fullName;
    }

    public void initFile() {
        FileStorage.createDrc(path);
    }

    public void deleteAll() {
        FileStorage.deleteDrc(path);
    }

    public void addFile() {
        store.uploadFile();
    }

    /**
     * Generate mc.
     */
    public void generateMc() {
        Path path2 = Paths.get(path);
        FileStorage.createDrc(path);
        create.createMcForm(Integer.toString(numberOfFiles(path2)));
    }



    public List<Path> getAllDirectories() {
        List<Path> directories = new ArrayList<>();
        try (Stream<Path> stream = Files.walk(reportsDir)) {
            stream.filter(Files::isDirectory)
                    .forEach(directories::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return directories;
    }

    public List<Path> getAllFiles(Path directory) {
        List<Path> files = new ArrayList<>();
        try (Stream<Path> stream = Files.walk(directory)) {
            stream.filter(Files::isRegularFile)
                    .forEach(files::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }
    public int numberOfFiles(Path drc) {
        return getAllFiles(drc).size();
    }
    /**
     * @param fileName
     * @return
     */
    public boolean fileExists(String fileName) {
        List<Path> directories = getAllDirectories();
        for (Path directory : directories) {
            List<Path> files = getAllFiles(directory);
            for (Path file : files) {
                if (file.getFileName().toString().equals(fileName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Display file.
     *
     * @param path the path
     */
    public void displayFile(Path path) {
        String fileName = path.getFileName().toString();
        String extension = fileName.substring(
                fileName.lastIndexOf('.') + 1).toLowerCase(Locale.ENGLISH);
        if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
            ImageReader imageReader = new ImageReader(path);
            imageReader.displayImage();
        } else if (extension.equals("pdf")) {
            PdfReader pdfReader = new PdfReader(path);
            pdfReader.displayPdf();
        } else {
            //adding custom exception of Wrong File type exception
            System.out.println("Invlid file type");
        }
    }

}
