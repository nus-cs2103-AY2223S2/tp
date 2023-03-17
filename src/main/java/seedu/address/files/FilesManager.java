package seedu.address.files;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * The type Files manager.
 */
public class FilesManager {
    private Path reportsDir = Paths.get("reports");

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
