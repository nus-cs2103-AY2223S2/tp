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
        String filePath = this.path + "/" + fileName;
        Path path1 = Paths.get(filePath);
        String extension = getFileExtension(fileName);
        if (isImage(extension)) {
            displayImage(path1);
        } else if (extension.equalsIgnoreCase("pdf")) {
            displayPdf(path1);
        } else {
            //adding custom exception of Wrong File type exception
            System.out.println("Invalid file type");
        }
    }

    /**
     * Calculates and returns the next number to be used in the file naming format "number-mc".
     *
     * @param drc The directory path where the files are located.
     * @return The next number to be used in the file naming format "number-mc".
     */
    public int nextMcNumber(Path drc) {
        int highestNumber = 0;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(drc, "*-mc.pdf")) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) {
                    String fileName = entry.getFileName().toString();
                    String[] tokens = fileName.split("-mc\\.pdf");
                    if (tokens.length > 0) {
                        int number = Integer.parseInt(tokens[0]);
                        highestNumber = Math.max(highestNumber, number);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highestNumber + 1;
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
        create.createMcForm(Integer.toString(nextMcNumber(path2)));
    }

    /**
     * Read nth file.
     *
     * @param number the number
     */
    public void readNthFile(int number) {
        //Check if the files list is empty or the input number is invalid
        if (files.isEmpty() || number <= 0 || number > files.size()) {
            System.out.println("Invalid file number or no files exist.");
            return;
        }
        Path nthFilePath = files.get(number - 1);
        String fileName = nthFilePath.getFileName().toString();
        displayFile(fileName);
    }

    /**
     * Delete nth file.
     *
     * @param number the number
     */
    public void deleteNthFile(int number) {
        //Check if the files list is empty or the input number is invalid
        if (files.isEmpty() || number <= 0 || number > files.size()) {
            System.out.println("Invalid file number or no files exist.");
            return;
        }
        Path nthFilePath = files.get(number - 1);
        String fileName = nthFilePath.getFileName().toString();
        deleteFile(fileName);

        //Update the files and fileNames lists
        setAllFiles();
        setFileNames();
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

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase(Locale.ENGLISH);
    }

    private boolean isImage(String extension) {
        return "jpg".equalsIgnoreCase(extension)
                || "jpeg".equalsIgnoreCase(extension)
                || "png".equalsIgnoreCase(extension);
    }

    private void displayImage(Path path1) {
        ImageReader imageReader = new ImageReader(path1);
        imageReader.displayImage();
    }

    private void displayPdf(Path path1) {
        PdfReader pdfReader = new PdfReader(path1);
        pdfReader.displayPdf();
    }
}
