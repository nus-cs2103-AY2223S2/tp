package seedu.address.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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

    public void generateMc() {
        create.createMcForm();
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

}
