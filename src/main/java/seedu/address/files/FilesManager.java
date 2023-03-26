package seedu.address.files;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
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
    private List<Path> files;
    private List<String> fileNames;

    /**
     * Instantiates a new Files manager.
     *
     * @param person the person
     */
    public FilesManager(Person person) {
        this.person = person;
        store = new FileStorage(person.getName().fullName);
        path = "reports/" + person.getName().fullName;
        setAllFiles();
        setFileNames();
    }

    public void initFile() {
        FileStorage.createDrc(path);
    }

    public void deleteAll() {
        FileStorage.deleteDrc(path);
    }

    /**
     * Delete file.
     *
     * @param fileName the file name
     */
    public void deleteFile(String fileName) {
        String uri = path + "/" + fileName;
        FileStorage.deleteFile(uri);
    }

    public void addFile() {
        store.uploadFile();
    }

    /**
     * Display file.
     *
     * @param fileName the file name
     */
    public void displayFile(String fileName) {
        //String fileName = path.getFileName().toString();
        String filePath = this.path + "/" + fileName;
        Path path1 = Paths.get(filePath);
        String extension = fileName.substring(
                fileName.lastIndexOf('.') + 1).toLowerCase(Locale.ENGLISH);
        if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
            ImageReader imageReader = new ImageReader(path1);
            imageReader.displayImage();
        } else if (extension.equals("pdf")) {
            PdfReader pdfReader = new PdfReader(path1);
            pdfReader.displayPdf();
        } else {
            //adding custom exception of Wrong File type exception
            System.out.println("Invlid file type");
        }
    }

    public int numberOfFiles(Path drc) {
        return files.size();
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    /**
     * Generate mc.
     */
    public void generateMc(String doctorName, String description, int days) {
        Path path2 = Paths.get(path);
        FileStorage.createDrc(path);
        create = new FileGenerator(person,
                doctorName, description, days);
        create.createMcForm(Integer.toString(numberOfFiles(path2)));
    }

    private void setAllFiles() {
        Path directory = Paths.get(path);
        files = new ArrayList<>();
        if (!isEmptyDirectory() && Files.exists(directory) && Files.isDirectory(directory)) {
            try (Stream<Path> stream = Files.walk(directory)) {
                stream.filter(Files::isRegularFile)
                        .forEach(files::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setFileNames() {
        if (files != null) {
            fileNames = files.stream()
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } else {
            fileNames = Collections.emptyList();
        }
    }

    private boolean isEmptyDirectory() {
        Path directory = Paths.get(path);
        if (Files.isDirectory(directory)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory)) {
                return !directoryStream.iterator().hasNext();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public Person getPerson() {
        return this.person;

    }
}
